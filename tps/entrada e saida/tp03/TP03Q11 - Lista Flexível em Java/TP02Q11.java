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

  /**Imprime os atributos do objeto serie */
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

  /**
   * Atribui o conteudo a um atributo da serie de acordo com o padrao encontradp
   * @param padrao padrao encontrado
   * @param conteudo conteudo do atributo a ser inserido
   */
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

  /**
   * Le um arquivo html e preenche os atributos do objeto serie
   * @param arq nome do arquivo
   */
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

  /**Inicializa o objeto */
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

  /**Inicializa o objeto com parametros para cada atributo*/
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

  /**Cria retorna um clone do objeto serie */
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

  /**
   * Cria um vetor de strings com padroes a serem pesquisados no arquivo html
   * @return vetor de strings
   */
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

  /**INICIO SETTERS E GETTERS SERIE*/
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
  /**FIM SETTERS E GETTERS SERIE*/
}

class Celula {

  public Series serie; // Elemento inserido na celula.
  public Celula prox; // Aponta a celula prox.

  /**
   * Construtor da classe.
   */
  public Celula() {
    this(null);
  }

  public Celula(Series serie) {
    this.serie = serie;
    this.prox = null;
  }
}

class Lista {

  private Celula primeiro;
  private Celula ultimo;

  /**
   * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
   */
  public Lista() {
    primeiro = new Celula();
    ultimo = primeiro;
  }

  /**
   * Insere um elemento na primeira posicao da lista.
   * @param s serie a ser inserida.
   */
  public void inserirInicio(Series s) {
    Celula tmp = new Celula(s);
    tmp.prox = primeiro.prox;
    primeiro.prox = tmp;
    if (primeiro == ultimo) {
      ultimo = tmp;
    }
    tmp = null;
  }

  /**
   * Insere um elemento na ultima posicao da lista.
   * @param s serie a ser inserida.
   */
  public void inserirFim(Series s) {
    ultimo.prox = new Celula(s);
    ultimo = ultimo.prox;
  }

  /**
   * Remove um elemento da primeira posicao da lista.
   * @return serie a ser removida.
   * @throws Exception Se a lista nao contiver elementos.
   */
  public Series removerInicio() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    Celula tmp = primeiro;
    primeiro = primeiro.prox;
    Series resp = primeiro.serie;
    tmp.prox = null;
    tmp = null;
    return resp;
  }

  /**
   * Remove um elemento da ultima posicao da lista.
   * @return serie a ser removida.
   * @throws Exception Se a lista nao contiver elementos.
   */
  public Series removerFim() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    // Caminhar ate a penultima celula:
    Celula i;
    for (i = primeiro; i.prox != ultimo; i = i.prox);

    Series resp = ultimo.serie;
    ultimo = i;
    i = ultimo.prox = null;

    return resp;
  }

  /**
   * Insere um elemento em uma posicao especifica considerando que o
   * primeiro elemento valido esta na posicao 0.
   * @param s serie a ser inserida.
   * @param pos int posicao da insercao.
   * @throws Exception Se <code>posicao</code> invalida.
   */
  public void inserir(Series s, int pos) throws Exception {
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
      inserirInicio(s);
    } else if (pos == tamanho) {
      inserirFim(s);
    } else {
      // Caminhar ate a posicao anterior a insercao
      Celula i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);

      Celula tmp = new Celula(s);
      tmp.prox = i.prox;
      i.prox = tmp;
      tmp = i = null;
    }
  }

  /**
   * Remove um elemento de uma posicao especifica da lista
   * considerando que o primeiro elemento valido esta na posicao 0.
   * @param posicao Meio da remocao.
   * @return serie a ser removida.
   * @throws Exception Se <code>posicao</code> invalida.
   */
  public Series remover(int pos) throws Exception {
    Series resp;
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
      resp = tmp.serie;
      i.prox = tmp.prox;
      tmp.prox = null;
      i = tmp = null;
    }

    return resp;
  }

  public void Imprimir() {
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      i.serie.imprimir();
    }
  }

  /**
   * Procura um elemento e retorna se ele existe.
   * @param s serie a pesquisar.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(Series s) {
    boolean resp = false;
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      if (i.serie.getNome() == s.getNome()) {
        resp = true;
        i = ultimo;
      }
    }
    return resp;
  }

  /**
   * Calcula e retorna o tamanho, em numero de elementos, da lista.
   * @return resp int tamanho
   */
  public int tamanho() {
    int tamanho = 0;
    for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
    return tamanho;
  }
}

public class TP02Q11 {

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

  public static void processa(String linha, Lista lista) {
    String[] split = new String[2];
    split = linha.split(" ");
    Series serie = new Series();

    switch (linha.charAt(0)) {
      case 'I':
        switch (linha.charAt(1)) {
          case 'I':
            serie.ler(split[1]);
            try {
              lista.inserirInicio(serie);
            } catch (Exception e) {}

            break;
          case 'F':
            serie.ler(split[1]);
            try {
              lista.inserirFim(serie);
            } catch (Exception e) {}
            break;
          case '*':
            serie.ler(split[2]);
            try {
              lista.inserir(serie, Integer.parseInt(split[1]));
            } catch (Exception e) {}
            break;
          default:
            break;
        }
        break;
      case 'R':
        switch (linha.charAt(1)) {
          case 'I':
            try {
              serie = lista.removerInicio();
            } catch (Exception e) {}

            break;
          case 'F':
            try {
              serie = lista.removerFim();
            } catch (Exception e) {}
            break;
          case '*':
            try {
              serie = lista.remover(Integer.parseInt(split[1]));
            } catch (Exception e) {}
            break;
          default:
            break;
        }
        MyIO.println("(R) " + serie.getNome());
        break;
      default:
        break;
    }
  }

  public static void main(String[] args) {
    MyIO.setCharset("UTF-8");

    String arq = MyIO.readLine(), linha = "";

    Lista listaSeries = new Lista();

    while (!isFim(arq)) {
      Series serie = new Series();
      serie.ler(arq);
      try {
        listaSeries.inserirFim(serie);
      } catch (Exception e) {
        //
      }
      arq = MyIO.readLine();
    }

    int i = MyIO.readInt();

    for (int j = 0; j < i; j++) {
      linha = MyIO.readLine();
      processa(linha, listaSeries);
    }

    listaSeries.Imprimir();
  }
}
