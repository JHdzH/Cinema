/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing;

import javax.swing.*;
import java.awt.*;
import vistas.swing.panels.FavoritosPanel;
import vistas.swing.panels.PeliculaPanel;
import vistas.swing.panels.UsuarioPanel;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane;
    
    public MainWindow() {
        super("Sistema de Gestión de Cine - Cinema Management");
        initialize();
        setupWindow();
    }
    
    private void initialize() {
        // Configurar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear panels
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Usuarios", new UsuarioPanel());
        tabbedPane.addTab("Películas", new PeliculaPanel());
        tabbedPane.addTab("Favoritos", new FavoritosPanel());
    }
    
    private void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);
        
        add(tabbedPane);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}