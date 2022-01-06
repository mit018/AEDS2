class Pilha {
    // Atributos
    public Celula topo;

    // Metodos especiais
    public Pilha() {
        topo = null;
    }

    public Pilha(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            Celula tmp = new Celula();
            tmp.prox = topo;
            topo = tmp;
            tmp = null;
        }
    }

    // Metodos e funcoes
    // Vai criar uma nova "caixinha" e fazer o topo apontar para ela
    public void inserir(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null; // Tecnicamente essa linha nao precisa, pois quando sair do metodo inserir o tmp iria desaparecer de qualquer maneira
    }

    // Vai remover um elemento, e fazer o topo apontar para o proximo
    public int remover() throws Exception {
        if (topo == null) {
            throw new Exception("Erro !!! topo igual a NULL | A pilha esta vazia.");
        }

        int elemento = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        //Desvincular a caixinha do elemento retirado
        tmp.prox = null;
        tmp = null;

        return elemento;
    }

    // Mostrar os elementos na mesma ordem da remocao
    public void mostrar() throws Exception {
        System.out.print("[ ");
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.print("]");
    }

    // Metodo que soma os elementos contidos na pilha
    public int somarConteudo() {
        int soma = 0;

        for (Celula i = topo; i != null; i = i.prox) {
            soma += i.elemento; // soma = soma + i.elemento
        }

        return soma;
    }
}

class Celula {
    // Atributos
    public int elemento;
    public Celula prox;

    // Metodos especiais
    public Celula() {
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

public class JogoDasPilhas {
    public static void main(String[] args) throws Exception {
        String[] entrada = new String[100];
        int w = 0;
        String linha = "";
        int resp = 0;


        linha = MyIO.readLine();
        while (!(linha.contains("0")) && linha.length() == 1) {
            int quantidadePilhas = Integer.parseInt(linha);
            int valor = quantidadePilhas;
            Pilha[] pilhas = new Pilha[quantidadePilhas];
            int p = 0;

            for (int i = 0; i < quantidadePilhas; i++) {
                pilhas[i] = new Pilha();
            }

            while (quantidadePilhas > 0) {
                linha = MyIO.readLine();

                String[] corteLinha = linha.split(" ");

                for (int i = 0; i < corteLinha.length; i++) {
                    pilhas[p].inserir(Integer.parseInt(corteLinha[i]));
                }

                //pilhas[p].mostrar();
                quantidadePilhas--;
                p++;
            }

            for (int i = 0; i < valor; i++) {
                int soma = pilhas[i].somarConteudo();

                if (soma % 3 == 0 && soma != 0) {
                    resp++;
                }
            }

            if (resp == valor) {
                System.out.println("1");
            } else {
                System.out.println("0");
            } 

            resp = 0;
            linha = MyIO.readLine();
        }
    }
}
