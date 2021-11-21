import java.util.Date;
import java.util.Locale;

class Comparacoes {

  public static int cont;
}
class Movimentacoes {

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

  /**
   * Troca o conteudo de duas posicoes do array
   * @param i int primeira posicao
   * @param j int segunda posicao
   */
  public void swap(int i, int j) {
    Series temp = series[i].clone();
    series[i] = series[j].clone();
    series[j] = temp;
  }

  /**FIM METODOS DE INSERCAO E REMOCAO DE OBJETOS SERIE DA LISTA */

  void ordenarHeapsort() throws Exception {
     if (n == 0) {
      throw new Exception("ERRO");
    }
    //Alterar o vetor ignorando a posicao zero
    Series[] tmp = new Series[n + 1];
    for (int i = 0; i < n; i++) {
      tmp[i + 1] = series[i];
    }
    series = tmp;

    //Contrucao do heap
    for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
      construir(tamHeap);
    }

    //Ordenacao propriamente dita
    int tamHeap = n;
    while (tamHeap > 1) {
      swap(1, tamHeap--);
      Movimentacoes.cont++;
      reconstruir(tamHeap);
    }

    //Alterar o vetor para voltar a posicao zero
    tmp = series;
    series = new Series[n];
    for (int i = 0; i < n; i++) {
      series[i] = tmp[i + 1];
    }
  }

    
  public void construir(int tamHeap) {
    for (int i = tamHeap; i > 1 && ((series[i].getFormato().compareTo(series[i/2].getFormato()) > 0) || (series[i].getFormato().compareTo(series[i/2].getFormato()) == 0 && series[i].getNome().compareTo(series[i/2].getNome()) > 0)); i /= 2) {
      Comparacoes.cont += 2;
      swap(i, i / 2);
      Movimentacoes.cont++;
    }
  }

  public void reconstruir(int tamHeap) {
    int i = 1;
    while (i <= (tamHeap / 2)) {
      int filho = getMaiorFilho(i, tamHeap);
      if ((series[i].getFormato().compareTo(series[filho].getFormato()) < 0) || (series[i].getFormato().compareTo(series[filho].getFormato()) == 0 && series[i].getNome().compareTo(series[filho].getNome()) < 0)) {
      Comparacoes.cont += 2;
        swap(i, filho);
        Movimentacoes.cont++;
        i = filho;
      } else {
        i = tamHeap;
      }
    }
  }

  public int getMaiorFilho(int i, int tamHeap) {
    int filho;
    if (2 * i == tamHeap || ((series[2*i].getFormato().compareTo(series[2*i + 1].getFormato()) > 0) || (series[2*i].getFormato().compareTo(series[2*i + 1].getFormato()) == 0 && series[2*i].getNome().compareTo(series[2*i + 1].getNome()) > 0))) {
      Comparacoes.cont += 2;
      filho = 2 * i;
    } else {
      filho = 2 * i + 1;
    }
    return filho;
  }

  /**Imprime a lista */
  void Imprimir() {
    for (int i = 0; i < n; i++) {
      series[i].imprimir();
    }
  }
}

public class TP03Q05 {

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
    Movimentacoes.cont = 0;
    tempo = new Date().getTime();
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

    try {
      listaSeries.ordenarHeapsort();
    } catch (Exception e) {
      //
    }

    listaSeries.Imprimir();

    Arq.openWrite("734661_heapsort.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println(
      "734661\t" + Comparacoes.cont + "\t" + Movimentacoes.cont + String.format("\t%.4f", tempo)
    );
    Arq.close();
  }
}
