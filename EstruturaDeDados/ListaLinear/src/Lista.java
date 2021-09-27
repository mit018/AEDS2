/**
 * 
 * @author Rodrigo Richard Gomes (baseado no código de Max do Val Machado)
 * @version 1 10/2020
 * 
 */
class Lista {
	private Object[] array;
	private int n;

	/**
	 * Construtor da classe.
	 */
	public Lista() {
		this(6);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param tamanho Tamanho da lista.
	 */
	public Lista(int tamanho) {
		array = new Object[tamanho];
		n = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e desloca os demais elementos
	 * para o fim da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirInicio(Object item) {
		if (n < array.length) {
			// Desloca elementos para o fim do array
			for (int i = n; i > 0; i--)
				array[i] = array[i - 1];

			array[0] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirFim(Object item) {
		// validar insercao
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento em uma posicao especifica e move os demais elementos para
	 * o fim da lista.
	 * 
	 * @param item: elemento a ser inserido.
	 * @param pos:  Posicao de insercao.
	 */
	public boolean inserir(Object item, int pos) {

		// validar insercao
		if (n < array.length && pos >= 0 && pos <= n) {
			// Desloca elementos para o fim do array
			for (int i = n; i > pos; i--)
				array[i] = array[i - 1];

			array[pos] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Object removerInicio() {
		if (n > 0) {
			Object item = array[0];
			n--;

			for (int i = 0; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Object removerFim() {
		if (n > 0)
			return array[--n];
		return null;
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @param pos: Posicao de remocao.
	 * @return Elemento a ser removido.
	 */
	public Object remover(int pos) {
		if (n > 0 && pos >= 0 && pos < n) {
			Object item = array[pos];
			n--;

			for (int i = pos; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("]");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * 
	 * @param x int elemento a ser pesquisado.
	 * @return true se o item existir, false caso contrário.
	 */
	public boolean pesquisar(Object item) {
		for (int i = 0; i < n; i++)
			if (array[i].equals(item))
				return true;
		return false;
	}
}