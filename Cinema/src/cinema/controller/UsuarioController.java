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
    
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioService.obtenerTodosUsuarios();
    }
    
    public Usuario obtenerUsuario(int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }
}