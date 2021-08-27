import java.io.*;
import java.net.*;

public class TP01Q08 {

  public static void main(String[] args) {
    MyIO.setCharset("ISO-8859-1");
    String linha = MyIO.readLine();
    String nome = "", html = "";
    int contVog[] = new int[27], contCons = 0, contTag[] = new int[2]; // contadores de cada vogal, consoantes e cada tag
    int[] vogais = {
      97, // a
      101, // e
      105, // i
      111, // o
      117, // u
      225, // á
      233, // é
      237, // í
      243, // ó
      250, // ú
      224, // à
      232, // è
      236, // ì
      242, // ò
      249, // ù 
      227, // ã
      245, // õ
      226, // â
      234, // ê
      238, // î
      244, // ô
      251, // û 
      65, // A
      69, // E
      73, // I
      79, // O
      85, // U
    }; // vetor com todas as letras que devem ser contadas

    while (!(isFim(linha))) {
      for (int i = 0; i < 27; i++) {
        contVog[i] = 0;
      }
      for (int i = 0; i < 2; i++) {
        contTag[i] = 0;
      }
      contCons = 0; // zera todos os contadores a cada repeticao

      nome = linha; // primeira linha eh o nome do site
      linha = MyIO.readLine(); // segunda eh o link
      html = html(linha); // resgata o codigo fonte da pagina

      for (int i = 0; i < html.length(); i++) {
        int teste = isVogal(html.charAt(i), vogais);
        if (teste >= 0) {
          contVog[teste]++;
        } else {
          teste = isLetra(html.charAt(i));
          if (teste == 0) {
            contCons++;
          } else {
            teste = isTag(html, i);
            if (teste >= 0) {
              i = i + 3;
              if(teste == 0){
                i = i + 3;
              }
              contTag[teste]++;
            }
          }
        }
      }
      printInfo(vogais, contVog, contCons, contTag, nome);
      linha = MyIO.readLine();
    }
  }

  /**
   * Printa as informacoes em ordem 
   * @param vogais lista de vogais pesquisadas
   * @param vogCont contados para cada vogal
   * @param cons contador de consoantes
   * @param tags contador de tags
   * @param nome titulo do tema do link
   */
  public static void printInfo(int vogais[], int vogCont[], int cons, int tags[], String nome) {

    for (int i = 0; i < 22; i++) {
      System.out.print((char)(vogais[i]) + "(" + vogCont[i] + ") ");
    }

    System.out.print("consoante(" + cons + ") ");
    System.out.print("<br>(" + tags[1] + ") ");
    System.out.print("<table>(" + tags[0] + ") ");

    System.out.print(nome);
    System.out.println("");
  }

  /**
   * Resgata o codigo fonte de um link
   * @param link -> string contendo o link a ser acessado 
   * @return -> string com o codigo fonte da pagina (html)
   */
  public static String html(String endereco){
    URL url;
    InputStream is = null;
    BufferedReader br;
    String resp = "", line;

    try {
       url = new URL(endereco);
       is = url.openStream();  // throws an IOException
       br = new BufferedReader(new InputStreamReader(is));

       while ((line = br.readLine()) != null) {
          resp += line + "\n";
       }
    } catch (MalformedURLException mue) {
       mue.printStackTrace();
    } catch (IOException ioe) {
       ioe.printStackTrace();
    } 

    try {
       is.close();
    } catch (IOException ioe) {
       // nothing to see here

    }

    return resp;
 }

  /**
   * Testa se o caractere eh algum da lista de vogais procuradas
   * @param c caractere a ser comparado
   * @param vogais lista de vogais
   * @return posicao no vetor se encontou ou -1 se nao encontrou nada
   */
  public static int isVogal(char c, int vogais[]) {
    int i = 0;

    for (i = 0; i < vogais.length - 1; i++) {
      if (c == vogais[i]) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Testa se o char eh uma letra
   * @param c char a ser testado
   * @return 0 se sim, -1 se nao
   */
  public static int isLetra(char c) {
    if (c >= 97 && c <= 122) {
      return 0;
    }
    return -1;
  }

  /**
   * Testa se a string a partir da posicao indicada eh uma das duas tags procuradas
   * @param str string a ser testada
   * @param i posicao indicada
   * @return -1 se nao for, 1 ou 0 de acordo com a tag encontrada
   */
  public static int isTag(String str, int i) {
    if (str.charAt(i) == '<') {
      if (str.charAt(i + 1) == 't') {
        if (str.charAt(i + 2) == 'a') {
          if (str.charAt(i + 3) == 'b') {
            if (str.charAt(i + 4) == 'l') {
              if (str.charAt(i + 5) == 'e') {
                if (str.charAt(i + 6) == '>') {
                  return 0;
                }
              }
            }
          }
        }
      } else if (str.charAt(i + 1) == 'b') {
        if (str.charAt(i + 2) == 'r') {
          if (str.charAt(i + 3) == '>') {
            return 1;
          }
        }
      }
    }

    return -1;
  }

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
}
