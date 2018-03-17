sum_of_digits :: Int -> Int

sum_of_digits x = sum_of_digits' (abs x)

sum_of_digits' :: Int -> Int
sum_of_digits' x | x <= 9	= x
				 | x > 9	= mod x 10 + sum_of_digits'(div x 10)