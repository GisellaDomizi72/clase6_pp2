package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// Clase para realizar operaciones CRUD en la tabla "personas"
public class PersonaDAO {
    Conexion conectar = new Conexion();  // Objeto para obtener la conexión
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    // Método para listar todas las personas
    public List listar(){
        List<Persona>datos = new ArrayList<>();
        String sql = "select * from personas"; // Consulta para obtener todos los registros
        try{
            con = conectar.getConnection();// Establece conexión
            ps=con.prepareStatement(sql);// Prepara la consulta
            rs=ps.executeQuery();// Ejecuta la consulta
            while(rs.next()){  // Recorre los resultados
                Persona p = new Persona();
                p.setId(rs.getInt(1));// Obtiene el ID
                p.setNombre(rs.getString(2)); // Obtiene el nombre
                p.setApellido(rs.getString(3)); // Obtiene el apellido
                p.setTelefono(rs.getString(4));  // Obtiene el teléfono
                datos.add(p);  // Agrega la persona a la lista
            }
        }catch(SQLException e){
            // Manejo de errores
        }
        return datos; // Retorna la lista de personas  
    }
    // Método para agregar una persona
    public int agregar(Persona p){
        String sql = "insert into personas (nombre_p, apellido, telefono) values (?,?,?)";
        try{
            con=conectar.getConnection();// Conexión
            ps=con.prepareStatement(sql);// Prepara consulta
            ps.setString(1,p.getNombre());// Asigna nombre
            ps.setString(2,p.getApellido());// Asigna apellido
            ps.setString(3,p.getTelefono());// Asigna teléfono
            ps.executeUpdate();// Ejecuta la consulta
        }catch(SQLException e){
            // Manejo de errores
        }
        return 1; // Retorna 1 si tuvo éxito
    }
    // Método para actualizar una persona
    public int Actualizar(Persona p){
        int r=0; // Variable para verificar éxito
        String sql="Update personas set nombre_p=?, apellido=?, telefono=? where id_persona=?";
        try{
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getTelefono());
            ps.setInt(4,p.getId());
            r= ps.executeUpdate();// Ejecuta y asigna resultado
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        }catch(SQLException e){
            // Manejo de errores
        }
        return r; // Retorna resultado
    }
    // Método para eliminar una persona por ID
    public void delete(int id){
        String sql = "delete from personas where id_persona="+id;
        try{
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();// Ejecuta la consulta
        }catch(SQLException e){
            // Manejo de errores
        }
    }
    
}
