import Control.Monad

data BinTree a = Leaf a | Node (BinTree a) a (BinTree a)

instance Foldable BinTree where
	foldl f v (Leaf val) = f v val
	foldl f v (Node l val r) = foldl f (f (foldl f v r) val) l
	
wrapp xs x = x:xs

inorder :: BinTree a -> [a]
inorder tree = foldl wrapp [] tree	

testTree = Node (Node (Leaf 2) 3 (Leaf 6)) 18 (Leaf 108)

multOfLast :: (Num a, Eq a) => BinTree a -> Maybe a
multOfLast tree =  multOfLast' (inorder tree) where
	multOfLast' :: (Num a, Eq a) => [a] -> Maybe a
	multOfLast' (x1:x2:x3:xs) = return (map (\(a, b, c) -> c) $
									filter (\(a, b, c) -> c == b*a) $
									zip3 (x1:x2:x3:xs) (x2:x3:xs) (x3:xs)) >>=
									(\l -> if null l then Nothing else Just (head l))
	multOfLast' _ = Nothing
	
main = do
	putStr "test tree>"
	print (inorder testTree)
	putStr "first element which is multiplication of two last elements>"
	print (multOfLast testTree)