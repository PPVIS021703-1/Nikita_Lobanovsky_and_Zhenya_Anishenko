#include <SFML/Graphics.hpp>
#include <iostream>
#include <algorithm>
#include <vector>
#include <ctime>
#include "game.h"

using namespace std;
using namespace sf;
typedef vector <int> vector_int;


int main() {
    setlocale(LC_ALL, "rus");
    srand(unsigned(time(NULL)));
   

    int sizeArray, radius, counter = 0, choice, emptySpace = 0, numb, speedTimer;
    vector_int table, winTable;
    bool isActive = true, isCorrect = false;
    char answer;

    cout << "Выберите :\nЛегкий\nСредний\nСложный:\n ";
    cin >> answer;

    Logic::complexity(&answer, sizeArray, radius, speedTimer);

    for (int counter = 1; counter <= sizeArray; counter++) {
        winTable.push_back(counter);
        table.push_back(counter);
    }

    random_shuffle(table.begin(), table.end());

    table.emplace_back(0);
    winTable.emplace_back(0);


    while (isActive) {

        
        Logic logic(sizeArray, table, isActive, isCorrect, radius);
        Logic winLogic(sizeArray, winTable, isActive, isCorrect, radius);

        table=++logic;
        logic.maps();
        
        

        cin >> choice;
        system("cls");
        logic.setChoice(choice);

        numb = logic.findIndexOfChoice();
        if (numb == table.size()) {
            cout << "Введите корректные данные!\nВвод: ";
            cin >> choice;
        }
        
        logic.gameLogic(table);
        
        isActive = winLogic==logic;

    }
    cout << "ОГО!" << endl;
    
    system("pause");
    return 0;
}