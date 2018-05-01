evenCounter1 :: [Int] -> Int
evenCounter1 = length.(filter even)

evenCounter2 :: [Int] -> Int
evenCounter2 = sum.map ((\x -> mod x 2).(+1))

evenCounter3 :: [Int] -> Int
evenCounter3 = foldr ((+).(\x -> mod x 2).(+1)) 0