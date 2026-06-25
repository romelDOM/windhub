/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Romel
 */
public class DBConnection {
    
    // Diccionario para almacenar las variables leídas del .env
    private static final Map<String, String> env = new HashMap<>();

    // Bloque estático: Se ejecuta automáticamente al invocar la clase por primera vez
    static {
        cargarVariablesEntorno();
    }

    private static void cargarVariablesEntorno() {
        try (BufferedReader br = new BufferedReader(new FileReader(".env"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                // Ignorar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                // Separar la clave del valor usando el primer '=' encontrado
                String[] partes = linea.split("=", 2);
                if (partes.length == 2) {
                    env.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo .env. Asegúrate de crearlo en la raíz.");
        }
    }
    
    // Extracción segura desde el entorno aislado
    static final String HOST = env.get("DB_HOST");
    static final String PORT = env.get("DB_PORT");
    static final String DBNAME = env.get("DB_NAME");
    static final String USER = env.get("DB_USER");
    static final String PASS = env.get("DB_PASS");
    
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME 
               + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            logger.log(Level.INFO, "✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "❌ Error de SQL al conectar", e);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "❌ Driver no encontrado", ex);
        }
        return con;
    }
    
    public static void main(String[] args) {
    }
}
