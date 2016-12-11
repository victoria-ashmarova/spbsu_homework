#pragma once

struct BinaryTree;

BinaryTree *createTree();

void clearTree(BinaryTree *tree);
void deleteTree(BinaryTree *tree);

int sizeTree(BinaryTree *tree);
bool isEmpty(BinaryTree *tree);

void addElement(BinaryTree *tree, int value);
void removeElement(BinaryTree *tree, int value);

bool isInTree(BinaryTree *tree, int value);

void inorderTree(BinaryTree *tree);
void preorderTree(BinaryTree *tree);
void postorderTree(BinaryTree *tree);
