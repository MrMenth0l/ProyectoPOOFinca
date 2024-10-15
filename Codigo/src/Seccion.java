import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Seccion
{
    private String seccion;
    private int tamaño;
    private String funcion;
    private String estado;
    private String medida;
    private List<CabezaGanado> cabezaGanadolist= new ArrayList<CabezaGanado>();
    private int cabezas;
    private File file;
    private String ID_Num;

    public void setSeccion(String seccion) {
        this.seccion = seccion;this.file = new File("Secciones.csv");
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSeccion() {
        return seccion;
    }

    public int getTamaño() {
        return tamaño;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getEstado() {
        return estado;
    }

    public List<CabezaGanado> getCabezaGanadolist() {return cabezaGanadolist;}
    public void addCabezaGanado(CabezaGanado cabezaGanado)
    {
        cabezaGanadolist.add(cabezaGanado);
    }

    public void setCabezas(int cabezas) {this.cabezas = cabezas;}

    public int getCabezasSize() {return cabezaGanadolist.size();}

    public int getCabezas() {return cabezas;}

    public void setCabezaGanadolist(List<CabezaGanado> cabezaGanadolist) {this.cabezaGanadolist = cabezaGanadolist;}

    public void setMedida(String medida) {this.medida = medida;}
    public String getMedida() {return medida;}

    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    public String getDatos(){return getSeccion()+","+getTamaño()+","+getMedida()+","+getFuncion()+","+getEstado()+","+ getCabezasSize()+ "," + getID_Num();}

    public void setID_Num(int ID_Num) {
        this.ID_Num = "#"+ getSeccion().substring(0,2) + getFuncion().substring(0,2) + ID_Num;
    }

    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}

    public String getID_Num(){return ID_Num;}
}