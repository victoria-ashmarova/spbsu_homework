import Control.Monad

firstHill :: (Ord a) => [a] -> Maybe a 
firstHill (x1:x2:x3:x) = return (map (\(a, b, c) -> b) $
									filter (\(a, b, c) -> b > a && b > c) $
										zip3 (x1:x2:x3:x) (x2:x3:x) (x3:x)) >>=
							(\l -> if null l then Nothing else Just (head l))
firstHill _ = Nothing