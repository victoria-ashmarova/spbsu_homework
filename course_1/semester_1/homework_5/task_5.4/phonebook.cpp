#include <iostream>
#include <fstream>

using namespace std;

int const sizeOfEntry = 32;

struct PartOfEntry
{
    int numberInBook;
    char entry[sizeOfEntry] = {'\0'};
    PartOfEntry *next;
};

struct Book
{
    int sizeOfBook;
    PartOfEntry *firstEntry;
};

Book *createBook()
{
    Book *book = new Book;
    book->firstEntry = nullptr;
    book->sizeOfBook = 0;
    return book;
}

int sizeBook(Book *book)
{
    return book->sizeOfBook;
}

bool areEmpty(Book *names, Book *numbers)
{
    return sizeBook(names) == 0 || sizeBook(numbers) == 0;
}

void changeSize(Book *names, Book *number)
{
    names->sizeOfBook++;
    number->sizeOfBook++;
}

void clearAndDeleteBook(Book *book)
{
    while (sizeBook(book) > 0)
    {
        PartOfEntry *toDelete = book->firstEntry;
        book->firstEntry = book->firstEntry->next;
        toDelete->next = nullptr;
        delete toDelete;
        toDelete = nullptr;
        book->sizeOfBook--;
    }
    delete(book->firstEntry);
    book->firstEntry = nullptr;
    delete book;
    book = nullptr;
}

bool firstLineIsMore(char *firstLine, char *secondLine)
{
    int i = 0;
    while (i < sizeOfEntry - 1 && firstLine[i] == secondLine[i] && firstLine[i] != '\0' && secondLine[i] != '\0')
    {
        i++;
    }
    return firstLine[i] > secondLine[i];
}

bool linesAreEgual(char firstLine[], char secondLine[])
{
    int i = 0;
    while (i < sizeOfEntry && firstLine[i] == secondLine[i])
    {
        i++;
    }
    return i == sizeOfEntry;
}

void sortBookWithTwoEnties(Book *book)
{
    PartOfEntry *temp = book->firstEntry;
    book->firstEntry = book->firstEntry->next;
    book->firstEntry->next = temp;
    temp->next = nullptr;
}

PartOfEntry *getFromIndex(Book *book, int number)
{
    number = (number < 1 ? 1 : number);
    number = (number > sizeBook(book) ? sizeBook(book) : number);

    PartOfEntry *temp = book->firstEntry;
    int i = 1;
    while (i < number)
    {
        i++;
        temp = temp->next;
    }
    return temp;
}

PartOfEntry *maxLine(Book *book, int beginIndex)
{
    if (beginIndex > sizeBook(book))
        return nullptr;

    PartOfEntry *currentMax = getFromIndex(book, beginIndex);
    PartOfEntry *temp = currentMax->next;
    while (temp != nullptr)
    {
        if (firstLineIsMore(temp->entry, currentMax->entry))
            currentMax = temp;
        temp = temp->next;
    }
    return currentMax;
}

PartOfEntry *getPrev(Book *book, PartOfEntry *element)
{
    PartOfEntry *temp = book->firstEntry;
    if (temp == element)
        return nullptr;

    while (temp->next != element && temp != nullptr)
    {
        temp = temp->next;
    }
    return temp;
}

void sortLines(Book *book)
{
    if (sizeBook(book) < 2)
        return;

    if (sizeBook(book) == 2)
    {
        if (firstLineIsMore(book->firstEntry->entry, book->firstEntry->next->entry))
        {
            sortBookWithTwoEnties(book);
        }
        return;
    }

    for (int i = 1; i <= sizeBook(book); i++)
    {
        PartOfEntry *max = maxLine(book, i);
        if (max != book->firstEntry)
        {
            PartOfEntry *prev = getPrev(book, max);
            prev->next = max->next;
            max->next = book->firstEntry;
            book->firstEntry = max;
        }
    }
}

int binarySearchLine(Book *book, char *toSearch)
{
    if (sizeBook(book) == 0)
        return -1;

    sortLines(book);
    int begin = 1;
    int end = sizeBook(book);
    int pivot = begin + end - (begin + end) / 2;
    PartOfEntry *current = getFromIndex(book, pivot);
    while (begin <= end && !linesAreEgual(current->entry, toSearch))
    {
        if (firstLineIsMore(current->entry, toSearch))
        {
            end = pivot - 1;
        }
        if (firstLineIsMore(toSearch, current->entry))
        {
            begin = pivot + 1;
        }
        pivot = begin + end - (begin + end) / 2;
        current = getFromIndex(book, pivot);
    }
    if (linesAreEgual(current->entry, toSearch))
    {
        PartOfEntry *toReturn = getFromIndex(book, pivot);
        return toReturn->numberInBook;
    }
    else
        return -1;
}

char *searchEntry(Book *book, int numberInBook)
{
    if (numberInBook > sizeBook(book) || numberInBook < 0)
        return nullptr;

    PartOfEntry *temp = book->firstEntry;
    while (temp != nullptr && temp->numberInBook != numberInBook)
        temp = temp->next;

    return (temp == nullptr ? nullptr : temp->entry);
}

bool lineIsEntry(char *line)
{
    bool twoPoints = false;
    bool entry = false;
    int i = 0;
    while (i < sizeOfEntry && line[i] != '\0')
    {
        if (line[i] == ':' && i < 30)
        {
            i = i + 2;
            twoPoints = true;
            entry = true;
        }
        entry = twoPoints && line[i] >= '0' && line[i] <= '9' && entry;
        i++;
    }
    return entry;
}

void makeLine(char *line, char *buffer, int begin, int end)
{
    if (end - begin > sizeOfEntry || begin > end)
        return;

    int i = begin;
    while (i <= end && buffer[i] != '\0')
    {
        line[i - begin] = buffer[i];
        i++;
    }
}

int numberOfTwoPoints(char *line)
{
    int i = 0;
    while (i < sizeOfEntry && line[i] != ':')
        i++;
    return i;
}

PartOfEntry *createEntry(char *line, int beginOfLine, int endOfLine, int number, PartOfEntry *next)
{
    PartOfEntry *newEntry = new PartOfEntry;
    newEntry->next = next;
    newEntry->numberInBook = number;
    makeLine(newEntry->entry, line, beginOfLine, endOfLine);
    return newEntry;
}

void addLineFromFile(Book *names, Book *numbers, char *line)
{
    if (!lineIsEntry(line))
        return;

    int twoPointsIndex = numberOfTwoPoints(line);
    char name[sizeOfEntry] = {'\0'};
    char number[sizeOfEntry] = {'\0'};
    makeLine(name, line, 0, twoPointsIndex - 2);
    if (binarySearchLine(names, name) != -1)
    {
        return;
    }

    makeLine(number, line, twoPointsIndex + 2, sizeOfEntry - 1);
    if (binarySearchLine(numbers, number) != -1)
    {
        return;
    }

    names->firstEntry = createEntry(name, 0, sizeOfEntry - 1, sizeBook(names) + 1, names->firstEntry);
    numbers->firstEntry = createEntry(number, 0, sizeOfEntry - 1, sizeBook(numbers) + 1, numbers->firstEntry);
    changeSize(names, numbers);
}

void readFromFile(Book *names, Book *numbers)
{
    cout << "Enter the name of file with data." << endl;
    int const maxSize = 64;
    char fileName[maxSize] = {'\0'};
    cin >> fileName;
    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "There is no file with data." << endl;
        return;
    }
    while (!file.eof())
    {
        char buffer[maxSize] = {'\0'};
        file.getline(buffer, maxSize);
        addLineFromFile(names, numbers, buffer);
    }
    cout << "Data from file was added." << endl;
    file.close();
}

void addLineFromConsole(Book *names, Book *numbers)
{
    char name[sizeOfEntry] = {'\0'};
    cout << "Enter name:" << endl;
    cin.getline(name, sizeOfEntry);
    //there is empty line in first reading
    cin.getline(name, sizeOfEntry);
    if (binarySearchLine(names, name) != -1)
    {
        cout << "There is entry with this name in book." << endl;
        return;
    }

    char number[sizeOfEntry] = {'\0'};
    cout << "Enter number." << endl;
    cin.getline(number, sizeOfEntry);
    if (binarySearchLine(numbers, number) != -1)
    {
        cout << "There is entry with this number in book." << endl;
        return;
    }

    names->firstEntry = createEntry(name, 0, sizeOfEntry - 1, sizeBook(names) + 1, names->firstEntry);
    numbers->firstEntry = createEntry(number, 0, sizeOfEntry - 1, sizeBook(numbers) + 1, numbers->firstEntry);
    changeSize(names, numbers);
}

PartOfEntry *maxNumber(Book *book, int beginIndex)
{
    if (beginIndex > sizeBook(book))
        return nullptr;

    PartOfEntry *currentMax = getFromIndex(book, beginIndex);
    PartOfEntry *temp = currentMax->next;
    while (temp != nullptr)
    {
        if (currentMax->numberInBook < temp->numberInBook)
            currentMax = temp;
        temp = temp->next;
    }
    return currentMax;
}

void sortIndex(Book *book)
{
    if (sizeBook(book) < 2)
        return;

    if (sizeBook(book) == 2)
    {
        if (book->firstEntry->numberInBook > book->firstEntry->next->numberInBook)
        {
            sortBookWithTwoEnties(book);
        }
        return;
    }

    for (int i = 1; i <= sizeBook(book); i++)
    {
        PartOfEntry *max = maxNumber(book, i);
        if (max != book->firstEntry)
        {
            PartOfEntry *prev = getPrev(book, max);
            prev->next = max->next;
            max->next = book->firstEntry;
            book->firstEntry = max;
        }
    }
}

void saveToFile(Book *names, Book *numbers)
{
    if (sizeBook(names) == 0 || sizeBook(numbers) == 0)
    {
        cout << "There is no enties to save in file." << endl;
        return;
    }

    sortIndex(names);
    sortIndex(numbers);

    cout << "Enter the name of file to save phonebook." << endl;
    int const sizeNameFile = 256;
    char nameFile[sizeNameFile] = {'\0'};
    cin >> nameFile;

    ofstream file(nameFile);

    PartOfEntry *firstTemp = names->firstEntry;
    PartOfEntry *secondTemp = numbers->firstEntry;
    while (firstTemp != nullptr && secondTemp != nullptr)
    {
        file << firstTemp->entry << " : " << secondTemp->entry << endl;
        firstTemp = firstTemp->next;
        secondTemp = secondTemp->next;
    }
    file.close();
}

void searchNumber(Book *names, Book *numbers)
{
    cout << "Enter name:" << endl;
    char name[sizeOfEntry] = {'\0'};
    cin.getline(name, sizeOfEntry);
    //there is empty line in first reading
    cin.getline(name, sizeOfEntry);

    int index = binarySearchLine(names, name);
    if (index == -1)
    {
        cout << "There is no number in phonebook." << endl;
        return;
    }

    char *number = searchEntry(numbers, index);
    cout << "number: " << number << endl;
}

void searchName(Book *names, Book *numbers)
{
    cout << "Enter number:" << endl;
    char number[sizeOfEntry] = {'\0'};
    cin >> number;

    int index = binarySearchLine(numbers, number);
    if (index == -1)
    {
        cout << "There is no name in phonebook." << endl;
        return;
    }

    char *name = searchEntry(names, index);
    cout << "name: " << name << endl;
}
