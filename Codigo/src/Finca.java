import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Finca {
    private File finca;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<CabezaGanado> cabezaGanados = new ArrayList<CabezaGanado>();
    private List<Seccion> secciones = new ArrayList<Seccion>();
    private List<Suministrador> suministradores = new ArrayList<Suministrador>();
    private List<Cosecha> cosechas = new ArrayList<Cosecha>();
    private List<Suministro> suministros = new ArrayList<Suministro>();
    private List<Trabajador> trabajadores = new ArrayList<Trabajador>();
    private List<Contacto> contactos = new ArrayList<Contacto>();
    private List<List<String>> tareas = new ArrayList<>();
    //private List<Finanzas> finanzas = new ArrayList<>();
    private String Nombre_Finca;
    private String Usuario_activo;



    public File getFinca() {return finca;}

    public List<Usuario> getUsuarios() {return usuarios;}
    public List<CabezaGanado> getCabezaGanados() {return cabezaGanados;}
    public List<Seccion> getSecciones() {return secciones;}
    public List<Cosecha> getCosechas() {return cosechas;}
    public List<Suministrador> getSuministradores() {return suministradores;}
    public List<Suministro> getSuministros() {return suministros;}
    public List<Trabajador> getTrabajadores() {return trabajadores;}
    public List<Contacto> getContactos() {return contactos;}
    public List<List<String>> getTareas() {return tareas;}
    //public List<Finanzas> getFinanzas(){return finanzas;}

    public void addUsuario(Usuario usuario){usuarios.add(usuario);}
    public void addCabezaGanado(CabezaGanado cabezaGanado){cabezaGanados.add(cabezaGanado);}
    public void addSeccion(Seccion seccion){secciones.add(seccion);}
    public void addSuministrador(Suministrador suministrador){suministradores.add(suministrador);}
    public void addCosecha(Cosecha cosecha){cosechas.add(cosecha);}
    public void addSuministros(Suministro suministro){suministros.add(suministro);}
    public void addTrabajador(Trabajador trabajador){trabajadores.add(trabajador);}
    public void addContacto(Contacto contacto){contactos.add(contacto);}
    public void addTarea(List<String> tarea){tareas.add(tarea);}

    public void removerCabezaGanado(CabezaGanado cabezaGanado){
                cabezaGanados.remove(cabezaGanado);
    }
    public void removerSeccion(Seccion seccion){
                secciones.remove(seccion);
    }
    public void removerSuministrador(Suministrador suministrador){
        suministradores.remove(suministrador);
    }
    public void removerCosecha(Cosecha cosecha){
        cosechas.remove(cosecha);
    }
    public void removerSuministro(Suministro suministro){
        suministros.remove(suministro);
    }
    public void removerTrabajador(Trabajador trabajador){
        trabajadores.remove(trabajador);
    }
    public void removerContacto(Contacto contacto){
        contactos.remove(contacto);
    }



    public void setNombre_Finca(String nombre_Finca) {Nombre_Finca = nombre_Finca;this.finca = new File("Fincas.csv");}
    public String getNombre_Finca() {return Nombre_Finca;}

    public String getFincaPath(){
        return "sistema/finca-"+getNombre_Finca();
    }

    public String getUsuario_activo(){return this.Usuario_activo;}
    public void setUsuario_activo(String usuario_activo) {this.Usuario_activo = usuario_activo;}

}
