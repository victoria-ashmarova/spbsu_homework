import Data.Char
import Control.Monad

data ExprTree = Val Int | Var String | Node ExprTree Oper ExprTree
instance Show ExprTree where
	show (Val val) = show val
	show (Var var) = show var
	show (Node l op r) = "(" ++ (show l) ++ (show op) ++ (show r) ++ ")"

data Oper = Add | Mult | Diff | Div
instance Show Oper where
	show Add = " + "
	show Mult = " * "
	show Diff = " - "
	show Div = " / "
	
derivative :: ExprTree -> ExprTree
derivative (Val val) = Val 0
derivative (Var var) = Val 1
derivative (Node l Add r) = Node (derivative l) Add (derivative r)
derivative (Node l Diff r) = Node (derivative l) Diff (derivative r)
derivative (Node l Mult r) = Node (Node (derivative l) Mult r) Add (Node l Mult (derivative r))
derivative (Node l Div r) = Node (Node (derivative l) Div r) Diff (Node (Node l Mult (derivative r)) Div (Node r Mult r))

simplify :: ExprTree -> ExprTree
simplify (Val val) = (Val val)
simplify (Var var) = (Var var)
simplify (Node (Val 0) Add r) = simplify r
simplify (Node l Add (Val 0)) = simplify l
simplify (Node l Diff (Val 0)) = simplify l
simplify (Node (Val 1) Mult r) = simplify r
simplify (Node l Mult (Val 1)) = simplify l
simplify (Node (Val 0) Mult r) = Val 0
simplify (Node l Mult (Val 0)) = Val 0
simplify (Node (Val 0) Div r) = Val 0
simplify (Node l Div (Val 1)) = l
simplify (Node l Div (Val 0)) = error "couldn't divide by 0"
simplify (Node (Val l) op (Val r)) = seq' op (Val l) (Val r)  where
	seq' Add (Val l) (Val r) = Val (l + r)
	seq' Mult (Val l) (Val r) = Val (l*r)
	seq' Diff (Val l) (Val r) = Val (l - r)
	seq' Div (Val l) (Val r) = Val (div l r)
simplify (Node l op r) = Node (simplify l) op (simplify r)

data Tokens = TVal Int | TVar String | TAdd | TDiff | TMult | TDiv | LParen | RParen
instance Eq Tokens where
	TVal v1 == TVal v2 = v1 == v2
	TVar v1 == TVar v2 = v1 == v2
	TAdd == TAdd = True
	TDiff == TDiff = True
	TMult == TMult = True
	TDiv == TDiv = True
	LParen == LParen = True
	RParen == RParen = True
	_ == _ = False
	
isValue :: Tokens -> Bool
isValue (TVal a) = True
isValue _ = False
	
isVar :: Tokens -> Bool
isVar (TVar a) = True
isVar _ = False
	
parseOper :: Tokens -> Oper
parseOper TAdd = Add
parseOper TMult = Mult
parseOper TDiff = Diff
parseOper TDiv = Div

parseLeaf :: Tokens -> ExprTree
parseLeaf (TVal v) = Val v
parseLeaf (TVar v) = Var v	
	
toTokens :: String -> [Tokens]
toTokens ss = reverse (toTokens' ss (0, False) []) where
	toTokens' :: String -> (Int, Bool) -> [Tokens] -> [Tokens]
	toTokens' [] (val, True) ts = (TVal val):ts
	toTokens' [] (val, False) ts = ts
	toTokens' (s:ss) (val, True) ts = if isDigit s 
									then toTokens' ss (val * 10 + digitToInt s, True) ts
									else toTokens' (s:ss) (0, False) ((TVal val):ts)
	toTokens' (s:ss) (val, False) ts | s == '+' = toTokens' ss (val, False) (TAdd:ts)
									| s == '-' = toTokens' ss (val, False) (TDiff:ts)
									| s == '*' = toTokens' ss (val, False) (TMult:ts)
									| s == '/' = toTokens' ss (val, False) (TDiv:ts)
									| s == '(' = toTokens' ss (val, False) (LParen:ts)
									| s == ')' = toTokens' ss (val, False) (RParen:ts)
									| s == 'x' = toTokens' ss (val, False) ((TVar (s:"")):ts)
									| (s >= '0' && s <= '9') = toTokens' ss (val * 10 + digitToInt s, True) ts
									| s == ' ' || s == '\t' || s == '\n' = toTokens' ss (val, False) ts
									| otherwise = error "Unknown element"
						
compositeTree :: ([Tokens], Maybe ExprTree) -> Tokens -> ([Tokens], Maybe ExprTree)
compositeTree ((v1:v2:vls), Nothing) op = (vls, Just(Node (parseLeaf v2) (parseOper op) (parseLeaf v1)))
compositeTree ((v1:vls), Just tree) op = (vls, Just(Node (parseLeaf v1) (parseOper op) tree))
						
makeTree :: [Tokens] -> Maybe ExprTree
makeTree ts = makeTree' ts [] ([],Nothing) where 
			makeTree' :: [Tokens] -> [Tokens] -> ([Tokens], Maybe ExprTree) -> Maybe ExprTree
			makeTree' [] [] ([], tree) = tree
			makeTree' [] [] ([t], Nothing) = Just (parseLeaf t)
			makeTree' [] (op:ops) (vls, tree) = makeTree' [] ops (compositeTree (vls, tree) op)
			makeTree' (t:ts) [] (vls, tree) | (isValue t || isVar t) = makeTree' ts [] ((t:vls), tree)
											| t == RParen = Nothing
											| otherwise = makeTree' ts [t] (vls, tree)
			makeTree' (t:ts) (op:ops) (vls, tree)   | (isValue t || isVar t) = makeTree' ts (op:ops) ((t:vls), tree)
													| (t == TAdd || t == TDiff) = if (op == LParen || op == TAdd || op == TDiff)
																				then makeTree' ts (t:op:ops) (vls, tree)
																				else makeTree' ts (t:ops) (compositeTree (vls, tree) op)
													| (t == TMult || t == TDiv || t == LParen) = makeTree' ts (t:op:ops) (vls, tree)
													| (t == RParen) = if op == LParen
																  then makeTree' ts ops (vls, tree)
																  else makeTree' (t:ts) ops (compositeTree (vls, tree) op)
			makeTree' _ _ (_,tree) = tree
			
derive line = liftM (show.simplify.derivative.makeTree.toTokens) (makeTree $ toTokens $ line)

main = do
	putStr "Enter an expression>"
	ex <- getLine
	putStr "Derivative: "
	liftM derive ex