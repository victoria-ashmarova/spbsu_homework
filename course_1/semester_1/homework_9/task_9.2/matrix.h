#pragma once

struct Point;

int evristic(Point point, Point finish);
void searchWay(char **table, int sizeX, int sizeY, int startX, int startY, int finishX, int finishY);
