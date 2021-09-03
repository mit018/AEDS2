#include <stdio.h>

int main(){
   char str[100];
   int inteiro;
   double real;
   char caractere;

   printf("\nEntre com uma palavra: ");
   scanf ("%s", str);
   printf("\nEntre com um inteiro: ");
   scanf ("%i", &inteiro);
   printf("\nEntre com um real: ");
   scanf ("%lf", &real);
   scanf("%*c");//limpar o buffer
   printf("\nEntre com um caractere: ");
   scanf ("%c", &caractere);

   printf("\nSua string: %s", str);
   printf("\nSeu inteiro: %i", inteiro);
   printf("\nSeu real: %0.1f", real);
   printf("\nSeu caractere: %c\n\n", caractere);
}
