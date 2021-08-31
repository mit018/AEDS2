public class outro {
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
        boolean result = true;
        int[] array = new int[expression.length()];
        int pos = 0;
        int count = 0; // Count the number of variables in expression

        // Get the boolean values and put them in an array, to make operations after
        for (int i = 0; expression.charAt(i) != ')'; i++) {
            if (expression.charAt(i) == '0' || expression.charAt(i) == '1') {
                array[pos] = expression.charAt(i) - 48;
                pos++;
                count++;
            }
        }

        // Check if expression is a NOT
        if (expression.charAt(0) == 'n') {
            result = ! BooleanValue(array[0]);
        // Check if expression is a AND
        } else if (expression.charAt(0) == 'a') {
            for (int i = 0; i < count; i++) {
                if (array[i] == 0) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        // Check if expression is a OR
        } else if (expression.charAt(0) == 'o') {
            for (int i = 0; i < count; i++) {
                if (array[i] == 1) {
                    result = true;
                    break;
                } else {
                    result = false;
                }
            }
        } else {
            MyIO.println("Deu ruim irmao\n");
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
        int count = 0;
        int anotherParenthesis = 0;
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
                count = 0;
                anotherParenthesis = 0;
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
                count = 0;
                anotherParenthesis = 0;
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