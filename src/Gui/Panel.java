/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.BorderLayout;
import logic.Node;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import logic.Graph;
import logic.GraphSearch;
import logic.HeuristicType;

/**
 *
 * @author USER
 */
public class Panel extends javax.swing.JPanel {

    private JLabel[][] labels;
    
    private static final String IMAGE_PATH_FINISH = "src/resources/finish.png";  // Ruta de la imagen para el nodo de final
    private static final String IMAGE_PATH_OPEN = "src/resources/grass.png";  // Ruta de la imagen para el nodo libre
    private static final String IMAGE_PATH_WALL = "src/resources/wall.png";  // Ruta de la imagen para el nodo de pared
    private static final String IMAGE_PATH_ROCK = "src/resources/stone.png";  // Ruta de la imagen para el nodo de roca
    private static final String IMAGE_PATH_ROBOT = "src/resources/robot.png";  // Ruta de la imagen para el nodo de roca
    private static final String IMAGE_PATH_START = "src/resources/comienzo.png"; // Inicio
    private static final int IMAGE_SIZE = 100; // Tamaño deseado de las imágenes (en píxeles)
    private Graph graph;

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
        labels = null;
        setLayout(new GridLayout(0, 1)); // GridLayout con un número variable de filas
        setBackground(Color.WHITE);
    }

    public void createLabelsFromGraph() {
        int numRows = graph.getHeight();
        int numCols = graph.getColumnWidth();

        labels = new JLabel[numRows][numCols];
        setLayout(new GridLayout(numRows, numCols)); // GridLayout con el número correcto de filas y columnas

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Node node;
                node = graph.getNode(row, col);
                JLabel label = new JLabel();
                label.setOpaque(true);

                switch (node.getValue()) {
                    case "I":
                        ImageIcon startIcon = new ImageIcon(IMAGE_PATH_START);
                        label.setIcon(scaleImage(startIcon));
                        break;
                    case "F":
                        ImageIcon finishIcon = new ImageIcon(IMAGE_PATH_FINISH);
                        label.setIcon(scaleImage(finishIcon));
                        break;
                    case "C":
                        ImageIcon openIcon = new ImageIcon(IMAGE_PATH_OPEN);
                        label.setIcon(scaleImage(openIcon));
                        break;
                    case "M":
                        ImageIcon wallIcon = new ImageIcon(IMAGE_PATH_WALL);
                        label.setIcon(scaleImage(wallIcon));
                        break;
                    case "R":
                        ImageIcon rockIcon = new ImageIcon(IMAGE_PATH_ROCK);
                        label.setIcon(scaleImage(rockIcon));
                        break;
                    default:
                        label.setIcon(null); // Sin imagen por defecto
                        break;
                }

                labels[row][col] = label;
                add(label);
            }
        }
        revalidate(); // Vuelve a validar el panel para mostrar los cambios
        repaint(); // Vuelve a pintar el panel

    }

    /**
     *
     * @param icon
     * @return
     */
    private ImageIcon scaleImage(ImageIcon icon) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public void changeNodeColor(int row, int col, Color color) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                labels[row][col].setIcon(null);
                labels[row][col].setBackground(color);
                labels[row][col].repaint();
            }
        });
    }

    public void changeNodeImg(int row, int col, ImageIcon image) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                labels[row][col].setIcon(null);
                labels[row][col].setIcon(scaleImage(image));
                labels[row][col].repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * combobox de botones
     *
     * @param mainPanel
     */
    public void addAlgorithmComboBox(JPanel mainPanel) {
        String[] algorithmNames = {
            "UCS",
            "DFS",
            "BFS",
            "A estrella M",
            "A estrella E",
            "Beam M",
            "Beam E",
            "Hill M",
            "Hill E"
        };
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithmNames);
        algorithmComboBox.addActionListener((ActionEvent e) -> {
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            Thread searchThread;
            searchThread = new Thread(() -> {
                GraphSearch graphSearch = new GraphSearch(Panel.this);
                try {
                    switch (selectedAlgorithm) {
                        case "UCS":
                            GraphSearch.ucs(graph, Panel.this);
                            break;
                        case "DFS":
                            GraphSearch.search(graph, false, Panel.this);
                            break;
                        case "BFS":
                            GraphSearch.search(graph, true, Panel.this);
                            break;
                        case "A estrella M":
                            GraphSearch.aStar(graph, HeuristicType.MANHATTAN, Panel.this);
                            break;
                        case "A estrella E":
                            GraphSearch.aStar(graph, HeuristicType.EUCLIDEAN, Panel.this);
                            break;
                        case "Beam M":
                            GraphSearch.beamSearch(graph, 2, HeuristicType.MANHATTAN, Panel.this);
                            break;
                        case "Beam E":
                            GraphSearch.beamSearch(graph, 2, HeuristicType.EUCLIDEAN, Panel.this);
                            break;
                        case "Hill M":
                            GraphSearch.hillClimbing(graph, HeuristicType.MANHATTAN, Panel.this);
                            break;
                        case "Hill E":
                            GraphSearch.hillClimbing(graph, HeuristicType.EUCLIDEAN, Panel.this);
                            break;
                    }
                } catch (InterruptedException ex) {
                }
            });
            searchThread.start();
        });
        mainPanel.add(algorithmComboBox);
    }

    public void clearPanel() {
        removeAll(); // Elimina todos los componentes del panel
        revalidate(); // Vuelve a validar el panel
        repaint(); // Vuelve a pintar el panel
    }

    /**
     * Botón para limpar el tablero
     *
     * @param mainPanel
     */
    public void addResetButton(JPanel mainPanel) {
        JButton resetButton = new JButton("Limpiar");
        resetButton.addActionListener((ActionEvent e) -> {
            clearPanel();
            createLabelsFromGraph();
        });
        mainPanel.add(resetButton);
    }
    
    public void changeNodeText(int row, int col, String text) {
        JLabel label = labels[row][col];

        // Crear un nuevo JPanel con un BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); // Establecer la opacidad del panel en falso para que no cubra la imagen

        // Crear un JLabel para mostrar el número
        JLabel numberLabel = new JLabel(text);
        numberLabel.setFont(numberLabel.getFont().deriveFont(Font.BOLD, 16));
        numberLabel.setForeground(Color.BLACK);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Agregar el JLabel del número al JPanel
        panel.add(numberLabel, BorderLayout.NORTH);

        // Agregar el JPanel al JLabel
        label.setLayout(new BorderLayout());
        label.add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
