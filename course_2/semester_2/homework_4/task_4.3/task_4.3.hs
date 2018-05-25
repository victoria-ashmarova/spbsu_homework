data BinTree a = Leaf a | Node (BinTree a) a (BinTree a) 

instance Foldable BinTree where
	--foldr :: (a -> b -> b) -> b -> BinTree a -> b
	foldr f v (Leaf val) = f val v
	foldr f v (Node l val r) = foldr f (f val (foldr f v l)) r
	
	--foldl :: (b -> a -> b) -> b -> BinTree a -> b
	foldl f v (Leaf val) = f v val
	foldl f v (Node l val r) = foldl f (f (foldl f v r) val) l
		
testTree = Node (Node (Leaf 1) 2 (Leaf 3)) 4 (Leaf 5)

wrapp xs x = x:xs

inorder :: BinTree a -> [a]
inorder tree = foldl wrapp [] tree
	
postorder :: BinTree a -> [a]
postorder tree = foldr (:) [] tree

main = do
	putStr (" inorder of test tree>" ++ (show $ inorder $ testTree))
	putStr ("\n postorder of test tree>" ++(show $ postorder $ testTree) ++ "\n")
	



