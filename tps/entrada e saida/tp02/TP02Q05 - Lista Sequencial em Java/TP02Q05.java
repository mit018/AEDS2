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

class Lista {

  private Series[] series;
  private int n;

  /**
   * Inicializa a lista com um tamanho especifico
   * @param tamanho tamanho do vetor de series
   */
  public Lista(int tamanho) {
    series = new Series[tamanho];
    n = 0;
  }

  /**Inicializa a lista com tamanho 100 */
  public Lista() {
    this(100);
    n = 0;
  }

  /**
   * Retorna o tamanho total da lista
   * @return
   */
  public int getTamanho() {
    return series.length;
  }
  
  /**INICIO METODOS DE INSERCAO E REMOCAO DE OBJETOS SERIE DA LISTA */
  void inserirFim(Series s) throws Exception {
    if (n >= series.length) {
      throw new Exception("ERRO");
    }

    series[n] = s.clone();
    n++;
  }

  void inserirInicio(Series s) throws Exception {
    if (n >= series.length) {
      throw new Exception("ERRO");
    }

    for (int i = n; i > 0; i--) {
      series[i] = series[i - 1];
    }

    series[0] = s.clone();
    n++;
  }

  void inserir(Series s, int pos) throws Exception {
    if (n >= series.length || pos < 0 || pos > n) {
      throw new Exception("ERRO");
    }

    for (int i = n; i > pos; i--) {
      series[i] = series[i - 1];
    }

    series[pos] = s.clone();
    n++;
  }

  Series remover(int pos) throws Exception {
    if (n == 0 || pos < 0 || pos > n) {
      throw new Exception("ERRO");
    }

    Series serie = series[pos];
    n--;

    for (int i = pos; i < n; i++) {
      series[i] = series[i + 1];
    }

    return serie;
  }

  Series removerInicio() throws Exception {
    if (n == 0) {
      throw new Exception("ERRO");
    }

    Series primeira = series[0];
    n--;
    for (int i = 0; i < n; i++) {
      series[i] = series[i + 1];
    }

    return primeira;
  }

  Series removerFim() throws Exception {
    if (n == 0) {
      throw new Exception("ERRO");
    }

    return series[--n];
  }
  /**FIM METODOS DE INSERCAO E REMOCAO DE OBJETOS SERIE DA LISTA */

  /**Imprime a lista */
  void Imprimir() {
    for (int i = 0; i < n; i++) {
      series[i].imprimir();
    }
  }
}

public class TP02Q05 {

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
