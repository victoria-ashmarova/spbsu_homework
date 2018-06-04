pairsOfBrackets = [('(', ')'), ('[',']'), ('{','}')]

isOpeningBracket :: Char -> Bool
isOpeningBracket s = (filter (\c -> c == s) (map fst pairsOfBrackets)) /= []

isClosingBracket :: Char -> Bool
isClosingBracket s = (filter (\c -> c == s) (map snd pairsOfBrackets)) /= []

arePair :: Char -> Char -> Bool
arePair ob cb = (filter (\(a1,a2) -> a1 == ob && a2 == cb) pairsOfBrackets) /= []

isCorrect :: [Char] -> Bool
isCorrect line = isCorrect' line [] True where 					
	isCorrect' :: [Char] -> [Char] -> Bool -> Bool
	isCorrect' [] [] result = result
	isCorrect' [] bs result = False
	isCorrect' (l:ls) [] result | isOpeningBracket l = isCorrect' ls [l] result
								| isClosingBracket l = False
								| otherwise = isCorrect' ls [] result
	isCorrect' (l:ls) (b:bs) result | isOpeningBracket l = isCorrect' ls (l:b:bs) result
									| isClosingBracket l = if arePair b l
														then isCorrect' ls bs result
														else False
								    |otherwise = isCorrect' ls (b:bs) result	