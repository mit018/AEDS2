/**
 * @author Rodrigo Richard Gomes
 *
 */
public class FilaPrincipal {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	    FilaCircular F = new FilaCircular(5);
	    F.enfileira(1);
	    F.enfileira(2);
	    F.enfileira(3);
	    F.enfileira(4);
	    F.enfileira(5);
	    F.enfileira(6);
	    F.mostrar();
	    System.out.println("Item removido = "+F.desenfileira());
	    F.enfileira(6);
	    F.mostrar();
	}

}
