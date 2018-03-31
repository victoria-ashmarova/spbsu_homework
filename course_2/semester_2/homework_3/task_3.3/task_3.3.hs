positionOfMax :: [Int] -> Maybe Int
positionOfMax [] = Nothing
positionOfMax (x:[]) = Nothing
positionOfMax (x1:x2:xs) = Just (maxSumAdj (0, 0) (1, x1) (zip [2 .. ] (x2:xs)) (1, x2))  where
	maxPair :: (Int, Int) -> (Int, Int) -> (Int, Int)
	maxPair a b = if snd b > snd a 
					then b
					else a
					
	sumAdj :: (Int, Int) -> (Int, Int) -> (Int, Int)
	sumAdj a b = (div (fst a + fst b) 2, snd a + snd b)
	
	incSnd :: (Int, Int) -> (Int, Int)
	incSnd (a, b) = (a + 1, b)

	maxSumAdj :: (Int, Int) -> (Int, Int) -> [(Int, Int)] -> (Int, Int) -> Int
	maxSumAdj b1 b2 [] mx = fst (maxPair mx (incSnd b1))
	maxSumAdj b1 b2 (x:xs) mx = maxSumAdj b2 x xs (maxPair mx (sumAdj x b1))