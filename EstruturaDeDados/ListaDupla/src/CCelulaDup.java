
class CCelulaDup {

	public Object item; // O Item armazendo pela clula
	public CCelulaDup ant; // Referencia a celula anterior
	public CCelulaDup prox; // Referencia a proxima celula

	// Inicializa uma nova instancia da classe CCelulaDup atribuindo null aos
	// atributos Item, Ant e Prox.
	public CCelulaDup() {
		item = null;
		ant = null;
		prox = null;
	}

	// Inicializa uma nova instancia da classe CCelula atribuindo o valor passado
	// por parametro ao atributo Item e null aos atributos Ant e Prox.
	// O parametro "valorItem" recebe o valor a ser armazenado pela celula.
	public CCelulaDup(Object valorItem) {
		item = valorItem;
		ant = null;
		prox = null;
	}

	// Inicializa uma nova instancia da classe CCelula atribuindo ValorItem ao
	// atributo Item e ProxCelula ao atributo Prox.
	// O parametro "valorItem" recebe o valor a ser armazenado pela celula.
	// Os parametros "celulaAnt" e "proxCelula" recebem as referencias para a celula
	// anterior e para a proxima celula.
	public CCelulaDup(Object valorItem, CCelulaDup celulaAnt, CCelulaDup proxCelula) {
		item = valorItem;
		ant = celulaAnt;
		prox = proxCelula;
	}
}