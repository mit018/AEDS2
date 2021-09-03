class palindromo {
    public static void main(String[] args) {
        String[] entrada = new String[1000];
    
        int numEntrada = 0;
        //Leitura da entrada padrao
        do {
          entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; //Desconsiderar ultima linha contendo a palavra FIM
    
        for (int i = 0; i < numEntrada; i++) {
            if(Palindromo(entrada[i], entrada[i].length() - 1, 0)){
                MyIO.println("SIM");
            }else{
                MyIO.println("NAO");
            }
        }
      }
    
      /**
       * Testa se a string eh um palindromo recursivamente
       * @param str string a ser testada
       * @param f posicao final
       * @param i posicao inicial
       * @return true ou false
       */
      public static boolean Palindromo(String str, int f, int i) {
        
        if(f == (str.length()/2) - 1){ // se chegar na posicao (metade + 1), deve parar
            return true;
        }else if(str.charAt(f) == str.charAt(i)){
            return Palindromo(str, f - 1, i + 1);
        }
    
        return false;
      }
    
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
    
  }
  