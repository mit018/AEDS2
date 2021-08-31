import java.io.RandomAccessFile;

class TP01Q09 {

  public static void main(String[] args) {
    try {
      int n = MyIO.readInt();
      float num = 0;
      Arq.openWrite("exemplo.txt");

      for (int i = 0; i < n; i++) {
        num = MyIO.readFloat();
        Arq.println(num);
      }

      Arq.close();

      RandomAccessFile arq = new RandomAccessFile("exemplo.txt", "r");

      int linha = 0, fim = (int) (arq.length() - 1);

      for (int i = fim; i >= 0; i--) {
        arq.seek(i);
        char digito = (char) arq.readByte();

        if (digito == '\n' || i == 0) {
          linha = i;
          for (int j = linha; j <= fim; j++) {
            arq.seek(j);
            if ((char) arq.readByte() != '\n') {
              arq.seek(j);

              MyIO.print((char) arq.readByte());
            }
            
          }
          MyIO.println("");
          fim = linha;
          i = fim;
        }
      }

      arq.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
