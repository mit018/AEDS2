class TP01Q03 {
    public static void main(String[] args) {
        String linha = MyIO.readLine();

        while(!(isFim(linha)))
        {
            MyIO.println(ciframento(linha));
            linha = MyIO.readLine();
        }

    }

    /**
     * Codifica a string com a cifra de cesar
     * @param str -> string para ser cifrada
     * @return -> string cifrada
     */
    public static String ciframento(String str){

        String cifra = "";

        for(int i = 0; i < str.length(); i++){
            
            cifra += (char)(str.charAt(i) + 3); // concatena o caractere somado a 3
        }
        return cifra;
    }

    /**
     * Testa se a string eh "FIM"
     * @param s -> linha a ser testada
     * @return -> boolean para a comparacao com "FIM"
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
