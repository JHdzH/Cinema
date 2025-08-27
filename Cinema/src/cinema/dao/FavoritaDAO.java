/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema.dao;

import cinema.config.DatabaseConfig;
import cinema.model.Favorita;
import cinema.model.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritaDAO {
    
    public boolean agregarFavorita(Favorita favorita) {
        String sql = "INSERT INTO favoritas (idPelicula, idUsuario) VALUES (?, ?)";
        
        try (
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql)) {
            
            pStmt.setInt(1, favorita.getIdPelicula());
            pStmt.setInt(2, favorita.getIdUsuario());
            
            return pStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error agregando favorita: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarFavorita(int idPelicula, int idUsuario) {
        String sql = "DELETE FROM favoritas WHERE idPelicula = ? AND idUsuario = ?";
        
        try (
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPelicula);
            stmt.setInt(2, idUsuario);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error eliminando favorita: " + e.getMessage());
            return false;
        }
    }
    
    public List<Pelicula> obtenerPeliculasFavoritas(int idUsuario) {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql =    "SELECT p.* FROM peliculas p " +
                        "INNER JOIN favoritas f ON p.idPelicula = f.idPelicula " +
                        "WHERE f.idUsuario = ?";
        
        try (
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDuracion(rs.getString("duracion"));
                
                peliculas.add(pelicula);
            }
            
        } catch (SQLException e) {
            System.err.println("Error obteniendo películas favoritas: " + e.getMessage());
        }
        
        return peliculas;
    }
    
    public boolean esFavorita(int idPelicula, int idUsuario) {
        String sql = "SELECT COUNT(*) FROM favoritas WHERE idPelicula = ? AND idUsuario = ?";
        
        try (
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPelicula);
            stmt.setInt(2, idUsuario);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error verificando favorita: " + e.getMessage());
        }
        
        return false;
    }
}
