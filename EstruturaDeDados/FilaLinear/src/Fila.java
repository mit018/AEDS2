/**
 * 
 * @author Rodrigo Richard Gomes - Fila estatica 
 * @version 1 10/2020
 * 
 */
public class Fila {
   private int[] array;
   private int frente; // Remove do indice "frente".
   private int tras; // Insere no indice "tras".

   /**
    * Construtor da classe.
    */
   public Fila () {
      this(5);
   }

   /**
    * Construtor da classe.
    * @param tamanho Tamanho da fila.
    */
   public Fila (int tamanho){
      array = new int[tamanho];
      frente = tras = 0;
   }

   /**
    * Insere um elemento na ultima posicao da fila.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a fila estiver cheia.
    */
   public boolean enfileira(int x) {
      /*if ((tras + 1) % array.length != frente) {
        array[tras] = x;
        tras = (tras + 1) % array.length;
        return true;
      }
      return false;*/
    if (tras - frente < array.length) {
       array[tras++] = x;
       return true;
     }
     return false;
	   
   }

   /**
    * Remove um elemento da primeira posicao da fila e movimenta 
    * os demais elementos para o frente da mesma.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a fila estiver vazia.
    */
   public int desenfileira() throws Exception {
      //validar remocao
      if (frente == tras)
         throw new Exception("Erro ao remover: FILA VAZIA!");

/*      int resp = array[frente];
      frente = (frente + 1) % array.length;
      return resp;*/
      return array[frente++];
   }

   /**
    * Mostra os array separados por espacos.
    */
   public void mostrar (){
      System.out.print("[ ");

      for(int i = frente; i != tras; i++)
         System.out.print(array[i%array.length] + " ");

      System.out.println("]");
   }

   public void mostrarRec(){
      System.out.print("[ ");
      mostrarRec(frente);
      System.out.println("]");
   }

   public void mostrarRec(int i){
      if(i != tras){
         System.out.print(array[i] + " ");
         mostrarRec((i + 1) % array.length);
      }
   }

   /**
    * Retorna um boolean indicando se a fila esta vazia
    * @return boolean indicando se a fila esta vazia
    */
   public boolean vazia() {
      return frente == tras; 
   }
}