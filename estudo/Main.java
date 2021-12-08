public class Main {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(1);

        // System.out.println(arvore.getAltura(5));
        // System.out.println(arvore.getAltura(2));
        // System.out.println(arvore.getAltura(3));

        // System.out.println(arvore.getProfundidade(5));
        // System.out.println(arvore.getProfundidade(2));
        // System.out.println(arvore.getProfundidade(3));

        arvore.getNoInfo();

        System.out.println(arvore.getNos());
        System.out.println(arvore.getSegMenor());

        arvore.getFolhasEsq();
        arvore.getFolhasDir();

        arvore.caminharDecrescente();

    }
}