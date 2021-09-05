class TP01Q06 {

  public static void main(String[] args) {
    String str = MyIO.readLine();

    while (!(isFim(str))) {
      if (allVogal(str)) {
        MyIO.print("SIM");
      } else {
        MyIO.print("NAO");
      }

      if (allCons(str)) {
        MyIO.print(" SIM");
      } else {
        MyIO.print(" NAO");
      }

      if (isInt(str)) {
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
  /**
     * 
     * @param s -> linha a ser testada
     * @return -> se eh igual a "FIM"
     */
  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  /**
   * Testa se a string por apenas vogais
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean allVogal(String s) {
    char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
    int teste = 0;

    for (int i = 0; i < s.length(); i++) {
      teste = 0;
      for (int j = 0; j < 10; j++) {
        if (s.charAt(i) == vogais[j]) {
          j = 10;
          teste = 1;
        }
      } // for para comparar todos os caracteres com todas as vogais no vetor 'vogais'
      if (teste == 0) { // se o teste permaneceu 0, significa que o caractere na posicao nao entrou na condicao em nenhuma vogal
        return false;
      }
    }
    return true;
  }

  /**
   * Testa se a string por apenas consoantes
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean allCons(String s) {
    char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < 10; j++) {
        if (s.charAt(i) == vogais[j] || s.charAt(i) < 65) {
          return false; // se em alguma posicao for uma vogal ou for algo diferente de uma letra, nao eh uma consoante
        }
      }
    }
    return true;
  }

  /**
   * Testa se a string eh um inteiro
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean isInt(String s) {
    int pon = 0; // contador de pontos

    for (int i = 0; i < s.length(); i++) { // percorre todos os caracteres
      if (s.charAt(i) < 48 || s.charAt(i) > 57) {
        if (s.charAt(i) == '.') {// se nao for um numero, pode ser um ponto
          pon++;
          if (pon > 1) {
          // se houver mais de um ponto, o segundo ponto deve ser precedido 
          //de no minimo 3 numeros e outro ponto e logo depois devem havem outros 3 numeros (ex: 1.000.000 e nao 1.000.)
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
    }

    if (pon == 1) { // se houver apenas um ponto, nao eh inteiro (ex: 1.0)
      return false;
    }

    return true;
  }

  /**
   * Testa se a string eh um numero real
   * @param s -> string a ser testada
   * @return -> true ou false
   */
  public static boolean isReal(String s) {
    int vir = 0, pon = 0; // contadores de virgulas e pontos

    if (!(isInt(s))) { // testa se eh um inteiro, se sim, tambem eh um real
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == ',') {
          vir++;
          if (vir > 1) { // se houver mais de uma virgula, nao eh real
            return false;
          }
        } else if (s.charAt(i) == '.') {
          pon++;
          if (pon > 1) { // se houver mais de um ponto, o segundo deve ser precedido de 3 numeros e outro ponto
            if (s.charAt(i - 1) < 48 || s.charAt(i - 1) > 57) {
              return false;
            } else if (s.charAt(i - 2) < 48 || s.charAt(i - 2) > 57) {
              return false;
            } else if (s.charAt(i - 3) < 48 || s.charAt(i - 3) > 57) {
              return false;
            } else if (s.charAt(i - 4) != '.') {
              return false;
            }
          } else if (vir > 0) { // se houver um ponto e nao houver nenhum virgula, nao eh real (passaria como inteiro inicialmente)
            return false;
          }
        } else if (s.charAt(i) < 48 || s.charAt(i) > 57) { // se nao for um numero nao eh real
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
