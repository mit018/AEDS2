class Celula {

  public String elemento; // Elemento inserido na celula.
  public Celula prox; // Aponta a celula prox.

  /**
   * Construtor da classe.
   */
  public Celula() {
    elemento = null;
    prox = null;
  }

  /**
   * Construtor da classe.
   * @param elemento int inserido na celula.
   */
  public Celula(String elemento) {
    this.elemento = elemento;
    this.prox = null;
  }
}

class Fila {

  private Celula primeiro;
  private Celula ultimo;

  /**
   * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
   */
  public Fila() {
    primeiro = new Celula();
    ultimo = primeiro;
  }

  /**
   * Insere elemento na fila (politica FIFO).
   * @param x String elemento a inserir.
   */
  public void inserir(String x) {
    ultimo.prox = new Celula(x);
    ultimo = ultimo.prox;
  }

  /**
   * Remove elemento da fila (politica FIFO).
   * @return Elemento removido.
   * @trhows Exception Se a fila nao tiver elementos.
   */
  public String remover() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover!");
    }

    Celula tmp = primeiro;
    primeiro = primeiro.prox;
    String resp = primeiro.elemento;
    tmp.prox = null;
    tmp = null;
    return resp;
  }

  /**
   * Mostra os elementos
   */
  public void mostrar() {
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      System.out.println(i.elemento);
    }
  }

  public void insereDuplicado(String pedido) {
    ultimo.prox = new Celula(pedido);
    ultimo = ultimo.prox;

    Celula tmp = new Celula(pedido);
    tmp.prox = primeiro.prox;
    primeiro.prox = tmp;
    if (primeiro == ultimo) {
      ultimo = tmp;
    }
    tmp = null;
  }
}

class Prova1Q3 {

  public static void processa(String s, Fila fila) {
    String linha = s;
    s = s.replaceAll("D ", "").replaceAll("I ", "");
    if (linha.charAt(0) == 'I') {
      fila.inserir(s);
    } else if (linha.charAt(0) == 'D') {
      fila.insereDuplicado(s);
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
    Fila fila = new Fila();

    String linha = MyIO.readLine();

    while (!isFim(linha)) {
      processa(linha, fila);
      linha = MyIO.readLine();
    }
    fila.mostrar();
  }
}
