class ExemploMyIO {
   public static void main (String[] args){
      String str;
      int inteiro;
      double real;
      char caractere;

      System.out.println("Entre com uma palavra: ");
      str = MyIO.readString();
      System.out.println("Entre com um inteiro: ");
      inteiro = MyIO.readInt();
      System.out.println("Entre com um real: ");
      real = MyIO.readDouble();
      System.out.println("Entre com um caractere: ");
      caractere = MyIO.readChar();

      System.out.println("Sua string: " + str);
      System.out.println("Seu inteiro: " + inteiro);
      System.out.println("Seu real: " + real);
      System.out.println("Seu caractere: " + caractere);
   }
}
