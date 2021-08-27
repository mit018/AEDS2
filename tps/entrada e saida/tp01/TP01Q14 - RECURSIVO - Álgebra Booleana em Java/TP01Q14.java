public class TP01Q14 {

    public static void main(String[] args) {
      String linha = MyIO.readLine();
      String exp = "";
      int i = 0, j = 0, cont = 0, x[] = new int[100];
  
      while (!(isFim(linha))) {
        exp = "";
        cont = 0;
        i = 0;
        int n = linha.charAt(i) - 48;
        for (i = 1; (cont <= n) && (i < linha.length()); i++) {
          if (linha.charAt(i) != ' ') {
            x[cont] = linha.charAt(i) - 48;
            cont++;
          }
        }
  
        for (j = i - 1; j < linha.length(); j++) {
          if (linha.charAt(j) != ' ') { // tira todos os espaços
            exp += linha.charAt(j);
          }
        }
  
        MyIO.println(processar(exp, x));
  
        linha = MyIO.readLine();
      }
    }
    
    /**
     * Processa as string de expressoes booleanas recursivamente
     * @param str string com a expressao
     * @param var vetor com os valores binarios setados
     * @return 0 ou 1
     */
    public static int processar(String str, int var[]) {
      int i = 0, j = 0, y = 0, open = 0, teste = 0, f = 0;
      String item[] = new String[100];
      int itemProc[] = new int[100];
  
      for (int k = 0; k < 100; k++) {
        item[k] = "";
      }
      char vir = ',';
  
      for (i = 0; i < str.length(); i++) {
        if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
          return var[str.charAt(i) - 65];
        } else {
          teste = testeBool(str, i);
          if(teste == 1 || teste == 3){
            f = 4;
          }else if(teste == 2){
            f = 3;
          }
          if (teste != 0) {
            for (j = i + f; j < str.length() - 1; j++) {
              if (str.charAt(j) == '(') {
                open++;
              } else if (str.charAt(j) == ')') {
                open--;
              }
              if (open != 0) {
                vir = ' ';
              }
              else if(open == 0){
                vir = ',';
              }
              if (str.charAt(j) != vir) {
                item[y] += str.charAt(j);
              } else {
                y++;
              }
            } // fim for
  
            for (int k = 0; k < y + 1; k++) {
              itemProc[k] = processar(item[k], var);
            }
            if (teste == 1) {
              return and(itemProc, y + 1);
            } else if (teste == 2) {
              return or(itemProc, y + 1);
            } else {
              return not(itemProc[0]);
            }
          }
        }
      }
  
      return -1;
    }
  
    public static int testeBool(String str, int i) {
      if (
        str.charAt(i) == 'a' &&
        str.charAt(i + 1) == 'n' &&
        str.charAt(i + 2) == 'd'
      ) {
        return 1;
      } else if (str.charAt(i) == 'o' && str.charAt(i + 1) == 'r') {
        return 2;
      } else if (
        str.charAt(i) == 'n' &&
        str.charAt(i + 1) == 'o' &&
        str.charAt(i + 2) == 't'
      ) {
        return 3;
      }
  
      return 0;
    }
  
    //3 0 0 0 or(or(and(not(and(A , B)) , not(C)) , and(not(A) , B , C) , and(A , B , C) , and(A , not(B) , not(C))) , and(A , not(B) , C))
  
    public static int and(int a[], int n) {
      for (int i = 0; i < n; i++) {
        if (a[i] == 0) {
          return 0;
        }
      }
  
      return 1;
    }
  
    public static int or(int a[], int n) {
      for (int i = 0; i < n; i++) {
        if (a[i] == 1) {
          return 1;
        }
      }
  
      return 0;
    }
  
    public static int not(int a) {
      if (a == 0) {
        return 1;
      }
      return 0;
    }
  
    public static boolean isFim(String s) {
      return (
        s.length() == 1 &&
        s.charAt(0) == '0'
      );
    }
  }
  //    3 1 1 1 and(A , or(B , C))
  