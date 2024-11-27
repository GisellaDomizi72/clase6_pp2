package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Vista.vista;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
// Clase para manejar los eventos de la vista y coordinar con el modelo
public class Controlador implements ActionListener{
    PersonaDAO dao = new PersonaDAO();// Acceso a la BD
    Persona p = new Persona(); // Objeto para datos de persona
    vista vista = new vista(); // Referencia a la vista
    DefaultTableModel modelo = new DefaultTableModel(); // Modelo de la tabla

    public Controlador(vista v) {
        this.vista = v; // Conecta la vista
        // Asigna los listeners a los botones
        this.vista.btnlistar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btneditar.addActionListener(this);
        this.vista.btnok.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        listar(vista.tabla); // Carga los datos iniciales
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        // Lógica para cada botón
        if(e.getSource() == vista.btnlistar){
            limpiarTabla();
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnGuardar){
            agregar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if (e.getSource()==vista.btneditar){
            int fila = vista.tabla.getSelectedRow(); // Obtiene fila seleccionada
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila.");
            }else{
                // Carga datos seleccionados a los campos de texto
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nombre=(String)vista.tabla.getValueAt(fila,1);
                String apellido=(String)vista.tabla.getValueAt(fila,2);  
                String telefono=(String)vista.tabla.getValueAt(fila,3);
                vista.txtid.setText(""+id);
                vista.txtnombre.setText(nombre);
                vista.txtapellido.setText(apellido);
                vista.txttelefono.setText(telefono);
                
            }
        }
        if(e.getSource()==vista.btnok){
            Actualizar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if(e.getSource()== vista.btneliminar){
            delete();
            limpiarTabla();
            listar(vista.tabla);
        }
    }
    // Método para eliminar una persona
    public void delete(){
        int fila = vista.tabla.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Registro.");
        }else{
            int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
            dao.delete(id); // Llama al DAO para eliminar
            JOptionPane.showMessageDialog(vista, "Registro Eliminado.");
        }
    }
    // Limpia la tabla de datos
    void limpiarTabla(){
        for(int i =0;i< vista.tabla.getRowCount();i++){
            modelo.removeRow(i);
            i=i-1;// Ajusta índice
        } 
    }
    // Actualiza datos de una persona
    public void Actualizar(){
        int id= Integer.parseInt(vista.txtid.getText());
        String nombre = vista.txtnombre.getText();
        String apellido = vista.txtapellido.getText();
        String telefono= vista.txttelefono.getText();
        p.setId(id);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setTelefono(telefono);
        int r = dao.Actualizar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista,"Usuario Actualizado con Exito!");
        }else{
            JOptionPane.showMessageDialog(vista,"Error");

        }
    }    
    // Agrega una nueva persona
    public void agregar(){
        String nombre = vista.txtnombre.getText();
        String apellido = vista.txtapellido.getText();
        String telefono= vista.txttelefono.getText();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setTelefono(telefono);
        int r =dao.agregar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista,"Usuario Agregado con Exito!");
        }else{
            JOptionPane.showMessageDialog(vista, "Error.");
        }       
    }
    // Lista las personas en la tabla
    public void listar(JTable tabla){
        modelo=(DefaultTableModel)tabla.getModel();
        List<Persona>lista=dao.listar(); // Obtiene lista de personas
        Object[]object = new Object[4];
        for (int i = 0;i < lista.size();i++){
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getApellido();
            object[3]=lista.get(i).getTelefono();
            modelo.addRow(object); // Agrega fila al modelo
        }   
        vista.tabla.setModel(modelo);  // Asigna modelo actualizado
            
    }
}
