package ordenamientov2;

import java.util.Arrays;
import java.util.Scanner;

public class Insercion {

    public static void sort(int[] array) {
        int steps = 0;
        int n = array.length;

        System.out.println("Arreglo original: " + arrayToString(array));

        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i < n; i++) {
            int currentElement = array[i];
            int j = i - 1;

            System.out.println("Paso " + (i) + ": " + arrayToString(array));
            System.out.println("    Elemento seleccionado: " + currentElement);

            if (Utilidades.estaOrdenado(array)) {
                System.out.println("El arreglo está ordenado. Finalizando.");
                break;
            }

            while (j >= 0 && array[j] > currentElement) {
                System.out.println("    Desplazando " + array[j] + " a la derecha");
                array[j + 1] = array[j];
                j--;
                steps++;
            }

            array[j + 1] = currentElement;

            System.out.println("    Insertando " + currentElement + " en la posición " + (j + 1));
            System.out.println("    Resultado parcial: " + arrayToString(array) + "\n");

            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine(); // Espera hasta que el usuario presione Enter
        }

        scanner.close();

        System.out.println("Arreglo ordenado con el método de inserción:"+ arrayToString(array) + "\n");
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
