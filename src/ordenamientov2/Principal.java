package ordenamientov2;

import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la longitud del arreglo:");
        int length = scanner.nextInt();

        int[] array = new int[length];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Seleccione el algoritmo de ordenamiento:");
        System.out.println("1. Burbuja");
        System.out.println("2. Selección");
        System.out.println("3. Inserción");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //SwingUtilities.invokeLater(() -> new BurbujaGrafica(array));
                Burbuja.sort(array);
                break;
            case 2:
                //SwingUtilities.invokeLater(() -> new SeleccionGrafica(array));
                Seleccion.sort(array);
                break;
            case 3:
                Insercion.sort(array);
                //SwingUtilities.invokeLater(() -> new InsercionGrafica(array));
                break;
                
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
    }
}