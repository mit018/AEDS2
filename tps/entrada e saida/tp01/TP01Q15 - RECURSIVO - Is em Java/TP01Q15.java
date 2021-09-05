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

      if (isInt(str, 0, 0)) {
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
    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  /**
   * Testa se a string por apenas vogais
   * 
   * @param s -> string a ser testada
   * @param i -> posicao da string
   * @return -> true ou false
   */
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

  /**
   * Testa se a string por apenas consoantes
   * 
   * @param s -> string a ser testada
   * @param i -> posicao da string
   * @return -> true ou false
   */
  public static boolean allCons(String s, int i) {
    char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

    if (i == s.length()) {
      return true;
    }

    for (int j = 0; j < 10; j++) {
      if (s.charAt(i) == vogais[j] || s.charAt(i) < 65) {
        return false; // se em alguma posicao for uma vogal ou for algo diferente de uma letra, nao eh
                      // uma consoante
      }
    }
    return allCons(s, i + 1);
  }

  /**
   * Testa se a string eh um inteiro
   * 
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean isInt(String s, int pon, int i) {
    if (i == s.length()) {
      return true;
    }

    if (s.charAt(i) < 48 || s.charAt(i) > 57) {
      if (s.charAt(i) == '.') {// se nao for um numero, pode ser um ponto
        pon++;
        if (pon > 1) {
          // se houver mais de um ponto, o segundo ponto deve ser precedido
          // de no minimo 3 numeros e outro ponto e logo depois devem havem outros 3
          // numeros (ex: 1.000.000 e nao 1.000.)
          if (s.charAt(i - 1) < 48 || s.charAt(i - 1) > 57) {
            return false;
          } else if (s.charAt(i - 2) < 48 || s.charAt(i - 2) > 57) {
            return false;
          } else if (s.charAt(i - 3) < 48 || s.charAt(i - 3) > 57) {
            return false;
          } else if (s.charAt(i - 4) != '.') {
            return false;
          } else if (s.length() < i + 4) {
            return false;
          } else if (s.charAt(i + 1) < 48 || s.charAt(i + 1) > 57) {
            return false;
          } else if (s.charAt(i + 2) < 48 || s.charAt(i + 2) > 57) {
            return false;
          } else if (s.charAt(i + 3) < 48 || s.charAt(i + 3) > 57) {
            return false;
          }
        }
      } else {
        return false;
      }
    }

    if (pon == 1) { // se houver apenas um ponto, nao eh inteiro (ex: 1.0)
      return false;
    }

    return isInt(s, pon, i + 1);
  }

  /**
   * Chama a funcao isReal com mais parametros
   * 
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean isReal(String s) {
    if (isInt(s, 0, 0)) {
      return true;
    } else {
      return isReal(s, 0, 0, 0);
    }
  }

  /**
   * Testa se a string eh um numero real
   * 
   * @param s   -> string a ser testada
   * @param i   -> posicao da string
   * @param vir -> contador de virgulas da string
   * @param pon -> contador de pontos da string
   * @return -> true ou false
   */
  public static boolean isReal(String s, int i, int vir, int pon) {

    if (i == s.length()) {
      if (vir == 1) {
        return true;
      } else if (pon > 1) {
        return false;
      }else if(pon == 1){
        return true;
      }
    }
    if (s.charAt(i) == ',') {
      vir++;
      if (vir > 1) { // se houver mais de uma virgula, nao eh real
        return false;
      }
    } else if (s.charAt(i) == '.') {
      pon++;
      if (pon > 1) { // se houver mais de um ponto, o segundo deve ser precedido de 3 numeros e outro
                     // ponto
        if (s.charAt(i - 1) < 48 || s.charAt(i - 1) > 57) {
          return false;
        } else if (s.charAt(i - 2) < 48 || s.charAt(i - 2) > 57) {
          return false;
        } else if (s.charAt(i - 3) < 48 || s.charAt(i - 3) > 57) {
          return false;
        } else if (s.charAt(i - 4) != '.') {
          return false;
        }
      } 
    } else if (s.charAt(i) < 48 || s.charAt(i) > 57) {// se nao for um numero nao eh real
      return false;
    }
    return isReal(s, i + 1, vir, pon);

  }
}
