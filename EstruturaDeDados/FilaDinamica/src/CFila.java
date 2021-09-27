/**
 * 
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 * 
 */
public class CFila {
	private CCelula frente; // Celula cabeca.
	private CCelula tras; // Ultima celula.
	private int qtde;

	/**
	 * Função construtora. Cria a célula cabeça e faz as referências frente e tras
	 * apontarem para ela.
	 */
	public CFila() {
		frente = new CCelula();
		tras = frente;
	}

	/**
	 * Verifica se a fila está vazia.
	 * 
	 * @return Retorna TRUE se a fila estiver vazia e FALSE caso contrário.
	 */
	public boolean vazia() {
		return frente == tras;
	}

	/**
	 * Imprime os elementos da fila
	 */
	public void mostra() {
		System.out.print("[ ");
		for (CCelula c = frente.prox; c != null; c = c.prox)
			System.out.print(c.item + " ");
		System.out.println("] ");
	}

	/**
	 * Insere um novo Item no fim da fila.
	 * 
	 * @param valorItem O parametro "valorItem" é um Object contendo o elemento a
	 *                  ser inserido no final da fila.
	 */
	public void enfileira(Object valorItem) {
		tras.prox = new CCelula(valorItem);
		tras = tras.prox;
		qtde++;
	}

	/**
	 * Retira e retorna o primeiro elemento da fila.
	 * 
	 * @return Retorna um Object contendo o primeiro elemento da fila. Caso a fila
	 *         esteja vazia retorna null.
	 */
	public Object desenfileira() {
		Object item = null;
		if (frente != tras) {
			frente = frente.prox;
			item = frente.item;
			qtde--;
		}
		return item;
	}
		
	// Idêntico ao método anterior, mas sem usar a variável temporária item
	public Object desenfileirav2() {
		if (frente != tras) {
			frente = frente.prox;
			qtde--;
			return frente.item;
		}
		return null;
	}
	

	/**
	 * Retorna o primeiro Item da fila sem remove-lo.
	 * 
	 * @return Retorna um Object contendo o primeiro Item da fila.
	 */
	public Object peek() {
		if (frente != tras)
			return frente.prox.item;
		else
			return null;
		//return (frente != tras)? frente.prox.item : null;
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila.
	 * 
	 * @param valorItem O parametro "valorItem" e um object contendo o Item a ser
	 *                  localizado.
	 * @return O método retorna TRUE caso o item esteja presente na fila.
	 */
	public boolean contem(Object valorItem) {
		CCelula aux = frente.prox;
		while (aux != null) {
			if (aux.item.equals(valorItem))
				return true;
			aux = aux.prox;
		}
		return false;
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila. (Obs: usa o
	 * comando FOR)
	 * 
	 * @param (valorItem) Recebe como parametro um object contendo o Item a ser
	 *                    localizado.
	 * @return Retorna TRUE caso o Item esteja presente na fila.
	 */
	public boolean contemFor(Object valorItem) {
		for (CCelula aux = frente.prox; aux != null; aux = aux.prox)
			if (aux.item.equals(valorItem))
				return true;
		return false;
	}

	/**
	 * Metodo que retorna a quantidade de itens da fila.
	 * 
	 * @return
	 */
	public int quantidade() {
		return qtde;
	}

}