module ExpressionTree where

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

isOPer :: Tokens -> Bool
isOper TAdd = True
isOper TDiff = True
isOper TMult = True
isOper TDiv = True
isOPer _ = False
	
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
			
toPostfix :: [Tokens] -> [Tokens]
toPostfix ts = reverse (toPostfix' ts [] []) where
	toPostfix' :: [Tokens] -> [Tokens] -> [Tokens] -> [Tokens]
	toPostfix' [] [] res = res
	toPostfix' [] (op:ops) res = toPostfix' [] ops (op:res)
	toPostfix' (t:ts) [] res | (isValue t || isVar t) = toPostfix' ts [] (t:res)
							 | t == RParen = error "is not expression"
							 | otherwise = toPostfix' ts [t] res
	toPostfix' (t:ts) (op:ops) res  | (isValue t || isVar t) = toPostfix' ts (op:ops) (t:res)
									| t == LParen = toPostfix' ts (t:op:ops) res
									| t == RParen = if op == LParen
													then toPostfix' ts ops res
													else toPostfix' (t:ts) ops (op:res)
									| (t == TMult || t == TDiv) = if (op == TMult || op == TDiv)
																 then toPostfix' (t:ts) ops (op:res) 
																 else toPostfix' ts (t:op:ops) res
									| (t == TAdd || t == TDiff) = if (op == LParen)
																  then toPostfix' ts (t:op:ops) res
																  else toPostfix' (t:ts) ops (op:res)
	toPostfix' _ _ _ = error "is not expression"
	
makeTree :: [Tokens] -> ExprTree
makeTree tokS = head (makeTree' tokS []) where
	makeTree' :: [Tokens] -> [ExprTree] -> [ExprTree]
	makeTree' [] trees = trees
	makeTree' (tn:tns) trs | (isValue tn || isVar tn) = makeTree' tns ((parseLeaf tn):trs)
	makeTree' (tn1:tn2:tns) (tr1:[]) | (isOper tn1) = makeTree' (tn1:tns) ((parseLeaf tn2):tr1:[])
	makeTree' (tn:tns) (tr1:tr2:trs) | (isOper tn) = makeTree' tns ((Node tr2 (parseOper tn) tr1):trs)