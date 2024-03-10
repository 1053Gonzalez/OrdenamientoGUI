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

public class SeleccionGrafica extends JFrame {

    private JTextArea areaTextoDetalle;
    private JPanel arrayPanel;
    private JButton botonSiguiente;
    private int[] array;
    private int pasos;
    private Timer timer;
    private Queue<String> actualizacionesCola;
    Color MoradoClaro = new Color(209, 196, 233);
    Color MoradoOscuro = new Color(112, 48, 160);

    public SeleccionGrafica(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.pasos = 0;
        this.actualizacionesCola = new LinkedList<>();
        inicializarIU();
    }

    private void inicializarIU() {
        //titulo de la ventana
        setTitle("Ordenamiento por Selección");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //panel para contener el arreglo
        arrayPanel = new JPanel();
        arrayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        areaTextoDetalle = new JTextArea(10, 40);
        areaTextoDetalle.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        areaTextoDetalle.setEditable(false);
        areaTextoDetalle.setLineWrap(true);
        areaTextoDetalle.setFont(new Font("Arial", Font.PLAIN, 16));
        areaTextoDetalle.append("ORDENAMIENTO GRÁFICO POR SELECCIÓN PARA EL ARREGLO: \n" + Arrays.toString(array) + "\n\n");

        JScrollPane scrollPane = new JScrollPane(areaTextoDetalle);

        //boton para siguiente paso
        botonSiguiente = new JButton("Siguiente Paso");
        botonSiguiente.setPreferredSize(new Dimension(260, 50));
        botonSiguiente.setFont(new Font("Arial", Font.BOLD, 18));
        botonSiguiente.setBorder(BorderFactory.createLineBorder(MoradoOscuro, 3, true));
        botonSiguiente.setBackground(MoradoClaro);
        botonSiguiente.setForeground(Color.BLACK);
        botonSiguiente.addActionListener(e -> siguientePaso());
        
        //boton para regresar al menu
        JButton botonRegresar = new JButton("Menú");
        botonRegresar = new JButton("Regresar al Menú");
        botonRegresar.setPreferredSize(new Dimension(260, 50));
        botonRegresar.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegresar.setBorder(BorderFactory.createLineBorder(MoradoOscuro, 3, true));
        botonRegresar.setBackground(MoradoClaro);
        botonRegresar.setText("Regresar al Menú");
        botonRegresar.setForeground(Color.BLACK);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        
        //panel principal de Seleccion Grafica
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
        panelPrincipal.add(arrayPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonsPanel.add(botonRegresar);
        buttonsPanel.add(botonSiguiente);
        controlPanel.add(buttonsPanel, BorderLayout.SOUTH);

        Dimension textAreaSize = areaTextoDetalle.getPreferredSize();
        buttonsPanel.setPreferredSize(new Dimension(textAreaSize.width / 2, buttonsPanel.getPreferredSize().height));

        add(panelPrincipal, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        pack();
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        timer = new Timer(150, e -> handleTimerTick());
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

            label.setBackground(MoradoClaro);
            label.setForeground(MoradoOscuro);
            label.setBorder(BorderFactory.createLineBorder(MoradoOscuro, 10));
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
        if (pasos < array.length - 1) {
            int minIndex = pasos;

            for (int j = pasos + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }

            }

            int temp = array[minIndex];
            array[minIndex] = array[pasos];
            array[pasos] = temp;

            actualizacionesCola.offer("\nPaso " + (pasos + 1) + ": " + Arrays.toString(array) + "\n");
            actualizacionesCola.offer("    Moviendo límite de la porción ordenada.\n");
            actualizacionesCola.offer("    Construyendo porción ordenada: " + Arrays.toString(Arrays.copyOfRange(array, 0, pasos + 1)) + "\n");
            actualizacionesCola.offer("    Intercambio realizado. Total de intercambios: " + (pasos + 1) + "\n");

            actualizarArrayPanel(Arrays.copyOf(array, array.length));

            pasos++;

            if (Utilidades.estaOrdenado(array)) {
                actualizacionesCola.offer("\n\nEl arreglo está ordenado:\n");
                actualizacionesCola.offer(Arrays.toString(array) + "\n");
                actualizacionesCola.offer("Finalizando.\n");
                botonSiguiente.setEnabled(false);
            }

            actualizacionesCola.offer("Presiona Enter para continuar...\n");
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            int[] array = {60, 50, 40, 30, 20, 10};
//            new SeleccionGrafica(array);
//        });
//    }
}
