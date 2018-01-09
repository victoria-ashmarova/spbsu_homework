#pragma once

struct SymbolChane;
struct Line;

SymbolChane *createSymbolChane();
void fullChane(SymbolChane *chane);
void clearAndDeleteChane(SymbolChane *chane);
int sizeChane(SymbolChane *chane);

Line *makeLine(SymbolChane *chane);
int lenght(Line *line);
void clearAndDeleteLine(Line *line);
char *getChar(Line *line);
