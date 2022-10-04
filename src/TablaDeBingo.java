public class TablaDeBingo extends Thread {
    public static FormaDeGanar formaDeGanar;
    public static int numeroAleatorioObtenido;
    private final int NUMERO_DE_FILAS = 5;
    private final int NUMERO_DE_COLUMNAS = 5;
    private int[][] numeros = new int[NUMERO_DE_FILAS][NUMERO_DE_COLUMNAS];
    private final int numeroDeTabla;
    private static TablaDeBingo tablaGanadora;

    public TablaDeBingo(int numeroDeTabla) {
        this.numeroDeTabla = numeroDeTabla;
        llenarTabla();
    }

    public static boolean algunaTablaEstaCompleta() {
        return tablaGanadora!= null;
        //return algunaTablaHaGanado;
    }

    public static TablaDeBingo obtenerTablaGanadora() {
        return tablaGanadora;
    }

    private void llenarTabla() {
        for (int i = 0; i < NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < NUMERO_DE_COLUMNAS; j++) {

                //Para conseguir un número entero entre M y N con M menor que N y ambos incluídos, debes usar esta fórmula
                //valorEntero = Math.floor(Math.random()*(N-M+1)+M);

                //Pero la siguiente parte +(i*15) es para iniciar mas arriba la suma

                numeros[i][j] = ((int) Math.floor(Math.random() * (15 - 1 + 1) + 1)) + (i * 15);

                //Para que no se repitan los numeros
                while (!esteNumeroEsDiferenteALosDemas(numeros[i], numeros[i][j], j)) {
                    numeros[i][j] = ((int) Math.floor(Math.random() * (15 - 1 + 1) + 1)) + (i * 15);
                }
            }
        }
    }

    private boolean esteNumeroEsDiferenteALosDemas(int[] numero, int numeroColocado, int posicionDelValorQueEstamosComparando) {
        for (int i = 0; i < numero.length; i++) {
            if (numero[i] == numeroColocado && i != posicionDelValorQueEstamosComparando) {
                return false;
            }
        }
        return true;
    }


    public boolean gano() {
        marcarNumeroAleatorioEnLaTabla();

        if (formaDeGanar == FormaDeGanar.CARTON_COMPLETO) {
            return estaTodaLaTablaMarcada();
        }

        if (formaDeGanar == FormaDeGanar.LINEA) {
            return algunaLineaEstaCompleta();
        }

        if (formaDeGanar == FormaDeGanar.DIAGONAL) {
            return algunaDiagonalEstaCompleta();
        }

        return lasCuatroEsquinasEstanMarcadas();
    }

    private boolean lasCuatroEsquinasEstanMarcadas() {
        return numeros[0][NUMERO_DE_COLUMNAS - 1] == 0 &&
                numeros[NUMERO_DE_COLUMNAS - 1][NUMERO_DE_COLUMNAS - 1] == 0 &&
                numeros[NUMERO_DE_COLUMNAS - 1][0] == 0 &&
                numeros[0][0] == 0;
    }

    private boolean algunaDiagonalEstaCompleta() {
        int contadorDeCeros = 0;
        for (int i = 0; i < NUMERO_DE_COLUMNAS; i++) {
            if (numeros[i][i] == 0) {
                contadorDeCeros++;
            }
        }
        if (contadorDeCeros == NUMERO_DE_COLUMNAS) {
            return true;
        } else {
            contadorDeCeros = 0;
        }

        for (int i = 0; i < NUMERO_DE_COLUMNAS; i++) {
            if (numeros[i][4 - i] == 0) {
                contadorDeCeros++;
            }
        }
        return contadorDeCeros == NUMERO_DE_COLUMNAS;
    }

    private boolean algunaLineaEstaCompleta() {
        int contadorDeCeros = 0;

        for (int i = 0; i < NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < NUMERO_DE_COLUMNAS; j++) {
                if (numeros[i][j] == 0) {
                    contadorDeCeros++;
                }
            }
            if (contadorDeCeros == NUMERO_DE_COLUMNAS) {
                return true;
            } else {
                contadorDeCeros = 0;
            }
        }


        for (int i = 0; i < NUMERO_DE_COLUMNAS; i++) {
            for (int j = 0; j < NUMERO_DE_FILAS; j++) {
                if (numeros[i][j] == 0) {
                    contadorDeCeros++;
                }
            }
            if (contadorDeCeros == NUMERO_DE_COLUMNAS) {
                return true;
            } else {
                contadorDeCeros = 0;
            }
        }

        return false;
    }

    private boolean estaTodaLaTablaMarcada() {
        for (int i = 0; i < NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < NUMERO_DE_COLUMNAS; j++) {
                if (numeros[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void marcarNumeroAleatorioEnLaTabla() {
        for (int i = 0; i < NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < NUMERO_DE_COLUMNAS; j++) {
                if (numeros[i][j] == numeroAleatorioObtenido) {
                    numeros[i][j] = 0;
                }
            }
        }
    }

    public void setNumeroDelLocutor(int numeroActual) {
        numeroAleatorioObtenido = numeroActual;
    }

    public void setFormaDeGanar(FormaDeGanar formaDeGanarEnEstaPartida) {
        formaDeGanar = formaDeGanarEnEstaPartida;
    }

    @Override
    public String toString() {
        String auxiliarParaMostrarLosNumeros = "";
        for (int i = 0; i < NUMERO_DE_COLUMNAS; i++) {
            for (int j = 0; j < NUMERO_DE_FILAS; j++) {
                auxiliarParaMostrarLosNumeros += numeros[j][i] + "\t";
            }
            auxiliarParaMostrarLosNumeros += '\n';
        }
        return "NumeroDeTabla = " + numeroDeTabla + "\nB\tI\tN\tG\tO\n" + auxiliarParaMostrarLosNumeros;

    }

    @Override
    public void run() {
        while (tablaGanadora == null) {
            marcarNumeroAleatorioEnLaTabla();
            if (gano()) {
                tablaGanadora = this;
            }
        }
    }


}
