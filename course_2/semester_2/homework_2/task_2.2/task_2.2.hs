power_two :: Int -> [Int]

power_two 1 = [2]
power_two n = 1 : map (*2) (power_two (n - 1))