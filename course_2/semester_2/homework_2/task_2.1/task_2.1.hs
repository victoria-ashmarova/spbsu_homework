reverse' :: [a] -> [a]
reverse' xs = reverse'' xs []

reverse'' :: [a] -> [a] -> [a]
reverse'' [] xs = xs
reverse'' (y:ys) xs = reverse'' ys (y:xs)