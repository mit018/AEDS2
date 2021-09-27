/**
 * 
 * @author Rodrigo Richard Gomes - Fila Circular estatica
 * @version 1 10/2020
 * 
 */
public class FilaCircular {
   private Object[] array;
   private int frente; // Aponta para a posição do vetor que armazena o primeiro elemento da fila
   private int tras;   // Aponta para a posição do vetor que armazena o último elemento da fila

   /**
    * Construtor da classe.
    */
   public FilaCircular() {
      this(5);
   }

   /**
    * Construtor da classe.
    * @param tamanho Tamanho da fila.
    */
   public FilaCircular(int tamanho){
      array = new Object[tamanho];
      frente = tras = 0;
   }

   /**
    * Insere um elemento na última posição da fila.
    * @param Object item: elemento a ser inserido.
    */
   public boolean enfileira(Object item) {
    if (tras - frente < array.length) {
       array[tras % array.length] = item;
       tras++;
       return true;
     }
     return false;
   }

   /**
    * Remove o elemento armazenado no posição referenciada pelo índice "frente".
    * @return Elemento desenfileirado.
    */
   public Object desenfileira() {
      //validar remocao
      if (frente == tras)
         return null;
      else 
        return array[frente++];
   }

    public void imprimeVet() {
      System.out.print("[ ");
      for(int i = 0; i < array.length; i++)
         System.out.print(array[i] + " ");
      System.out.println("]");
    }
    
    /**
     * Mostra os elementos da Fila separados por espaços.
     */    
   public void mostrar (){
      System.out.print("[ ");

      for(int i = frente; i < tras; i++)
         System.out.print(array[i % array.length] + " ");

      System.out.println("]");
   }

   /**
    * Mostra os elementos da Fila separados por espaços (método recursivo).
    */  
   public void mostrarRec(){
      System.out.print("[ ");
      mostrarRec(frente);
      System.out.println("]");
   }

   public void mostrarRec(int i){
      if(i != tras){
         System.out.print(array[i % array.length] + " ");
         mostrarRec(++i);
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