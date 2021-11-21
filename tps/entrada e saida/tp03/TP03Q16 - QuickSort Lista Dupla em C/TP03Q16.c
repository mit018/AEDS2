#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <err.h>
#include <locale.h>
#include <time.h>

#define MAX_TAM 100
int comp = 0;
int mov = 0;

struct Series
{
    char nome[50];
    char formato[50];
    char duracao[50];
    char pais[50];
    char idioma[50];
    char emissora[50];
    char transmissao[100];
    int temporadas;
    int episodios;
};

struct Series clone(struct Series s)
{
    struct Series clone;

    strcpy(clone.duracao, s.duracao);
    strcpy(clone.emissora, s.emissora);
    strcpy(clone.nome, s.nome);
    strcpy(clone.formato, s.formato);
    strcpy(clone.pais, s.pais);
    strcpy(clone.idioma, s.idioma);
    strcpy(clone.transmissao, s.transmissao);

    clone.episodios = s.episodios;
    clone.temporadas = s.temporadas;

    return clone;
}

struct Series new_Serie()
{
    struct Series s;

    strcpy(s.duracao, "");
    strcpy(s.emissora, "");
    strcpy(s.nome, "");
    strcpy(s.formato, "");
    strcpy(s.pais, "");
    strcpy(s.idioma, "");
    strcpy(s.transmissao, "");

    s.episodios = 0;
    s.temporadas = 0;

    return s;
}

void imprimir(struct Series *s)
{
    setlocale(LC_ALL, "Portuguese");

    printf("%s %s %s %s %s %s %s %i %i\n", s->nome, s->formato, s->duracao, s->pais, s->idioma, s->emissora, s->transmissao, s->temporadas, s->episodios);
}

typedef struct CelulaDupla
{
    struct Series serie;
    struct CelulaDupla *prox, *ant;
} CelulaDupla;

CelulaDupla *new_celula_dupla(struct Series serie)
{
    CelulaDupla *temp = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    temp->serie = clone(serie);
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}

typedef struct ListaDupla
{
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;

ListaDupla new_lista_dupla()
{
    struct Series s = new_Serie();
    ListaDupla temp;
    temp.primeiro = temp.ultimo = new_celula_dupla(s);
    temp.size = 0;
    return temp;
}

int size_lista_dupla(ListaDupla *l)
{
    return l->size;
}

void insert_begin_dupla(ListaDupla *l, struct Series serie)
{

    CelulaDupla *temp;
    temp->ant = NULL;
    temp->prox = l->primeiro;

    l->primeiro->serie = clone(serie);
    l->primeiro->ant = temp;
    l->primeiro = temp;
    l->size++;
}

void insert_end_dupla(ListaDupla *l, struct Series serie)
{
    l->ultimo->prox = new_celula_dupla(serie);
    l->ultimo->prox->ant = l->ultimo;
    l->ultimo = l->ultimo->prox;
    l->size++;
}

void insert_at_dupla(ListaDupla *l, struct Series s, int pos)
{

    if (pos < 0 || pos > l->size)
        printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == 0)
        insert_begin_dupla(l, s);
    else if (pos == l->size)
        insert_end_dupla(l, s);
    else
    {

        CelulaDupla *ant = l->primeiro;
        for (int i = 0; i < pos; i++)
            ant = ant->prox;

        CelulaDupla *temp = new_celula_dupla(s);
        temp->prox = ant->prox;
        temp->prox->ant = temp;
        temp->ant = ant;
        ant->prox = temp;
        l->size++;
    }
}

struct Series remove_end_dupla(ListaDupla *l)
{

    if (l->primeiro == l->ultimo)
    {
        errx(1, "Erro ao remover!");
    }

    CelulaDupla *temp = l->ultimo;
    struct Series s = clone(temp->serie);

    l->ultimo = l->ultimo->ant;
    l->ultimo->prox = NULL;
    l->size--;

    free(temp);

    return s;
}

struct Series remove_at_dupla(ListaDupla *l, int pos)
{

    if (l->primeiro == l->ultimo)
    {
        errx(1, "Erro ao remover!");
    }
    else if (pos < 0 || pos > l->size - 1)
        printf("Erro ao tentar remover item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == l->size - 1)
        remove_end_dupla(l);
    else
    {
        CelulaDupla *ant = l->primeiro;
        for (int i = 0; i < pos; i++)
            ant = ant->prox;

        CelulaDupla *temp = ant->prox;
        struct Series s = clone(temp->serie);

        temp->prox->ant = ant;
        ant->prox = temp->prox;
        free(temp);

        l->size--;

        return s;
    }
}

struct Series remove_begin_dupla(ListaDupla *l)
{
    return remove_at_dupla(l, 0);
}

void print_lista_dupla(ListaDupla *l)
{
    CelulaDupla *i;
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
    {
        imprimir(&i->serie);
    }
}

void delete_lista_dupla(ListaDupla *l)
{
    while (l->size > 0)
        remove_at_dupla(l, 0);
    free(l->primeiro);
}

int correcaoNum(char linha[])
{
    int j = 0, n = 0;
    char num[strlen(linha)];

    linha[strlen(linha)] = '\0'; // -> para o verde

    for (int i = 0; i < strlen(linha); i++)
    {
        if (linha[i] >= 48 && linha[i] <= 57)
        {
            num[j++] = linha[i];
        }
        else
        {
            i = strlen(linha);
        }
    }

    n = atoi(num);
    return n;
}

char *trim(char *str)
{
    char *end;

    while (isspace((unsigned char)*str))
        str++;

    if (*str == 0)
        return str;

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
        end--;

    end[1] = '\0';

    return str;
}

void correcao(char linha[])
{
    char correcao[strlen(linha)];

    int i = 0, j = 0;
    while (i < strlen(linha))
    {
        if (linha[i] == '<')
        {
            i++;
            while (linha[i] != '>')
            {
                i++;
            }
        }
        else if (linha[i] == '&')
        {
            i++;
            while (linha[i] != ';')
            {
                i++;
            }
        }
        else if (linha[i] != '\n')
        {
            correcao[j] = linha[i];
            j++;
        }
        i++;
    }
    correcao[j] = '\0'; // -> para o verde
    // correcao[j - 1] = '\0';

    strcpy(linha, correcao);
}

void correcaoTitle(char linha[])
{
    char correcao[strlen(linha)];
    int j = 0;

    for (int i = 0; i < strlen(linha); i++)
    {
        if (linha[i] == '(')
        {
            i++;
            while (linha[i] != ')')
            {
                i++;
            }
        }
        else if (linha[i + 4] == 'W' && linha[i + 5] == 'i' && linha[i + 6] == 'k')
        {
            i = strlen(linha);
        }
        else
        {
            correcao[j++] = linha[i];
        }
    }
    correcao[j] = '\0';

    strcpy(linha, correcao);
}

void ler(char arq[], struct Series *s)
{
    char linha[6000], conteudo[6000];
    char nomeArq[] = {"/tmp/series/"};

    strcat(nomeArq, arq);

    FILE *fp;
    fp = fopen(nomeArq, "r");

    if (!fp)
    {
        printf("\nOcorreu um erro na abertura do arquivo.");
    }

    int pad = 0;
    char padrao[9][50];
    strcpy(padrao[0], "<title>");
    strcpy(padrao[1], "Formato");                        // proxima linha
    strcpy(padrao[2], "Duração");                        // proxima linha
    strcpy(padrao[3], "País de origem");                 // proxima linha
    strcpy(padrao[4], "Idioma original");                // proxima linha
    strcpy(padrao[5], "Emissora de televisão original"); // proxima linha
    strcpy(padrao[6], "Transmissão original");           // proxima linha
    strcpy(padrao[7], "N.º de temporadas");              // proxima linha
    strcpy(padrao[8], "N.º de episódios");               // proxima linha

    fgets(linha, 6000, fp);

    while (s->episodios == 0)
    {
        if (strstr(linha, padrao[pad]) != NULL)
        {

            if (pad == 0)
            {
                correcao(linha);
                correcaoTitle(linha);
                strcpy(linha, trim(linha));
                strcpy(s->nome, linha);
            }
            else
            {
                fgets(linha, 6000, fp);
                correcao(linha);
                strcpy(linha, trim(linha));

                switch (pad)
                {
                case 1:
                    strcpy(s->formato, linha);
                    break;
                case 2:
                    strcpy(s->duracao, linha);
                    break;
                case 3:
                    strcpy(s->pais, linha);
                    break;
                case 4:
                    strcpy(s->idioma, linha);
                    break;
                case 5:
                    strcpy(s->emissora, linha);
                    break;
                case 6:
                    strcpy(s->transmissao, linha);
                    break;
                case 7:
                    s->temporadas = correcaoNum(linha);
                    break;
                case 8:
                    s->episodios = correcaoNum(linha);
                    break;
                default:
                    break;
                }
            }
            pad++;
        }
        fgets(linha, 6000, fp);
    }

    fclose(fp);
}

struct CelulaDupla *getCelula(struct ListaDupla *lista, int pos)
{
    if (pos < 0 || pos > size_lista_dupla(lista))
    {
        errx(1, "Erro ao remover!");
    }

    struct CelulaDupla *resp = lista->primeiro->prox;
    for (int i = 0; (i < pos && resp->prox != NULL); resp = resp->prox, i++)
        ;
    return resp;
}

struct Series getSerie(struct ListaDupla *lista, int pos)
{
    if (pos < 0 || pos > size_lista_dupla(lista))
    {
        errx(1, "Erro ao remover!");
    }

    struct CelulaDupla *resp = lista->primeiro->prox;
    for (int i = 0; (i < pos && resp->prox != NULL); resp = resp->prox, i++)
        ;
    return resp->serie;
}

void swap(struct ListaDupla *lista, int pos1, int pos2)
{
    struct CelulaDupla *c1 = getCelula(lista, pos1);
    struct CelulaDupla *c2 = getCelula(lista, pos2);
    struct Series tmp = clone(c1->serie);

    c1->serie = clone(c2->serie);
    c2->serie = clone(tmp);
}

void quicksort(struct ListaDupla *lista, int esq, int dir)
{
    int i = esq, j = dir;
    struct Series pivo = getSerie(lista, (esq + dir) / 2);

    while (i <= j)
    {
        while (
            strcmp(getSerie(lista, i).pais, pivo.pais) < 0 ||
            (strcmp(getSerie(lista, i).pais, pivo.pais) == 0 &&
             strcmp(getSerie(lista, i).nome, pivo.nome) < 0))
        {
            comp += 2;
            i++;
        }
        while (
            strcmp(getSerie(lista, j).pais, pivo.pais) > 0 ||
            (strcmp(getSerie(lista, j).pais, pivo.pais) == 0 &&
             strcmp(getSerie(lista, j).nome, pivo.nome) > 0))
        {
            comp += 2;
            j--;
        }

        if (i <= j)
        {
            swap(lista, i, j);
            mov++;

            i++;
            j--;
        }
    }
    if (esq < j)
        quicksort(lista, esq, j);
    if (i < dir)
        quicksort(lista, i, dir);
}

void ordenarQuicksort(struct ListaDupla *lista)
{
    if (size_lista_dupla(lista) == 0)
    {
        return;
    }
    quicksort(lista, 0, size_lista_dupla(lista) - 1);
}

int isFim(char string[])
{
    return strcmp(string, "FIM\0");
}

int main()
{
    clock_t t = clock();
    setlocale(LC_ALL, "Portuguese");
    char arq[50];
    struct Series s;
    struct ListaDupla lista = new_lista_dupla();
    int linhas = 0;

    scanf(" %[^\n]", arq);
    //arq[strlen(arq) - 1] = '\0'; // -> caso de segmentation fault

    while (isFim(arq) != 0)
    {
        s.episodios = 0;
        ler(arq, &s);
        insert_end_dupla(&lista, s);
        strcpy(arq, "");
        scanf(" %[^\n]", arq);
        //arq[strlen(arq) - 1] = '\0';
    }

    ordenarQuicksort(&lista);
    print_lista_dupla(&lista);

    FILE *file;
    file = fopen("734661_quicksort3.txt", "w");

    if (file == NULL)
    {
        printf("Erro na abertura do arquivo!");
        return 0;
    }

    t = clock() - t;
    fprintf(file, "734661\t%i\t%i\t%li", comp, mov, t);
    fclose(file);

    return 0;
}