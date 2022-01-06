public class Main {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();
 
        //5, 8, 45, 18, 2, 16, 9, 31, 23, 40.

        arvore.inserir(5);
        arvore.inserir(8);
        arvore.inserir(45);
        arvore.inserir(18);
        arvore.inserir(2);
        arvore.inserir(16);
        arvore.inserir(9);
        arvore.inserir(31);
        arvore.inserir(23);
        arvore.inserir(40);

        System.out.println(arvore.getAltura(5));
        // System.out.println(arvore.getAltura(2));
        // System.out.println(arvore.getAltura(3));

        // System.out.println(arvore.getProfundidade(5));
        // System.out.println(arvore.getProfundidade(2));
        // System.out.println(arvore.getProfundidade(3));

        System.out.println(arvore.misterio());

    }
}