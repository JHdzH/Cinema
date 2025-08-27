/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.controller;

import cinema.model.Pelicula;
import cinema.service.FavoritaService;
import java.util.List;

public class FavoritaController {
    private FavoritaService favoritaService;
    
    public FavoritaController() {
        this.favoritaService = new FavoritaService();
    }
    
    public boolean agregarFavorita(int idPelicula, int idUsuario) {
        // Validaciones
        if (idPelicula <= 0 || idUsuario <= 0) {
            System.out.println("Error: IDs deben ser mayores a 0");
            return false;
        }
        
        // Verificar si ya es favorita
        if (favoritaService.esPeliculaFavorita(idPelicula, idUsuario)) {
            System.out.println("La película ya está en favoritos");
            return false;
        }
        
        return favoritaService.agregarPeliculaFavorita(idPelicula, idUsuario);
    }
    
    public boolean eliminarFavorita(int idPelicula, int idUsuario) {
        if (idPelicula <= 0 || idUsuario <= 0) {
            System.out.println("Error: IDs deben ser mayores a 0");
            return false;
        }
        
        return favoritaService.eliminarPeliculaFavorita(idPelicula, idUsuario);
    }
    
    public List<Pelicula> obtenerFavoritasPorUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            System.out.println("Error: ID de usuario debe ser mayor a 0");
            return List.of(); // Lista vacía
        }
        
        return favoritaService.obtenerPeliculasFavoritas(idUsuario);
    }
    
    public boolean esFavorita(int idPelicula, int idUsuario) {
        return favoritaService.esPeliculaFavorita(idPelicula, idUsuario);
    }
}