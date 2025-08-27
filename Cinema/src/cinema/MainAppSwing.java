/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema;

import vistas.swing.MainWindow;

public class MainAppSwing {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Gestión de Cine...");
        
        // Iniciar interfaz Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}