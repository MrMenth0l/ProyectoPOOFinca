import java.io.File;

public class Usuario {
    private String User;
    private String pass;
    private Finca finca;

    public void setUser(String user) {User = user;}
    public String getUser() {return User;}

    public void setPass(String pass) {this.pass = pass;}
    public String getPass() {return pass;}

    public File getFile() {
        File file = new File("Usuarios.csv");
        return file;}
}