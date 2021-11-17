#pragma once
#include "Animal.cpp"

using namespace std;
using namespace animal;

namespace swimming {
    class Swimming :private Animal {
    private:
        int time_under_water;

    public:
        Swimming(string name, int weight, string country, int time_under_water) :Animal(name, weight, country) {
            this->time_under_water = time_under_water;
        }
        virtual int getTimeUnderWater() {
            return time_under_water;
        }

        virtual void PrinterW(Swimming* swimming) {
            cout << "1.Название: " << swimming->getName() << endl;
            cout << "2.Вес:  " << swimming->getWeight() << endl;
            cout << "3.Страна: " << swimming->getCountry() << endl;
            cout << "4.Может пробыть подводой: " << swimming->getTimeUnderWater() << endl;
            cout << endl;
        }
    };
}