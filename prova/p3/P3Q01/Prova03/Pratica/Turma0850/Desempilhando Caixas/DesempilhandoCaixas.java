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

class CelulaDupla {
	public int elemento;
    public Pilha pilha;
	public CelulaDupla ant;
	public CelulaDupla prox;

	public CelulaDupla() {
		this(0);
	}

	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDuplaPilha {
    public CelulaDupla primeiro, ultimo;

	public ListaDuplaPilha() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	public void inserir(int x, int posicao) {
        int i = 1;
        CelulaDupla tmp = primeiro;
        
        for (tmp = primeiro; i < posicao; tmp = tmp.prox, i++);

        tmp.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = tmp.prox;

    }

    public void mostrar() throws Exception {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento);
            //i.pilha.mostrar();
		}
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

public class DesempilhandoCaixas {
    public static void main(String[] args) throws Exception {
        String linha = "";

        linha = MyIO.readLine();
        while (!(linha.contains("0 0"))) {
            String[] splitLinha = linha.split(" ");

            int elementos = Integer.parseInt(splitLinha[0]);
            int pilhasQuantidade = Integer.parseInt(splitLinha[1]);

            ListaDuplaPilha[] listaPilha = new ListaDuplaPilha[pilhasQuantidade];

            for (int i = 0; i < pilhasQuantidade; i++) {
                listaPilha[i] = new ListaDuplaPilha();
            }

            for (int i = 0; i < pilhasQuantidade; i++) {
                linha = MyIO.readLine();
                String[] splitLinha2 = linha.split(" ");
                
                for (int k = 1; k < splitLinha2.length; k++) {
                    listaPilha[i].inserir(Integer.parseInt(splitLinha2[k]), k);
                }

                System.out.println("========");
                listaPilha[i].mostrar();
            }

            linha = MyIO.readLine();
        }
    }
}
