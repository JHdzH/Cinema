/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.dialogs;

import cinema.controller.PeliculaController;
import cinema.model.Pelicula;
import javax.swing.*;
import java.awt.*;

public class PeliculaDialog extends JDialog {
    private PeliculaController controller;
    private Pelicula pelicula;
    
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtDuracion;
    
    public PeliculaDialog(Frame parent, PeliculaController controller) {
        this(parent, controller, null);
    }
    
    public PeliculaDialog(Frame parent, PeliculaController controller, Pelicula pelicula) {
        super(parent, true);
        this.controller = controller;
        this.pelicula = pelicula;
        initialize();
    }
    
    private void initialize() {
        setTitle(pelicula == null ? "Agregar Película" : "Editar Película");
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setLayout(new GridLayout(4, 2, 10, 10));
        
        // Campos del formulario
        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);
        
        add(new JLabel("Género:"));
        txtGenero = new JTextField();
        add(txtGenero);
        
        add(new JLabel("Duración:"));
        txtDuracion = new JTextField();
        add(txtDuracion);
        
        // Botones
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> guardarPelicula());
        btnCancelar.addActionListener(e -> dispose());
        
        add(btnGuardar);
        add(btnCancelar);
        
        // Si está editando, cargar datos
        if (pelicula != null) {
            txtTitulo.setText(pelicula.getTitulo());
            txtGenero.setText(pelicula.getGenero());
            txtDuracion.setText(pelicula.getDuracion());
        }
    }
    
    private void guardarPelicula() {
        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        String duracion = txtDuracion.getText().trim();
        
        if (titulo.isEmpty() || genero.isEmpty() || duracion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (pelicula == null) {
                // Nueva película
                boolean success = controller.agregarPelicula(titulo, genero, duracion);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Película agregada exitosamente");
                    dispose();
                }
            } else {
                // Editar película existente
                pelicula.setTitulo(titulo);
                pelicula.setGenero(genero);
                pelicula.setDuracion(duracion);
                // Implementar actualización en controller
                JOptionPane.showMessageDialog(this, "Película actualizada exitosamente");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
