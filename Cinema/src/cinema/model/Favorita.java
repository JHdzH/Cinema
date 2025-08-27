/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.model;

public class Favorita {
    private int idPelicula;
    private int idUsuario;
    
    // Constructores
    public Favorita() {}
    
    public Favorita(int idPelicula, int idUsuario) {
        this.idPelicula = idPelicula;
        this.idUsuario = idUsuario;
    }
    
    // Getters y Setters
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    @Override
    public String toString() {
        return "Favorita{idPelicula=" + idPelicula + ", idUsuario=" + idUsuario + "}";
    }
}
