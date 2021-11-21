class Celula {

  public int elemento;
  public Celula inf, sup, esq, dir;

  public Celula() {
    this(0, null, null, null, null);
  }

  public Celula(int elemento) {
    this(elemento, null, null, null, null);
  }

  public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
    this.elemento = elemento;
    this.inf = inf;
    this.sup = sup;
    this.esq = esq;
    this.dir = dir;
  }
}

class Matriz {

  private Celula inicio, aux, aux2;
  private int linha, coluna;

  public Matriz() {
    this(3, 3);
  }

  public Matriz(int linha, int coluna) {
    this.linha = linha;
    this.coluna = coluna;

    for (int i = 0; i < linha; i++) {
      for (int j = 0; j < coluna; j++) {
        if (i == 0) {
          if (j == 0) {
            this.inicio = new Celula();
            aux = inicio;
            aux2 = inicio;
          } else {
            Celula celula = new Celula();
            aux.dir = celula;
            aux.dir.esq = aux;
            aux = aux.dir;
          }
        } else {
          Celula celula = new Celula();
          aux.inf = celula;
          celula.sup = aux;
          if (j != 0) {
            aux.esq.inf.dir = celula;
            celula.esq = aux.esq.inf;
          }
          if (aux.dir != null) {
            aux = aux.dir;
          }
        }
      }
      if (i != 0) {
        aux2 = aux2.inf;
      }
      aux = aux2;
    }
  }

  public void preencher() {
    Celula coluna = new Celula(), linha = inicio;

    for (int i = 0; i < this.linha; i++) {
      coluna = linha;
      for (int j = 0; j < this.coluna; j++) {
        coluna.elemento = MyIO.readInt();
        coluna = coluna.dir;
      }
      linha = linha.inf;
    }
  }

  public void preencher(int[][] n) {
    Celula coluna = new Celula(), linha = inicio;
    int x = 0;

    for (int i = 0; i < this.linha; i++) {
      coluna = linha;
      for (int j = 0; j < this.coluna; j++) {
        coluna.elemento = n[i][j];
        coluna = coluna.dir;
      }
      linha = linha.inf;
    }
  }

  public Matriz soma(Matriz m) {
    Matriz resp = null;

    if (this.linha == m.linha && this.coluna == m.coluna) {
      resp = new Matriz(this.linha, this.coluna);
      int[][] num = new int[resp.linha][resp.coluna];

      for (int i = 0; i < linha; i++) {
        for (int j = 0; j < coluna; j++) {
          num[i][j] = this.getElemento(i, j) + m.getElemento(i, j);
        }
      }
      resp.preencher(num);
    }

    return resp;
  }

   public Matriz multiplicacao(Matriz m) {
      Matriz resp = null;
      if (this.coluna == m.linha) {
         resp = new Matriz(this.linha, m.coluna);
         int[][] num = new int[resp.linha][resp.coluna];
         for (int i = 0; i < resp.linha; i++) {
            for (int j = 0; j < resp.coluna; j++) {
               num[i][j] = 0;
            }
         }
    
      for (int i = 0; i < resp.linha; i++) {
        for (int j = 0; j < resp.coluna; j++) {
          for (int k = 0; k < this.coluna; k++) {
            num[i][j] += this.getElemento(i, k) * m.getElemento(k, j);
          }
        }
      }
      resp.preencher(num);
      }

    
      return resp;
   }

  public boolean isQuadrada() {
    return (this.linha == this.coluna);
  }

  public void mostrarDiagonalPrincipal() {
    if (isQuadrada() == true) {
      for (int i = 0, j = 0; i < this.linha && j < this.coluna; i++, j++) {
        MyIO.print(getElemento(i, j) + " ");
      }
    }
  }

  public void mostrarDiagonalSecundaria() {
    if (isQuadrada() == true) {
      for (int i = 0, j = (this.coluna - 1); i >= 0 && j >= 0; i++, j--) {
        MyIO.print(getElemento(i, j) + " ");
      }
    }
  }

  public int getElemento(int pos1, int pos2) {
    int resp = 0;
    Celula coluna = new Celula(), linha = inicio;
    for (int j = 0; j < this.linha; j++) {
      coluna = linha;
      for (int i = 0; i < this.coluna; i++) {
        if (j == pos1 && i == pos2) {
          resp = coluna.elemento;
        }
        coluna = coluna.dir;
      }
      linha = linha.inf;
    }

    return resp;
  }

  public void mostrar() {
    Celula coluna = new Celula(), linha = inicio;
    for (int j = 0; j < this.linha; j++) {
      coluna = linha;
      for (int i = 0; i < this.coluna; i++) {
        MyIO.print(coluna.elemento);
        coluna = coluna.dir;
        MyIO.print(" ");
      }
      linha = linha.inf;

      MyIO.println("");
    }
  }
}

public class TP03Q17 {

  public static void main(String[] args) {
    MyIO.setCharset("UTF-8");
    Matriz m1, m2, multiplicacao, soma;
    int casos = MyIO.readInt();
    int caso = 1;

    while (caso <= casos) {
      m1 = new Matriz(MyIO.readInt(), MyIO.readInt());
      m1.preencher();
      m2 = new Matriz(MyIO.readInt(), MyIO.readInt());
      m2.preencher();

      m1.mostrarDiagonalPrincipal();
      MyIO.println("");
      m1.mostrarDiagonalSecundaria();
      MyIO.println("");
      soma = m1.soma(m2);
      soma.mostrar();
      multiplicacao = m1.multiplicacao(m2);
      multiplicacao.mostrar();

      caso++;
    }
  }
}
