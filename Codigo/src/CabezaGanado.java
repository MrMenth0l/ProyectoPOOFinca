import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CabezaGanado
{
    private String Nombre;
    private String Raza;
    private String Especie;
    private int Edad;
    private String Estado;
    private String Consumo;
    private File file;
    private File fileSeccion;
    private Seccion seccion;
    private String ID_Num;
    private boolean embarazada;
    private Date embarazo_inicio;
    private Date embarazo_alerta;
    private int embarazos;

    public void setNombre(String nombre) {
        Nombre = nombre;
        this.file = new File("Cabezas_de_Ganado.csv");
        this.fileSeccion = new File("Cabezas&Ganado.csv");
    }
    public String getNombre() {
        return Nombre;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }
    public String getRaza() {
        return Raza;
    }

    public void setEspecie(String especie) {Especie = especie;}

    public String getEspecie() {return Especie;}

    public void setEdad(int edad) {
        Edad = edad;
    }
    public int getEdad() {
        return Edad;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getEstado() {
        return Estado;
    }

    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    public File getFileSeccion() {return fileSeccion;}

    public void setConsumo(String consumo) {
        Consumo = consumo;
    }
    public String getConsumo() {
        return Consumo;
    }

    public String getDatos(){
        if (embarazada){
            return getNombre() +","+getRaza()+","+ getEspecie()+","+getEdad()+"," + getID_Num() + ",true,"+ getEmbarazo_inicio(" ") + ","+getEmbarazo_alerta(" ");
        }
        return getNombre() +","+getRaza()+","+ getEspecie()+","+getEdad()+"," + getID_Num() + ",false, no aplica, no aplica";
    }

    public void setSeccion(Seccion seccion) {this.seccion = seccion;}
    public Seccion getSeccion() {return seccion;}
    public String getSeccionNombre(){return seccion.getSeccion();}

    public void setID_Num(int ID_Num) {
        this.ID_Num = "#"+ getNombre().substring(0,1) +getEspecie().substring(0,2)+ getRaza().substring(0,2) + ID_Num;
    }

    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}

    public String getID_Num(){return ID_Num;}

    public void setEmbarazada(boolean embarazada) {this.embarazada = embarazada;}

    public boolean isEmbarazada() {return embarazada;}

    public void setEmbarazo_inicio(Date embarazo_inicio) {this.embarazo_inicio = embarazo_inicio;}

    public Date getEmbarazo_inicio() {return embarazo_inicio;}

    public void setEmbarazo_alerta(Date embarazo_alerta) {
        this.embarazo_alerta = embarazo_alerta;
    }

    public Date getEmbarazo_alerta() {
        return embarazo_alerta;
    }
    public String getEmbarazo_alerta(String pp){
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(embarazo_alerta);
        return dateOnly;
    }

    public String getEmbarazo_inicio(String pp){
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(embarazo_inicio);
        return dateOnly;
    }

    public void setEmbarazos(int embarazos) {this.embarazos = embarazos;}

    public int getEmbarazos() {return embarazos;}

}
