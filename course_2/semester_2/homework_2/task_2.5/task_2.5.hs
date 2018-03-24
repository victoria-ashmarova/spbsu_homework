sumOfThreeLists :: [Int] -> [Int] -> [Int] -> [Int]
sumOfThreeLists [] [] [] = []
sumOfThreeLists a b c = ((emptyHead a + emptyHead b + emptyHead c): sumOfThreeLists (emptyTail a) (emptyTail b) (emptyTail c)) where
			emptyHead [] = 0
			emptyHead (x:xs) = x
			emptyTail [] = []
			emptyTail (x:xs) = xs