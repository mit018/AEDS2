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
      Comparacoes.cont++;
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
        Comparacoes.cont++;
        setSerie(pad, conteudo);
        pad++;
      }
      Comparacoes.cont++;
      linha = Arq.readLine();
    }
    Comparacoes.cont++;
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
    Comparacoes.cont++;
    this.temporadas = Integer.parseInt(temporadas);
  }

  public void setEpisodios(String episodios) {
    if (episodios.indexOf(" ") > 0) {
      episodios = episodios.substring(0, episodios.indexOf(" ")).trim();
    }
    Comparacoes.cont++;
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

class tabelaHash {

  private Series[] series;
  int m1, m2, m, reserva;
  private int n;

  /**
   * Inicializa a lista com um tamanho especifico
   * @param tamanho tamanho do vetor de series
   */
  public tabelaHash(int m1, int m2) {
    this.m1 = m1;
    this.m2 = m2;
    this.m = m1 + m2;
    series = new Series[m];
    for (int i = 0; i < m1; i++) {
      series[i] = null;
    }
    reserva = 0;
    n = 0;
  }

  /**Inicializa a lista com tamanho 100 */
  public tabelaHash() {
    this(21, 9);
  }

  public int hash(String elemento) {
    int num = 0;
    for (int i = 0; i < elemento.length(); i++) {
      num += elemento.charAt(i);
    }
    return num % this.m1;
  }

  /**
   * Retorna o tamanho total da lista
   * @return
   */
  public int getTamanho() {
    return series.length;
  }

  public boolean inserir(Series elemento) throws Exception {
    if (n >= this.m) {
      throw new Exception("ERRO");
    }
    boolean resp = false;
    if (elemento != null) {
      int pos = hash(elemento.getNome());
      if (series[pos] == null) {
        Comparacoes.cont++;
        series[pos] = elemento;
        resp = true;
        n++;
      } else if (reserva < m2) {
        series[m1 + reserva] = elemento;
        reserva++;
        resp = true;
        n++;
      }
    }
    return resp;
  }

  Series remover(Series elemento) throws Exception {
    if (n == 0) {
      throw new Exception("ERRO");
    }
    Series serie = series[0];
    // n--;

    // for (int i = pos; i < n; i++) {
    //   series[i] = series[i + 1];
    // }
    return serie;
  }

  /**FIM METODOS DE INSERCAO E REMOCAO DE OBJETOS SERIE DA LISTA */

  /**Imprime a lista */
  void Imprimir() {
    for (int i = 0; i < n; i++) {
      if (series[i] != null) {
        series[i].imprimir();
      }
    }
  }

  /**
   * Pesquisa sequencial de um nome de serie em toda a lista
   * @param linha nome da serie pesquisada
   * @return resposta booleana para a pesquisa
   */
  public boolean pesquisar(String linha) {
    boolean resp = false;
    int pos = hash(linha);

    if (series[pos].getNome().equals(linha)) {
      Comparacoes.cont++;
      resp = true;
    } else if (series[pos] != null) {
      for (int i = 0; i < reserva; i++) {
        if (series[m1 + i].getNome().equals(linha)) {
          Comparacoes.cont++;
          resp = true;
          i = reserva;
        }
      }
    }
    return resp;
  }
}

public class TP04Q06 {

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
    double tempo = 0;
    Comparacoes.cont = 0;
    tempo = new Date().getTime();

    MyIO.setCharset("UTF-8");

    String arq = MyIO.readLine(), resp = "";

    tabelaHash hashSeries = new tabelaHash();

    while (!isFim(arq)) {
      Series serie = new Series();
      serie.ler(arq);

      try {
        hashSeries.inserir(serie);
      } catch (Exception e) {
        //
      }
      arq = MyIO.readLine();
    }

    arq = MyIO.readLine();
    
    while (!isFim(arq)) {
      if (hashSeries.pesquisar(arq) == true) {
        MyIO.println(" SIM");
      } else {
        MyIO.println(" NAO");
      }

      // resp = hashSeries.pesquisar(arq) ? "SIM" : "NAO";
      // MyIO.println(resp);
      arq = MyIO.readLine();
    }

    Arq.openWrite("734661_hashReserva.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println("734661" + String.format("\t%.4f\t", tempo) + Comparacoes.cont);
    Arq.close();
  }
}
