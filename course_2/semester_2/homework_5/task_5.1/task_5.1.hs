split :: Int -> [String]
split n = split' n n where
	split' :: Int -> Int -> [String]
	split' 0 _ = [""]
	
	split' r m = do
		x <- [1..m]
		s <- map (++ (if x == r then "" else "+") ++ show x) $ (split' (r - x) (min (r - x) (min m x)))
		return s
	
	
	