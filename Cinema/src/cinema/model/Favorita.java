/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.model;

public class Favorita {
    private int idPelicula;
    private int idUsuario;
    private Pelicula pelicula;  // Objeto completo
    private Usuario usuario;    // Objeto completo
    
    // Constructores
    public Favorita() {}
    
    public Favorita(int idPelicula, int idUsuario) {
        this.idPelicula = idPelicula;
        this.idUsuario = idUsuario;
    }
    
    public Favorita(Pelicula pelicula, Usuario usuario) {
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.idPelicula = pelicula.getIdPelicula();
        this.idUsuario = usuario.getId();
    }
    
    // Getters y Setters
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { 
        this.pelicula = pelicula;
        this.idPelicula = pelicula.getIdPelicula();
    }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario;
        this.idUsuario = usuario.getId();
    }
    
    @Override
    public String toString() {
        return "Favorita{idPelicula=" + idPelicula + ", idUsuario=" + idUsuario + "}";
    }
}