/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.service;

import cinema.dao.FavoritaDAO;
import cinema.model.Favorita;
import cinema.model.Pelicula;
import java.util.List;

public class FavoritaService {
    private FavoritaDAO favoritaDAO;
    
    public FavoritaService() {
        this.favoritaDAO = new FavoritaDAO();
    }
    
    public boolean agregarPeliculaFavorita(int idPelicula, int idUsuario) {
        Favorita favorita = new Favorita(idPelicula, idUsuario);
        return favoritaDAO.agregarFavorita(favorita);
    }
    
    public boolean eliminarPeliculaFavorita(int idPelicula, int idUsuario) {
        return favoritaDAO.eliminarFavorita(idPelicula, idUsuario);
    }
    
    public List<Pelicula> obtenerPeliculasFavoritas(int idUsuario) {
        return favoritaDAO.obtenerPeliculasFavoritas(idUsuario);
    }
    
    public boolean esPeliculaFavorita(int idPelicula, int idUsuario) {
        return favoritaDAO.esFavorita(idPelicula, idUsuario);
    }
}