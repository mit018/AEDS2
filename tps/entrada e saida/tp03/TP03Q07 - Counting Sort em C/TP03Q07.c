#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
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

struct Lista
{
    struct Series series[MAX_TAM];
    int n;
};

void initiLista(struct Lista *lista)
{
    lista->n = 0;
}

void inserirFim(struct Lista *lista, struct Series s)
{
    if (lista->n >= MAX_TAM)
    {
        printf("\nERRO");
    }

    lista->series[lista->n] = clone(s);
    lista->n++;
}

void inserirInicio(struct Lista *lista, struct Series s)
{
    if (lista->n >= MAX_TAM)
    {
        printf("\nERRO");
    }

    for (int i = lista->n; i > 0; i--)
    {
        lista->series[i] = lista->series[i - 1];
    }

    lista->series[0] = clone(s);
    lista->n++;
}

void inserir(struct Lista *lista, struct Series s, int pos)
{
    if (lista->n >= MAX_TAM || pos < 0 || pos > lista->n)
    {
        printf("\nERRO");
    }

    for (int i = lista->n; i > pos; i--)
    {
        lista->series[i] = lista->series[i - 1];
    }

    lista->series[pos] = clone(s);
    lista->n++;
}

struct Series remover(struct Lista *lista, int pos)
{
    if (lista->n == 0 || pos < 0 || pos > lista->n)
    {
        printf("\nERRO");
    }

    struct Series serie = lista->series[pos];
    lista->n--;

    for (int i = pos; i < lista->n; i++)
    {
        lista->series[i] = lista->series[i + 1];
    }

    return serie;
}

struct Series removerInicio(struct Lista *lista)
{
    if (lista->n == 0)
    {
        printf("\nERRO");
    }

    struct Series primeira = clone(lista->series[0]);
    lista->n--;
    for (int i = 0; i < lista->n; i++)
    {
        lista->series[i] = lista->series[i + 1];
    }

    return primeira;
}

struct Series removerFim(struct Lista *lista)
{
    if (lista->n == 0)
    {
        printf("\nERRO");
    }

    lista->n--;
    return clone(lista->series[lista->n + 1]);
}

void imprimir(struct Series *s)
{
    setlocale(LC_ALL, "Portuguese");

    printf("%s %s %s %s %s %s %s %i %i\n", s->nome, s->formato, s->duracao, s->pais, s->idioma, s->emissora, s->transmissao, s->temporadas, s->episodios);
}

void imprimirLista(struct Lista lista)
{
    for (int i = 0; i < lista.n; i++)
    {
        imprimir(&lista.series[i]);
    }
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

int isFim(char string[])
{
    return strcmp(string, "FIM\0");
}

struct Series getMaior(struct Lista *lista)
{
    struct Series maior = lista->series[0];

    for (int i = 0; i < lista->n; i++)
    {
        if (maior.temporadas < lista->series[i].temporadas)
        {
            maior = lista->series[i];
        }
    }
    return maior;
}

void ordenarCountingsort(struct Lista *lista)
{
    //Array para contar o numero de ocorrencias de cada elemento
    int tamCount = getMaior(lista).temporadas + 1;
    int count[tamCount];
    struct Series ordenado[lista->n];

    //Inicializar cada posicao do array de contagem
    for (int i = 0; i < tamCount; count[i] = 0, i++);

    //Agora, o count[i] contem o numero de elemento iguais a i
    for (int i = 0; i < lista->n; count[lista->series[i].temporadas]++, i++);

    //Agora, o count[i] contem o numero de elemento menores ou iguais a i
    for (int i = 1; i < tamCount; count[i] += count[i - 1], i++);

    //Ordenando
    for (int i = lista->n - 1; i >= 0; count[lista->series[i].temporadas]--, i--)
    {
        ordenado[count[lista->series[i].temporadas] - 1] = lista->series[i];
    }

    for(int i = 1; i < lista->n; i++){
        struct Series tmp = ordenado[i];
        int j = i - 1;

        while((j >= 0) && ordenado[j].temporadas == tmp.temporadas && strcmp(ordenado[j].nome, tmp.nome) > 0){
            comp+=2;
            ordenado[j+1] = ordenado[j];
            j--;
        }
        ordenado[j+1] = tmp;
    }

    //Copiando para o array original
    for (int i = 0; i < lista->n; lista->series[i] = ordenado[i], i++);
}

int main()
{
    clock_t t = clock();
    setlocale(LC_ALL, "Portuguese");
    char arq[50];
    struct Series s;
    struct Lista lista;
    initiLista(&lista);
    int linhas = 0;

    scanf(" %[^\n]", arq);
    //arq[strlen(arq) - 1] = '\0'; // -> caso de segmentation fault

    while (isFim(arq) != 0)
    {
        s.episodios = 0;
        ler(arq, &s);
        inserirFim(&lista, s);
        strcpy(arq, "");
        scanf(" %[^\n]", arq);
        //arq[strlen(arq) - 1] = '\0';
    }

    ordenarCountingsort(&lista);
    imprimirLista(lista);

    FILE *file;
    file = fopen("734661_coutingsort.txt", "w");

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