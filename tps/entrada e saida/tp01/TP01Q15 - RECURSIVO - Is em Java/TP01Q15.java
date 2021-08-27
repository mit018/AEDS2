class TP01Q15 {

  public static void main(String[] args) {
    String str = MyIO.readLine();

    while (!(isFim(str))) {
      if (allVogal(str, 0)) {
        MyIO.print("SIM");
      } else {
        MyIO.print("NAO");
      }

      if (allCons(str, 0)) {
        MyIO.print(" SIM");
      } else {
        MyIO.print(" NAO");
      }

      if (isInt(str, 0)) {
        MyIO.print(" SIM");
      } else {
        MyIO.print(" NAO");
      }

      if (isReal(str)) {
        MyIO.print(" SIM");
      } else {
        MyIO.print(" NAO");
      }

      MyIO.println("");

      str = MyIO.readLine();
    }
  }

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  public static boolean allVogal(String s, int i) {
    char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

    if (i == s.length()) {
      return true;
    }

    for (int j = 0; j < 10; j++) {
      if (s.charAt(i) == vogais[j]) {
        return allVogal(s, i + 1);
      }
    }

    return false;
  }

  public static boolean allCons(String s, int i) {
    char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

    if (i == s.length()) {
      return true;
    }

    for (int j = 0; j < 10; j++) {
      if (s.charAt(i) == vogais[j] || s.charAt(i) < 65) {
        return false;
      }
    }
    return allCons(s, i + 1);
  }

  public static boolean isInt(String s, int i) {
    int pon = 0;

    if (i == s.length()) {
      return true;
    }

    if (!(s.charAt(i) < 48 || s.charAt(i) > 57)) {
      
    }

    if (pon == 1) {
      return false;
    }

    return isInt(s, i + 1);
  }

  public static boolean isReal(String s) {
    int vir = 0, pon = 0;

    if (!(isInt(s, 0))) {
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == ',') {
          vir++;
          if (vir > 1) {
            return false;
          }
        } else if (s.charAt(i) == '.') {
          pon++;
          if (pon > 1) {
            if (s.charAt(i - 1) < 48 || s.charAt(i - 1) > 57) {
              return false;
            } else if (s.charAt(i - 2) < 48 || s.charAt(i - 2) > 57) {
              return false;
            } else if (s.charAt(i - 3) < 48 || s.charAt(i - 3) > 57) {
              return false;
            } else if (s.charAt(i - 4) != '.') {
              return false;
            }
          } else if (vir > 0) {
            return false;
          }
        } else if (s.charAt(i) < 48 || s.charAt(i) > 57) {
          return false;
        }
      }

      if (vir == 1) {
        return true;
      } else if (pon > 1) {
        return false;
      } else {
        return true;
      }
    }

    return true;
  }
}
