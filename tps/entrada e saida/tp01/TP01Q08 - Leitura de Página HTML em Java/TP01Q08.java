import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TP01Q08 {

  public static void main(String[] args) {
    String linha = MyIO.readLine();
    String nome = "", html = "";
    int contVog[] = new int[27], contCons = 0, contTag[] = new int[2];
    char[] vogais = {
      'a',
      'e',
      'i',
      'o',
      'u',
      'á',
      'é',
      'í',
      'ó',
      'ú',
      'à',
      'è',
      'ì',
      'ò',
      'ù',
      'ã',
      'õ',
      'â',
      'ê',
      'î',
      'ô',
      'û',
      'A',
      'E',
      'I',
      'O',
      'U',
    };

    while (!(isFim(linha))) {
      for (int i = 0; i < 27; i++) {
        contVog[i] = 0;
      }
      for (int i = 0; i < 2; i++) {
        contTag[i] = 0;
      }
      contCons = 0;

      nome = linha;
      linha = MyIO.readLine();
      html = html(linha);

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

  public static void printInfo(
    char vogais[],
    int vogCont[],
    int cons,
    int tags[],
    String nome
  ) {
    for (int i = 0; i < 22; i++) {
      MyIO.print(vogais[i] + "(" + vogCont[i] + ") ");
    }
    MyIO.print("consoante(" + cons + ") ");
    MyIO.print("<br>(" + tags[1] + ") ");
    MyIO.print("<table>(" + tags[0] + ") ");
    MyIO.print(nome);
    MyIO.println("");
  }

  public static String html(String link) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(link))
      .GET()
      .build();
    String str = "";
    try {
      HttpResponse response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
      );
      str = response.body().toString();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return str;
  }

  public static int isVogal(char c, char vogais[]) {
    int i = 0;

    for (i = 0; i < vogais.length - 1; i++) {
      if (c == vogais[i]) {
        return i;
      }
    }

    return -1;
  }

  public static int isLetra(char c) {
    if (c >= 97 && c <= 122) {
      return 0;
    }
    return -1;
  }

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

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }
}
