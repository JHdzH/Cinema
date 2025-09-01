/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.panels;

import cinema.controller.FavoritaController;
import cinema.controller.UsuarioController;
import cinema.controller.PeliculaController;
import cinema.model.Pelicula;
import cinema.model.Usuario;
import vistas.swing.tables.PeliculaTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FavoritosPanel extends JPanel {
    private FavoritaController favoritaController;
    private UsuarioController usuarioController;
    private PeliculaController peliculaController;
    
    private JComboBox<String> cmbUsuarios; // Cambiado a String
    private JTable table;
    private PeliculaTableModel tableModel;
    private JButton btnAgregarFavorita;
    private JButton btnRemoverFavorita;
    
    // Lista para mantener referencia a los usuarios
    private List<Usuario> listaUsuarios;
    
    public FavoritosPanel() {
        this.favoritaController = new FavoritaController();
        this.usuarioController = new UsuarioController();
        this.peliculaController = new PeliculaController();
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Selección de usuario
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        //topPanel.setBorder(BorderFactory.createTitledBorder("Selección de Usuario"));
        
        JLabel lblUsuario = new JLabel("Usuario:");
        //lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        
        cmbUsuarios = new JComboBox<>();
        cmbUsuarios.setPreferredSize(new Dimension(300, 30));
        cargarUsuarios();
        cmbUsuarios.addActionListener(e -> cargarFavoritos());
        
        topPanel.add(lblUsuario);
        topPanel.add(cmbUsuarios);
        
        // Panel central - Tabla de favoritos
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Películas Favoritas"));
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        //table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior - Botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Acciones"));
        
        btnAgregarFavorita = new JButton("Agregar a Favoritos");
        btnRemoverFavorita = new JButton("Remover de Favoritos");
        
        // Mejorar apariencia de botones
        //estiloBoton(btnAgregarFavorita, new Color(46, 204, 113)); // Verde
        //estiloBoton(btnRemoverFavorita, new Color(231, 76, 60));  // Rojo
        
        btnAgregarFavorita.addActionListener(e -> agregarFavorita());
        btnRemoverFavorita.addActionListener(e -> removerFavorita());
        
        bottomPanel.add(btnAgregarFavorita);
        bottomPanel.add(btnRemoverFavorita);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /*
    private void estiloBoton(JButton boton, Color colorFondo) {
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
*/
    
    private void cargarUsuarios() {
        listaUsuarios = usuarioController.obtenerTodosUsuarios();
        cmbUsuarios.removeAllItems();
        
        // Agregar opción por defecto
        cmbUsuarios.addItem("Seleccione un usuario...");
        
        for (Usuario usuario : listaUsuarios) {
            // Usuario.toString() ahora mostrará "Nombre (correo)"
            cmbUsuarios.addItem(usuario.toString());
        }
    }
    
    private Usuario obtenerUsuarioSeleccionado() {
        int selectedIndex = cmbUsuarios.getSelectedIndex();
        if (selectedIndex <= 0 || selectedIndex > listaUsuarios.size()) {
            return null;
        }
        // Restar 1 porque el primer item es "Seleccione un usuario..."
        return listaUsuarios.get(selectedIndex - 1);
    }
    
    private void cargarFavoritos() {
        Usuario usuario = obtenerUsuarioSeleccionado();
        if (usuario != null) {
            List<Pelicula> favoritas = favoritaController.obtenerPeliculasFavoritas(usuario);
            tableModel = new PeliculaTableModel(favoritas);
            table.setModel(tableModel);
            
            // Habilitar/deshabilitar botones según selección
            btnRemoverFavorita.setEnabled(!favoritas.isEmpty());
        } else {
            // Limpiar tabla si no hay usuario seleccionado
            table.setModel(new PeliculaTableModel(List.of()));
            btnRemoverFavorita.setEnabled(false);
        }
    }
    
    private void agregarFavorita() {
        Usuario usuario = obtenerUsuarioSeleccionado();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un usuario primero", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Diálogo para seleccionar película
        List<Pelicula> peliculas = peliculaController.obtenerTodasPeliculas();
        
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay películas disponibles", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Crear array de strings para mostrar en el diálogo
        String[] opcionesPeliculas = new String[peliculas.size()];
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula p = peliculas.get(i);
            opcionesPeliculas[i] = p.getTitulo() + " (" + p.getGenero() + ")";
        }
        
        String selectedPeliculaStr = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione una película para agregar a favoritos:",
            "Agregar a Favoritos - " + usuario.getNombre(),
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesPeliculas,
            null
        );
        
        if (selectedPeliculaStr != null) {
            // Encontrar la película seleccionada
            Pelicula selectedPelicula = null;
            for (Pelicula p : peliculas) {
                String peliculaStr = p.getTitulo() + " (" + p.getGenero() + ")";
                if (peliculaStr.equals(selectedPeliculaStr)) {
                    selectedPelicula = p;
                    break;
                }
            }
            
            if (selectedPelicula != null) {
                // Verificar si ya es favorita
                if (favoritaController.esFavorita(selectedPelicula, usuario)) {
                    JOptionPane.showMessageDialog(this,
                        "Esta película ya está en los favoritos de " + usuario.getNombre(),
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                boolean success = favoritaController.agregarFavorita(selectedPelicula, usuario);
                if (success) {
                    JOptionPane.showMessageDialog(this,
                        "Película agregada a favoritos de " + usuario.getNombre(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                    cargarFavoritos();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Error al agregar a favoritos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void removerFavorita() {
        Usuario usuario = obtenerUsuarioSeleccionado();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un usuario primero", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione una película para remover", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pelicula pelicula = tableModel.getPeliculaAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Remover '" + pelicula.getTitulo() + "' de los favoritos de " + usuario.getNombre() + "?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = favoritaController.eliminarFavorita(pelicula, usuario);
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Película removida de favoritos",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                cargarFavoritos();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al remover de favoritos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}