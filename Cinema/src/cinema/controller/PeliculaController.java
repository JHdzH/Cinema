/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.controller;

import cinema.model.Pelicula;
import cinema.service.PeliculaService;
import java.util.List;

public class PeliculaController {

    private PeliculaService peliculaService;

    public PeliculaController() {
        this.peliculaService = new PeliculaService();
    }

    public boolean agregarPelicula(String titulo, String genero, String duracion) {
        try {
            return peliculaService.agregarPelicula(titulo, genero, duracion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<Pelicula> obtenerTodasPeliculas() {
        return peliculaService.obtenerTodasPeliculas();
    }

    public Pelicula obtenerPelicula(int idPelicula) {
        return peliculaService.obtenerPeliculaPorId(idPelicula);
    }

    public boolean actualizarPelicula(int idPelicula, String titulo, String genero, String duracion) {
        try {
            return peliculaService.actualizarPelicula(idPelicula, titulo, genero, duracion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPelicula(int idPelicula) {
        return peliculaService.eliminarPelicula(idPelicula);
    }

    public boolean actualizarPelicula(Pelicula pelicula) {
        try {
            System.out.println("Controller: Actualizando película ID: " + pelicula.getIdPelicula());
            return peliculaService.actualizarPelicula(pelicula);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado actualizando película: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
