/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.model;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String genero;
    private String duracion;
    
    // Constructores
    public Pelicula() {}
    
    public Pelicula(String titulo, String genero, String duracion) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
    }
    
    // Getters y Setters
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    
    @Override
    public String toString() {
        return "Pelicula{id=" + idPelicula + ", titulo='" + titulo + "', genero='" + genero + "', duracion='" + duracion + "'}";
    }
}