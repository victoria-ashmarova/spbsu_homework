#pragma once

struct Book;

Book *createBook();
void clearAndDeleteBook(Book *book);
int sizeBook(Book *book);
void readFromFile(Book *names, Book *numbers);
void addLineFromConsole(Book *names, Book *numbers);
void saveToFile(Book *names, Book *numbers);
void searchName(Book *names, Book *numbers);
void searchNumber(Book *names, Book *numbers);
