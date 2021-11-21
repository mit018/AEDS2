#include <stdio.h>

int func(int n)
{

    if (n > 0)
    {
        return func(n - 1);
    }
    else
    {
        return 1;
    }
}

void main()
{

    int n = 1;

    for (int i = 1; i <= 10; i++)
    {
        for (int j = 1; j <= i; j++)
        {
            if ((j % 2) == 0)
            {
                n += 1;
            }
            else
            {
                n -= 1;
            }
        }
    }

    printf("%i", n);
}