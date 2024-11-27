package Modelo;
// Clase que representa la estructura de la tabla "personas" en la BD
public class Persona {
    // Atributos que coinciden con las columnas de la tabla
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    // Constructor vacío
    public Persona() {

    }
    // Constructor con todos los atributos
    public Persona(int id, String nombre, String apellido, String telefono) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    // Métodos *setter* para asignar valores a los atributos
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    // Métodos *getter* para obtener los valores de los atributos
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTelefono() {
        return telefono;
    }
}
