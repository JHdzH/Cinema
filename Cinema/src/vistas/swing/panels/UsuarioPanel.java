/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.panels;

import cinema.controller.UsuarioController;
import cinema.model.Usuario;
import vistas.swing.tables.UsuarioTableModel;
import vistas.swing.dialogs.UsuarioDialog;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UsuarioPanel extends JPanel {

    private UsuarioController controller;
    private JTable table;
    private UsuarioTableModel tableModel;

    public UsuarioPanel() {
        this.controller = new UsuarioController();
        initialize();
        loadUsuarios();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAgregar = new JButton("Agregar Usuario");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");

        btnAgregar.addActionListener(e -> agregarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnActualizar.addActionListener(e -> loadUsuarios());

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

    private void loadUsuarios() {
        List<Usuario> usuarios = controller.obtenerTodosUsuarios();
        tableModel = new UsuarioTableModel(usuarios);
        table.setModel(tableModel);
    }

    private void cargarUsuarios() {
        List<Usuario> usuarios = controller.obtenerTodosUsuarios();
        if (tableModel == null) {
            tableModel = new UsuarioTableModel(usuarios);
            table.setModel(tableModel);
        } else {
            tableModel.actualizarDatos(usuarios); // Esto llama a fireTableDataChanged()
        }

        // Forzar actualización visual de la tabla :cite[2]:cite[5]
        table.revalidate();
        table.repaint();
    }

    private void agregarUsuario() {
        UsuarioDialog dialog = new UsuarioDialog((Frame) SwingUtilities.getWindowAncestor(this), controller);
        dialog.setVisible(true);
        // Actualizar la tabla después de agregar
        loadUsuarios();
    }

    private void editarUsuario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = tableModel.getUsuarioAt(selectedRow);
        UsuarioDialog dialog = new UsuarioDialog((Frame) SwingUtilities.getWindowAncestor(this), controller, usuario);
        dialog.setVisible(true);
        // Actualizar la tabla después de editar
        loadUsuarios();
    }

    private void eliminarUsuario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = tableModel.getUsuarioAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar al usuario: " + usuario.getNombre() + "?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.eliminarUsuario(usuario.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente");
                cargarUsuarios(); // Actualizar tabla después de eliminar
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
