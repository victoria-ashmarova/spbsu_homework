superMap :: [a] -> [(a -> b)] -> [b]
superMap xs fs = map func (listPais xs fs) where
	listPais :: [a] -> [(a -> b)] -> [(a, (a -> b))]
	listPais xs fs = [(x, f) | x <- xs, f <- fs]
	func :: (a, (a -> b)) -> b
	func (x, f) = f x