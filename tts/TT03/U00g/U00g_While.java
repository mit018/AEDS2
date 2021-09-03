public class U00g_While {

  public static void main(String[] args) {
    //media();
    //impar();
    //fibonacci();
  }

  public static void media() {
    float notas[] = new float[5], total = 0;
    int i = 0;

    while (i < 5) {
      notas[i] = MyIO.readFloat();
      total += notas[i];
      i++;
    }

    MyIO.println("Media = " + total / 5);
  }

  public static void impar() {
    float N = MyIO.readFloat();
    int i = 1;

    while (i <= N) {
      MyIO.println(i);
      i += 2;
    }
  }

  public static void fibonacci() {
    int n = MyIO.readInt(), f0 = 1, f1 = 1, i = 2, termo = 0;
    if (n == 1 || n == 2) {
      MyIO.println("1");
    } else {
      while (i < n) {
        termo = f0 + f1;

        f0 = f1;
        f1 = termo;

        i++;
      }
      MyIO.println(termo);
    }
  }
}
