import java.io.File;

class Suministro
{
    private String Nombre;
    private String Tipo;
    private int Cantidad;
    private CabezaGanado Necesita;
    private Suministrador Suministrador;
    private int DiasDesdeCompra;
    private File file;
    private String ID_Num;

    public void setNombre(String nombre) {
        Nombre = nombre;
        this.file = new File("Suministros.csv");
    }

    public String getNombre() {
        return Nombre;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setNecesita(CabezaGanado necesita) {
        Necesita = necesita;
    }

    public CabezaGanado getNecesita() {return Necesita;}

    public Suministrador getSuministrador() {return Suministrador;}

    public void setSuministrador(Suministrador suministrador) {
        Suministrador = suministrador;
    }

    public void setDiasDesdeCompra(int diasDesdeCompra) {DiasDesdeCompra = diasDesdeCompra;}

    public int getDiasDesdeCompra() {return DiasDesdeCompra;}

    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    public String getDatos(){return getNombre()+","+getTipo()+","+getCantidad()+","+getDiasDesdeCompra()+ "," + getID_Num();}

    public void setID_Num(int ID_Num) {
        this.ID_Num = "#"+ getNombre().substring(0,2) + getTipo().substring(0,2) + ID_Num;
    }

    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}

    public String getID_Num(){return ID_Num;}
}