class Celula {

  public int chegada; // Elemento inserido na celula.
  public int saida; // Elemento inserido na celula.
  public Celula prox; // Aponta a celula prox.

  /**
   * Construtor da classe.
   */
  public Celula() {
    this(0, 0);
  }

  /**
   * Construtor da classe.
   * @param elemento int inserido na celula.
   */
  public Celula(int chegada, int saida) {
    this.chegada = chegada;
    this.saida = saida;
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
  public void inserir(int c, int s, int max) throws Exception {
    for (Celula i = topo; i != null; i = i.prox) {
      if (i.saida < s) {
        throw new Exception();
      }
      if (c >= topo.saida) {
        remover();
      }
      if (getQuant() < max) {
        remover();
      }
    }

    Celula tmp = new Celula(c, s);
    tmp.prox = topo;
    topo = tmp;
    tmp = null;
  }

  public int getQuant() {
    int resp = 0;
    for (Celula i = topo; i != null; i = i.prox) {
      resp++;
    }
    return resp;
  }

  public int removerProx() throws Exception {
    if (topo == null) {
      throw new Exception("Erro ao remover!");
    }
    int resp = topo.chegada;
    Celula tmp = topo;
    topo = topo.prox;
    tmp.prox = null;
    tmp = null;
    return resp;
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
    int resp = topo.chegada;
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
    System.out.print("[ ");
    for (Celula i = topo; i != null; i = i.prox) {
      System.out.print(i.chegada + "/" + i.saida + " ");
    }
    System.out.println("] ");
  }

  public void mostraPilha() {
    mostraPilha(topo);
  }

  private void mostraPilha(Celula i) {
    if (i != null) {
      mostraPilha(i.prox);
      System.out.println(i.chegada + "/" + i.saida + " ");
    }
  }
}

public class P3Q02 {

  public static void main(String[] args) {
    // String linha = readLine
    int motoristas = MyIO.readInt();
    int carros = MyIO.readInt();

    int chegada, saida;
    boolean resp = true;

    while (carros != 0 && motoristas != 0) {
      Pilha estacionamento = new Pilha();

      resp = true;

      for (int i = 0; i < motoristas; i++) {
        chegada = MyIO.readInt();
        saida = MyIO.readInt();

        try {
          estacionamento.inserir(chegada, saida, carros);
        } catch (Exception e) {
          resp = false;
        }
      }
      motoristas = MyIO.readInt();
      carros = MyIO.readInt();

      if (resp == false) {
        MyIO.println("Nao");
      } else {
        MyIO.println("Sim");
      }
    }
  }
}
