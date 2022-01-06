class Celula {
    //Atributos
    public int elemento;
    public Celula prox;

    //Metodos especiais
    public Celula() {
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public Celula(int elemento, Celula prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}

class No {
    //Atributos
    public int elemento;
    public No esq;
    public No dir;

    //Metodos especiais
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
}

class HashT2 {
    //Atributos
    public int tabela[];
    public int NULO = -0x7FFFFF;
    public int tamanho;
    public Celula primeiro, ultimo;
    public No raiz;

    //Metodos especiais
    public HashT2() {
        this.tamanho = 10;
        this.tabela = new int[10];
        
        for (int i = 0; i < tamanho; i++) {
            this.tabela[i] = NULO;
        }

        primeiro = new Celula();
        ultimo = primeiro;

        raiz = null;
    }

    public HashT2(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            this.tabela[i] = NULO;
        }

        primeiro = new Celula();
        ultimo = primeiro;

        raiz = null;
    }

    //Metodos e funcoes
    public int hash(int elemento) {
        return elemento % tamanho;
    }

    public int rehash(int elemento) {
        return ++elemento % tamanho;
    }

    public int hashT3(int elemento) {
        return elemento % 2;
    }

    public void inserir(int elemento) {
        if (elemento != NULO) {
            int posicao = hash(elemento);

            if (tabela[posicao] == NULO) {
                tabela[posicao] = elemento;
            } else {
                posicao = rehash(elemento);

                if (tabela[posicao] == NULO) {
                    tabela[posicao] = elemento;
                } else {
                    posicao = hashT3(elemento);

                    if (posicao == 0) {
                        inserirLista(elemento);
                    } else {
                        raiz = inserirArvore(elemento, raiz);
                    }
                }
            }
        }
    }

    public void inserirLista(int elemento) {
        ultimo.prox = new Celula(elemento);
        ultimo = ultimo.prox;
    }

    public No inserirArvore(int elemento, No no) {
        if (no == null) {
            no = new No(elemento);
        } else if (no.elemento < elemento) {
            no.esq = inserirArvore(elemento, no.esq);
        } else if (no.elemento > elemento) {
            no.dir = inserirArvore(elemento, no.dir);
        } else {
            System.out.println("Erro de Insercao");
        }

        return no;
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        if (elemento != NULO) {
            int posicao = hash(elemento);

            if (tabela[posicao] == elemento) {
                resp = true;
            } else {
                posicao = rehash(elemento);

                if (tabela[posicao] == elemento) {
                    resp = true;
                } else {
                    posicao = hashT3(elemento);

                    if (posicao == 0) {
                        resp = pesquisarLista(elemento);
                    } else {
                        resp = pesquisarArvore(elemento, raiz);
                    }
                }
            }
        }

        return resp;
    }

    public boolean pesquisarLista(int elemento) {
        boolean resp = false;

        for (Celula i = primeiro; i != null && resp == false; i = i.prox) {
            if (i.elemento == elemento) {
                resp = true;
            }
        }

        return resp;
    }

    public boolean pesquisarArvore(int elemento, No no) {
        boolean resp = false;

        if (no == null) {
            
        } else if (no.elemento < elemento) {
            resp = pesquisarArvore(elemento, no.esq);
        } else if (no.elemento > elemento) {
            resp = pesquisarArvore(elemento, no.dir);
        } else {
            resp = true;
        }

        return resp;
    }
}

class Doidona {
    //Atributos
    public int tamanhoT1;
    public int tamanhoT2;
    public int NULO = -0x7FFFFF;
    public HashT2[] tabela;

    //Metodos especiais
    public Doidona() {
        this.tamanhoT1 = 10;
        this.tamanhoT2 = 10;
        this.tabela = new HashT2[10];

        for (int i = 0; i < 10; i++) {
            this.tabela[i] = new HashT2(10);
        }
    }

    public Doidona(int tamanhot1, int tamanhot2) {
        this.tamanhoT1 = tamanhot1;
        this.tamanhoT2 = tamanhot2;
        this.tabela = new HashT2[tamanhot1];

        for (int i = 0; i < tamanhot1; i++) {
            this.tabela[i] = new HashT2(tamanhot2);
        }
    }

    //Metodos e funcoes
    public int hash(int elemento) {
        return elemento % tamanhoT1;
    }

    public void inserir(int elemento) {
        if (elemento != NULO) {
            int posicao = hash(elemento);

            tabela[posicao].inserir(elemento);
        }
    }

    public boolean pesquisar(int elemento) {
        boolean resp = false;

        if (elemento != NULO) {
            int posicao = hash(elemento);

            resp = tabela[posicao].pesquisar(elemento);
        }

        return resp;
    }
}

public class Questao32 {
    public static void main(String[] args) {
        Doidona doidona = new Doidona(10, 10);

        doidona.inserir(3);
        doidona.inserir(34);
        doidona.inserir(1);

        //Pesquisar pelo elemento 34
        System.out.println("resp = " + doidona.pesquisar(34));
    }
}

/*
Analise de complexidade

Melhor caso = O(1)
Pior caso = O(n)

*/
