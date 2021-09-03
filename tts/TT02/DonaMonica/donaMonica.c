#include <stdio.h>

int main()
{
    int M, f[3], maior = 0;

    scanf("%i", &M); // recebe o primeiro numero separado

    while (M != 0)
    {
        scanf("%i %i", &f[0], &f[1]); // recebe a idade dos filhos

        f[2] = M - (f[0] + f[1]);

        for (int i = 0; i < 3; i++)
        {
            if (maior < f[i])
            {
                maior = f[i];
            }
        }

        printf("%i", maior); 

        scanf("%i", &M);// recebe o primeiro numero separado
    }
    return 0;
}