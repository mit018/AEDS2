#include <iostream>
using namespace std;

int main(){
   char str[100];
   int inteiro;
   double real;
   char caractere;

   cout << "\nEntre com uma palavra: ";
   cin >> str;
   cout << "\nEntre com um inteiro: ";
   cin >> inteiro;
   cout << "\nEntre com um real: ";
   cin >> real;
   cout << "\nEntre com um caractere: ";
   cin >> caractere;

   cout << "\nSua string: " << str;
   cout << "\nSeu inteiro: " << inteiro;
   cout << "\nSeu real: " << real;
   cout << "\nSeu caractere: " << caractere << "\n\n";
}
