public class TP01Q05 {

  public static void main(String[] args) {
    String linha = MyIO.readLine();
    int i = 0, cont = 0, x[] = new int[100];

    //while (!(isFim(linha))) {
    int n = linha.charAt(i) - 48;
    for (i = 1; (cont <= n) && (i < linha.length()); i++) {
      if (linha.charAt(i) != ' ') {
        x[cont] = linha.charAt(i) - 48;
        cont++;
      }
    }
    //}
  }
/**
 * 
 * @param s
 * @return
 */
  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }
}
