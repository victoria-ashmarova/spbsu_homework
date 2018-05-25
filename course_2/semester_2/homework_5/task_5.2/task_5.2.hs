multiPairs :: Int -> [Int]
multiPairs n = [1..n] >>= ([1..n] >>= ) . (return .) . (*)