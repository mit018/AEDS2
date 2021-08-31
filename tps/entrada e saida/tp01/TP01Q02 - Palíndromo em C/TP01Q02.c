#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// verifica se uma palavra eh um palindromo

void palindromo(char palavra[]);
int isFim(char palavra[]);
int main();


void palindromo(char palavra[])
{
    char copia[1000];
    strcpy(copia, palavra); // copia a palavra em outra variavel (string)
    int x = strlen(palavra) - 1;

    for (int i = 0; i < strlen(palavra); i++)
    {
        copia[i] = palavra[x--];
    }

    if (strcmp(palavra, copia) == 0)
    {
        printf("SIM\n");
    }
    else
    {
        printf("NAO\n");
    }
} // fim int palindromo

int isFim(char palavra[])
{
    if (strcmp(palavra, "FIM") == 0)
    {
        return 1;
    }
    return 0;
}// testa se a palavra eh "FIM"

int main()
{
    int i = 0;
    char str[800][1000];

    scanf(" %[^\n]", str[i]);
    //str[i][strlen(str[i]) - 1] = '\0';// -> se der Segmentation fault

    while (!(isFim(str[i])))
    {
        i++;
        scanf(" %[^\n]", str[i]);
        //str[i][strlen(str[i]) - 1] = '\0'; //-> se der Segmentation fault
    }

    for (int j = 0; j < i; j++)
    {
        palindromo(str[j]);
    }

    return 0;
} // fim int main
