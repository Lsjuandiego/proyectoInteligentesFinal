/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import proyectointeligentesfinal.Graph;
import proyectointeligentesfinal.GraphReader;
import proyectointeligentesfinal.GraphSearch;
import proyectointeligentesfinal.HeuristicType;

/**
 *
 * @author USER
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new Gui.Panel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        GraphReader reader = new GraphReader();
        Graph graph = reader.readGraphFromFile("src/files/camino2.txt");
        graph.newList(graph);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Panel panel = new Panel();
                JFrame frame = new JFrame("Matrix Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                panel.createLabelsFromGraph("src/files/camino2.txt");

                /**
                 * Botón para busqueda UCS
                 */
                JButton ucsButton = new JButton("UCS");
                ucsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.ucs(graph, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda DFS
                 */
                JButton dfsButton = new JButton("DFS");
                dfsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.search(graph, false, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda BFS
                 */
                JButton bfsButton = new JButton("BFS");
                bfsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.search(graph, true, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda A estrella con heuristica Manhattan
                 */
                JButton astarM = new JButton("A estrella M");
                astarM.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.aStar(graph, HeuristicType.MANHATTAN, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda A estrella con heuristica Euclidiana
                 */
                JButton astarE = new JButton("A estrella E");
                astarE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.aStar(graph, HeuristicType.EUCLIDEAN, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda Beam search con heuristica Manhattan
                 */
                JButton beamM = new JButton("Beam M");
                beamM.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.beamSearch(graph, 2, HeuristicType.MANHATTAN,panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Botón para busqueda Beam search con heuristica Euclideana
                 */
                JButton beamE = new JButton("Beam E");
                beamE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.beamSearch(graph, 2, HeuristicType.EUCLIDEAN,panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Hill climbing con Manhattan
                 */
                JButton hillM = new JButton("Hill M");
                hillM.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.hillClimbing(graph, HeuristicType.MANHATTAN, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });

                /**
                 * Hill climbing con Euclideana
                 */
                JButton hillE = new JButton("Hill E");
                hillE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thread searchThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                GraphSearch graphSearch = new GraphSearch(panel);
                                try {
                                    graphSearch.hillClimbing(graph, HeuristicType.EUCLIDEAN, panel);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        searchThread.start();
                    }
                });
    
                /**
                 * Botón para reiniciar el panel
                 */
                JButton resetButton = new JButton("Reiniciar");
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                         Panel panel = new Panel();
                JFrame frame = new JFrame("Matrix Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                panel.createLabelsFromGraph("src/files/camino2.txt");
                panel.repaint();
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(resetButton);
                buttonPanel.add(ucsButton);
                buttonPanel.add(dfsButton);
                buttonPanel.add(bfsButton);
                buttonPanel.add(astarM);
                buttonPanel.add(astarE);
                buttonPanel.add(beamM);
                buttonPanel.add(beamE);
                buttonPanel.add(hillM);
                buttonPanel.add(hillE);

                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
                frame.getContentPane().add(panel, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private Gui.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
