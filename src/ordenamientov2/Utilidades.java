package ordenamientov2;

import java.util.Arrays;

public class Utilidades {

    //utilidad para validar si esta ordenado
    public static boolean estaOrdenado(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }



}
