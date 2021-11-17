#pragma once
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef vector <int> vector_int;

class Logic {
private:
    int choice;
    int sizeArray;
    int radius;
    vector_int table;
    bool isActive;
    bool isCorrect;
public:
    Logic(
        int sizeArray,
        vector_int table,
        bool isActive,
        bool isCorrect,
        int radius) {

        this->sizeArray = sizeArray;
        this->table = table;
        this->isActive = isActive;
        this->isCorrect = isCorrect;
        this->radius = radius;
        
    }
    bool operator==(const Logic& other);

    vector_int operator ++();
    

    void setChoice(int choice);
    void static complexity(char* answer, int& sizeArray, int& radius, int& speedTimer);
    void maps();
   
    int findEmptySpace();
    int findIndexOfChoice();
    void gameLogic(vector_int& table);
};