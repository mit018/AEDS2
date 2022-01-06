import java.util.Random;

class No {
    // Atributos
    public int elemento;
    public No esq, dir;
    public int nivel;

    // Metodos especiais
    public No(int elemento) {
        this(elemento, null, null, 1);
    }

    public No(int elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }

    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }
}

class ArvoreAVL {
    // Atributos
    private No raiz;

    // Metodos especiais
    public ArvoreAVL() {
        raiz = null;
    }

    // Funcoes e metodos
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (x == i.elemento) {
            resp = true;

        } else if (x < i.elemento) {
            resp = pesquisar(x, i.esq);

        } else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return balancear(i);
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(int x, No i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);

            // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // No a esquerda e no a direita.
        } else {
            i.esq = antecessor(i, i.esq);
        }

        return balancear(i);
    }

    private No antecessor(No i, No j) {
        if (j.dir != null) {
            j.dir = antecessor(i, j.dir);
        } else {
            i.elemento = j.elemento;
            j = j.esq;
        }
        return j;
    }

    private No balancear(No no) throws Exception {
        if (no != null) {
            int fator = No.getNivel(no.dir) - no.getNivel(no.esq);

            if (Math.abs(fator) <= 1) {
                no.setNivel();
            } else if (fator == 2) {

                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);

                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                }
                no = rotacionarEsq(no);

            } else if (fator == -2) {

                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);

                if (fatorFilhoEsq == 1) {
                    no.esq = rotacionarEsq(no.esq);
                }
                no = rotacionarDir(no);

            } else {
                throw new Exception("Erro fator de balanceamento (" + fator + ") invalido!");
            }
        }

        return no;
    }

    private No rotacionarDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setNivel();
        noEsq.setNivel();

        return noEsq;
    }

    private No rotacionarEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setNivel();
        noDir.setNivel();
        return noDir;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    public void mostrar(No no) {
        if (no != null) {
            mostrar(no.esq);
            System.out.println("T2 = " + no.elemento);
            mostrar(no.dir);
        }
    }
}

class NoAN {
    // Atributos
    public boolean cor;
    public int elemento;
    public NoAN esq, dir;

    // Metodos especiais
    public NoAN() {
        this(-1);
    }

    public NoAN(int elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(int elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(int elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBicolor {
    // Atributos
    private NoAN raiz;

    // Metodos especiais
    public ArvoreBicolor() {
        raiz = null;
    }

    // Funcoes e metodos
    public boolean pesquisar(int elemento) {
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(int elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (elemento == i.elemento) {
            resp = true;

        } else if (elemento < i.elemento) {
            resp = pesquisar(elemento, i.esq);

        } else {
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(NoAN no) {
        if (no != null) {
            mostrar(no.esq);
            System.out.println("T4 = " + no.elemento);
            mostrar(no.dir);
        }
    }

    public void inserir(int elemento) throws Exception {

        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento, false);
        } else if (raiz.esq == null && raiz.dir == null) {
            if (raiz.elemento > elemento) {
                raiz.esq = new NoAN(elemento, true);
            } else {
                raiz.dir = new NoAN(elemento, true);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {

            if (raiz.elemento > elemento) {
                raiz.esq = new NoAN(elemento);

            } else if (raiz.dir.elemento > elemento) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {

            if (raiz.elemento < elemento) {
                raiz.dir = new NoAN(elemento);
            } else if (raiz.esq.elemento < elemento) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {

        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {

            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento > avo.elemento) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento > pai.elemento) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento < pai.elemento) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento < bisavo.elemento) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }

            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    private void inserir(int elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {

            if (elemento < pai.elemento) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }

            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }

        } else {

            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }

            if (elemento < i.elemento) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento > i.elemento) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

class HashT2 {
    // Atributos
    public int tamanho;
    public int[] tabela;
    public int NULO = -0x7FFFFF;
    public ArvoreAVL arvore;

    // Metodos especiais
    public HashT2() {
        this.tamanho = 10;
        this.tabela = new int[10];

        for (int i = 0; i < this.tamanho; i++) {
            this.tabela[i] = NULO;
        }

        arvore = new ArvoreAVL();
    }

    public HashT2(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];

        for (int i = 0; i < this.tamanho; i++) {
            this.tabela[i] = NULO;
        }

        arvore = new ArvoreAVL();
    }

    // Funcoes e metodos
    public int hashT2(int elemento) {
        return elemento % tamanho;
    }

    public int rehashT2_1(int elemento) {
        return ++elemento % tamanho;
    }

    public int rehashT2_2(int elemento) {
        return (elemento + 2) % tamanho;
    }

    public void inserir(int elemento) throws Exception {
        if (elemento != NULO) {
            int posicao = hashT2(elemento);

            if (tabela[posicao] == NULO) {
                tabela[posicao] = elemento;
            } else {
                posicao = rehashT2_1(elemento);

                if (tabela[posicao] == NULO) {
                    tabela[posicao] = elemento;
                } else {
                    posicao = rehashT2_2(elemento);

                    if (tabela[posicao] == NULO) {
                        tabela[posicao] = elemento;
                    } else {
                        arvore.inserir(elemento);
                    }
                }
            }
        }
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        if (elemento != NULO) {
            int posicao = hashT2(elemento);

            if (tabela[posicao] == elemento) {
                resp = true;
            } else {
                posicao = rehashT2_1(elemento);

                if (tabela[posicao] == elemento) {
                    resp = true;
                } else {
                    posicao = rehashT2_2(elemento);

                    if (tabela[posicao] == elemento) {
                        resp = true;
                    } else {
                        resp = arvore.pesquisar(elemento);
                    }
                }
            }
        }

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != NULO) {
                System.out.println("T2 = " + tabela[i]);
            }
        }

        arvore.mostrar();
    }
}

class HashT3 {
    // Atributos
    public int tamanho;
    public int[] tabela;
    public int NULO = -0x7FFFFF;
    public int reserva = 3;
    public int contador;

    // Metodos especiais
    public HashT3() {
        this.tamanho = 10;
        this.tabela = new int[10 + 3];

        for (int i = 0; i < tamanho + 3; i++) {
            this.tabela[i] = NULO;
        }

        contador = 0;
    }

    public HashT3(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho + 3];

        for (int i = 0; i < tamanho + 3; i++) {
            this.tabela[i] = NULO;
        }

        contador = 0;
    }

    // Funcoes e metodos
    public int hashT3(int elemento) {
        return elemento % tamanho;
    }

    public void inserir(int elemento) {
        if (elemento != NULO) {
            int posicao = hashT3(elemento);

            if (tabela[posicao] == NULO) {
                tabela[posicao] = elemento;
            } else if (contador < reserva) {
                tabela[tamanho + contador] = elemento;
                contador++;
            }
        }
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        if (elemento != NULO) {
            int posicao = hashT3(elemento);

            if (tabela[posicao] == elemento) {
                resp = true;
            } else if (tabela[posicao] != NULO) {
                for (int i = 0; i < reserva; i++) {
                    if (tabela[tamanho + i] == elemento) {
                        resp = true;
                        i = reserva;
                    }
                }
            }
        }

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < tamanho + 3; i++) {
            if (tabela[i] != NULO) {
                System.out.println("T3 = " + tabela[i]);
            }
        }
    }
}

class HashT4 {
    // Atributos
    public int tamanho;
    public ArvoreBicolor[] tabela;

    // Metodos especiais
    public HashT4() {
        this.tamanho = 10;
        this.tabela = new ArvoreBicolor[10];

        for (int i = 0; i < 10; i++) {
            tabela[i] = new ArvoreBicolor();
        }
    }

    public HashT4(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ArvoreBicolor[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBicolor();
        }
    }

    // Funcoes e metodos
    public int hashT4(int elemento) {
        return elemento % tamanho;
    }

    public void inserir(int elemento) throws Exception {
        int posicao = hashT4(elemento);
        tabela[posicao].inserir(elemento);
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        int posicao = hashT4(elemento);
        resp = tabela[posicao].pesquisar(elemento);

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < tamanho; i++) {
            tabela[i].mostrar();
        }
    }
}

class Doidona {
    // Atributos
    public int tamanhoT1, tamanhoT2, tamanhoT3, tamanhoT4;
    public int NULO = -0x7FFFFF;
    public int[] tabela;
    public HashT2 hashT2;
    public HashT3 hashT3;
    public HashT4 hashT4;

    // Metodos especiais
    public Doidona() {
        this.tamanhoT1 = 10;
        this.tamanhoT2 = 10;
        this.tamanhoT3 = 10;
        this.tamanhoT4 = 10;
        this.tabela = new int[10];

        for (int i = 0; i < tamanhoT1; i++) {
            tabela[i] = NULO;
        }

        hashT2 = new HashT2();
        hashT3 = new HashT3();
        hashT4 = new HashT4();
    }

    public Doidona(int tamanhoT1, int tamanhoT2, int tamanhoT3, int tamanhoT4) {
        this.tamanhoT1 = tamanhoT1;
        this.tamanhoT2 = tamanhoT2;
        this.tamanhoT3 = tamanhoT3;
        this.tamanhoT4 = tamanhoT4;
        this.tabela = new int[tamanhoT1];

        for (int i = 0; i < tamanhoT1; i++) {
            tabela[i] = NULO;
        }

        hashT2 = new HashT2(tamanhoT2);
        hashT3 = new HashT3(tamanhoT3);
        hashT4 = new HashT4(tamanhoT4);
    }

    // Funcoes e metodos
    public int hash(int elemento) {
        return elemento % tamanhoT1;
    }

    public int hashReserva(int elemento) {
        return elemento % 3;
    }

    public void inserir(int elemento) throws Exception {
        if (elemento != NULO) {
            int posicao = hash(elemento);

            if (tabela[posicao] == NULO) {
                tabela[posicao] = elemento;
            } else {
                posicao = hashReserva(elemento);

                if (posicao == 0) {
                    hashT2.inserir(elemento);
                } else if (posicao == 1) {
                    hashT3.inserir(elemento);
                } else {
                    hashT4.inserir(elemento);
                }
            }
        }
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        if (elemento != NULO) {
            int posicao = hash(elemento);

            if (tabela[posicao] == elemento) {
                resp = true;
            } else {
                posicao = hashReserva(elemento);

                if (posicao == 0) {
                    resp = hashT2.pesquisar(elemento);
                } else if (posicao == 1) {
                    resp = hashT3.pesquisar(elemento);
                } else {
                    resp = hashT4.pesquisar(elemento);
                }
            }
        }

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < tamanhoT1; i++) {
            if (tabela[i] != NULO) {
                System.out.println("T1 = " + tabela[i]);
            }
        }

        hashT2.mostrar();
        hashT3.mostrar();
        hashT4.mostrar();
    }
}

public class Questao32 {
    public static void main(String[] args) throws Exception {
        Doidona doidona = new Doidona();

        // Inserir elementos
        doidona.inserir(4);
        doidona.inserir(3);
        doidona.inserir(1);
        doidona.inserir(12);
        doidona.inserir(43);
        doidona.inserir(7);
        doidona.inserir(9);
        doidona.inserir(0);
        doidona.inserir(5);
        doidona.inserir(22);
        doidona.inserir(76);
        doidona.inserir(55);
        doidona.inserir(98);
        doidona.inserir(101);
        doidona.inserir(37);
        doidona.inserir(33);
        doidona.inserir(20);
        doidona.inserir(19);
        doidona.inserir(47);
        doidona.inserir(11);

        // Mostrar elementos
        doidona.mostrar();
    }
}