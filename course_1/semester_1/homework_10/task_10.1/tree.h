#pragma once

using namespace std;

struct Node;
struct Buffer;
struct Queue;

Queue *createQueue();
int sizeQueue(Queue *queue);
bool isInQueue(Queue *queue, char litera);
void countVariable(Queue *queue, char litera);
void addElementToQueue(Queue *queue, char litera, Node *left, Node *right);

void makeTreeElement(Queue *queue);
void orderWithVariables(Queue *queue);
void preorder(Queue *queue, ofstream &outFile);
void deleteQueue(Queue *queue);
void code(Queue *queue, Buffer *buffer, ofstream &out);

Buffer *createBuffer();
void deleteBuffer(Buffer *buffer);
void addElementToBuffer(Buffer *buffer, char symbol, int placetoAdd);
int sizeBuffer(Buffer *buffer);
char getSymbol(Buffer *buffer, int number);
