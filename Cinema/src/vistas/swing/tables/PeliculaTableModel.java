/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.tables;

import cinema.model.Pelicula;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PeliculaTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Título", "Género", "Duración"};
    private List<Pelicula> peliculas;
    
    public PeliculaTableModel(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    @Override
    public int getRowCount() {
        return peliculas.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Pelicula pelicula = peliculas.get(row);
        switch (column) {
            case 0: return pelicula.getIdPelicula();
            case 1: return pelicula.getTitulo();
            case 2: return pelicula.getGenero();
            case 3: return pelicula.getDuracion();
            default: return null;
        }
    }
    
    public Pelicula getPeliculaAt(int row) {
        return peliculas.get(row);
    }
    
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
        fireTableDataChanged();
    }
    
    
   public void actualizarDatos(List<Pelicula> nuevasPeliculas) {
    System.out.println("DEBUG: Actualizando TableModel con " + nuevasPeliculas.size() + " películas");
    this.peliculas = nuevasPeliculas;
    fireTableDataChanged(); 
    System.out.println("DEBUG: TableModel notificó cambios a la JTable");
}   
}
