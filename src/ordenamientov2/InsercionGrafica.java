package ordenamientov2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class InsercionGrafica extends JFrame {

    private JTextArea areaTextoDetalle;
    private JPanel arrayPanel;
    private JButton botonSiguiente;
    private int[] array;
    private int pasos;
    private Timer timer;
    private Queue<String> actualizacionesCola;
    Color VerdeClaro = new Color(204, 255, 229);
    Color VerdeOscuro = new Color(0, 102, 51);

    public InsercionGrafica(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.pasos = 0;
        this.actualizacionesCola = new LinkedList<>();
        inicializarIU();
    }

    private void inicializarIU() {
        setTitle("Ordenamiento por Inserción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        arrayPanel = new JPanel();
        arrayPanel.setLayout(new FlowLayout());
        arrayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        areaTextoDetalle = new JTextArea(10, 40);
        areaTextoDetalle.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        areaTextoDetalle.setEditable(false);
        areaTextoDetalle.setLineWrap(true);
        areaTextoDetalle.setFont(new Font("Arial", Font.PLAIN, 16));
        areaTextoDetalle.append("ORDENAMIENTO GRAFICO POR INSECION PARA EL ARREGLO: \n" + Arrays.toString(array) + "\n\n");

        JScrollPane scrollPane = new JScrollPane(areaTextoDetalle);

        //boton para siguiente paso
        botonSiguiente = new JButton("Siguiente Paso");
        botonSiguiente.setPreferredSize(new Dimension(260, 50));
        botonSiguiente.setFont(new Font("Arial", Font.BOLD, 18));
        botonSiguiente.setBorder(BorderFactory.createLineBorder(VerdeOscuro, 3, true));
        botonSiguiente.setBackground(VerdeClaro);
        botonSiguiente.setText("Siguiente Paso");
        botonSiguiente.setForeground(Color.BLACK);

        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguientePaso();
            }
        });

        //boton para regresar al menu
        JButton botonRegresar = new JButton("Menú");
        botonRegresar = new JButton("Regresar al Menú");
        botonRegresar.setPreferredSize(new Dimension(260, 50));
        botonRegresar.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegresar.setBorder(BorderFactory.createLineBorder(VerdeOscuro, 3, true));
        botonRegresar.setBackground(VerdeClaro);
        botonRegresar.setText("Regresar al Menú");
        botonRegresar.setForeground(Color.BLACK);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual

            }
        });

        //panel para contener los cuadros y el botón
        JPanel panelPrincipal = new JPanel();
        //panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(arrayPanel, BorderLayout.CENTER);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));

        //panel para contener el área de texto y el botón
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonsPanel.add(botonRegresar);
        buttonsPanel.add(botonSiguiente);
        controlPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar ambos paneles al contenedor principal
        add(panelPrincipal, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        pack();
        setSize(1200, 500); // Establecer el tamaño de la ventana
        setLocationRelativeTo(null);
        setVisible(true);

        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTimerTick();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void handleTimerTick() {
        if (!actualizacionesCola.isEmpty()) {
            areaTextoDetalle.append(actualizacionesCola.poll());
        }
    }
    //Metodo para actualizar los elementos graficos del arreglo en el panel
    private void actualizarArrayPanel(int[] array) {
        Border margin = BorderFactory.createEmptyBorder(150, 20, 100, 10);
        arrayPanel.setBorder(margin);
        arrayPanel.removeAll();

        for (int num : array) {
            JLabel label = new JLabel(Integer.toString(num));

            label.setBackground(VerdeClaro);
            label.setForeground(VerdeOscuro);
            label.setBorder(BorderFactory.createLineBorder(VerdeOscuro, 10));
            label.setFont(new Font("Unispace", Font.BOLD, 18));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setPreferredSize(new Dimension(80, 80));
            label.setOpaque(true);

            arrayPanel.add(label);
        }
        arrayPanel.revalidate();
        arrayPanel.repaint();

    }
    
    //metodo con la logica necesaria para realizar cada paso con el algoritmo de ordenamiento seleccionado
    private void siguientePaso() {
        //titulo de la ventana
        if (pasos < array.length) {
            int currentElement = array[pasos];
            int j = pasos - 1;

            //panel para contener el arreglo
            actualizacionesCola.offer("\nPaso " + (pasos + 1) + ": " + Arrays.toString(array) + "\n");
            actualizacionesCola.offer("    Elemento seleccionado: " + currentElement + "\n");

            while (j >= 0 && array[j] > currentElement) {
                actualizacionesCola.offer("    Desplazando " + array[j] + " a la derecha\n");
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = currentElement;

            actualizacionesCola.offer("    Insertando " + currentElement + " en la posición " + (j + 1) + "\n");
            actualizacionesCola.offer("    Resultado parcial: " + Arrays.toString(array) + "\n");
            System.out.println(Arrays.toString(array));

            actualizarArrayPanel(Arrays.copyOf(array, array.length));

            pasos++;

            if (Utilidades.estaOrdenado(array)) {
                actualizacionesCola.offer("\n\nEl arreglo está ordenado. Finalizando.\n");
                actualizacionesCola.offer(Arrays.toString(array) + "\n");
                botonSiguiente.setEnabled(false);
            }

            actualizacionesCola.offer("Presiona Enter para continuar...\n");

        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            int[] array = {60, 50, 40, 30, 20, 10};
//            new InsercionGrafica(array);
//        });
//    }
//
}
