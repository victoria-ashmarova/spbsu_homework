import System.Random
import Control.Monad

randomChange :: [a] -> IO [Int]
randomChange lst = forM lst change where
    change :: a -> IO Int
    change x = do
        value <- randomIO :: IO Int
        return value