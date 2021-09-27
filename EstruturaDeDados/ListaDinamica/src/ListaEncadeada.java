public class ListaEncadeada {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CLista L = new CLista();
		L.insereFim(9);
		L.insereFim(2);		
		L.insereFim(4);
		L.insereFim(1);
		L.imprime();
		System.out.println();
		L.imprimeFormatoLista();
		L.imprimeFormatoLista("IMPRIMINDO NOVAMENTE\n====================");

	}

}