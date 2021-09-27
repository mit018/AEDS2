/**
 * 
 * @author Rodrigo Richard Gomes - Fila dinamica
 * @version 1 10 2020
 * 
 */
class FilaPrincipal {
	
	public static CFila PegaOs2Primeiros(CFila F1, CFila F2) {
	  CFila temp = new CFila();
	  temp.enfileira(F1.peek());
	  temp.enfileira(F2.peek());
	  return temp;
	}

	public static int SomaOs2Primeiros(CFila F1, CFila F2) {
		  int Soma = 0;
		  Soma = (int)F1.peek();
		  Soma += (int)F2.peek();
		  return Soma;
		}

   public static void main(String[] args) throws Exception {
      System.out.println("==== FILA DINAMICA ====");
      CFila fila = new CFila();
      CFila fila2 = new CFila();
      CFila F2primeiros;
      
      fila.enfileira(1);
      fila.enfileira(2);
      fila.enfileira(3);
      
      fila2.enfileira(3);
      fila2.enfileira(4);
      fila2.enfileira(5);
      
      F2primeiros = PegaOs2Primeiros(fila, fila2);
      
      F2primeiros.mostra();
      
      System.out.println(SomaOs2Primeiros(fila, fila2));

      //CFila F = new CFila();
      /*
      Object x1, x2, x3;

      fila.enfileira(5);
      fila.enfileira(7);
      fila.enfileira(8);
      fila.enfileira(9);
      
      System.out.println("Apos insercoes(5, 7, 8, 9): ");
      fila.mostra();
      
      System.out.println("A fila contém o elemento 7? "+fila.contem(7));

      x1 = fila.desenfileira();
      x2 = fila.desenfileira();

      System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
      fila.mostra();
      
      System.out.println("A fila contém o elemento 7? "+fila.contemFor(7));

      fila.enfileira(3);
      fila.enfileira(4);

      System.out.println("Apos insercoes(3, 4): ");
      fila.mostra();

      x1 = fila.desenfileira();
      x2 = fila.desenfileira();
      x3 = fila.desenfileira();

      System.out.println("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
      fila.mostra();

      fila.enfileira(4);
      fila.enfileira(5);

      System.out.println("Apos insercoes(4, 5): ");
      fila.mostra();

      x1 = fila.desenfileira();
      x2 = fila.desenfileira();

      System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
      fila.mostra();

      fila.enfileira(6);
      fila.enfileira(7);

      System.out.println("Apos insercoes(6, 7): ");
      fila.mostra();

      x1 = fila.desenfileira();

      System.out.println("Apos remocao (" + x1 + "): ");
      fila.mostra();
      
      fila.enfileira("Marcus");
      fila.enfileira("Julia");
      System.out.println("Apos insercoes(Marcus, Julia): ");
      fila.mostra(); */
   }
}