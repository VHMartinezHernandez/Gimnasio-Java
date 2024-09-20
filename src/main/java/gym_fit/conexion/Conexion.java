package gym_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {  // Cambié el nombre de la clase a "Conexion" para seguir las convenciones de nomenclatura
    public static Connection getConexion() {
        Connection conexion = null;
        String baseDatos = "gym_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "admin";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error al conectarse a la BD: " + e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();  // Usar "Conexion" en lugar de "conexion"
        if (conexion != null) {
            System.out.println("Conexión exitosa: " + conexion);
        } else {
            System.out.println("Error al conectarse");
        }
    }
}
