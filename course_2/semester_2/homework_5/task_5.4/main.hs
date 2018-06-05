import ExpressionTree
import TreeDerivator
import Control.Monad

derive line = simplify $ derivative $ makeTree $ toPostfix $ toTokens $ line

main = do
	putStr "Enter an expression>"
	ex <- getLine
	putStr "Derivative: "
	print $ derive ex