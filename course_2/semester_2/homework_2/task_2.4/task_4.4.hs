first_location :: Int -> [Int] -> Maybe Integer
first_location y xs = first_location' (locations y xs) where
	locations y xs = filter(\x -> snd x == y) (zip [1 .. ] xs)
	first_location' [] = Nothing
	first_location' (x:xs) = Just (fst x)