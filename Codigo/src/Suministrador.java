import java.io.File;

public class Suministrador
{
    private String nombre;
    private String producto;
    private int precio;
    private int dias_espera;
    private File file;
    private String ID_Num;

    public Suministrador() {
        this.file = new File( "Suministradores.csv");
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setProducto(String producto) {this.producto = producto;}

    public void setPrecio(int precio) {this.precio = precio;}

    public void setDias_espera(int dias_espera) {this.dias_espera = dias_espera;}

    public String getNombre() {return nombre;}

    public String getProducto() {return producto;}

    public int getPrecio() {return precio;}

    public int getDias_espera() {return dias_espera;}

    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    public String getDatos(){return getNombre()+"," +getProducto()+","+getPrecio()+","+getDias_espera()+ "," + getID_Num();}
    public void setID_Num(int ID_Num) {
        this.ID_Num = "#"+ getNombre().substring(0,2) + getProducto().substring(0,2) + ID_Num;
    }

    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}

    public String getID_Num(){return ID_Num;}
}