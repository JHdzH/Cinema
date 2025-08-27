/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
*/
package cinema;

import cinema.config.DatabaseConfig;
import cinema.controller.FavoritaController;
import cinema.controller.PeliculaController;
import cinema.controller.UsuarioController;
import cinema.model.Pelicula;
import cinema.model.Usuario;
import java.sql.Connection;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA CINEMA MVC ===");
        
        // Probar conexión
        DatabaseConfig.testConnection();
        
        try (Connection conn = DatabaseConfig.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Conexión establecida, probando operaciones...");
                
                // Probar controllers
                probarUsuarioController();
                probarPeliculaController();
                probarFavoritaController();
                
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void probarUsuarioController() {
        System.out.println("\n--- Probando UsuarioController ---");
        UsuarioController usuarioController = new UsuarioController();
        
        // Registrar usuario
        boolean registrado = usuarioController.registrarUsuario("Ana García", "ana@email.com");
        System.out.println("Usuario registrado: " + registrado);
        
        // Obtener todos los usuarios
        List<Usuario> usuarios = usuarioController.obtenerTodosUsuarios();
        System.out.println("Usuarios en sistema: " + usuarios.size());
        for (Usuario usuario : usuarios) {
            System.out.println(" - ID: " + usuario.getId() + ", " + 
                             usuario.getNombre() + " (" + usuario.getCorreo() + ")");
        }
    }
    
    private static void probarPeliculaController() {
        System.out.println("\n--- Probando PeliculaController ---");
        PeliculaController peliculaController = new PeliculaController();
        
        // Agregar películas
        boolean agregada1 = peliculaController.agregarPelicula("Inception", "Ciencia Ficción", "148 min");
        boolean agregada2 = peliculaController.agregarPelicula("The Dark Knight", "Acción", "152 min");
        System.out.println("Películas agregadas: " + agregada1 + ", " + agregada2);
        
        // Obtener todas las películas
        List<Pelicula> peliculas = peliculaController.obtenerTodasPeliculas();
        System.out.println("Películas en sistema: " + peliculas.size());
        for (Pelicula pelicula : peliculas) {
            System.out.println(" - ID: " + pelicula.getIdPelicula() + ", " + 
                             pelicula.getTitulo() + " (" + pelicula.getGenero() + ")");
        }
    }
    
    private static void probarFavoritaController() {
        System.out.println("\n--- Probando FavoritaController ---");
        FavoritaController favoritaController = new FavoritaController();
        
        // Suponiendo que tenemos usuario ID 1 y película ID 1
        int usuarioId = 1;
        int peliculaId = 1;
        
        // Agregar a favoritos
        boolean agregada = favoritaController.agregarFavorita(peliculaId, usuarioId);
        System.out.println("Película agregada a favoritos: " + agregada);
        
        // Verificar si es favorita
        boolean esFavorita = favoritaController.esFavorita(peliculaId, usuarioId);
        System.out.println("¿Es favorita? " + esFavorita);
        
        // Obtener películas favoritas del usuario
        List<Pelicula> favoritas = favoritaController.obtenerFavoritasPorUsuario(usuarioId);
        System.out.println("Películas favoritas del usuario " + usuarioId + ": " + favoritas.size());
        for (Pelicula pelicula : favoritas) {
            System.out.println(" - " + pelicula.getTitulo());
        }
        
        // Eliminar de favoritos
        boolean eliminada = favoritaController.eliminarFavorita(peliculaId, usuarioId);
        System.out.println("Película eliminada de favoritos: " + eliminada);
    }
}
//=============================================================================
/*
public class MainApp {
    public static void main(String[] args) {
        // Test de conexión y operaciones
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        
        // Insertar usuario de prueba
        Usuario nuevoUsuario = new Usuario("Ana García", "ana@email.com");
        boolean insertado = usuarioDAO.insertarUsuario(nuevoUsuario);
        System.out.println("Usuario insertado: " + insertado);
        
        // Obtener todos los usuarios
        List<Usuario> usuarios = usuarioDAO.obtenerTodosUsuarios();
        System.out.println("\nUsuarios en la base de datos:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        
        // Obtener todas las películas
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasPeliculas();
        System.out.println("\nPelículas en la base de datos:");
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula);
        }
    }
}
*/
