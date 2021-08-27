public class TP01Q05 {
  public static boolean isFim(String s) {
      return (s.length() == 1 && s.charAt(0) == '0');
  }

  public static boolean BooleanValue(int valor) {
      if (valor == 0) {
          return false;
      } else {
          return true;
      }
  }

  public static String ExpressionSolver(String expression) {
      boolean result = false;
      // If length == 6 is a NOT with 1 variable
      if (expression.length() == 6) {
          if (expression.charAt(4) == '0') {
              result = true;
          } else {
              result = false;
          }
      // If length == 8 or 10 is a AND
      } else if (expression.length() == 8 || expression.length() == 10) {
          if (expression.length() == 8) {
              result = BooleanValue(Character.getNumericValue(expression.charAt(4))) && BooleanValue(Character.getNumericValue(expression.charAt(6)));
          } else {
              result = BooleanValue(Character.getNumericValue(expression.charAt(4))) && BooleanValue(Character.getNumericValue(expression.charAt(6))) && BooleanValue(Character.getNumericValue(expression.charAt(8)));
          }
      // If length == 7 or 9 or 11 is a OR
      } else if (expression.length() == 7 || expression.length() == 9 || expression.length() == 11) {
          if (expression.length() == 7) {
              result = BooleanValue(Character.getNumericValue(expression.charAt(3))) || BooleanValue(Character.getNumericValue(expression.charAt(5)));
          } else if (expression.length() == 9) {
              result = BooleanValue(Character.getNumericValue(expression.charAt(3))) || BooleanValue(Character.getNumericValue(expression.charAt(5))) || BooleanValue(Character.getNumericValue(expression.charAt(7)));
          } else {
              result = BooleanValue(Character.getNumericValue(expression.charAt(3))) || BooleanValue(Character.getNumericValue(expression.charAt(5))) || BooleanValue(Character.getNumericValue(expression.charAt(7))) || BooleanValue(Character.getNumericValue(expression.charAt(9)));
          }
      }

      if (result == true) {
          return "1";
      } else {
          return "0";
      }
  }

  public static String CalcularExpressao(String expressao, int quantidade) {
      int[] array = new int[quantidade];
      int j = 2;
      boolean bool = false;
      String expression = "";

      for (int i = 0; i < quantidade; i = i + 1) {
          array[i] = ((int) expressao.charAt(j)) - 48;
          j = j + 2;
      }

      for (int i = 0; i < expressao.length(); i++) {
          if (expressao.charAt(i) == ' ') {
              expression = expression + "";
          } else if (expressao.charAt(i) == 'A') {
              expression = expression + array[0];
          } else if (expressao.charAt(i) == 'B') {
              expression = expression + array[1];
          } else if (expressao.charAt(i) == 'C') {
              expression = expression + array[2];
          } else if (!(expressao.charAt(i) >= 48 && expressao.charAt(i) <= 57)) {
              expression = expression + expressao.charAt(i);
          }
      }
      
      while (expression.length() > 1) {
          // Do all NOT
          for (int i = 0; i < expression.length(); i++) {
              if (expression.charAt(i) == 'n' && expression.charAt(i + 1) == 'o' && expression.charAt(i + 2) == 't' && expression.charAt(i + 5) == ')') {
                  expression = expression.substring(0, i) + ExpressionSolver(expression.substring(i, i + 6)) + expression.substring(i + 6);
              }
          }

          // Do all AND
          for (int i = 0; i < expression.length(); i++) {
              int count = 0;
              int anotherParenthesis = 0;
              if (expression.charAt(i) == 'a' && expression.charAt(i + 1) == 'n' && expression.charAt(i + 2) == 'd') {
                  for (int end = i + 4; anotherParenthesis == 0; end++) {
                      count++;

                      if (expression.charAt(end) == '(') {
                          anotherParenthesis = -1;
                      } else if (expression.charAt(end) == ')') {
                          anotherParenthesis++;
                      }
                  }

                  if (anotherParenthesis != -1) {
                      expression = expression.substring(0, i) + ExpressionSolver(expression.substring(i, i + count + 4)) + expression.substring(i + count + 4);
                  }
              }
          }

          // Do all OR
          for (int i = 0; i < expression.length(); i++) {
              int count = 0;
              int anotherParenthesis = 0;
              if (expression.charAt(i) == 'o' && expression.charAt(i + 1) == 'r') {
                  for (int end = i + 3; anotherParenthesis == 0; end++) {
                      count++;

                      if (expression.charAt(end) == '(') {
                          anotherParenthesis = -1;
                      } else if (expression.charAt(end) == ')') {
                          anotherParenthesis++;
                      }
                  }

                  if (anotherParenthesis != -1) {
                      expression = expression.substring(0, i) + ExpressionSolver(expression.substring(i, i + count + 3)) + expression.substring(i + count + 3);
                  }
              }
          }
      }

      return expression;
  }

  public static void main(String[] args) {
      // Declarando Variáveis e charset
      MyIO.setCharset("ISO-8859-1");
      String[] entrada = new String[1000];
      int numEntrada = 0;
      int quantidade = 0;
      String resp = "";

      // Leitura da entrada padrao
      do {
          entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

      for (int i = 0; i < numEntrada; i = i + 1) {
          // Vai chamar a função, retornar a string modificada ou nao e depois printar
          quantidade = ((int) entrada[i].charAt(0)) - 48;
          resp = CalcularExpressao(entrada[i], quantidade);
          MyIO.println(resp);
      }
  }
}