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

class CelulaDupla {

  public Series serie;
  public CelulaDupla ant;
  public CelulaDupla prox;

  /**
   * Construtor da classe.
   */
  public CelulaDupla() {
    this(null);
  }

  /**
   * Construtor da classe.
   * @param elemento int inserido na celula.
   */
  public CelulaDupla(Series serie) {
    this.serie = serie;
    this.ant = this.prox = null;
  }
}

class ListaDupla {

  private CelulaDupla primeiro;
  private CelulaDupla ultimo;

  /**
   * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
   */
  public ListaDupla() {
    primeiro = new CelulaDupla();
    ultimo = primeiro;
  }

  /**
   * Insere um elemento na primeira posicao da lista.
   * @param x int elemento a ser inserido.
   */
  public void inserirInicio(Series s) {
    CelulaDupla tmp = new CelulaDupla(s);

    tmp.ant = primeiro;
    tmp.prox = primeiro.prox;
    primeiro.prox = tmp;
    if (primeiro == ultimo) {
      ultimo = tmp;
    } else {
      tmp.prox.ant = tmp;
    }
    tmp = null;
  }

  /**
   * Insere um elemento na ultima posicao da lista.
   * @param x int elemento a ser inserido.
   */
  public void inserirFim(Series s) {
    ultimo.prox = new CelulaDupla(s);
    ultimo.prox.ant = ultimo;
    ultimo = ultimo.prox;
  }

  /**
   * Remove um elemento da primeira posicao da lista.
   * @return resp int elemento a ser removido.
   * @throws Exception Se a lista nao contiver elementos.
   */
  public Series removerInicio() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    CelulaDupla tmp = primeiro;
    primeiro = primeiro.prox;
    Series resp = primeiro.serie;
    tmp.prox = primeiro.ant = null;
    tmp = null;
    return resp;
  }

  /**
   * Remove um elemento da ultima posicao da lista.
   * @return resp int elemento a ser removido.
   * @throws Exception Se a lista nao contiver elementos.
   */
  public Series removerFim() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }
    Series resp = ultimo.serie;
    ultimo = ultimo.ant;
    ultimo.prox.ant = null;
    ultimo.prox = null;
    return resp;
  }

  /**
   * Insere um elemento em uma posicao especifica considerando que o
   * primeiro elemento valido esta na posicao 0.
   * @param x int elemento a ser inserido.
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
      CelulaDupla i = primeiro;
      for (int j = 0; j < pos; j++, i = i.prox);

      CelulaDupla tmp = new CelulaDupla(s);
      tmp.ant = i;
      tmp.prox = i.prox;
      tmp.ant.prox = tmp.prox.ant = tmp;
      tmp = i = null;
    }
  }

  /**
   * Remove um elemento de uma posicao especifica da lista
   * considerando que o primeiro elemento valido esta na posicao 0.
   * @param posicao Meio da remocao.
   * @return resp int elemento a ser removido.
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
      CelulaDupla i = primeiro.prox;
      for (int j = 0; j < pos; j++, i = i.prox);

      i.ant.prox = i.prox;
      i.prox.ant = i.ant;
      resp = i.serie;
      i.prox = i.ant = null;
      i = null;
    }

    return resp;
  }

  /**
   * Mostra os elementos da lista separados por espacos.
   */
  public void Imprimir() {
    for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
      i.serie.imprimir();
    }
  }

  /**
   * Mostra os elementos da lista de forma invertida
   * e separados por espacos.
   */
  public void mostrarInverso() {
    System.out.print("[ ");
    for (CelulaDupla i = ultimo; i != primeiro; i = i.ant) {
      System.out.print(i.serie + " ");
    }
    System.out.println("] "); // Termina de mostrar.
  }

  /**
   * Procura um elemento e retorna se ele existe.
   * @param x Elemento a pesquisar.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(Series s) {
    boolean resp = false;
    for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
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
    for (CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
    return tamanho;
  }

  public CelulaDupla getCelula(int pos) {
    if (pos < 0 || pos > tamanho()) {
      return null;
    }

    CelulaDupla resp = primeiro.prox;
    for (int i = 0; (i < pos && resp.prox != null); resp = resp.prox, i++);
    return resp;
  }

  public Series getSerie(int pos) {
    if (pos < 0 || pos > tamanho()) {
      return null;
    }

    CelulaDupla resp = primeiro.prox;
    for (int i = 0; (i < pos && resp.prox != null); resp = resp.prox, i++);
    return resp.serie;
  }

  public void swap(int pos1, int pos2) {
    CelulaDupla c1 = getCelula(pos1);
    CelulaDupla c2 = getCelula(pos2);
    Series tmp = c1.serie;

    c1.serie = c2.serie;
    c2.serie = tmp;
    tmp = null;
  }

  void ordenarQuicksort() throws Exception {
    if (tamanho() == 0) {
      throw new Exception("ERRO");
    }
    quicksort(0, tamanho() - 1);
  }

  public void quicksort(int esq, int dir) throws Exception {
    int i = esq, j = dir;
    Series pivo = getSerie((esq + dir) / 2);

    while (i <= j) {
      while (
        getSerie(i).getPais().compareTo(pivo.getPais()) < 0 ||
        (
          getSerie(i).getPais().compareTo(pivo.getPais()) == 0 &&
          getSerie(i).getNome().compareTo(pivo.getNome()) < 0
        )
      ) {
        i++;
        Comparacoes.cont += 2;
      }
      while (
        getSerie(j).getPais().compareTo(pivo.getPais()) > 0 ||
        (
          getSerie(j).getPais().compareTo(pivo.getPais()) == 0 &&
          getSerie(j).getNome().compareTo(pivo.getNome()) > 0
        )
      ) {
        j--;
        Comparacoes.cont += 2;
      }

      if (i <= j) {
        swap(i, j);
        Movimentacoes.cont++;
        i++;
        j--;
      }
    }
    if (esq < j) quicksort(esq, j);
    if (i < dir) quicksort(i, dir);
  }
}

public class TP03Q14 {

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

    ListaDupla listaSeries = new ListaDupla();

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
      listaSeries.ordenarQuicksort();
    } catch (Exception e) {
      //
    }

    listaSeries.Imprimir();

    Arq.openWrite("matricula_quicksort2.txt");
    tempo = ((new Date().getTime()) - tempo) / 1000;
    Arq.println(
      "734661\t" + Comparacoes.cont + "\t" + Movimentacoes.cont + String.format("\t%.4f", tempo)
    );
    Arq.close();
  }
}
