class Celula {

  public String assassino; // assassino inserido na celula.
  int assassinatos;
  public Celula prox; // Aponta a celula prox.

  /**
   * Construtor da classe.
   */
  public Celula() {
    this(null);
  }

  public Celula(String assassino) {
    this.assassino = assassino;
    this.assassinatos = 0;
    this.prox = null;
  }
}

class Lista {

  private Celula primeiro;
  private Celula ultimo;

  /**
   * Construtor da classe que cria uma lista sem assassinos (somente no cabeca).
   */
  public Lista() {
    primeiro = new Celula();
    ultimo = primeiro;
  }

  /**
   * Insere um assassino na primeira posicao da lista.
   * @param s assassino a ser inserida.
   */
  public void inserirInicio(String x) {
    Celula tmp = new Celula(x);
    tmp.prox = primeiro.prox;
    primeiro.prox = tmp;
    if (primeiro == ultimo) {
      ultimo = tmp;
    }
    tmp = null;
  }

  /**
   * Insere um assassino na ultima posicao da lista.
   * @param s assassino a ser inserida.
   */
  public void inserirFim(String x) {
    ultimo.prox = new Celula(x);
    ultimo = ultimo.prox;
  }

  /**
   * Remove um assassino da primeira posicao da lista.
   * @return assassino a ser removida.
   * @throws Exception Se a lista nao contiver assassinos.
   */
  public String removerInicio() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    Celula tmp = primeiro;
    primeiro = primeiro.prox;
    String resp = primeiro.assassino;
    tmp.prox = null;
    tmp = null;
    return resp;
  }

  /**
   * Remove um assassino da ultima posicao da lista.
   * @return assassino a ser removida.
   * @throws Exception Se a lista nao contiver assassinos.
   */
  public String removerFim() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    // Caminhar ate a penultima celula:
    Celula i;
    for (i = primeiro; i.prox != ultimo; i = i.prox);

    String resp = ultimo.assassino;
    ultimo = i;
    i = ultimo.prox = null;

    return resp;
  }

  /**
   * Insere um assassino em uma posicao especifica considerando que o
   * primeiro assassino valido esta na posicao 0.
   * @param s assassino a ser inserida.
   * @param pos int posicao da insercao.
   * @throws Exception Se <code>posicao</code> invalida.
   */
  public void inserir(String x, int pos) throws Exception {
    int tamanho = tamanho();

    if (pos < 0 || pos > tamanho) {
      throw new Exception(
        "Erro ao inserir posicao (" +
        pos +
        " / tamanho = " +
        tamanho +
        ") invalida!"
      );
    } else if (pos == 0) {
      inserirInicio(x);
    } else if (pos == tamanho) {
      inserirFim(x);
    } else {
      // Caminhar ate a posicao anterior a insercao
      Celula i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);

      Celula tmp = new Celula(x);
      tmp.prox = i.prox;
      i.prox = tmp;
      tmp = i = null;
    }
  }

  /**
   * Remove um assassino de uma posicao especifica da lista
   * considerando que o primeiro assassino valido esta na posicao 0.
   * @param posicao Meio da remocao.
   * @return assassino a ser removida.
   * @throws Exception Se <code>posicao</code> invalida.
   */
  public String remover(int pos) throws Exception {
    String resp;
    int tamanho = tamanho();

    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    } else if (pos < 0 || pos >= tamanho) {
      throw new Exception(
        "Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!"
      );
    } else if (pos == 0) {
      resp = removerInicio();
    } else if (pos == tamanho - 1) {
      resp = removerFim();
    } else {
      // Caminhar ate a posicao anterior a insercao
      Celula i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);

      Celula tmp = i.prox;
      resp = tmp.assassino;
      i.prox = tmp.prox;
      tmp.prox = null;
      i = tmp = null;
    }

    return resp;
  }

  public void imprimir() {
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      MyIO.println(i.assassino + " " + i.assassinatos);
    }
  }

  /**
   * Procura um assassino e retorna se ele existe.
   * @param s assassino a pesquisar.
   * @return <code>true</code> se o assassino existir,
   * <code>false</code> em caso contrario.
   */
  public int pesquisar(String x) {
    int resp = -1, pos = 0;
    for (Celula i = primeiro.prox; i != null; i = i.prox, pos++) {
      if (i.assassino.compareTo(x) == 0) {
        resp = pos;
        i = ultimo;
      }
    }
    return resp;
  }

  public void contar(String x) {
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      if (i.assassino.compareTo(x) == 0) {
        i.assassinatos++;
        i = ultimo;
      }
    }
  }

  /**
   * Calcula e retorna o tamanho, em numero de assassinos, da lista.
   * @return resp int tamanho
   */
  public int tamanho() {
    int tamanho = 0;
    for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
    return tamanho;
  }

  public Celula getPos(int x) {
    Celula resp = new Celula();
    int pos = 0;
    for (Celula i = primeiro.prox; i != null; i = i.prox, pos++) {
      if (x == pos) {
        resp = i;
        i = ultimo;
      }
    }
    return resp;
  }

  /**
   * Troca o conteudo de duas posicoes do array
   * @param i int primeira posicao
   * @param j int segunda posicao
   */
  public void swap(int pos1, int pos2) {
    Celula i = getPos(pos1);
    Celula j = getPos(pos2);
    if (pos1 > pos2) {
      int tmp = pos1;
      pos1 = pos2;
      pos2 = tmp;
    }

    try {
      remover(pos1);
      inserir(j.assassino, pos1);
      remover(pos2);
      inserir(i.assassino, pos2);
    } catch (Exception e) {}
  }

  void selecao() throws Exception {
    int n = tamanho();
    if (n == 0) {
      throw new Exception("ERRO");
    }
    boolean comp = false;
    Celula a, b;
    for (int i = 0; i < (n - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < n; j++) {
        a = getPos(menor);
        b = getPos(j);
        if (a.assassino.compareTo(b.assassino) > 0) {
          swap(menor, j);
        }
      }
    }
  }
}

public class Prova2Q1 {

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

  public static void main(String[] args) {
    MyIO.setCharset("UTF-8");

    String linha = MyIO.readLine();
    String[] split = new String[2];
    split = linha.split(" ");

    Lista listaAssassinos = new Lista();
    Lista listaAssassinados = new Lista();
    int pesquisa;

    String assassino, assassinado;
    while (!isFim(linha)) {
      assassino = split[0];
      assassinado = split[1];
      pesquisa = -1;
      try {
        listaAssassinados.inserirFim(assassinado); // insere o morto

        pesquisa = listaAssassinos.pesquisar(assassinado); // se quem morreu, for um assassino, remove ele da lista de assassinos
        if (pesquisa != -1) { // pesquisa retorna a posicao na lista do assassino assassinado
          listaAssassinos.remover(pesquisa);
        }
        if (listaAssassinados.pesquisar(assassino) == -1) {
          if (listaAssassinos.pesquisar(assassino) == -1) {
            listaAssassinos.inserirFim(assassino);
            listaAssassinos.contar(assassino);
          } else {
            listaAssassinos.contar(assassino);
          }
        }
      } catch (Exception e) {
        //
      }
      linha = MyIO.readLine();
      split[0] = "";
      split[1] = "";
      split = linha.split(" ");
    }
    MyIO.println("HALL OF MURDERERS");
    try {
      listaAssassinos.selecao();
    } catch (Exception e) {}
    listaAssassinos.imprimir();
  }
}
