class Celula {

  public int elemento; // Elemento inserido na celula.
  public int menor;
  public Celula prox; // Aponta a celula prox.

  /**
   * Construtor da classe.
   */
  public Celula() {
    this(0);
  }

  /**
   * Construtor da classe.
   * @param elemento int inserido na celula.
   */
  public Celula(int elemento) {
    this.elemento = elemento;
    this.menor = elemento;
    this.prox = null;
  }
}

class Pilha {

  private Celula topo;

  /**
   * Construtor da classe que cria uma fila sem elementos.
   */
  public Pilha() {
    topo = null;
  }

  /**
   * Insere elemento na pilha (politica FILO).
   *
   * @param x int elemento a inserir.
   */
  public void inserir(int x) {
    Celula tmp = new Celula(x);
    tmp.prox = topo;
    topo = tmp;
    tmp = null;
  }

  /**
   * Remove elemento da pilha (politica FILO).
   *
   * @return Elemento removido.
   * @trhows Exception Se a sequencia nao contiver elementos.
   */
  public int remover() throws Exception {
    if (topo == null) {
      throw new Exception("Erro ao remover!");
    }
    int resp = topo.elemento;
    Celula tmp = topo;
    topo = topo.prox;
    tmp.prox = null;
    tmp = null;
    return resp;
  }

  /**
   * Mostra os elementos separados por espacos, comecando do topo.
   */
  public void mostrar() {
    for (Celula i = topo; i != null; i = i.prox) {
      System.out.println(i.elemento);
    }
  }

  public int getMin() {
    int min = topo.elemento;
    for (Celula i = topo.prox; i != null; i = i.prox) {
      if (i.elemento < min) min = i.elemento;
    }
    return min;
  }

  public void mostraPilha() {
    mostraPilha(topo);
  }

  private void mostraPilha(Celula i) {
    if (i != null) {
      mostraPilha(i.prox);
      System.out.println("" + i.elemento);
    }
  }

  public int peek() {
    return topo.elemento;
  }
}

public class Prova1Q1 {

  public static void processa(String s, Pilha pilha) {
    String[] split = new String[2];
    split = s.split(" ");

    if (s.charAt(0) == 'I') {
      pilha.inserir(Integer.parseInt(split[1]));
    } else if (s.charAt(0) == 'T') {
      MyIO.println(pilha.peek());
    } else if (s.charAt(0) == 'M') {
      MyIO.println(pilha.getMin());
    } else if (s.charAt(0) == 'D') {
      try {
        MyIO.println(pilha.remover());
      } catch (Exception erro) {
        System.out.println(erro.getMessage());
      }
    }
  }

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  public static void main(String[] args) {
    Pilha pilha = new Pilha();
    String linha = MyIO.readLine();

    while (!isFim(linha)) {
      processa(linha, pilha);
      linha = MyIO.readLine();
    }

    pilha.mostrar();
  }
}
