lists :: Int -> [[Int]]
lists 0 = [[]]
lists n = [helpFunc1 y | y <- [1 .. n]] where 
	helpFunc1 :: Int -> [Int]
	helpFunc1 k = [helpFunc2 x k | x <- [1 .. n]] where
		helpFunc2 z j | z <= j = j
					  | z > j = z
	