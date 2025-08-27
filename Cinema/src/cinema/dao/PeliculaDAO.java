/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.dao;

import cinema.config.DatabaseConfig;
import cinema.model.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    
    public boolean insertarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas (titulo, genero, duracion) VALUES (?, ?, ?)";
        
        try (
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pStmt.setString(1, pelicula.getTitulo());
            pStmt.setString(2, pelicula.getGenero());
            pStmt.setString(3, pelicula.getDuracion());
            
            int filasAfectadas = pStmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                try (ResultSet llavesGeneradas = pStmt.getGeneratedKeys()) {
                    if (llavesGeneradas.next()) {
                        pelicula.setIdPelicula(llavesGeneradas.getInt(1));
                    }
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error en insertarPelicula: " + e.getMessage());
            return false;
        }
    }
    
    public List<Pelicula> obtenerTodasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDuracion(rs.getString("duracion"));
                
                peliculas.add(pelicula);
            }
            
        } catch (SQLException e) {
            System.err.println("Error en obtenerTodasPeliculas: " + e.getMessage());
        }
        
        return peliculas;
    }
    
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        String sql = "SELECT * FROM peliculas WHERE idPelicula = ?";
        Pelicula pelicula = null;
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDuracion(rs.getString("duracion"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error en obtenerPeliculaPorId: " + e.getMessage());
        }
        
        return pelicula;
    }
    
    public boolean actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE peliculas SET titulo = ?, genero = ?, duracion = ? WHERE idPelicula = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getGenero());
            stmt.setString(3, pelicula.getDuracion());
            stmt.setInt(4, pelicula.getIdPelicula());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error actualizando película: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarPelicula(int idPelicula) {
        String sql = "DELETE FROM peliculas WHERE idPelicula = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPelicula);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error eliminando película: " + e.getMessage());
            return false;
        }
    }
}