public class halley {

  public static void main(String[] args) {
    int ano = 0, passagem = 2062;

    ano = MyIO.readInt();

    while (ano != 0) {
      while (ano >= passagem) {
        passagem = passagem + 76;
      } // enquanto o ano for maior que o ano de passagem do cometa, soma-se 76

      MyIO.println(passagem);
      passagem = 2062; // reseta o ano de passagem para o primeiro depois de 2020
      ano = 0;
      ano = MyIO.readInt();
    }
  }
}
