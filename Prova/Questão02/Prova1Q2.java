class Celula {

  public String item;
  public Celula prox;

  public Celula(String valorItem, Celula proxCelula) {
    item = valorItem;
    prox = proxCelula;
  }

  public Celula(String valorItem) {
    item = valorItem;
    prox = null;
  }

  public Celula() {
    item = null;
    prox = null;
  }
}

class Pilha {

  private Celula topo;
  private Celula tras;

  public void empilha(String valorItem) {
    topo = new Celula(valorItem, topo);
  }

  public String desempilha() {
    String item = null;
    if (topo != null) {
      item = topo.item;
      topo = topo.prox;
    }
    return item;
  }

  public String peek() {
    return (topo != null) ? topo.item : null;
  }

  public Pilha() {
    topo = new Celula();
    tras = topo;
  }
}

public class Prova1Q2 {

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  public static void processa(String s, Pilha pilha) {
    int cont = 0;

    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case '(':
          cont++;
          break;
        case ')':
          if (cont < 1) {
            i = s.length();
          }
          cont--;
          break;
        default:
          break;
      }
    }
    if (cont != 0) {
      MyIO.println("INCORRETO");
    } else {
      MyIO.println("CORRETO");
    }
  }

  public static void main(String[] args) {
    Pilha pilha = new Pilha();
    String linha = MyIO.readLine();
    
    while (!isFim(linha)) {
      processa(linha, pilha);
      linha = MyIO.readLine();
    }
  }
}
