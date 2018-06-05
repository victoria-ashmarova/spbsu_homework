import System.IO
import Control.Exception
import Control.Monad

data Entry = Entry {name :: String,
					number :: String} deriving (Show, Eq)
					
isCorrectNumber :: String -> Bool
isCorrectNumber [] = False
isCorrectNumber (d:ds) = isCorrectNumber' ds (d == '+' || (d >= '1' && d <= '9')) where
	isCorrectNumber' :: String -> Bool -> Bool
	isCorrectNumber' [] _ = False
	isCorrectNumber' (d:[]) val = (val && d >= '0' && d <= '9') 
	isCorrectNumber' (d:ds) val = isCorrectNumber' ds (val && d >= '0' && d <= '9')
					
addEntry :: [Entry] -> String -> String -> [Entry]
addEntry es newName newNumber = if (exists es name newName) || (exists es number newNumber) || not (isCorrectNumber newNumber)
								then es 
								else (Entry{name = newName, number = newNumber}):es 

exists :: [Entry] -> (Entry -> String) -> String -> Bool 
exists [] param value = False
exists (e:es) param value = if (param e) == value 
							then True
							else exists es param value
							
search :: [Entry] -> (Entry -> String) -> (Entry -> String) -> String -> Maybe String 
search [] key val inf = Nothing
search (e:es) key val inf = if (key e) == inf
							then Just (val $ e)
							else search es key val inf

appendEntries :: [String] -> [Entry] -> [Entry]
appendEntries [] es = es
appendEntries [e] es = es
appendEntries (w1:w2:ws) es = appendEntries ws (addEntry es w1 w2)
 							
main = dialog [] where
dialog :: [Entry] -> IO	()
dialog es = do
	putStr "Press 0 to exit\n\t 1 to add entry\n\t 2 to search number\n\t 3 to search name\n\t 4 to save entries to file\n\t 5 to read entries from file\n\t>"
	command <- getLine
	case command of
		"0" -> return()
		"1" -> do
			putStr "enter the name to add>"
			newName <- getLine
			putStr "enter the number to add>"
			newNumber <- getLine
			dialog $ addEntry es newName newNumber
		"2" -> 	do
			putStr "enter the name to search number>"
			sName <- getLine
			print (search es name number sName)
			dialog es
		"3" -> do
			putStr "enter the number to search name>"
			sNumber <- getLine
			print (search es number name sNumber)
			dialog es
		"4" -> do
			putStr "enter the name of file>"
			fp <- getLine
			forM_ es (\e -> appendFile fp ((name e)++" "++(number e)++"\n"))
			dialog es
		"5" -> do
			catch (do
				putStr "enter the name of file>"
				fp <- getLine	
				contents <- readFile fp
				dialog $ appendEntries (words contents) es)
				(\e -> do 
					putStrLn $ show (e :: IOException)
					dialog es)
		otherwise -> do
			putStr "command not found\n"
			dialog es