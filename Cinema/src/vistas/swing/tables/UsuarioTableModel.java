/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.swing.tables;

import cinema.model.Usuario;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Nombre", "Correo"};
    private List<Usuario> usuarios;

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
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
        Usuario usuario = usuarios.get(row);
        switch (column) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getNombre();
            case 2:
                return usuario.getCorreo();
            default:
                return null;
        }
    }

    public Usuario getUsuarioAt(int row) {
        return usuarios.get(row);
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
    }

    // En UsuarioTableModel.java
    public void actualizarDatos(List<Usuario> nuevosUsuarios) {
        this.usuarios = nuevosUsuarios;
        fireTableDataChanged(); // Esto notifica a la JTable que los datos cambiaron
    }
}
