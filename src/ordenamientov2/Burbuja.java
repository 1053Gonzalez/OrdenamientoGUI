package ordenamientov2;

import java.util.Scanner;

public class Burbuja {
    public static void sort(int[] array) {
        int steps = 0;
        int n = array.length;

        System.out.println("Arreglo original: " + arrayToString(array));

        int totalPasos = 0;
        int totalPasadas = 0;
        int indicePasos = 0;

        Scanner scanner = new Scanner(System.in);

        while (totalPasadas < n - 1) {
            int i = indicePasos;
            int j = indicePasos + 1;

            if (array[i] > array[j]) {
                System.out.println("Paso " + (steps + 1) + ": " + arrayToString(array));
                System.out.println("    " + array[i] + " es mayor que " + array[j]);
                System.out.println("    Intercambiando " + array[i] + " con " + array[j]);

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            } else {
                System.out.println("Paso " + (steps + 1) + ": " + arrayToString(array));
                System.out.println("    " + array[i] + " es menor o igual a " + array[j]);
                System.out.println("    No se realiza intercambio");
            }

            System.out.println("    Resultado: " + arrayToString(array) + "\n");

            if (Utilidades.estaOrdenado(array)) {
                System.out.println("El arreglo está ordenado. Finalizando.");
                break;
            }

            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine(); // Espera hasta que el usuario presione Enter

            indicePasos++;
            totalPasos++;

            if (indicePasos >= n - totalPasadas - 1) {
                System.out.println("Finalizada pasada " + (totalPasadas + 1) + "\n");
                indicePasos = 0;
                totalPasadas++;
            }

            steps++;
        }

        scanner.close();

        System.out.println("Arreglo ordenado con el método de burbuja.");
        System.out.println("Pasos tomados: " + steps);
    }

    private static String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
