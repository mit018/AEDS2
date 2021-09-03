#include <iostream>
#include <string.h>
#include <stdbool.h>
using namespace std;

#define NUMLINHA    1000
#define TAMPALAVRA  100


bool isMaiuscula (char c){
   return (c >= 'A' && c <= 'Z');
}


bool isFim(char s[]){
   return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}


int contarLetrasMaiusculas (char s[]){
   int resp = 0;

   for(int i = 0; i < strlen(s); i++){
      if(isMaiuscula(s[i]) == true){
         resp ++; 
      }
   }

   return resp;
}


int main(){
   //Declaracao de variaveis
   char entrada[NUMLINHA][TAMPALAVRA];
   int numEntrada = 0;

   //Leitura da entrada padrao
   do {
       cin >> entrada[numEntrada];
   } while (isFim(entrada[numEntrada++]) == false);
   numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

   //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
   for(int i = 0; i < numEntrada; i++){
      cout << contarLetrasMaiusculas(entrada[i]) << endl;
   }
}

