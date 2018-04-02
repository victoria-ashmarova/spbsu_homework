conditionForEach :: [a] -> (a -> Bool) -> Bool
conditionForEach xs f = foldl (&&) True (map f xs)