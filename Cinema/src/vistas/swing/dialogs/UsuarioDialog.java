/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.dialogs;

import cinema.controller.UsuarioController;
import cinema.model.Usuario;
import javax.swing.*;
import java.awt.*;

public class UsuarioDialog extends JDialog {
    private UsuarioController controller;
    private Usuario usuario;
    
    private JTextField txtNombre;
    private JTextField txtCorreo;
    
    public UsuarioDialog(Frame parent, UsuarioController controller) {
        this(parent, controller, null);
    }
    
    public UsuarioDialog(Frame parent, UsuarioController controller, Usuario usuario) {
        super(parent, true);
        this.controller = controller;
        this.usuario = usuario;
        initialize();
    }
    
    private void initialize() {
        setTitle(usuario == null ? "Agregar Usuario" : "Editar Usuario");
        setSize(400, 200);
        setLocationRelativeTo(getParent());
        setLayout(new GridLayout(3, 2, 10, 10));
        
        // Campos del formulario
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);
        
        add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        add(txtCorreo);
        
        // Botones
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> guardarUsuario());
        btnCancelar.addActionListener(e -> dispose());
        
        add(btnGuardar);
        add(btnCancelar);
        
        // Si está editando, cargar datos
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtCorreo.setText(usuario.getCorreo());
        }
    }
    
    private void guardarUsuario() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        
        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (usuario == null) {
                // Nuevo usuario
                boolean success = controller.registrarUsuario(nombre, correo);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente");
                    dispose();
                }
            } else {
                // Editar usuario existente
                usuario.setNombre(nombre);
                usuario.setCorreo(correo);
                // Implementar actualización en controller
                JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}