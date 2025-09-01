/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.service;

import cinema.dao.UsuarioDAO;
import cinema.model.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean registrarUsuario(String nombre, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo debe ser válido");
        }

        Usuario usuario = new Usuario(nombre, correo);
        return usuarioDAO.insertarUsuario(usuario);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.obtenerTodosUsuarios();
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() <= 0) {
            throw new IllegalArgumentException("Usuario inválido para actualización");
        }

        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (usuario.getCorreo() == null || !usuario.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo debe ser válido");
        }

        return usuarioDAO.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido");
        }

        // Opcional: Verificar que el usuario existe antes de eliminar
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        return usuarioDAO.eliminarUsuario(id);
    }

}
