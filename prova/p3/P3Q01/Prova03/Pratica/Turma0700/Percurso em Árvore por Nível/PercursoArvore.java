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

public class No {
    // Atributos
    public int elemento;
    public No esq;
    public No dir;

    // Metodos Especiais
    public No() {
        this.elemento = 0;
        this.esq = null;
        this.dir = null;
    }

    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

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

class ArvoreBinaria {
    
    //------------------------------------------------ Atributos ---------------------------------//
    public No raiz;

    //------------------------------------------------ Metodos Especiais ---------------------------------//
    public ArvoreBinaria() {
        raiz = null;
    }

    //------------------------------------------------ Metodos e funcoes ---------------------------------//

    // Inserir um elemento na arvore
    public void inserir(int elemento) throws Exception {
        raiz = inserir(elemento, raiz);
    }

    public No inserir(int elemento, No no) throws Exception {
        if (no == null) {
            no = new No(elemento);
        } else if (elemento < no.elemento) {
            no.esq = inserir(elemento, no.esq);
        } else if (elemento > no.elemento) {
            no.dir = inserir(elemento, no.dir);
        } else {
            throw new Exception("Erro, nao pode inserir um elemento repetido");
        }

        return no;
    }

    // Pesquisar um valor na arvore e retornar true caso encontrar
    public boolean pesquisar(int elemento) {
        return pesquisar(elemento, raiz);
    }

    public boolean pesquisar(int elemento, No no) {
        boolean resp;

        if (no == null) {
            resp = false;
        } else if (elemento == no.elemento) {
            resp = true;
        } else if (elemento < no.elemento) {
            resp = pesquisar(elemento, no.esq);
        } else {
            resp = pesquisar(elemento, no.dir);
        }

        return resp;
    }

    // Vai printar os elementos em ordem crescente
    public void caminharCentral(No no) {
        if (no != null) {
            caminharCentral(no.esq);
            System.out.println(no.elemento + " ");
            caminharCentral(no.dir);
        }
    }

    // Metodo para retornar altura da arvore
    public int getAltura() {
        int altura = 0;
        return getAltura(raiz, altura);
    }

    public int getAltura(No no, int altura) {
        if (no == null) {
            altura--;
        } else {
            int alturaEsq = getAltura(no.esq, altura + 1);
            int alturaDir = getAltura(no.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }

        return altura;
    }

    public void inserirPilhas(Pilha pilhas[]) {
        int i = 1;
        inserirPilhas(pilhas, raiz, i);
    }

    public void inserirPilhas(Pilha pilhas[], No no, int i) {
        if (no != null) {
            inserirPilhas(pilhas[], no.esq, ++i);

            if (no.elemento == raiz) {
                pilhas[0].inserir(raiz);
            }
            pilhas[i].inserir(no.elemento);
            i = 1;

            inserirPilhas(pilhas[], no.dir, ++i);
        }
    }
}


public class PercursoArvore {
    public static void main(String[] args) {
        ArvoreBinaria[] arvore = new ArvoreBinaria[quantidade];

        for (int i = 0; i < quantidade; i++) {
            arvore[i] = new ArvoreBinaria();
        }

        //Inserir elementos
        arvore[i].inserir()...

        int altura = arvore[i].getAltura();

        Pilha[] pilhas = new Pilha[altura];

        for (int i = 0; i < altura; i++) {
            pilhas[i] = new Pilha();
        }

        arvore[i].inserirPilhas(pilhas);
    }
}
