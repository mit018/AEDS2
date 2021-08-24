import java.util.Random;

public class TP01Q04 {

  public static void main(String[] args) {
    String str = MyIO.readLine();
    Random gerador = new Random();
    gerador.setSeed(4);

    while (!(isFim(str))) {
      str = random(str, gerador);
      MyIO.println(str);
      str = MyIO.readLine();
    }
  }

  public static String random(String str, Random gerador) {
    String nova = "";
    char pri = gerarChar(gerador), seg = gerarChar(gerador);

    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == pri) {
        nova += seg;
      } else {
        nova += str.charAt(i);
      }
    }
    return nova;
  }

  public static char gerarChar(Random gerador) {
    char c = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
    return c;
  }

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }
}
