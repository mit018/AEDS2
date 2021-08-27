class TP01Q01 {

   /**
    * compara a string com "FIM"
    * @param s -> linha lida
    * @return -> retorna se eh igual a "FIM"
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
   * Testa se eh um palindromo
   * @param str -> string para testar
   * @param n -> ultima posicao
   */
  public static void palindromo(String str, int n) {
    int x = 0, cont = 0;

    while (x < str.length()) {
      if (str.charAt(n) == str.charAt(x)) { // compara a ultima com a primeira posiçao, a penultima com a segunda, etc
        n--;
        x++;
      }else{
        x = str.length() + 1; // se encontrar alguma letra diferente, encerra a repeticao
      }
    }

    if (x == str.length()) {
      MyIO.println("SIM");
    } else {
      MyIO.println("NAO");
    }
  }

  public static void main(String[] args) {
    
    String[] entrada = new String[1000]; // vetor de strings para guardar até 1000 linhas

    int numEntrada = 0;
    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--; //Desconsiderar ultima linha contendo a palavra FIM

    for (int i = 0; i < numEntrada; i++) {
      palindromo(entrada[i], entrada[i].length() - 1);
    }
  }
}
