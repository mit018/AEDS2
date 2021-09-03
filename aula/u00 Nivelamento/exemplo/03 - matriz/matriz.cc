#include <iostream>
using namespace std;

int main(){
   //Declaracao de variaveis
   int n;
   int matriz[10][10];
   int soma[10];

   //Leitura do numero de linhas e colunas
   cin >> n;

   //Leitura dos elementos
   for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
         cin >> matriz[i][j];
      }
   }

   //Soma das colunas
   for(int j = 0; j < n; j++){
      soma[j] = 0;
      for(int i = 0; i < n; i++){
         soma[j] += matriz[i][j];
      }
   }

   //Escrever saida
   for(int j = 0; j < n; j++){
      cout << soma[j] << " ";
   }
}
