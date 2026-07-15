/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import view.Register;
/**
 *
 * @author Romel
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 1. Crear la vista
                Register vista = new Register();
                
                // 2. Crear el controlador conectándolo con la vista
                controllers.registerController controlador = new controllers.registerController(vista);
                
                // 3. Mostrar la vista
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });
    }
    
}
