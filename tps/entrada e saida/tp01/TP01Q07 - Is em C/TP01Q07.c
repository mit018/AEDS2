#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int isFim(char palavra[])
{
    if (strcmp(palavra, "FIM") == 0)
    {
        return 1;
    }
    return 0;
}

int allVogal(char s[])
{
    char vogais[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    int teste = 0;
    for (int i = 0; i < strlen(s); i++)
    {
        teste = 0;
        for (int j = 0; j < 10; j++)
        {
            if (s[i] == vogais[j])
            {
                j = 10;
                teste = 1;
            }
        }               // for para comparar todos os caracteres com todas as vogais no vetor 'vogais'
        if (teste == 0) // se o teste permaneceu 0, significa que o caractere na posicao nao entrou na condicao em nenhuma vogal
        {
            return 0;
        }
    }
    return 1;
}

int allCons(char s[])
{
    char vogais[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    for (int i = 0; i < strlen(s); i++)
    {
        for (int j = 0; j < 10; j++)
        {
            if (s[i] == vogais[j] || s[i] < 65)
            {
                return 0; // se em alguma posicao for uma vogal ou for algo diferente de uma letra, nao eh uma consoante
            }
        }
    }
    return 1;
}

int isInt(char s[])
{
    int pon = 0; // contador de pontos
    for (int i = 0; i < strlen(s); i++)
    { // percorre todos os caracteres
        if (s[i] < 48 || s[i] > 57)
        {
            if (s[i] == '.') // se nao for um numero, pode ser um ponto
            {
                pon++;
                if (pon > 1)
                { // se houver mais de um ponto, o segundo ponto deve ser precedido
                    //de no minimo 3 numeros e outro ponto e logo depois devem havem outros 3 numeros (ex: 1.000.000 e nao 1.000.)
                    if (s[i - 1] < 48 || s[i - 1] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 2] < 48 || s[i - 2] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 3] < 48 || s[i - 3] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 4] != '.')
                    {
                        return 0;
                    }
                    else if (strlen(s) < i + 4)
                    {
                        return 0;
                    }
                    else if (s[i + 1] < 48 || s[i + 1] > 57)
                    {
                        return 0;
                    }
                    else if (s[i + 2] < 48 || s[i + 2] > 57)
                    {
                        return 0;
                    }
                    else if (s[i + 3] < 48 || s[i + 3] > 57)
                    {
                        return 0;
                    }
                }
            }
            else
            {
                return 0;
            }
        }
    }

    if (pon == 1) // se houver apenas um ponto, nao eh inteiro (ex: 1.0)
    {
        return 0;
    }

    return 1;
}

int isReal(char s[])
{
    int vir = 0, pon = 0; // contadores de virgulas e pontos

    if (!(isInt(s)))
    { // testa se eh um inteiro, se sim, tambem eh um real
        for (int i = 0; i < strlen(s); i++)
        {
            if (s[i] == ',')
            {
                vir++;
                if (vir > 1) // se houver mais de um virgula, nao eh real
                {
                    return 0;
                }
            }
            else if (s[i] == '.')
            {
                pon++;
                if (pon > 1) // se houver mais de um ponto, o segundo deve ser precedido de 3 numeros e outro ponto
                {
                    if (s[i - 1] < 48 || s[i - 1] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 2] < 48 || s[i - 2] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 3] < 48 || s[i - 3] > 57)
                    {
                        return 0;
                    }
                    else if (s[i - 4] != '.')
                    {
                        return 0;
                    }
                }
                else if (vir > 0)
                { // se houver um ponto e nao houver nenhum virgula, nao eh real (passaria como inteiro inicialmente)
                    return 0;
                }
            }
            else if (s[i] < 48 || s[i] > 57) // se nao for um numero nao eh real
            {
                return 0;
            }
        }

        if (vir == 1)
        {
            return 1;
        }
        else if (pon > 1)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    return 1;
}

int main()
{
    int i = 0;
    char str[800][1000];

    scanf(" %[^\n]", str[i]);
    //str[i][strlen(str[i]) - 1] = '\0'; -> se der Segmentation fault

    while (!(isFim(str[i])))
    {
        i++;
        scanf(" %[^\n]", str[i]);
        //str[i][strlen(str[i]) - 1] = '\0'; -> se der Segmentation fault
    }

    for (int j = 0; j < i; j++)
    {
        if (allVogal(str[j]))
        {
            printf("SIM");
        }
        else
        {
            printf("NAO");
        }

        if (allCons(str[j]))
        {
            printf(" SIM");
        }
        else
        {
            printf(" NAO");
        }

        if (isInt(str[j]))
        {
            printf(" SIM");
        }
        else
        {
            printf(" NAO");
        }

        if (isReal(str[j]))
        {
            printf(" SIM");
        }
        else
        {
            printf(" NAO");
        }

        printf("\n");
    }
   
}
