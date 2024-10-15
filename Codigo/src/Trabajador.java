import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trabajador {
    private String Nombre;
    private String Rol;
    private double Sueldo;
    private String ID_Num;
    private String Telefono;
    private List<List<String>> Tareas = new ArrayList<>();
    private File file;

    public Trabajador(){this.file = new File("Trabajadores.csv");}

    public void setNombre(String nombre) {Nombre = nombre;}
    public void setRol(String rol) {Rol = rol;}
    public void setSueldo(double sueldo) {Sueldo = sueldo;}
    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}
    public void setID_Num(int ID_Num) {this.ID_Num = "#"+ getNombre().substring(0,2) + getRol().substring(0,2) + ID_Num;}

    public void setTareas() {}
    public void addTarea(List<String> tarea){Tareas.add(tarea);}
    public void setTelefono(String telefono) {Telefono = telefono;}


    public String getNombre() {return Nombre;}
    public String getRol() {return Rol;}
    public String getID_Num() {return ID_Num;}
    public double getSueldo() {return Sueldo;}
    public List<List<String>> getTareas() {return Tareas;}
    public File getFile() {return file;}
    public String getTelefono() {return Telefono;}

    public String getDatos(){return getNombre()+","+getRol()+","+getSueldo()+","+getTelefono()+","+getID_Num();}
}