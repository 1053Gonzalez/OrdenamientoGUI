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

public class BurbujaGrafica extends JFrame {

    private JTextArea areaTextoDetalle;
    private JPanel arrayPanel;
    private JButton botonSiguiente;
    private int[] array;
    private int pasos;
    private int indicePasos = 0;
    private int totalPasos = 0;
    private int totalPasadas = 0;
    private Timer timer;
    private Queue<String> actualizacionesCola;
    Color AzuOscuro = new Color(0, 102, 204);

    public BurbujaGrafica(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.pasos = 0;
        this.actualizacionesCola = new LinkedList<>();
        inicializarIU();
    }

    private void inicializarIU() {
        //titulo de la ventana
        setTitle("Ordenamiento por Burbuja Grafica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        
        //panel para contener el arreglo
        arrayPanel = new JPanel();
        arrayPanel.setLayout(new FlowLayout());
        arrayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        areaTextoDetalle = new JTextArea(10, 40);
        areaTextoDetalle.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        areaTextoDetalle.setEditable(false);
        areaTextoDetalle.setLineWrap(true);
        areaTextoDetalle.setFont(new Font("Arial", Font.PLAIN, 16));
        areaTextoDetalle.append("ORDENAMIENTO GRAFICO BURBUJA PARA EL ARREGLO: \n" + Arrays.toString(array) + "\n\n");

        JScrollPane scrollPane = new JScrollPane(areaTextoDetalle);
        
        //boton para siguiente paso
        botonSiguiente = new JButton("Siguiente Paso");
        botonSiguiente.setPreferredSize(new Dimension(260, 50));
        botonSiguiente.setFont(new Font("Arial", Font.BOLD, 18));
        botonSiguiente.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, true));
        botonSiguiente.setBackground(AzuOscuro);
        botonSiguiente.setText("Siguiente Paso");
        botonSiguiente.setForeground(Color.WHITE);

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
        botonRegresar.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, true));
        botonRegresar.setBackground(AzuOscuro);
        botonRegresar.setText("Regresar al Menú");
        botonRegresar.setForeground(Color.WHITE);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        //panel principal de Burbuja Grafoca para contener los cuadros y el botón
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
            Color background = new Color(204, 229, 255); // Azul claro
            Color foreground = new Color(0, 51, 102);    // Azul oscuro
            label.setBackground(background);
            label.setForeground(foreground);
            label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
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
        if (Utilidades.estaOrdenado(array)) {
            actualizacionesCola.offer("\n\nEl arreglo está ordenado:\n");
            actualizacionesCola.offer(Arrays.toString(array) + "\n");
            actualizacionesCola.offer("Número total de pasos: " + totalPasos + "\n");
            actualizacionesCola.offer("Número total de pasadas: " + totalPasadas + "\n");
            botonSiguiente.setEnabled(false);
            return;
        }

        int i = indicePasos;
        int j = indicePasos + 1;

        actualizacionesCola.offer("Paso " + (indicePasos + 1) + ": ");
        actualizacionesCola.offer("Comparando " + array[i] + " con " + array[j]);

        if (array[i] > array[j]) {
            actualizacionesCola.offer(", " + array[i] + " es mayor que " + array[j] + "\n");
            actualizacionesCola.offer("Intercambiando " + array[i] + " con " + array[j] + "\n");

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        } else {
            actualizacionesCola.offer(", " + array[i] + " es menor o igual a " + array[j] + "\n");
            actualizacionesCola.offer("No se realiza intercambio\n");
        }

        System.out.println(Arrays.toString(array));
        actualizacionesCola.offer("" + Arrays.toString(array) + "\n\n");

        indicePasos++;
        totalPasos++;

        if (indicePasos >= array.length - totalPasadas - 1) {
            actualizacionesCola.offer("Finalizada pasada " + (totalPasadas + 1) + "\n");
            indicePasos = 0;
            totalPasadas++;
        }

        actualizarArrayPanel(Arrays.copyOf(array, array.length));
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            int[] array = {50, 40, 30, 20, 10};
//            new BurbujaGrafica(array);
//        });
//    }
}
