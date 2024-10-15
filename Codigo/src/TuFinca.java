import java.util.ArrayList;
import java.util.List;

public class TuFinca
{
    private List<Finca> fincas = new ArrayList<Finca>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public List<Finca> getFincas() {return fincas;}

    public void addFinca(Finca finca) {
        fincas.add(finca);
    }

    public List<Usuario> getUsuarios() {return usuarios;}

    public void addUsuarios(Usuario usuario) {usuarios.add(usuario);}
}