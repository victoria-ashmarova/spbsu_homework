fib :: Int -> Int
fib x = fib' x 0 1 where
	fib' :: Int -> Int -> Int -> Int
	fib' x a1 a2 | x == 0 	= a1
				 | x > 0 = fib' (x - 1) (a1 + a2) a1 
				 | x < 0 = fib' (x + 1) (a2 - a1) a1