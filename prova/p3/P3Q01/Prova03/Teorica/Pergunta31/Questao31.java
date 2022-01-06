class ArvoreTrie {
    // Atributos
    public No raiz;

    // Funcoes e metodos
    public void inserir(String elemento) throws Exception {
        inserir(elemento, raiz, 0);
    }

    private void inserir(String elemento, No no, int i) throws Exception {
        if(no.prox[elemento.charAt(i)] == null){
            no.prox[elemento.charAt(i)] = new No(elemento.charAt(i));

            if(i == elemento.length() - 1){
                no.prox[elemento.charAt(i)].folha = true;
            }else{
                inserir(elemento, no.prox[elemento.charAt(i)], i + 1);
            }

        } else if (i < elemento.length() - 1){
            inserir(elemento, no.prox[s.charAt(i)], i + 1);

        } else if(i == elemento.length() - 1){
            no.prox[elemento.charAt(i)].folha = true;
        } 
    }
}

/* 
Analise de complexidade

Levando em consideracao que a palavra tem tamanho s, sera O(s)

*/
