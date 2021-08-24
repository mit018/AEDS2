class Somatorio {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(somatorio(n));
    }

    public static int somatorio(int n){
        int soma = 0, i = 0;

        for(i = 1; i <= n; i++){
            soma += i;
        }
        return soma;
    }
}
