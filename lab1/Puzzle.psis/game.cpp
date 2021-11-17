#include "game.h"

bool Logic::operator==(const Logic& other)
{
    return !(this->table == other.table);
}

vector_int Logic::operator++()
{
    int counter = 0;
    while (counter != this->table.size()) {
        if (this->table[counter] != 0) {
            this->table[counter]++;
            
        }
        else this->table[counter]+=0;

        counter++;
    }
   
    return table;
}



void Logic::setChoice(int choice)
{
	this->choice = choice;
}

void Logic::complexity(char* answer, int& sizeArray, int& radius, int& speedTimer)
{
    if (strcmp(answer, "Легкий") == 0 || strcmp(answer, "легкий") == 0 || atoi(answer) == 1) {
        sizeArray = 8;
        radius = 3;
        speedTimer = 6000;
    }
    if (strcmp(answer, "Сложный") == 0 || strcmp(answer, "средний") == 0 || atoi(answer) == 2) {
        sizeArray = 15;
        radius = 4;
        speedTimer = 7000;
    }
    if (strcmp(answer, "Сложный") == 0 || strcmp(answer, "сложный") == 0 || atoi(answer) == 3) {
        sizeArray = 24;
        radius = 5;
        speedTimer = 8000;
    }
}

void Logic::maps()
{
    int counter = 0, columns = radius;

    while (counter != this->table.size()) {
        cout << this->table[counter] << '\t';
        counter++;
        if (counter % columns == 0)
            cout << endl;
    }
    cout << endl;
}


int Logic::findEmptySpace()
{
    for (int counter = 0; counter < table.size(); counter++)
        if (table[counter] == 0)
            return counter;
}

int Logic::findIndexOfChoice()
{
    for (int counter = 0; counter < table.size(); counter++)
        if (table[counter] == choice)
            return counter;
}

void Logic::gameLogic(vector_int& table)
{
    int
        emptySpace = findEmptySpace(),
        upItr,
        downItr,
        left = emptySpace - 1,
        right = emptySpace + 1,
        up = emptySpace - radius,
        down = emptySpace + radius,
        indexChoice = findIndexOfChoice();

    if (indexChoice == left || indexChoice == right ||
        indexChoice == up || indexChoice == down)
        swap(table[indexChoice], table[emptySpace]);
    else cout << "Значение не верно!\n";
}
