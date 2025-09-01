/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.service;

import cinema.dao.FavoritaDAO;
import cinema.model.Favorita;
import cinema.model.Pelicula;
import cinema.model.Usuario;
import java.util.List;

public class FavoritaService {
    private FavoritaDAO favoritaDAO;
    
    public FavoritaService() {
        this.favoritaDAO = new FavoritaDAO();
    }
    
    public boolean agregarFavorita(Favorita favorita) {
        if (favorita.getPelicula() == null || favorita.getUsuario() == null) {
            throw new IllegalArgumentException("Película y Usuario deben ser objetos válidos");
        }
        
        if (esFavorita(favorita)) {
            throw new IllegalArgumentException("La película ya está en favoritos");
        }
        
        return favoritaDAO.agregarFavorita(favorita);
    }
    
    public boolean agregarFavorita(Pelicula pelicula, Usuario usuario) {
        Favorita favorita = new Favorita(pelicula, usuario);
        return agregarFavorita(favorita);
    }
    
    public boolean eliminarFavorita(Favorita favorita) {
        if (favorita.getPelicula() == null || favorita.getUsuario() == null) {
            throw new IllegalArgumentException("Película y Usuario deben ser objetos válidos");
        }
        
        return favoritaDAO.eliminarFavorita(favorita);
    }
    
    public boolean eliminarFavorita(Pelicula pelicula, Usuario usuario) {
        Favorita favorita = new Favorita(pelicula, usuario);
        return eliminarFavorita(favorita);
    }
    
    public List<Favorita> obtenerFavoritasCompletasPorUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        return favoritaDAO.obtenerFavoritasPorUsuario(usuario.getId());
    }
    
    public List<Pelicula> obtenerPeliculasFavoritas(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        return favoritaDAO.obtenerPeliculasFavoritas(usuario.getId());
    }
    
    public boolean esFavorita(Favorita favorita) {
        return favoritaDAO.esFavorita(favorita);
    }
    
    public boolean esFavorita(Pelicula pelicula, Usuario usuario) {
        Favorita favorita = new Favorita(pelicula, usuario);
        return esFavorita(favorita);
    }
}