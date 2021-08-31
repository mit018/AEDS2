#include <stdio.h>

int main()
{

    int M, f[3], maior = 0;

    scanf("%i %i %i", &M, &f[0], &f[1]);

    f[2] = M - (f[0] + f[3]);

    for (int i = 0; i < 3; i++)
    {
        if (maior < f[i]){
            maior = f[i];
        }
    }
}