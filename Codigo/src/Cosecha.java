import java.io.File;

public class Cosecha
{
    private String tipo;
    private int tamaño;
    private String estado;
    private String epoca;
    private String medida;
    private String geografia;
    private File file;
    private String ID_Num;

    public Cosecha()
    {
        this.file = new File("Cosechas.csv");
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public void setGeografia(String geografia) {
        this.geografia = geografia;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public String getEstado() {
        return estado;
    }

    public String getEpoca() {
        return epoca;
    }

    public String getGeografia() {return geografia;}

    public void setMedida(String medida) {this.medida = medida;}
    public String getMedida() {return medida;}

    public File getFile() {return file;}
    public void setFile(File file) {this.file = file;}

    public String getDatos(){return getTipo()+","+getTamaño()+","+getMedida()+","+getEstado()+","+getEpoca()+","+getGeografia()+ "," + getID_Num();}

    public void setID_Num(int ID_Num) {
        this.ID_Num = "#"+ getTipo().substring(0,2) + getGeografia().substring(0,1) + ID_Num;
    }

    public void setID_Num(String ID_Num) {this.ID_Num = ID_Num;}

    public String getID_Num(){return ID_Num;}
}