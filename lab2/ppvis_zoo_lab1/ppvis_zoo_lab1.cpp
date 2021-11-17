#include <iostream>

#include <string>
#include "Mamals.cpp"
#include "Winged.cpp"
#include "Animal.cpp"
#include "Swiming.cpp"

using namespace std;
using namespace mamals;
using namespace winged;
using namespace swimming;

class Monkey :public Mamals {
public:
    Monkey(string name, int weight, string country, int speed) : Mamals(name, weight, country, speed) {}
    void sound() override
    {
        cout << "Я люблю пиццу" << endl;
    }

    

};

class Eagle :public Winged {
public:
    Eagle(string name, int weight, string country, int speed_of_flight) : Winged(name, weight, country, speed_of_flight) {}

    void sound() override
    {
        cout << "АААААААААААрррррррРРР" << endl;
    }

   
};

class Crocodile :public Swimming {
public:
    Crocodile(string name, int weight, string country, int time_under_water) :Swimming(name, weight, country, time_under_water) {}

    void sound() override
    {
        cout << "*звук удара зубов*" << endl;
    }

    
};
class Bat : public Winged,  public Mamals {
public:
    Bat(string name, int weight, string country, int speed_of_flight, int speed) : Winged(name, weight, country, speed_of_flight), Mamals(name, weight, country, speed) {}

    void PrinterBat() {
        cout << "1.Название: " << Mamals::getName() << endl;
        cout << "2.Вес:  " << Mamals::getWeight() << endl;
        cout << "3.Страна: " << Mamals::getCountry() << endl;
        cout << "4.Скорость бега: " << Mamals::getSpeed() << endl;
        cout << "5.Скорость полёта: " << Winged::getSpeedOfFlight() << endl;
        cout << endl;
    }
    void sound() override
    {
        cout << "*Ультрзвук*" << endl;
    }

    
};

int main()
{
    setlocale(LC_ALL, "rus");

    int choice;


    Monkey monkey("Горилла Олег", 10, "Африка", 10);
    Eagle eagle("Орёл", 25, "АМЕРИКА", 40);
    Bat bat("Бэтман", 5, "Китай", 6, 7);
    Crocodile crocodile("Крокодил", 5, "Абдубай", 10);
    while (true) {

        cout << "Просмотр: " << endl;
        cout << "1.Обезьяна" << endl;
        cout << "2.Птица" << endl;
        cout << "3.Летающее млекопитающее" << endl;
        cout << "4.Амфибия" << endl;
        cout << "5.Выход" << endl;

        cin >> choice;
        switch (choice) {
            case 1:
             
                system("cls");
                monkey.PrinterMamals(&monkey);
                cout << "Издоваемый звук: " << endl;
                monkey.sound();
                cout << endl;
                
                
                break;
            case 2:
              
                system("cls");
                eagle.PrinterWinged(&eagle);
                cout << "Издоваемый звук: " << endl;
                eagle.sound();
                cout << endl;
                break;
            case 3:
               
                system("cls");
                bat.PrinterBat();
                cout << "Издоваемый звук: " << endl;
                bat.sound();
                cout << endl;

                break;
            case 4:
             
                system("cls");
                crocodile.PrinterW(&crocodile);
                cout << "Издоваемый звук: " << endl;
                crocodile.sound();
                cout << endl;
                break;
            case 5:
                system("cls");
                return 0;
                break;
        }

    }

    system("pause");
    return 0;
}