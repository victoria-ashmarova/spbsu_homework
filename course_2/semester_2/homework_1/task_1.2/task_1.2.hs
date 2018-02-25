fibonaccyNumber :: Int -> Int
fibonaccyNumber 0 = 0
fibonaccyNumber 1 = 1
fibonaccyNumber n = fibonaccyNumber(n - 1) + fibonaccyNumber(n - 2)