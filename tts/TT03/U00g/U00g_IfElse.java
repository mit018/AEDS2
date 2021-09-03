public class U00g_IfElse {

  public static void main(String[] args) {
    //triangulo();
    //menorMaior();
    //maior();
    //operacoes();
    //futebol();
    //emprestimo();
    //cubica();
    //menorMaior10();
  }

  public static void triangulo() {
    int l1 = MyIO.readInt(), l2 = MyIO.readInt(), l3 = MyIO.readInt();
    if (l1 == l2 && l2 == l3) {
      MyIO.println("Triangulo Equilatero");
    } else if (
      (l1 == l2 && l2 != l3) || (l1 == l3 && l1 != l2) || (l2 == l3 && l2 != l1)
    ) {
      MyIO.println("Triangulo Isosceles");
    } else if (l1 != l2 && l1 != l3) {
      MyIO.println("Triangulo Escaleno");
    }
  }

  public static void menorMaior() {
    int num[] = new int[3], maior = 0, menor = 0;

    for (int i = 0; i < 3; i++) {
      num[i] = MyIO.readInt();
      if (num[i] > maior) {
        maior = num[i];
      }
    }

    menor = num[0];
    for (int i = 0; i < 3; i++) {
      if (num[i] < menor) {
        menor = num[i];
      }
    }

    MyIO.println("Maior = " + maior);
    MyIO.println("Menor = " + menor);
  }

  public static void maior() {
    int num[] = new int[10], maior = 0;

    for (int i = 0; i < 10; i++) {
      num[i] = MyIO.readInt();
      if (num[i] > maior) {
        maior = num[i];
      }
    }

    MyIO.println("Maior = " + maior);
  }

  public static void operacoes() {
    float num1 = MyIO.readFloat(), num2 = MyIO.readFloat();

    if (num1 > 45 || num2 > 45) {
      MyIO.println(num1 + num2);
    } else if (num1 > 20 && num2 > 20) {
      if (num1 > num2) {
        MyIO.println(num1 - num2);
      } else {
        MyIO.println(num2 - num1);
      }
    } else if ((num1 < 10 && num2 > 0) || (num2 > 10 && num1 > 0)) {
      MyIO.println(num1 / num2);
    } else {
      MyIO.println("Laura Xavier");
    }
  }

  public static void futebol() {
    int mandante = MyIO.readInt(), visitante = MyIO.readInt();
    if (mandante == visitante) {
      MyIO.println("Empate!");
    } else if (mandante > visitante) {
      MyIO.println("Vencedor: Mandante!");
    } else {
      MyIO.println("Vencedor: Visitante!");
    }
  }

  public static void emprestimo() {
    float salario = MyIO.readFloat(), prestacao = MyIO.readFloat();

    if (salario * 0.4 < prestacao) {
      MyIO.println("Emprestimo nao concedido.");
    } else {
      MyIO.println("Emprestimo concedido.");
    }
  }

  public static void cubica() {
    float num1 = MyIO.readFloat(), num2 = MyIO.readFloat(), aux;

    if (num1 > num2) {
      aux = num1;
      num1 = num2;
      num2 = aux;
    }

    MyIO.println("Raiz cubica = " + Math.pow(num1, 3));
    MyIO.println("Logaritmo = " + Math.log10(num1) / Math.log(num2));
  }

  public static void menorMaior10() {
    int num[] = new int[10], maior = 0, menor = 0;

    for (int i = 0; i < 10; i++) {
      num[i] = MyIO.readInt();
      if (num[i] > maior) {
        maior = num[i];
      }
    }
  }
}
