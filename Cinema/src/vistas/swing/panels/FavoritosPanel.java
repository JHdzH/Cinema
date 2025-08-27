/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.panels;

import cinema.controller.FavoritaController;
import cinema.controller.UsuarioController;
import cinema.controller.PeliculaController;
import cinema.model.Pelicula;
import cinema.model.Usuario;
import vistas.swing.tables.PeliculaTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FavoritosPanel extends JPanel {
    private FavoritaController favoritaController;
    private UsuarioController usuarioController;
    private PeliculaController peliculaController;
    
    private JComboBox<Usuario> cmbUsuarios;
    private JTable table;
    private PeliculaTableModel tableModel;
    private JButton btnAgregarFavorita;
    private JButton btnRemoverFavorita;
    
    public FavoritosPanel() {
        this.favoritaController = new FavoritaController();
        this.usuarioController = new UsuarioController();
        this.peliculaController = new PeliculaController();
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Selección de usuario
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Seleccionar Usuario:"));
        
        cmbUsuarios = new JComboBox<>();
        cargarUsuarios();
        cmbUsuarios.addActionListener(e -> cargarFavoritos());
        topPanel.add(cmbUsuarios);
        
        // Panel central - Tabla de favoritos
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Panel inferior - Botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnAgregarFavorita = new JButton("Agregar a Favoritos");
        btnRemoverFavorita = new JButton("Remover de Favoritos");
        
        btnAgregarFavorita.addActionListener(e -> agregarFavorita());
        btnRemoverFavorita.addActionListener(e -> removerFavorita());
        
        bottomPanel.add(btnAgregarFavorita);
        bottomPanel.add(btnRemoverFavorita);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void cargarUsuarios() {
        List<Usuario> usuarios = usuarioController.obtenerTodosUsuarios();
        cmbUsuarios.removeAllItems();
        for (Usuario usuario : usuarios) {
            cmbUsuarios.addItem(usuario);
        }
    }
    
    private void cargarFavoritos() {
        Usuario usuario = (Usuario) cmbUsuarios.getSelectedItem();
        if (usuario != null) {
            List<Pelicula> favoritas = favoritaController.obtenerFavoritasPorUsuario(usuario.getId());
            tableModel = new PeliculaTableModel(favoritas);
            table.setModel(tableModel);
        }
    }
    
    private void agregarFavorita() {
        Usuario usuario = (Usuario) cmbUsuarios.getSelectedItem();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Diálogo para seleccionar película
        List<Pelicula> peliculas = peliculaController.obtenerTodasPeliculas();
        Pelicula[] peliculasArray = peliculas.toArray(new Pelicula[0]);
        
        Pelicula selectedPelicula = (Pelicula) JOptionPane.showInputDialog(
            this,
            "Seleccione una película:",
            "Agregar a Favoritos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            peliculasArray,
            peliculasArray.length > 0 ? peliculasArray[0] : null
        );
        
        if (selectedPelicula != null) {
            boolean success = favoritaController.agregarFavorita(selectedPelicula.getIdPelicula(), usuario.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Película agregada a favoritos");
                cargarFavoritos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar a favoritos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void removerFavorita() {
        Usuario usuario = (Usuario) cmbUsuarios.getSelectedItem();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película para remover", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pelicula pelicula = tableModel.getPeliculaAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Remover '" + pelicula.getTitulo() + "' de favoritos?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = favoritaController.eliminarFavorita(pelicula.getIdPelicula(), usuario.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Película removida de favoritos");
                cargarFavoritos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al remover de favoritos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}