package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    // Método para establecer la conexión a la base de datos
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/pp_clase6";// URL de la BD
        String user="root"; // Usuario de la BD
        String pass=""; // Contraseña de la BD
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
            con=DriverManager.getConnection(url,user,pass); // Establecer conexión
        }catch(ClassNotFoundException | SQLException e){
            // Manejo de errores, aquí se puede imprimir un mensaje si ocurre una excepción
        }
        return con;    // Retorna la conexión
    }  
}
