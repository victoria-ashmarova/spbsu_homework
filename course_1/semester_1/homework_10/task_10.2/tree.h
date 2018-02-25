#pragma once

using namespace std;

struct Tree;

Tree *createTree();
void fullTree(ifstream &file, Tree *tree);
void deleteTree(Tree *tree);
void decode(Tree *tree, ifstream &in, ofstream &out);
