package ordenamientov2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI extends JFrame {


    private JTextArea arrayField;
    private JComboBox<String> algorithmComboBox;

    public PrincipalGUI() {
        setTitle("Ordenamiento de Arreglos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para ingresar los elementos del arreglo
        JPanel panelArreglo = new JPanel(new BorderLayout());
        // Mensaje de bienbenida
        JLabel Bienbenida = new JLabel("\nBienbenido al sofware de ordenamieto algorimico de JoseIGP.");
        Bienbenida.setFont(new Font("Arial", Font.BOLD, 18));
        Bienbenida.setHorizontalAlignment(SwingConstants.CENTER);

        // Area para ingresar los elementos del arreglo
        JLabel Arreglo = new JLabel("Ingrese los elementos del arreglo separados por (,):");
        Arreglo.setFont(new Font("Arial", Font.BOLD, 18));
        Arreglo.setHorizontalAlignment(SwingConstants.CENTER);
        arrayField = new JTextArea(2, 20);
        arrayField.setEditable(true);
        arrayField.setFont(new Font("UNISPACE", Font.BOLD, 24));

        JScrollPane panelDesplazamiento = new JScrollPane(arrayField);

        panelArreglo.add(Bienbenida, BorderLayout.NORTH);
        panelArreglo.add(Arreglo, BorderLayout.CENTER);
        panelArreglo.add(panelDesplazamiento, BorderLayout.SOUTH);

        // Panel para seleccionar el algoritmo de ordenamiento
        JPanel algoritmoPanel = new JPanel(new FlowLayout());
        JLabel seleccionAlgoritmo = new JLabel("Seleccione el algoritmo de ordenamiento:");
        seleccionAlgoritmo.setFont(new Font("Arial", Font.BOLD, 18));
        algorithmComboBox = new JComboBox<>(new String[]{"Burbuja", "Selección", "Inserción"});
        algorithmComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        algoritmoPanel.add(seleccionAlgoritmo);
        algoritmoPanel.add(algorithmComboBox);

        // Botón para iniciar el ordenamiento
        JButton botonOrdenar = new JButton("Ordenar");
        botonOrdenar.setPreferredSize(new Dimension(160, 50));
        botonOrdenar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonOrdenar.setForeground(Color.WHITE);
        botonOrdenar.setBackground(Color.GRAY);
        botonOrdenar.setFont(new Font("Arial", Font.BOLD, 18));
        botonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = arrayField.getText();
                //si no hay datos a ordenar, no avanzamos al algoritmo de ordenamiento grafico
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números para ordenar", "Alerta", JOptionPane.WARNING_MESSAGE);
                    return; 
                }

                int length = input.split(",").length;
                int[] array = analizarArray(input, length);
                String algorithm = (String) algorithmComboBox.getSelectedItem();
                if (array.length > 0) {
                    startSorting(array, algorithm);
                }
            }
        });

        // Agregar los paneles al contenedor principal
        add(panelArreglo, BorderLayout.NORTH);
        add(arrayField, BorderLayout.CENTER);
        add(algoritmoPanel, BorderLayout.SOUTH);
        add(botonOrdenar, BorderLayout.EAST);

        pack();
        setSize(800, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //Metodo para dividir la cadena ingresada tomando como delimitador la coma
    private int[] analizarArray(String input, int length) {
        int[] array = new int[length];
        String[] elements = input.split(",");
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(elements[i].trim());
        }
        return array;
    }
    
    //Menu seleccionable para ir a la ventana de ordenamiento seleccionada
    private void startSorting(int[] array, String algorithm) {
        switch (algorithm) {
            case "Burbuja" -> SwingUtilities.invokeLater(() -> new BurbujaGrafica(array));
            case "Selección" -> SwingUtilities.invokeLater(() -> new SeleccionGrafica(array));
            case "Inserción" -> SwingUtilities.invokeLater(() -> new InsercionGrafica(array));
        }
    }

    //inializamos la interfaz grafica de usuario
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrincipalGUI::new);
    }
}
