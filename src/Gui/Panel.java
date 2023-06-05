/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import proyectointeligentesfinal.Node;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import proyectointeligentesfinal.Graph;
import proyectointeligentesfinal.GraphReader;

/**
 *
 * @author USER
 */
public class Panel extends javax.swing.JPanel {

    private GraphReader graphReader;
    private JLabel[][] labels;
    private static final String IMAGE_PATH_START = "src/resources/grass.png"; // Ruta de la imagen para el nodo de inicio
    private static final String IMAGE_PATH_FINISH = "src/resources/grass.png";  // Ruta de la imagen para el nodo de final
    private static final String IMAGE_PATH_OPEN = "src/resources/grass.png";  // Ruta de la imagen para el nodo libre
    private static final String IMAGE_PATH_WALL = "src/resources/wall.png";  // Ruta de la imagen para el nodo de pared
    private static final String IMAGE_PATH_ROCK = "src/resources/stone.png";  // Ruta de la imagen para el nodo de roca
    private static final String IMAGE_PATH_ROBOT = "src/resources/robot.png";  // Ruta de la imagen para el nodo de roca
    private static final int IMAGE_SIZE = 50; // Tamaño deseado de las imágenes (en píxeles)
    private Graph graph;
// Agrega más rutas de imágenes para otros tipos de nodos si los tienes

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
        graphReader = new GraphReader();
        labels = null;

        setPreferredSize(new Dimension(1080, 720)); // Establecer la dimensión preferida del panel
        setLayout(new GridLayout(0, 1)); // GridLayout con un número variable de filas
        setBackground(Color.WHITE);
    }

    public void createLabelsFromGraph(String filename) {
        Graph graph = graphReader.readGraphFromFile(filename);
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
                // Agrega más casos para otros tipos de nodos si los tienes
                default:
                    label.setIcon(null); // Sin imagen por defecto
                    break;
            }

                labels[row][col] = label;
                add(label);
            }
        }

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

    /**
     * Funciones para pintar las busquedas
     */
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
