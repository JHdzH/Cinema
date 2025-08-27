/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.service;

import cinema.dao.PeliculaDAO;
import cinema.model.Pelicula;
import java.util.List;

public class PeliculaService {
    private PeliculaDAO peliculaDAO;
    
    public PeliculaService() {
        this.peliculaDAO = new PeliculaDAO();
    }
    
    public boolean agregarPelicula(String titulo, String genero, String duracion) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        
        Pelicula pelicula = new Pelicula(titulo, genero, duracion);
        return peliculaDAO.insertarPelicula(pelicula);
    }
    
    public List<Pelicula> obtenerTodasPeliculas() {
        return peliculaDAO.obtenerTodasPeliculas();
    }
    
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        return peliculaDAO.obtenerPeliculaPorId(idPelicula);
    }
    
    public boolean actualizarPelicula(int idPelicula, String titulo, String genero, String duracion) {
        Pelicula pelicula = new Pelicula(titulo, genero, duracion);
        pelicula.setIdPelicula(idPelicula);
        return peliculaDAO.actualizarPelicula(pelicula);
    }
    
    public boolean eliminarPelicula(int idPelicula) {
        return peliculaDAO.eliminarPelicula(idPelicula);
    }
}
