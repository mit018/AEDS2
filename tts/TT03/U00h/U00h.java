public class U00h {

  public static void main(String[] args) {}

  public static int produto(int a, int b) {
    int resp = 0;
    if (b > 0) {
      resp = a + produto(a, b - 1);
    }
    return resp;
  }

  public static int maior(int v[], int n) {
    return maior(v, n, 0);
  }

  public static int maior(int v[], int n, int i) {
    int resp;
    if (i == n - 1) {
      resp = v[n - 1];
    } else {
      resp = maior(v, n, i + 1);
      if (resp < v[i]) {
        resp = v[i];
      }
    }
    return resp;
  }

  public static boolean isPalindromo(String s) {
    return isPalindromo(s, 0);
  }

  public static boolean isPalindromo(String s, int i) {
    boolean resp;
    if (i >= s.length() / 2) {
      resp = true;
    } else if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
      resp = false;
    } else {
      resp = isPalindromo(s, i + 1);
    }
    return resp;
  }
}
