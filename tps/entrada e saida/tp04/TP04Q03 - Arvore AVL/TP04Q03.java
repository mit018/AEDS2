import java.util.Date;
import java.util.Locale;

class Comparacoes {

  public static int cont;
}

class Series {

  private String nome;
  private String formato;
  private String duracao;
  private String pais;
  private String idioma;
  private String emissora;
  private String transmissao;
  private int temporadas;
  private int episodios;

  public void imprimir() {
    MyIO.println(
      this.nome +
      " " +
      this.formato +
      " " +
      this.duracao +
      " " +
      this.pais +
      " " +
      this.idioma +
      " " +
      this.emissora +
      " " +
      this.transmissao +
      " " +
      this.temporadas +
      " " +
      this.episodios
    );
  }

  public void setSerie(int padrao, String conteudo) {
    switch (padrao) {
      case 0:
        this.setNome(conteudo);
        break;
      case 1:
        this.setFormato(conteudo);
        break;
      case 2:
        this.setDuracao(conteudo);
        break;
      case 3:
        this.setPais(conteudo);
        break;
      case 4:
        this.setIdioma(conteudo);
        break;
      case 5:
        this.setEmissora(conteudo);
        break;
      case 6:
        this.setTransmissao(conteudo);
        break;
      case 7:
        this.setTemporadas(conteudo);
        break;
      case 8:
        this.setEpisodios(conteudo);
        break;
      default:
        break;
    }
  }

  public void ler(String arq) {
    String linha = "", conteudo = "";
    int pad = 0;
    String[] padrao = setPadroes();

    Arq.openRead("/tmp/series/" + arq, "UTF-8");

    linha = Arq.readLine();

    while (this.getEpisodios() == 0) {
      if (linha.contains(padrao[pad])) {
        if (pad != 0) {
          conteudo =
            Arq
              .readLine()
              .replaceAll("\\<.*?\\>", "")
              .replaceAll("&#160;", "")
              .replaceAll("&nbsp;", "")
              .trim(); // &#160;
        } else {
          conteudo =
            linha
              .replaceAll("\\<.*?\\>", "")
              .replaceAll("\\(.*?\\)", "")
              .replaceAll(" – Wikipédia, a enciclopédia livre", "")
              .trim();
        }
        setSerie(pad, conteudo);
        pad++;
      }
      linha = Arq.readLine();
    }

    Arq.close();
  }

  public Series() {
    this.setNome("0");
    this.setFormato("0");
    this.setDuracao("0");
    this.setPais("0");
    this.setIdioma("0");
    this.setEmissora("0");
    this.setTransmissao("0");
    this.setTemporadas("0");
    this.setEpisodios("0");
  }

  public Series(
    String nome,
    String formato,
    String duracao,
    String pais,
    String idioma,
    String emissora,
    String transmissao,
    int temporadas,
    int episodios
  ) {
    this.setNome(nome);
    this.setFormato(formato);
    this.setDuracao(duracao);
    this.setPais(pais);
    this.setIdioma(idioma);
    this.setEmissora(emissora);
    this.setTransmissao(transmissao);
    this.temporadas = temporadas;
    this.episodios = episodios;
  }

  public Series clone() {
    Series clone = new Series(
      this.nome,
      this.formato,
      this.duracao,
      this.pais,
      this.idioma,
      this.emissora,
      this.transmissao,
      this.temporadas,
      this.episodios
    );

    return clone;
  }

  public static String[] setPadroes() {
    String[] padroes = new String[9];

    padroes[0] = "<title>";
    padroes[1] = "Formato"; // proxima linha
    padroes[2] = "Duração"; // proxima linha
    padroes[3] = "País de origem"; // proxima linha
    padroes[4] = "Idioma original"; // proxima linha
    padroes[5] = "Emissora de televisão original"; // proxima linha
    padroes[6] = "Transmissão original"; // proxima linha
    padroes[7] = "N.º de temporadas"; // proxima linha
    padroes[8] = "N.º de episódios"; // proxima linha

    return padroes;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public void setDuracao(String duracao) {
    this.duracao = duracao;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public void setEmissora(String emissora) {
    this.emissora = emissora;
  }

  public void setTransmissao(String transmissao) {
    this.transmissao = transmissao;
  }

  public void setTemporadas(String temporadas) {
    if (temporadas.indexOf(" ") > 0) {
      temporadas = temporadas.substring(0, temporadas.indexOf(" ")).trim();
    }
    this.temporadas = Integer.parseInt(temporadas);
  }

  public void setEpisodios(String episodios) {
    if (episodios.indexOf(" ") > 0) {
      episodios = episodios.substring(0, episodios.indexOf(" ")).trim();
    }
    this.episodios = Integer.parseInt(episodios);
  }

  public String getNome() {
    return this.nome;
  }

  public String getFormato() {
    return this.formato;
  }

  public String getDuracao() {
    return this.duracao;
  }

  public String getPais() {
    return this.pais;
  }

  public String getIdioma() {
    return this.idioma;
  }

  public String getEmissora() {
    return this.emissora;
  }

  public String getTransmissao() {
    return this.transmissao;
  }

  public int getTemporadas() {
    return this.temporadas;
  }

  public int getEpisodios() {
    return this.episodios;
  }
}

class No {

  public Series elemento; // Conteudo do no.
  public No esq, dir; // Filhos da esq e dir.
  public int nivel; // Numero de niveis abaixo do no

  /**
   * Construtor da classe
   * @param elemento Conteudo do no.
   */
  public No(Series elemento) {
    this(elemento, null, null, 1);
  }

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   * @param esq      No da esquerda.
   * @param dir      No da direita.
   */
  public No(Series elemento, No esq, No dir, int nivel) {
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
    this.nivel = nivel;
  }

  /**
   * Cálculo do número de níveis a partir de um vértice
   */
  public void setNivel() {
    this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
  }

  /**
   * Retorna o número de níveis a partir de um vértice
   * @param no nó que se deseja o nível.
   */
  public static int getNivel(No no) {
    return (no == null) ? 0 : no.nivel;
  }
}

class AVL {

  private No raiz; // Raiz da arvore.

  /**
   * Construtor da classe.
   */
  public AVL() {
    raiz = null;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(String x) {
    MyIO.print("raiz ");
    return pesquisar(x, raiz);
  }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  private boolean pesquisar(String x, No i) {
    boolean resp;
    if (i == null) {
      resp = false;
    } else if (x.equals(i.elemento.getNome())) {
      Comparacoes.cont++;
      resp = true;
    } else if (x.compareTo(i.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      MyIO.print("esq ");
      resp = pesquisar(x, i.esq);
    } else {
      MyIO.print("dir ");
      resp = pesquisar(x, i.dir);
    }
    return resp;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar2(String x) {
    return pesquisar2(x, raiz);
  }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  private boolean pesquisar2(String x, No i) {
    boolean resp;
    if (i == null) {
      resp = false;
    } else if (x.equals(i.elemento.getNome())) {
      Comparacoes.cont++;
      resp = true;
    } else if (x.compareTo(i.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      resp = pesquisar2(x, i.esq);
    } else {
      resp = pesquisar2(x, i.dir);
    }
    return resp;
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
      System.out.print(i.elemento.getNome() + " "); // Conteudo do no.
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
      System.out.print(i.elemento.getNome() + " "); // Conteudo do no.
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
      System.out.print(i.elemento.getNome() + " "); // Conteudo do no.
    }
  }

  /**
   * Metodo publico iterativo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserir(Series x) throws Exception {
    raiz = inserir(x, raiz);
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se o elemento existir.
   */
  private No inserir(Series x, No i) throws Exception {
    if (i == null) {
      i = new No(x);
    } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      i.esq = inserir(x, i.esq);
    } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
      Comparacoes.cont++;
      i.dir = inserir(x, i.dir);
    } else {
      throw new Exception("Erro ao inserir!");
    }
    return balancear(i);
  }

  /**
   * Metodo publico iterativo para remover elemento.
   * @param x Elemento a ser removido.
   * @throws Exception Se nao encontrar elemento.
   */
  public void remover(String x) throws Exception {
    raiz = remover(x, raiz);
  }

  /**
   * Metodo privado recursivo para remover elemento.
   * @param x Elemento a ser removido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se nao encontrar elemento.
   */
  private No remover(String x, No i) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover!");
    } else if (x.compareTo(i.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      i.esq = remover(x, i.esq);
    } else if (x.compareTo(i.elemento.getNome()) > 0) {
      Comparacoes.cont++;
      i.dir = remover(x, i.dir);
      // Sem no a direita.
    } else if (i.dir == null) {
      i = i.esq;
      // Sem no a esquerda.
    } else if (i.esq == null) {
      i = i.dir;
      // No a esquerda e no a direita.
    } else {
      i.esq = maiorEsq(i, i.esq);
    }
    return balancear(i);
  }

  private No balancear(No no) throws Exception {
    if (no != null) {
      int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
      // Se balanceada
      if (Math.abs(fator) <= 1) {
        no.setNivel();
        // Se desbalanceada para a direita
      } else if (fator == 2) {
        int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
        // Se o filho a direita tambem estiver desbalanceado
        if (fatorFilhoDir == -1) {
          no.dir = rotacionarDir(no.dir);
        }
        no = rotacionarEsq(no);
        // Se desbalanceada para a esquerda
      } else if (fator == -2) {
        int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
        // Se o filho a esquerda tambem estiver desbalanceado
        if (fatorFilhoEsq == 1) {
          no.esq = rotacionarEsq(no.esq);
        }
        no = rotacionarDir(no);
      } else {
        throw new Exception(
          "Erro no No(" +
          no.elemento.getNome() +
          ") com fator de balanceamento (" +
          fator +
          ") invalido!"
        );
      }
    }
    return no;
  }

  private No rotacionarDir(No no) {
    // System.out.println("Rotacionar DIR(" + no.elemento.getNome() + ")");
    No noEsq = no.esq;
    No noEsqDir = noEsq.dir;

    noEsq.dir = no;
    no.esq = noEsqDir;
    no.setNivel(); // Atualizar o nivel do no
    noEsq.setNivel(); // Atualizar o nivel do noEsq

    return noEsq;
  }

  private No rotacionarEsq(No no) {
    // System.out.println("Rotacionar ESQ(" + no.elemento.getNome() + ")");
    No noDir = no.dir;
    No noDirEsq = noDir.esq;

    noDir.esq = no;
    no.dir = noDirEsq;

    no.setNivel(); // Atualizar o nivel do no
    noDir.setNivel(); // Atualizar o nivel do noDir
    return noDir;
  }

  /**
   * Metodo para trocar o elemento "removido" pelo maior da esquerda.
   * @param i No que teve o elemento removido.
   * @param j No da subarvore esquerda.
   * @return No em analise, alterado ou nao.
   */
  private No maiorEsq(No i, No j) {
    // Encontrou o maximo da subarvore esquerda.
    if (j.dir == null) {
      i.elemento = j.elemento; // Substitui i por j.
      j = j.esq; // Substitui j por j.ESQ.
      // Existe no a direita.
    } else {
      // Caminha para direita.
      j.dir = maiorEsq(i, j.dir);
    }
    return j;
  }

  /**
   * Metodo que retorna o maior elemento da árvore
   * @return int maior elemento da árvore
   */
  public Series getMaior() {
    Series resp = null;

    if (raiz != null) {
      No i;
      for (i = raiz; i.dir != null; i = i.dir);
      resp = i.elemento;
    }

    return resp;
  }

  /**
   * Metodo que retorna o menor elemento da árvore
   * @return int menor elemento da árvore
   */
  public Series getMenor() {
    Series resp = null;

    if (raiz != null) {
      No i;
      for (i = raiz; i.esq != null; i = i.esq);
      resp = i.elemento;
    }

    return resp;
  }

  /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
  public int getAltura() {
    return getAltura(raiz, 0);
  }

  /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
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

  /**
   * Metodo publico iterativo para remover elemento.
   * @param x Elemento a ser removido.
   * @throws Exception Se nao encontrar elemento.
   */
  public void remover2(String x) throws Exception {
    if (raiz == null) {
      throw new Exception("Erro ao remover2!");
    } else if (x.compareTo(raiz.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      remover2(x, raiz.esq, raiz);
    } else if (x.compareTo(raiz.elemento.getNome()) > 0) {
      Comparacoes.cont++;
      remover2(x, raiz.dir, raiz);
    } else if (raiz.dir == null) {
      raiz = raiz.esq;
    } else if (raiz.esq == null) {
      raiz = raiz.dir;
    } else {
      raiz.esq = maiorEsq(raiz, raiz.esq);
    }
  }

  /**
   * Metodo privado recursivo para remover elemento.
   * @param x Elemento a ser removido.
   * @param i No em analise.
   * @param pai do No em analise.
   * @throws Exception Se nao encontrar elemento.
   */
  private void remover2(String x, No i, No pai) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover2!");
    } else if (x.compareTo(i.elemento.getNome()) < 0) {
      Comparacoes.cont++;
      remover2(x, i.esq, i);
    } else if (x.compareTo(i.elemento.getNome()) > 0) {
      Comparacoes.cont++;
      remover2(x, i.dir, i);
    } else if (i.dir == null) {
      pai = i.esq;
    } else if (i.esq == null) {
      pai = i.dir;
    } else {
      i.esq = maiorEsq(i, i.esq);
    }
  }

  public Series getRaiz() throws Exception {
    return raiz.elemento;
  }

  public static boolean igual(AVL a1, AVL a2) {
    return igual(a1.raiz, a2.raiz);
  }

  private static boolean igual(No i1, No i2) {
    boolean resp;
    if (i1 != null && i2 != null) {
      resp =
        (i1.elemento.getNome().equals(i2.elemento.getNome())) &&
        igual(i1.esq, i2.esq) &&
        igual(i1.dir, i2.dir);
      Comparacoes.cont++;
    } else if (i1 == null && i2 == null) {
      resp = true;
    } else {
      resp = false;
    }
    return resp;
  }
}

public class TP04Q03 {

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

    double tempo = 0;
    Comparacoes.cont = 0;
    tempo = new Date().getTime();

    String arq = MyIO.readLine(), linha = "";

    AVL arvoreSeries = new AVL();

    while (!isFim(arq)) {
      Series serie = new Series();
      serie.ler(arq);
      try {
        if (arvoreSeries.pesquisar2(serie.getNome()) == false) {
          arvoreSeries.inserir(serie);
        }
      } catch (Exception e) {
        //
      }
      arq = MyIO.readLine();
    }

    linha = MyIO.readLine();
    while (!isFim(linha)) {
      if (arvoreSeries.pesquisar(linha)) {
        MyIO.print("SIM");
      } else {
        MyIO.print("NAO");
      }
      MyIO.println("");
      linha = MyIO.readLine();
    }

    Arq.openWrite("734661_avl.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println("734661" + String.format("\t%.4f\t", tempo) + Comparacoes.cont);
    Arq.close();
  }
}
