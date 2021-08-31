#include <stdio.h>
#include <string.h>

int main()
{
    int i = 0, n = 0;
    float num;
    FILE *arq;
    arq = fopen("exemplo.txt", "w");

    scanf(" %i", &n);

    for (i = 0; i < n; i++)
    {
        scanf(" %f", &num);
        fprintf(arq, "%g\n", num);
    }

    fclose(arq);

    arq = fopen("exemplo.txt", "r");
    fseek(arq, 0, SEEK_END);
    int tamanho = ftell(arq);

    int fim = ftell(arq), j = 0, linhas = 0;

    for (i = ftell(arq); i >= 0 || linhas <= n; i--)
    {
        j = 0;
        fseek(arq, i, SEEK_SET);

        if (fgetc(arq) == '\n' || i == 0)
        {

            if (linhas == n)
            {
                fseek(arq, i, SEEK_SET);
            }
            else if (linhas == 0)
            {
                fseek(arq, i, SEEK_SET);
            }

            //fseek(arq, i, SEEK_SET);
            j = i;
            while (j < fim)
            {
                if (linhas != 0)
                {
                    printf("%c", fgetc(arq));
                }
                j++;
            }

            fim = i;
            linhas++;
        }
    }

    fclose(arq);
    // for(i = strlen(arq); )

    return 0;
} // fim int main
