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

class NoLetra {

  public char elemento; // Conteudo do no.
  public NoLetra esq, dir; // Filhos da esq e dir.
  public No no;

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   */
  public NoLetra(char elemento) {
    this(elemento, null, null, null);
  }

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   * @param esq No da esquerda.
   * @param dir No da direita.
   */
  public NoLetra(char elemento, NoLetra esq, NoLetra dir, No no) {
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
    this.no = no;
  }
}

class No {

  public Series elemento; // Conteudo do no.
  public No esq, dir; // Filhos da esq e dir.

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   */
  public No(Series elemento) {
    this(elemento, null, null);
  }

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   * @param esq No da esquerda.
   * @param dir No da direita.
   */
  public No(Series elemento, No esq, No dir) {
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

class ArvoreArvore {

  private NoLetra raiz; // Raiz da arvore.

  /**
   * Construtor da classe.
   */
  public ArvoreArvore() {
    raiz = null;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(char x) {
    return pesquisar(x, raiz);
  }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  private boolean pesquisar(char x, NoLetra i) {
    boolean resp;
    if (i == null) {
      resp = false;
    } else if (x == i.elemento) {
      Comparacoes.cont++;
      resp = true;
    } else if (x < i.elemento) {
      Comparacoes.cont++;
      resp = pesquisar(x, i.esq);
    } else {
      resp = pesquisar(x, i.dir);
    }
    return resp;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   *
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir, <code>false</code> em caso
   *         contrario.
   */
  public boolean pesquisar(String x) {
    System.out.print("raiz ");
    return pesquisar(x, raiz);
  }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   *
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir, <code>false</code> em caso
   *         contrario.
   */
  private boolean pesquisar(String x, NoLetra i) {
    boolean resp;
    if (i == null) {
      resp = false;
    } else if (pesquisarSerie(i.no, x)) {
      resp = true;
    } else {
      System.out.print("esq ");
      resp = pesquisar(x, i.esq);
      if (!resp) {
        System.out.print("dir ");
        resp = pesquisar(x, i.dir);
      }
    }
    return resp;
  }

  private boolean pesquisarSerie(No no, String x) {
    boolean resp;
    if (no == null) {
      resp = false;
    } else {
      if (x.compareTo(no.elemento.getNome()) == 0) {
        Comparacoes.cont++;
        resp = true;
      } else {
        System.out.print("ESQ ");
        resp = pesquisarSerie(no.esq, x);
        if (!resp) {
          System.out.print("DIR ");
          resp = pesquisarSerie(no.dir, x);
        }
      }
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
  private void caminharCentral(NoLetra i) {
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
  private void caminharPre(NoLetra i) {
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
  private void caminharPos(NoLetra i) {
    if (i != null) {
      caminharPos(i.esq); // Elementos da esquerda.
      caminharPos(i.dir); // Elementos da direita.
      System.out.print(i.elemento + " "); // Conteudo do no.
    }
  }

  /**
   * Metodo publico iterativo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserir(char x) throws Exception {
    raiz = inserir(x, raiz);
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se o elemento existir.
   */
  private NoLetra inserir(char x, NoLetra i) throws Exception {
    if (i == null) {
      i = new NoLetra(x);
    } else if (x < i.elemento) {
      Comparacoes.cont++;
      i.esq = inserir(x, i.esq);
    } else if (x > i.elemento) {
      Comparacoes.cont++;
      i.dir = inserir(x, i.dir);
    } else {
      throw new Exception("Erro ao inserir!");
    }

    return i;
  }

  /**
   * Metodo publico iterativo para inserir elemento.
   *
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserir(Series x) throws Exception {
    inserir(x, raiz);
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   *
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se o elemento existir.
   */
  public void inserir(Series x, NoLetra i) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao inserir: caractere invalido!");
    } else {
      if (x.getNome().charAt(0) < i.elemento) {
        Comparacoes.cont++;
        inserir(x, i.esq);
      } else {
        if (x.getNome().charAt(0) > i.elemento) {
          Comparacoes.cont++;
          inserir(x, i.dir);
        } else {
          i.no = inserir(x, i.no);
        }
      }
    }
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   *
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
      throw new Exception("Erro ao inserir: elemento existente!");
    }
    return i;
  }

  /**
   * Metodo publico para inserir elemento.
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserirPai(char x) throws Exception {
    if (raiz == null) {
      raiz = new NoLetra(x);
    } else if (x < raiz.elemento) {
      Comparacoes.cont++;
      inserirPai(x, raiz.esq, raiz);
    } else if (x > raiz.elemento) {
      Comparacoes.cont++;
      inserirPai(x, raiz.dir, raiz);
    } else {
      throw new Exception("Erro ao inserirPai!");
    }
  }

  /**
   * Metodo privado recursivo para inserirPai elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @param pai No superior ao em analise.
   * @throws Exception Se o elemento existir.
   */
  private void inserirPai(char x, NoLetra i, NoLetra pai) throws Exception {
    if (i == null) {
      if (x < pai.elemento) {
        Comparacoes.cont++;
        pai.esq = new NoLetra(x);
      } else {
        pai.dir = new NoLetra(x);
      }
    } else if (x < i.elemento) {
      Comparacoes.cont++;
      inserirPai(x, i.esq, i);
    } else if (x > i.elemento) {
      Comparacoes.cont++;
      inserirPai(x, i.dir, i);
    } else {
      throw new Exception("Erro ao inserirPai!");
    }
  }

  /**
   * Metodo publico iterativo para remover elemento.
   * @param x Elemento a ser removido.
   * @throws Exception Se nao encontrar elemento.
   */
  public void remover(char x) throws Exception {
    raiz = remover(x, raiz);
  }

  /**
   * Metodo privado recursivo para remover elemento.
   * @param x Elemento a ser removido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se nao encontrar elemento.
   */
  private NoLetra remover(char x, NoLetra i) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover!");
    } else if (x < i.elemento) {
      Comparacoes.cont++;
      i.esq = remover(x, i.esq);
    } else if (x > i.elemento) {
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

    return i;
  }

  /**
   * Metodo para trocar o elemento "removido" pelo maior da esquerda.
   * @param i No que teve o elemento removido.
   * @param j No da subarvore esquerda.
   * @return No em analise, alterado ou nao.
   */
  private NoLetra maiorEsq(NoLetra i, NoLetra j) {
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
  public char getMaior() {
    char resp = 0;

    if (raiz != null) {
      NoLetra i;
      for (i = raiz; i.dir != null; i = i.dir);
      resp = i.elemento;
    }

    return resp;
  }

  /**
   * Metodo que retorna o menor elemento da árvore
   * @return int menor elemento da árvore
   */
  public char getMenor() {
    char resp = 0;

    if (raiz != null) {
      NoLetra i;
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
  public int getAltura(NoLetra i, int altura) {
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
  public void remover2(char x) throws Exception {
    if (raiz == null) {
      throw new Exception("Erro ao remover2!");
    } else if (x < raiz.elemento) {
      Comparacoes.cont++;
      remover2(x, raiz.esq, raiz);
    } else if (x > raiz.elemento) {
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
  private void remover2(char x, NoLetra i, NoLetra pai) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover2!");
    } else if (x < i.elemento) {
      Comparacoes.cont++;
      remover2(x, i.esq, i);
    } else if (x > i.elemento) {
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

  public char getRaiz() throws Exception {
    return raiz.elemento;
  }

  public static boolean igual(ArvoreArvore a1, ArvoreArvore a2) {
    return igual(a1.raiz, a2.raiz);
  }

  private static boolean igual(NoLetra i1, NoLetra i2) {
    boolean resp;
    if (i1 != null && i2 != null) {
      resp =
        (i1.elemento == i2.elemento) &&
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

public class TP04Q02 {

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

    ArvoreArvore arvoreLetras = new ArvoreArvore();
    char[] letras = {
      'D',
      'R',
      'Z',
      'X',
      'V',
      'B',
      'F',
      'P',
      'U',
      'I',
      'G',
      'E',
      'J',
      'L',
      'H',
      'T',
      'A',
      'W',
      'S',
      'O',
      'M',
      'N',
      'K',
      'C',
      'Y',
      'Q',
    };
    for (int i = 0; i < letras.length; i++) {
      try {
        arvoreLetras.inserir(letras[i]);
      } catch (Exception e) {
        //
      }
    }

    String arq = MyIO.readLine(), linha = "";

    while (!isFim(arq)) {
      Series serie = new Series();
      serie.ler(arq);
      try {
        arvoreLetras.inserir(serie);
      } catch (Exception e) {
        //
      }
      arq = MyIO.readLine();
    }

    linha = MyIO.readLine();
    while (!isFim(linha)) {
      if (arvoreLetras.pesquisar(linha)) {
        MyIO.print(" SIM");
      } else {
        MyIO.print(" NAO");
      }
      MyIO.println("");
      linha = MyIO.readLine();
    }

    Arq.openWrite("734661_arvoreArvore.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println("734661" + String.format("\t%.4f\t", tempo) + Comparacoes.cont);
    Arq.close();
  }
}
