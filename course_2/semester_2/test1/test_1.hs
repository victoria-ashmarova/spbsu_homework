list_1 :: [Int]
list_1 = [(-1)^n | n <- [1 .. ]]
list_n :: [Int]
list_n = map f (zip list_1 [1 .. ]) where
	f :: (Int, Int) -> Int
	f (a, b) = a * b * (-1)