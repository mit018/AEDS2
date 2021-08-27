#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// verifica se uma palavra eh um palindromo

int palindromo(char palavra[], int f, int i);
int isFim(char palavra[]);
int main();

int palindromo(char palavra[], int f, int i)
{
    if (f == (strlen(palavra) / 2) - 1) // se chegar na posicao (metade + 1), deve parar
    {
        return 1;
    }
    else if (palavra[f] == palavra[i])
    {
        return palindromo(palavra, f - 1, i + 1);
    }

    return 0;
} // fim int palindromo recursivo

int isFim(char palavra[])
{
    if (strcmp(palavra, "FIM") == 0)
    {
        return 1;
    }

    return 0;
} // compara a string com "FIM"

int main()
{
    int i = 0;
    char str[800][1000];

    scanf(" %[^\n]", str[i]);
    str[i][strlen(str[i])] = '\0';


    while (!(isFim(str[i])))
    {
        i++;
        scanf(" %[^\n]", str[i]);
        str[i][strlen(str[i])] = '\0';

    }

    for (int j = 0; j < i; j++)
    {
        if (palindromo(str[j], strlen(str[j]) - 1, 0))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    return 0;
} // fim int main
