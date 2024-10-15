import java.io.File;

public class Contacto {
    private String Contacto_ID;
    private String Nombre;
    private String Tipo_de_contacto;
    private String Direccion;
    private String Telefono;
    private String Correo;
    private String ID_Num;
    private File file;

    public Contacto(){this.file = new File("Contactos.csv");}

    public void setContacto_ID(String contacto_ID) {Contacto_ID = contacto_ID;}
    public void setNombre(String nombre) {Nombre = nombre;}
    public void setTipo_de_contacto(String tipo_de_contacto) {Tipo_de_contacto = tipo_de_contacto;}
    public void setDireccion(String direccion) {Direccion = direccion;}
    public void setTelefono(String telefono) {Telefono = telefono;}
    public void setCorreo(String correo) {Correo = correo;}
    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}
    public void setID_Num(int ID_Num) {this.ID_Num = "#"+ getNombre().substring(0,2) + getTipo_de_contacto().substring(0,2) + ID_Num;}
    public void setFile(File file) {this.file = file;}

    public String getContacto_ID() {return Contacto_ID;}
    public String getNombre() {return Nombre;}
    public String getTipo_de_contacto() {return Tipo_de_contacto;}
    public String getDireccion() {return Direccion;}
    public String getTelefono() {return Telefono;}
    public String getCorreo() {return Correo;}
    public String getID_Num() {return ID_Num;}
    public File getFile() {return file;}

    public String getDatos(){return getNombre()+","+getTipo_de_contacto()+","+getDireccion()+","+getTelefono()+","+getCorreo()+","+getID_Num();}
}
