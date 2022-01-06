import java.io.*;

class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;
    
    public No (){
       this(' ');
    }
 
    public No (char elemento){
       this.elemento = elemento;
       prox = new No [tamanho];
       for (int i = 0; i < tamanho; i++) prox[i] = null;
       folha = false;
    }
 
    public static int hash (char x){
       return (int)x;
    }
}

class ArvoreTrie {
    private No raiz;

    public ArvoreTrie(){
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
        if(no.prox[s.charAt(i)] == null){
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){
                no.prox[s.charAt(i)].folha = true;
            }else{
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (i < s.length() - 1){
            inserir(s, no.prox[s.charAt(i)], i + 1);
        } else if(i == s.length() - 1){
            no.prox[s.charAt(i)].folha = true;
        } 
    }


    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
        boolean resp;
        if(no.prox[s.charAt(i)] == null){
            resp = false;
        } else if(i == s.length() - 1){
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if(i < s.length() - 1 ){
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }


    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true){
            System.out.println(s + no.elemento);
        }

        for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s + no.elemento, no.prox[i]);
            }
        }
        
    }
}

public class PrimeiroDicionario {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linha;
        ArvoreTrie arvore = new ArvoreTrie();

        while ((linha = br.readLine()) != null) {       
            if (linha.contains(" ") || linha.contains(".")) { //Da pra melhorar isso
                linha = linha.replace(".", " ").replace("(", " ").replace("*$", "").replace("#", "").replace(":", "").replace("\"", "");
                linha = linha.replace("  ", " ");
                String[] splitLinha = linha.split(" ");

                for (int i = 0; i < splitLinha.length; i++) {
                    arvore.inserir(splitLinha[i].toLowerCase());
                }
            }
        }

        arvore.mostrar();
    }
}
