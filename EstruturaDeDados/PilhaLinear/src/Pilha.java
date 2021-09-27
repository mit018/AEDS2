/**
 * 
 * @author Rodrigo Richard Gomes
 * @version 1 10/2020
 * 
 */
class Pilha {
	private Object[] array;
	private int qtde;

	/**
	 * Construtor da classe.
	 */
	public Pilha() {
		this(5);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param tamanho Tamanho da pilha.
	 */
	public Pilha(int tamanho) {
		array = new Object[tamanho];
		qtde = 0;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean empilha(Object item) {
		// Validar a inserção
		if (qtde < array.length) {
			array[qtde] = item;
			qtde++;
			return true;
		}
		return false;
	}

	/**
	 * Desempilha um elemento da pilha (o último elemento inserido).
	 * 
	 * @return Elemento removido.
	 */
	public Object desempilha() {
		if (qtde > 0)
			return array[--qtde];
		return null;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		for (int i = qtde-1; i >= 0; i--) {
			System.out.println("["+array[i]+"]");
		}
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * 
	 * @param item: O elemento a ser pesquisado.
	 * @return Retorna true se o item existir, false caso contrário.
	 */
	public boolean pesquisar(Object item) {
		for (int i = 0; i < qtde; i++)
			if (array[i].equals(item))
				return true;
		return false;
	}
}