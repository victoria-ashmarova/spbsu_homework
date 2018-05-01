data SortedList a = SortedList [a]
	deriving Show
	
createSortedList :: SortedList a
createSortedList = SortedList []

add :: Ord a => SortedList a -> a -> SortedList a
add (SortedList l) v = SortedList (add' l v) where
		add' [] v = [v]
		add' (x:xs) v = if v < x then (v:x:xs) 
							else x:(add' xs v)
							
remove :: Ord a => SortedList a -> a -> SortedList a
remove (SortedList l) v = SortedList (remove' l v) where
	remove' [] v = []
	remove' (x:xs) v = if x == v then xs
								else x:(remove' xs v)
		
main = handle createSortedList where
	handle :: SortedList Int -> IO() 		
	handle l = do
		putStr "press 1 to add value to sorted list\n  2 to remove value from sorted list\n  3 to show sorted list\n  0 to finish running\n>"
		action <- getLine
		case action of
			"0" -> return()
			"1" -> do 
				putStr "enter value to add\n>"
				value <- getLine
				let v = (read value :: Int)
				handle $ add l v 
			"2" -> do
				putStr "enter value to remove\n>"
				value <- getLine
				let v = (read value :: Int)
				handle $ remove l v
			"3" -> do
				putStr $ show l
				putStr "\n"
				handle l
			otherwise -> do
				putStr "unknown command\n"
				handle l				