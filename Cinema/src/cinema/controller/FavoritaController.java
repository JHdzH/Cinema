/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.controller;

import cinema.model.Favorita;
import cinema.model.Pelicula;
import cinema.model.Usuario;
import cinema.service.FavoritaService;
import java.util.List;

public class FavoritaController {
    private FavoritaService favoritaService;
    
    public FavoritaController() {
        this.favoritaService = new FavoritaService();
    }
    
    public boolean agregarFavorita(Favorita favorita) {
        try {
            return favoritaService.agregarFavorita(favorita);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }
    
    public boolean agregarFavorita(Pelicula pelicula, Usuario usuario) {
        try {
            return favoritaService.agregarFavorita(pelicula, usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarFavorita(Favorita favorita) {
        try {
            return favoritaService.eliminarFavorita(favorita);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarFavorita(Pelicula pelicula, Usuario usuario) {
        try {
            return favoritaService.eliminarFavorita(pelicula, usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }
    
    public List<Favorita> obtenerFavoritasCompletasPorUsuario(Usuario usuario) {
        try {
            return favoritaService.obtenerFavoritasCompletasPorUsuario(usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return List.of();
        }
    }
    
    public List<Pelicula> obtenerPeliculasFavoritas(Usuario usuario) {
        try {
            return favoritaService.obtenerPeliculasFavoritas(usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return List.of();
        }
    }
    
    public boolean esFavorita(Pelicula pelicula, Usuario usuario) {
        try {
            return favoritaService.esFavorita(pelicula, usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }
}   