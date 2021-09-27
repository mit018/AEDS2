public class PrincipalLista {
   public static void main(String[] args) throws Exception {
      System.out.println("==== LISTA ESTATICA ====");
      Lista lista = new Lista(); // Lista de Inteiros
      Lista alunos = new Lista(); // Lista de Strings
      int x1, x2, x3;

      lista.inserirInicio(1);
      lista.inserirInicio(0);
      lista.inserirFim(2);
      lista.inserirFim(3);
      lista.inserir(4, 3);
      lista.inserir(5, 2);

      System.out.print("Apos insercoes: ");
      lista.mostrar();

      x1 = (int)lista.removerInicio(); // Typecasting
      x2 = (int)lista.removerFim();
      x3 = (int)lista.remover(2);

      System.out.print("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
      lista.mostrar();
      
      alunos.inserirFim("Hugo");
      alunos.inserirFim("Pedro");
      alunos.inserirFim("Daniella");
      alunos.inserirFim("Ian");
      alunos.inserirFim("Isabel");
      alunos.inserirFim("Leonardo");
      alunos.inserir("Alexandre", 3);
      
      alunos.mostrar();    
   }
}