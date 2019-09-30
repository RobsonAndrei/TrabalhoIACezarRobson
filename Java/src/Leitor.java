import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Leitor {

    private static final String LABIRINTO_TXT = "labirinto1_10.txt";
    private int tamanho;
    private String[][] matriz;
    public static int SIZE = 0; //Tamanho do vetor com as sequencia das soluções
    public final static int TAM = 11;// Tamanho da população: numero do soluções
    public final static int MAX_GERACAO = 50;//Numero máximo de gerações


    /***
     * =========================    Construtor  =========================
     * @throws IOException
     */
    public Leitor() throws IOException {
        this.tamanho = lerArqRetMatriz().length;
        this.matriz = geraMatriz();
    }

    public int getTamanho() {
        return tamanho;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void _ALGORITIMOGENETICO() {
        int pontuacao = 0;
        SIZE = getPosicoesVazias();
        int[] vetorSolucao = new int[SIZE];//Vetor de soluções possiveis do tamanho minimo das posições vazias;
        /***
         * =============================================================================================
         *                      Codificação para os movientos do agente no tabuleiro
         * =============================================================================================
         *
         *          Cima:                   - 0     linha - 1   coluna
         *          Baixo:                  - 1     linha + 1   coluna
         *          Esquerda                - 2     linha       coluna - 1
         *          Direita                 - 3     linha       coluna + 1
         *          Diagonal cima direita   - 4     linha - 1   coluna + 1
         *          Diagonal cima esquerda  - 5     linha - 1   coluna - 1
         *          Diagonal baixo direita  - 6     linha + 1   coluna + 1
         *          Diagonal baixo esquerda - 7     linha + 1   coluna - 1
         *
         * =============================================================================================
         * =============================================================================================
         */
        Random r = new Random();
        for (int i = 0; i < vetorSolucao.length; i++) {
            vetorSolucao[i] = r.nextInt(8);
        }

        for (int i = 0; i < vetorSolucao.length; i++) {
            System.out.print(vetorSolucao[i] + " ");
        }


        pontuacao = getAptidao(vetorSolucao);

        System.out.println("Aptidão : " + pontuacao);


    }

    /***
     *
     * @param vetorSolucao
     * @return
     *
     * Recebe um vetor de soluções e retorna um valor de acordo com a aptidão dos movientos do
     * Agente dentro do tabuleiro.
     */
    private int getAptidao(int[] vetorSolucao) {
        int aptidao = 0;
        int _AgLinha = linhaAgente(matriz);
        int _AgColun = colunaAgente(matriz);
        for (int i = 0; i < vetorSolucao.length; i++) {
            switch (vetorSolucao[i]) {
                case 0:
                    try {
                        if (matriz[_AgLinha - 1][_AgColun].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha - 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 1:
                    try {
                        if (matriz[_AgLinha + 1][_AgColun].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha + 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 2:
                    try {
                        if (matriz[_AgLinha][_AgColun - 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgColun = _AgColun - 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 3:
                    try {
                        if (matriz[_AgLinha][_AgColun + 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgColun = _AgColun + 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 4:
                    try {
                        if (matriz[_AgLinha - 1][_AgColun + 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha - 1;
                            _AgColun = _AgColun + 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 5:
                    try {
                        if (matriz[_AgLinha - 1][_AgColun - 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha - 1;
                            _AgColun = _AgColun - 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 6:
                    try {
                        if (matriz[_AgLinha + 1][_AgColun + 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha + 1;
                            _AgColun = _AgColun + 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                case 7:
                    try {
                        if (matriz[_AgLinha + 1][_AgColun - 1].equals("0")) {
                            aptidao = aptidao + 0;
                            _AgLinha = _AgLinha + 1;
                            _AgColun = _AgColun - 1;
                            break;
                        } else {
                            aptidao++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Erro! " + e.getMessage());
                    }
                default:
                    aptidao = aptidao;

            }
        }
        return aptidao;
    }

    /***
     * =========================    Mostra a matriz =====================================
     */
    public void printaMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");

            }
            System.out.println();
        }
    }

    /***
     *
     * @param matrix
     * @return
     * =========================    Posição da linha do Agente  =========================
     */
    public int linhaAgente(String[][] matrix) {
        int aux = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].equals("E")) {
                    aux = i;
                }
            }
        }
        return aux;
    }


    /***
     *
     * @param matrix
     * @return
     * =========================    Posição da coluna do Agente =========================
     */
    public int colunaAgente(String[][] matrix) {
        int aux = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("E")) {
                    aux = j;
                }
            }
        }

        return aux;
    }

    /***
     *
     * @return
     * =========================    Pega numero de posições vazias no tabuleiro =========================
     */
    public int getPosicoesVazias() {
        int aux = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].contains("0")) {
                    aux++;
                }
            }
        }

        return aux;
    }

    /***
     *
     * @return
     * =========================    Mostras a localizações de todas as posições vazias  =========================
     */
    public String getCoordVazias() {
        String aux = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].contains("0")) {
                    aux = aux + j + " " + i + "\n";
                }
            }
        }

        return aux;
    }


    /***
     *
     * @return
     * =========================    Retorna uma lista em a ordem de inserção das coordenadas de cada posicao vazia  =========================
     */
    public ArrayList<String> getCoorVaziList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].contains("0")) {
                    list.add(j + " " + i);
                }
            }
        }
        return list;
    }

    /***
     *
     * @return
     * @throws IOException
     * =========================    Gera Matriz de Strings    =========================
     */
    public String[][] geraMatriz() throws IOException {
        String[][] mat = lerArqRetMatriz();
        String[] vetorString = new String[mat.length];
        try {
            //// BufferedReader inn = new BufferedReader(new FileReader("labirinto5_20.txt"));
            Scanner in = new Scanner(new FileReader(LABIRINTO_TXT));
            in.nextLine();
            char ch;
            while (in.hasNextLine()) {
                for (int i = 0; i < vetorString.length; i++) {

                    String line = in.nextLine().replace(" ", "");
                    //System.out.println(line);
                    vetorString[i] = line;
                }
            }
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    ch = (vetorString[i].charAt(j));
                    mat[i][j] = String.valueOf(ch);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    /***
     *
     *
     * @return
     * =========================
     */
    public String disTVisinhos() {
        String result = "";
        ArrayList<String> lista = new ArrayList<>();
        lista = getCoorVaziList();
        int a = 0;
        int b = 0;

        for (int x = 0; x < lista.size(); x++) {
            if (lista.get(x).length() == 3) {
                a = Integer.parseInt(lista.get(x).substring(0, 1));// Coordenada da primeira posição vazia no tabuleiro
                // representa coluna "J"
                b = Integer.parseInt(lista.get(x).substring(2, 3));// Coordenada da primeira posição vazia dao tabuleiro
                // representa linha
                // Coluna = variável J
                // Linha = variável I
            }
            if (lista.get(x).length() == 4) {
                a = Integer.parseInt(lista.get(x).substring(0, 1));
                b = Integer.parseInt(lista.get(x).substring(3, 4));
            }


            result = result + ehVisinho(a, b) + "\n";

        }

        return result;
    }

    /***
     *
     * @return
     * @throws FileNotFoundException
     * =========================    Le arquivo labirinto    =========================
     */
    public String[][] lerArqRetMatriz() throws FileNotFoundException {
        String[][] aux = null;
        int tam = 0;
        Scanner in = new Scanner(new FileReader(LABIRINTO_TXT));
        tam = in.nextInt();

        aux = new String[tam][tam];
        in.close();


        return aux;
    }

    /***
     * @param coluna
     * @param linha
     * @return
     * =========================    Se é visinho     =========================
     */
    public String ehVisinho(int coluna, int linha) {
        String visi = "";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].equals("0") && ((j == coluna - 1 && i == linha - 1) || (j == coluna && i == linha - 1) || (j == coluna + 1 && i == linha - 1) || (j == coluna - 1 && i == linha) || (j == coluna + 1 && i == linha) || (j == coluna - 1 && i == linha + 1) || (j == coluna && i == linha + 1) || (j == coluna + 1 && i == linha + 1))) {
                    visi = visi + "1 ";
                } else {
                    visi = visi + "0 ";
                }
            }
        }

        return visi;
    }


    /***
     * =========================    Main ()   =========================
     */
    public static void main(String[] args) throws IOException {
        Leitor l = new Leitor();
        l.lerArqRetMatriz();
        String[][] mat = l.geraMatriz();
        l.printaMatriz();
        System.out.println();
        //System.out.println("Coordenadas vazias \n\n" + l.getCoordVazias());
        System.out.println("Vetor de visinhos");
        // System.out.println(l.disTVisinhos());
        System.out.println("Posicoes vazias: " + l.getPosicoesVazias());
        System.out.println("Posicoes linha do agente " + l.linhaAgente(mat) + " Coluna agente: " + l.colunaAgente(mat));
        l._ALGORITIMOGENETICO();

    }
}
