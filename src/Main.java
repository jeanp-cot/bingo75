import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numeroDeTablas = 5;

        ArrayList<TablaDeBingo> tablasDeBingo = crearCantidadDeTablasDeBingo(numeroDeTablas);

        for (TablaDeBingo tabla:tablasDeBingo){
            tabla.start();
        }

        ArrayList<Integer> numerosQueHanSalido = new ArrayList<>();

        tablasDeBingo.get(0).setFormaDeGanar(FormaDeGanar.LINEA);

        for (int i = 0; i < 75; i++) {

            Integer numeroQueSalioDelBombo = obtenerUnNumeroNuevo(numerosQueHanSalido);

            tablasDeBingo.get(0).setNumeroDelLocutor(numeroQueSalioDelBombo);

            System.out.println(numeroQueSalioDelBombo);
            System.out.println("Vuelta #" + i);
            System.out.println(tablasDeBingo);


            if (TablaDeBingo.algunaTablaEstaCompleta()){
                System.out.println("La tabla ganadora es:\n" + TablaDeBingo.obtenerTablaGanadora());
                break;
            }

        }

    }

    private static ArrayList<TablaDeBingo> crearCantidadDeTablasDeBingo(int numeroDeTablasACrear) {
        ArrayList<TablaDeBingo> auxiliarParaPasarLasTablas = new ArrayList<>();
        for (int i =0; i<numeroDeTablasACrear ; i++){
            auxiliarParaPasarLasTablas.add(new TablaDeBingo(i+1));
        }
        return auxiliarParaPasarLasTablas;
    }

    private static int obtenerUnNumeroNuevo(ArrayList<Integer> numerosQueHanSalido) {
        int numero = ((int) Math.floor(Math.random() * (75 - 1 + 1) + 1));
        while (numerosQueHanSalido.contains(numero)) {
            numero = ((int) Math.floor(Math.random() * (75 - 1 + 1) + 1));
        }
        numerosQueHanSalido.add(numero);
        return numero;
    }
}