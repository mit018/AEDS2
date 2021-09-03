class Matriz {
   public static void main (String[] args){
      //Declaracao de variaveis
      int n;
      int matriz[][] = new int[10][10];
      int soma[] = new int[10];

      //Leitura do numero de linhas e colunas
      n = MyIO.readInt();

      //Leitura dos elementos
      for(int i = 0; i < n; i++){
         for(int j = 0; j < n; j++){
            matriz[i][j] = MyIO.readInt();
         }
      }

      //Soma das colunas
      for(int j = 0; j < n; j++){
         soma[j] = 0;
         for(int i = 0; i < n; i++){
            soma[j] += matriz[i][j];
         }
      }

      //Escrever saida
      for(int j = 0; j < n; j++){
         System.out.print(soma[j] + " ");
      }
   }
}
