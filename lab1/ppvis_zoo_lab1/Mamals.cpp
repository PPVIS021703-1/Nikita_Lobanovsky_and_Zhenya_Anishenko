#pragma once
#include <iostream>
#include "Animal.cpp"

using namespace std;
using namespace animal;

namespace mamals {
    class Mamals :protected virtual Animal{
    private:
        int speed;

    public:
        Mamals(string name, int weight, string country, int speed) :Animal(name, weight, country) {
            this->speed = speed;
        }
        virtual int getSpeed() {
            return speed;
        }

        virtual void sound() override {
            cout << "���� ������" << endl;
        }

        virtual void PrinterMamals(Mamals* mamal) {
            cout << "1.��������: " << mamal->getName() << endl;
            cout << "2.���:  " << mamal->getWeight() << endl;
            cout << "3.������: " << mamal->getCountry() << endl;
            cout << "4.�������� ����: " << mamal->getSpeed() << endl;
            cout << endl;
        };
    };
}