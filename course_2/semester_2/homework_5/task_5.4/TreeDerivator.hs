module TreeDerivator where

import ExpressionTree

derivative :: ExprTree -> ExprTree
derivative (Val val) = Val 0
derivative (Var var) = Val 1
derivative (Node l Add r) = Node (derivative l) Add (derivative r)
derivative (Node l Diff r) = Node (derivative l) Diff (derivative r)
derivative (Node l Mult r) = Node (Node (derivative l) Mult r) Add (Node l Mult (derivative r))
derivative (Node l Div r) = Node (Node (derivative l) Div r) Diff (Node (Node l Mult (derivative r)) Div (Node r Mult r))
	
simplify :: ExprTree -> ExprTree
simplify (Val val) = simplify' (Val val)
simplify (Var var) = simplify' (Var var)	
simplify (Node l op r) = simplify' (Node (simplify l) op (simplify r)) 

simplify' :: ExprTree -> ExprTree
simplify' (Val val) = (Val val)
simplify' (Var var) = (Var var)
simplify' (Node (Val 0) Add r) = simplify r
simplify' (Node l Add (Val 0)) = simplify l
simplify' (Node l Diff (Val 0)) = simplify l
simplify' (Node (Val 1) Mult r) = simplify r
simplify' (Node l Mult (Val 1)) = simplify l
simplify' (Node (Val 0) Mult r) = Val 0
simplify' (Node l Mult (Val 0)) = Val 0
simplify' (Node (Val 0) Div r) = Val 0
simplify' (Node l Div (Val 1)) = simplify l
simplify' (Node l Div (Val 0)) = error "couldn't divide by 0"
simplify' (Node (Val l) op (Val r)) = seq' op (Val l) (Val r)  where
	seq' Add (Val l) (Val r) = Val (l + r)
	seq' Mult (Val l) (Val r) = Val (l*r)
	seq' Diff (Val l) (Val r) = Val (l - r)
	seq' Div (Val l) (Val r) = Val (div l r)
simplify' tree = tree