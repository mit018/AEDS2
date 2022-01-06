/**
 * Principal para Arvore Binaria de Pesquisa
 * @author Max do Val Machado
 */
import java.util.*;

public class Principal2 {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria a = new ArvoreBinaria();

       a.inserir(15);
       a.inserir(6);
       a.inserir(18);
       a.inserir(3);
       a.inserir(7);
       a.inserir(17);
       a.inserir(20);
       a.inserir(2);
       a.inserir(4);
       a.inserir(13);
       a.inserir(9);

       a.remover(7);
       a.remover(15);
       a.remover(18);

       a.caminharPos();
    }
}
