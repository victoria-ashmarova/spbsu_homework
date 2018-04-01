isCorrect :: [Char] -> Bool
isCorrect line = isCorrect' line [] True where 
	isOpenedBracket :: Char -> Bool
	isOpenedBracket s = if (s == '(') || (s == '[') || (s == '{')
						then True
						else False
	isClosedBracket :: Char -> Bool
	isClosedBracket s = if (s == ')') || (s == ']') || (s == '}')
						then True
						else False
	arePair :: Char -> Char -> Bool
	arePair ob cb = if (ob == '('  && cb == ')') || (ob == '['  && cb == ']') || (ob == '{'  && cb == '}')
					then True
					else False
	isCorrect' :: [Char] -> [Char] -> Bool -> Bool
	isCorrect' [] [] result = result
	isCorrect' [] bs result = False
	isCorrect' (l:ls) [] result = if isOpenedBracket l
								  then isCorrect' ls [l] result
								  else if isClosedBracket l
										then False
										else isCorrect' ls [] result
	isCorrect' (l:ls) (b:bs) result = if isOpenedBracket l
										then isCorrect' ls (l:b:bs) result
										else if isClosedBracket l
											 then if arePair b l
												  then isCorrect' ls bs result
												  else False
											 else isCorrect' ls (b:bs) result	