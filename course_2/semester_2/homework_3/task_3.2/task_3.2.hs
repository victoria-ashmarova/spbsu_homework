list179 = list179' where
	list0 = 0 : list179'
	listPairs = [(x, y)| x <- list0, y <- [1, 7, 9]]
	list179' = map (\z -> (fst z) * 10 + snd z) listPairs