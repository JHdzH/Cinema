/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.controller;

import cinema.model.Usuario;
import cinema.service.UsuarioService;
import java.util.List;

public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public boolean registrarUsuario(String nombre, String correo) {
        try {
            return usuarioService.registrarUsuario(nombre, correo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO NUEVO: Actualizar usuario
    public boolean actualizarUsuario(Usuario usuario) {
        try {
            return usuarioService.actualizarUsuario(usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int id) {
        System.out.println("Controller: Eliminando usuario ID: " + id);
        try {
            boolean result = usuarioService.eliminarUsuario(id);
            System.out.println("Controller: Resultado eliminación: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("Controller: Error: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioService.obtenerTodosUsuarios();
    }

    public Usuario obtenerUsuario(int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }
}
