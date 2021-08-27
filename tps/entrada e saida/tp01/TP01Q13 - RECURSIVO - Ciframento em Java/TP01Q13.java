class TP01Q13 {
    public static void main(String[] args) {
        String linha = MyIO.readLine();

        while(!(isFim(linha)))
        {
            String cifra = "";
            MyIO.println(ciframento(linha, cifra, 0));
            linha = MyIO.readLine();
        }

    }

    /**
     * Codifica a string com a cifra de cesar recursivamente
     * @param str string a ser cifrada
     * @param cifra string concatenada
     * @param i posicao do proximo caractere para ser codificado
     * @return string cifrada
     */
    public static String ciframento(String str, String cifra, int i){

        if(i == str.length() - 1){
            cifra += (char)(str.charAt(i) + 3);
            return cifra;
        }else{
            cifra += (char)(str.charAt(i) + 3);
        }
        return ciframento(str, cifra,i + 1);
        
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
