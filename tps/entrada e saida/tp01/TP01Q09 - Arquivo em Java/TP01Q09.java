import java.io.RandomAccessFile;

class TP01Q09 {

  public static void main(String[] args) {
    try {
      int n = MyIO.readInt();
      double num = 0;
      int num2 = 0;
      RandomAccessFile arq = new RandomAccessFile("exemplo.txt", "rw");

      for (int i = 0; i < n; i++) {
        num = MyIO.readDouble();
        arq.writeDouble(num);
      }

      arq.close();

      arq = new RandomAccessFile("exemplo.txt", "r");
      int k;

      for (int i = n - 1; i >= 0; i--) {
        k = i*8; // 8 = tamanho de um double
        arq.seek(k);
        num = arq.readDouble();
        num2 = (int)num;

        if(num == num2){ // se o numero for igual a versao int dele mesmo, ex: 1.0 == 1; a versao inteiro eh printada (1 e nao 1.0)
          MyIO.println(num2);
        }else{
          MyIO.println(num);
        }
      }

      arq.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
