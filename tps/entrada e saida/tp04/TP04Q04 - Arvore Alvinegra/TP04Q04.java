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

class NoAN {

  public boolean cor;
  public Series elemento;
  public NoAN esq, dir;

  public NoAN() {
    this(null);
  }

  public NoAN(Series elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(Series elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(Series elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

class Alvinegra {

  private NoAN raiz; // Raiz da arvore.

  /**
   * Construtor da classe.
   */
  public Alvinegra() {
    raiz = null;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(String x) {
    MyIO.print(" raiz ");
    return pesquisar(x, raiz);
  }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  private boolean pesquisar(String x, NoAN i) {
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
  private boolean pesquisar2(String x, NoAN i) {
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
  private void caminharCentral(NoAN i) {
    if (i != null) {
      caminharCentral(i.esq); // Elementos da esquerda.
      System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
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
  private void caminharPre(NoAN i) {
    if (i != null) {
      System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
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
  private void caminharPos(NoAN i) {
    if (i != null) {
      caminharPos(i.esq); // Elementos da esquerda.
      caminharPos(i.dir); // Elementos da direita.
      System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
    }
  }

  public void inserir(Series elemento) throws Exception {
    // Se a arvore estiver vazia
    if (raiz == null) {
      raiz = new NoAN(elemento);
      // Senao, se a arvore tiver um elemento
    } else if (raiz.esq == null && raiz.dir == null) {
      if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
        Comparacoes.cont++;
        raiz.esq = new NoAN(elemento);
      } else {
        raiz.dir = new NoAN(elemento);
      }
      // Senao, se a arvore tiver dois elementos (raiz e dir)
    } else if (raiz.esq == null) {
      if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
        Comparacoes.cont++;
        raiz.esq = new NoAN(elemento);
      } else if (
        elemento.getNome().compareTo(raiz.dir.elemento.getNome()) < 0
      ) {
        Comparacoes.cont++;
        raiz.esq = new NoAN(raiz.elemento);
        raiz.elemento = elemento;
      } else {
        raiz.esq = new NoAN(raiz.elemento);
        raiz.elemento = raiz.dir.elemento;
        raiz.dir.elemento = elemento;
      }
      raiz.esq.cor = raiz.dir.cor = false;
      // Senao, se a arvore tiver dois elementos (raiz e esq)
    } else if (raiz.dir == null) {
      if (elemento.getNome().compareTo(raiz.elemento.getNome()) > 0) {
        Comparacoes.cont++;
        raiz.dir = new NoAN(elemento);
      } else if (
        elemento.getNome().compareTo(raiz.esq.elemento.getNome()) > 0
      ) {
        Comparacoes.cont++;
        raiz.dir = new NoAN(raiz.elemento);
        raiz.elemento = elemento;
      } else {
        raiz.dir = new NoAN(raiz.elemento);
        raiz.elemento = raiz.esq.elemento;
        raiz.esq.elemento = elemento;
      }
      raiz.esq.cor = raiz.dir.cor = false;
      // Senao, a arvore tem tres ou mais elementos
    } else {
      inserir(elemento, null, null, null, raiz);
    }
    raiz.cor = false;
  }

  private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
    // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
    if (pai.cor == true) {
      // 4 tipos de reequilibrios e acoplamento
      if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
        Comparacoes.cont++;
        if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
          Comparacoes.cont++;
          avo = rotacaoEsq(avo);
        } else {
          avo = rotacaoDirEsq(avo);
        }
      } else { // rotacao a direita ou esquerda-direita
        if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
          Comparacoes.cont++;
          avo = rotacaoDir(avo);
        } else {
          avo = rotacaoEsqDir(avo);
        }
      }
      if (bisavo == null) {
        raiz = avo;
      } else if (
        avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0
      ) {
        Comparacoes.cont++;
        bisavo.esq = avo;
      } else {
        bisavo.dir = avo;
      }
      // reestabelecer as cores apos a rotacao
      avo.cor = false;
      avo.esq.cor = avo.dir.cor = true;
    } // if(pai.cor == true)
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   *
   * @param elemento Elemento a ser inserido.
   * @param avo      NoAN em analise.
   * @param pai      NoAN em analise.
   * @param i        NoAN em analise.
   * @throws Exception Se o elemento existir.
   */
  private void inserir(
    Series elemento,
    NoAN bisavo,
    NoAN avo,
    NoAN pai,
    NoAN i
  )
    throws Exception {
    if (i == null) {
      if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
        Comparacoes.cont++;
        i = pai.esq = new NoAN(elemento, true);
      } else {
        i = pai.dir = new NoAN(elemento, true);
      }
      if (pai.cor == true) {
        balancear(bisavo, avo, pai, i);
      }
    } else {
      // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
      if (
        i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true
      ) {
        i.cor = true;
        i.esq.cor = i.dir.cor = false;
        if (i == raiz) {
          i.cor = false;
        } else if (pai.cor == true) {
          balancear(bisavo, avo, pai, i);
        }
      }
      if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
        Comparacoes.cont++;
        inserir(elemento, avo, pai, i, i.esq);
      } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
        Comparacoes.cont++;
        inserir(elemento, avo, pai, i, i.dir);
      } else {
        throw new Exception("Erro inserir (elemento repetido)!");
      }
    }
  }

  private NoAN rotacaoDir(NoAN no) {
    NoAN noEsq = no.esq;
    NoAN noEsqDir = noEsq.dir;

    noEsq.dir = no;
    no.esq = noEsqDir;

    return noEsq;
  }

  private NoAN rotacaoEsq(NoAN no) {
    NoAN noDir = no.dir;
    NoAN noDirEsq = noDir.esq;

    noDir.esq = no;
    no.dir = noDirEsq;
    return noDir;
  }

  private NoAN rotacaoDirEsq(NoAN no) {
    no.dir = rotacaoDir(no.dir);
    return rotacaoEsq(no);
  }

  private NoAN rotacaoEsqDir(NoAN no) {
    no.esq = rotacaoEsq(no.esq);
    return rotacaoDir(no);
  }

  /**
   * Metodo para trocar o elemento "removido" pelo maior da esquerda.
   * @param i No que teve o elemento removido.
   * @param j No da subarvore esquerda.
   * @return No em analise, alterado ou nao.
   */
  private NoAN maiorEsq(NoAN i, NoAN j) {
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
      NoAN i;
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
      NoAN i;
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
  public int getAltura(NoAN i, int altura) {
    if (i == null) {
      altura--;
    } else {
      int alturaEsq = getAltura(i.esq, altura + 1);
      int alturaDir = getAltura(i.dir, altura + 1);
      altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
    }
    return altura;
  }

  public Series getRaiz() throws Exception {
    return raiz.elemento;
  }

  public static boolean igual(Alvinegra a1, Alvinegra a2) {
    return igual(a1.raiz, a2.raiz);
  }

  private static boolean igual(NoAN i1, NoAN i2) {
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

public class TP04Q04 {

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

    Alvinegra arvoreSeries = new Alvinegra();

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

    Arq.openWrite("734661_alvinegra.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println("734661" + String.format("\t%.4f\t", tempo) + Comparacoes.cont);
    Arq.close();
  }
}
