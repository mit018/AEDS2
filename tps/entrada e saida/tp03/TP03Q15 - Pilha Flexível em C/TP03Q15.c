#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <err.h>
#include <locale.h>

#define MAX_TAM 100

// void imprimir(struct Series *s);
// char *trim(char str[]);
// void correcao(char linha[]);
// void correcaoTitle(char linha[]);
// void ler(char arq[], struct Series *s);
// int isFim(char s[]);
// void initiLista(struct Lista *lista);
// void inserirInicio(struct Lista *lista, struct Series s);
// void inserir(struct Lista *lista, struct Series s, int pos);
// void imprimirLista(struct Lista lista);
// struct Series clone(struct Series s);
// struct Series remover(struct Lista *lista, int pos);
// struct Series removerInicio(struct Lista *lista);
// struct Series removerFim(struct Lista *lista);

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

typedef struct Celula
{
    struct Series serie;
    struct Celula *prox;
} Celula;

Celula *new_celula(struct Series s)
{
    Celula *temp = (Celula *)malloc(sizeof(Celula));
    temp->serie = clone(s);
    temp->prox = NULL;
    return temp;
}

typedef struct Pilha
{
    struct Celula *topo;
    int size;
} Pilha;

Pilha new_pilha()
{
    Pilha temp;
    temp.topo = NULL;
    temp.size = 0;
    return temp;
}

int size_pilha(Pilha *p)
{
    return p->size;
}

void push_pilha(Pilha *p, struct Series s)
{
    Celula *temp = new_celula(s);
    temp->prox = p->topo;
    p->topo = temp;
    p->size++;
}
//=============================================================================
struct Series pop_pilha(Pilha *p)
{
    if (p->topo == NULL)
    {
        errx(1, "Erro ao remover!");
    }

    struct Series resp = p->topo->serie;
    Celula *tmp = p->topo;
    p->topo = p->topo->prox;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

void imprimir(struct Series *s)
{
    setlocale(LC_ALL, "Portuguese");

    printf("%s %s %s %s %s %s %s %i %i\n", s->nome, s->formato, s->duracao, s->pais, s->idioma, s->emissora, s->transmissao, s->temporadas, s->episodios);
}

void print_pilha(Pilha *p)
{
    Celula *i;
    for (i = p->topo; i != NULL; i = i->prox)
    {
        imprimir(&i->serie);
    }
}

void delete_pilha(Pilha *p)
{
    while (p->size > 0)
        pop_pilha(p);
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

void processa(struct Pilha *pilha, char linha[])
{
    char *split[2];
    int i = 0;
    split[0] = strtok(linha, " ");

    while (split[i] != NULL)
    {
        i++;
        split[i] = strtok(NULL, " ");
    }

    struct Series serie;
    serie.episodios = 0;

    switch (linha[0])
    {
    case 'I':
        ler(split[1], &serie);
        push_pilha(pilha, serie);
        break;
    case 'R':
        serie = clone(pop_pilha(pilha));
        printf("(R) %s\n", serie.nome);
        break;
    default:
        break;
    }
}

int isFim(char string[])
{
    return strcmp(string, "FIM\0");
}

int main()
{
    setlocale(LC_ALL, "Portuguese");
    char arq[50];
    struct Series s;
    struct Pilha pilha = new_pilha();
    int linhas = 0;

    scanf(" %[^\n]", arq);
    //arq[strlen(arq) - 1] = '\0'; // -> caso de segmentation fault

    while (isFim(arq) != 0)
    {
        s.episodios = 0;
        ler(arq, &s);
        push_pilha(&pilha, s);
        strcpy(arq, "");
        scanf(" %[^\n]", arq);
        //arq[strlen(arq) - 1] = '\0';
    }

    scanf("%i", &linhas);

    for (int i = 0; i < linhas; i++)
    {
        scanf(" %[^\n]", arq);
        //arq[strlen(arq) - 1] = '\0';
        processa(&pilha, arq);

        strcpy(arq, "");
    }

    print_pilha(&pilha);

    return 0;
}