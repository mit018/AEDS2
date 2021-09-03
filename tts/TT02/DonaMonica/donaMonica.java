public class donaMonica {

public static void main(String[] args) {
    
    int M = 0, maior = 0;
    int f[] = new int[3];

    M = MyIO.readInt();

    while (M != 0)
    {
        f[0] = MyIO.readInt();
        f[1] = MyIO.readInt();

        f[2] = M - (f[0] + f[1]);

        for (int i = 0; i < 3; i++)
        {
            if (maior < f[i])
            {
                maior = f[i];
            }
        }
        
        MyIO.println(maior);

        M = MyIO.readInt();
    }
}
}
