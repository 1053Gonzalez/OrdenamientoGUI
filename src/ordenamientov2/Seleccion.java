package ordenamientov2;

import java.util.Arrays;
import java.util.Scanner;

public class Seleccion {

    public static void sort(int[] array) {
        int swaps = 0; // Contador de intercambios
        int n = array.length;

        System.out.println("Arreglo original: " + arrayToString(array));

        int totalPasos = 0;
        int totalPasadas = 0;

        Scanner scanner = new Scanner(System.in);

        while (totalPasadas < n - 1) {
            int minIndex = totalPasadas;

            for (int j = totalPasadas + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                totalPasos++;
            }

            // Intercambio de elementos
            int temp = array[minIndex];
            array[minIndex] = array[totalPasadas];
            array[totalPasadas] = temp;
            swaps++;

            System.out.println("Paso " + (totalPasadas + 1) + ": " + arrayToString(array));
            System.out.println("    Moviendo límite de la porción ordenada.");
            System.out.println("    Construyendo porción ordenada: " + arrayToString(Arrays.copyOfRange(array, 0, totalPasadas + 1)));
            System.out.println("    Intercambio realizado. Total de intercambios: " + swaps);

            if (Utilidades.estaOrdenado(array)) {
                System.out.println("El arreglo está ordenado:");
                System.out.println(arrayToString(array));
                System.out.println("Finalizando.");
                break;
            }

            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine(); // Espera hasta que el usuario presione Enter

            totalPasadas++;
        }

        scanner.close();

        System.out.println("Arreglo ordenado con el método de selección.");
        System.out.println("Intercambios realizados: " + swaps);
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
