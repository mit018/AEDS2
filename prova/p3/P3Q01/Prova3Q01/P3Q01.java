class Celula {

  public int elemento;
  public Celula prox;

  public Celula() {
    this.elemento = 0;
    this.prox = null;
  }

  public Celula(int x) {
    this.elemento = x;
    this.prox = null;
  }
}

class No {

  public int elemento;
  public No esq;
  public No dir;

  public No() {
    this.elemento = 0;
    this.esq = null;
    this.dir = null;
  }

  public No(int elemento) {
    this.elemento = elemento;
    this.esq = null;
    this.dir = null;
  }

  public No(int elemento, No esq, No dir) {
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

class Fila {

  private Celula primeiro;
  private Celula ultimo;

  public Fila() {
    primeiro = new Celula();
    ultimo = primeiro;
  }


  public void inserir(int x) {
    ultimo.prox = new Celula(x);
    ultimo = ultimo.prox;
  }

  public int remover() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    // Caminhar ate a penultima celula:
    Celula i;
    for (i = primeiro; i.prox != ultimo; i = i.prox);

    int resp = ultimo.elemento;
    ultimo = i;
    i = ultimo.prox = null;

    return resp;
  }

  public void mostrar() throws Exception {
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      System.out.print(i.elemento + " ");
    }
  }
}

class ArvoreBinaria {

  public No raiz;

  public ArvoreBinaria() {
    raiz = null;
  }

  public void inserir(int x) throws Exception {
    raiz = inserir(x, raiz);
  }

  private No inserir(int x, No i) throws Exception {
    if (i == null) {
      i = new No(x);
    } else if (x < i.elemento) {
      i.esq = inserir(x, i.esq);
    } else if (x > i.elemento) {
      i.dir = inserir(x, i.dir);
    } else {
      throw new Exception("Erro ao inserir!");
    }

    return i;
  }

  public boolean pesquisar(int elemento) {
    return pesquisar(elemento, raiz);
  }

  public boolean pesquisar(int elemento, No no) {
    boolean resp;

    if (no == null) {
      resp = false;
    } else if (elemento == no.elemento) {
      resp = true;
    } else if (elemento < no.elemento) {
      resp = pesquisar(elemento, no.esq);
    } else {
      resp = pesquisar(elemento, no.dir);
    }

    return resp;
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharDecrescente() {
    System.out.print("[ ");
    caminharDecrescente(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharDecrescente(No i) {
    if (i != null) {
      caminharDecrescente(i.dir); // Elementos da esquerda.
      System.out.print(i.elemento + " "); // Conteudo do no.
      caminharDecrescente(i.esq); // Elementos da direita.
    }
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharCentral() {
    System.out.print("[ ");
    caminharCentral(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharCentral(No i) {
    if (i != null) {
      caminharCentral(i.esq); // Elementos da esquerda.
      System.out.print(i.elemento + " "); // Conteudo do no.
      caminharCentral(i.dir); // Elementos da direita.
    }
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharPre() {
    System.out.print("[ ");
    caminharPre(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharPre(No i) {
    if (i != null) {
      System.out.print(i.elemento + " "); // Conteudo do no.
      caminharPre(i.esq); // Elementos da esquerda.
      caminharPre(i.dir); // Elementos da direita.
    }
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharPos() {
    System.out.print("[ ");
    caminharPos(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharPos(No i) {
    if (i != null) {
      caminharPos(i.esq); // Elementos da esquerda.
      caminharPos(i.dir); // Elementos da direita.
      System.out.print(i.elemento + " "); // Conteudo do no.
    }
  }

  public int getAltura() {
    return getAltura(raiz, 0);
  }

  public int getAltura(No i, int altura) {
    if (i == null) {
      altura--;
    } else {
      int alturaEsq = getAltura(i.esq, altura + 1);
      int alturaDir = getAltura(i.dir, altura + 1);
      altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
    }
    return altura;
  }

  public void inserirNasFilas(Fila Filas[]) {
    inserirNasFilas(Filas, raiz);
  }

  public void inserirNasFilas(Fila Filas[], No no) {
    if (no != null) {
      Filas[getProfundidade(no.elemento)].inserir(no.elemento);
      inserirNasFilas(Filas, no.esq); // Elementos da esquerda.
      inserirNasFilas(Filas, no.dir); // Elementos da direita.
      
    }
  }

  public int getProfundidade() {
    return getAltura();
  }

  public int getProfundidade(int x) {
    return getProfundidade(raiz, x, 0);
  }

  public int getProfundidade(No i, int x, int profundidade) {
    int resp;
    if (i != null) {
      if (i.elemento == x) {
        return profundidade;
      } else {
        resp = getProfundidade(i.dir, x, profundidade + 1);

        if (resp >= 0) {
          return resp;
        } else {
          return getProfundidade(i.esq, x, profundidade + 1);
        }
      }
    } else {
      return -1;
    }
  }
}

public class P3Q01 {

  public static void main(String[] args) {
    int casos = 0;
    int quantNum, num, altura;
    casos = MyIO.readInt();

    ArvoreBinaria[] arvore = new ArvoreBinaria[casos];
    for (int i = 0; i < casos; i++) {
      arvore[i] = new ArvoreBinaria();

      quantNum = MyIO.readInt();

      for (int j = 0; j < quantNum; j++) {
        num = MyIO.readInt();
        try {
          arvore[i].inserir(num);
        } catch (Exception e) {}
      }

      altura = arvore[i].getAltura();
      altura++;

      Fila[] Filas = new Fila[altura];

      for (int j = 0; j < altura; j++) {
        Filas[j] = new Fila();
      }

      arvore[i].inserirNasFilas(Filas);
       MyIO.println("Case " + (i + 1) + ":");
      for (int j = 0; j < altura; j++) {
        try {
          Filas[j].mostrar();
        } catch (Exception e) {}
      }
      MyIO.println("");
      MyIO.println("");
    }
  }
}
