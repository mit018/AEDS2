/**
 * 
 * @author Rodrigo Richard Gomes
 *
 */
public class PilhaPrincipal {
	public static void main(String[] args) {
		System.out.println(" ==== PILHA DINAMICA ====");
		CPilha pilha = new CPilha();
		Object x1, x2, x3;

		pilha.empilha(0);
		pilha.empilha(1);
		pilha.empilha("dois");
		pilha.empilha(3);
		pilha.empilha(4);
		pilha.empilha(5);

		System.out.println("Apos insercoes: ");
		pilha.mostra();
		System.out.println("Qtde. elementos = "+pilha.quantidade());

		x1 = pilha.desempilha();
		x2 = pilha.desempilha();
		x3 = pilha.desempilha();

		System.out.println("Apos as remocoes (" + x1 + "," + x2 + "," + x3 + "): ");
		pilha.mostra();
		System.out.println("Qtde. elementos = "+pilha.quantidade());
		
		for(int i = 0; i < 5; i++)
			System.out.print(pilha.peek()+" ");
	}
}