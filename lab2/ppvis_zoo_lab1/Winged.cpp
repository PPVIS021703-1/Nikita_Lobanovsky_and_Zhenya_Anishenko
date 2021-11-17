#pragma once
#include <iostream>
#include "Animal.cpp"

using namespace std;
using namespace animal;

namespace winged {
    class Winged :protected virtual Animal {
    private:
        int speed_of_flight;

    public:
        Winged(string name, int weight, string country, int speed_of_flight) :Animal(name, weight, country) {
            this->speed_of_flight = speed_of_flight;
        }
        virtual int getSpeedOfFlight() {
            return speed_of_flight;
        }

        virtual void sound() override {
            cout << "Звук крыльев" << endl;
        }

        virtual void PrinterWinged(Winged* winged) {
            cout << "1.Название: " << winged->getName() << endl;
            cout << "2.Вес:  " << winged->getWeight() << endl;
            cout << "3.Страна: " << winged->getCountry() << endl;
            cout << "4.Скорость полёта: " << winged->getSpeedOfFlight() << endl;
            cout << endl;
        }

    };
}