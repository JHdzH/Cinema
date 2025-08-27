/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.panels;

import cinema.controller.PeliculaController;
import cinema.model.Pelicula;
import vistas.swing.tables.PeliculaTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import vistas.swing.dialogs.PeliculaDialog;

public class PeliculaPanel extends JPanel {
    private PeliculaController controller;
    private JTable table;
    private PeliculaTableModel tableModel;
    
    public PeliculaPanel() {
        this.controller = new PeliculaController();
        initialize();
        loadPeliculas();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnAgregar = new JButton("Agregar Película");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        
        btnAgregar.addActionListener(e -> agregarPelicula());
        btnEditar.addActionListener(e -> editarPelicula());
        btnEliminar.addActionListener(e -> eliminarPelicula());
        btnActualizar.addActionListener(e -> loadPeliculas());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnActualizar);
        
        // Tabla
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadPeliculas() {
        List<Pelicula> peliculas = controller.obtenerTodasPeliculas();
        tableModel = new PeliculaTableModel(peliculas);
        table.setModel(tableModel);
    }
    
    private void agregarPelicula() {
        PeliculaDialog dialog = new PeliculaDialog((Frame) SwingUtilities.getWindowAncestor(this), controller);
        dialog.setVisible(true);
        loadPeliculas();
    }
    
    private void editarPelicula() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pelicula pelicula = tableModel.getPeliculaAt(selectedRow);
        PeliculaDialog dialog = new PeliculaDialog((Frame) SwingUtilities.getWindowAncestor(this), controller, pelicula);
        dialog.setVisible(true);
        loadPeliculas();
    }
    
    private void eliminarPelicula() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pelicula pelicula = tableModel.getPeliculaAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar la película: " + pelicula.getTitulo() + "?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.eliminarPelicula(pelicula.getIdPelicula());
            if (success) {
                JOptionPane.showMessageDialog(this, "Película eliminada exitosamente");
                loadPeliculas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la película", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}