/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DBConnection;
/**
 *
 * @author Romel
 */
public class gestor {
    public static void guardarUsuario(int cedula, String nombre, String apellido, String password_hash, String token, int cargo, int departamento, int admin) {
        
        String sql = "INSERT INTO empleados (id_empleado, nombre, apellido, password_hash, token, cargo, departamento, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Reemplazamos los (?) con los datos reales
            // El primer número indica la posición del signo de interrogación en el SQL
            pstmt.setInt(1, cedula);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4, password_hash);
            pstmt.setString(5, token);
            pstmt.setInt(6, cargo);
            pstmt.setInt(7, departamento);
            pstmt.setInt(8, admin);

            // 4. Ejecutamos la inserción en la base de datos
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro guardado exitosamente en la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar el registro: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    }
}
