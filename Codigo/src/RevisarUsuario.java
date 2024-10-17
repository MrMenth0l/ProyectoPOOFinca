import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RevisarUsuario extends JFrame {
   private JPanel RevisarUsuario;
   private String Usuario;

   public RevisarUsuario(TuFinca sistema, Finca finca, export_csv csv){
       setTitle("Agregar Cosecha");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       RevisarUsuario = new JPanel();
       RevisarUsuario.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(5, 5, 5, 5);

       JLabel Titulo = new JLabel("Usuario activo:");
       Titulo.setFont(new Font("Raleway", Font.BOLD, 18));
       gbc.gridx = 1;
       gbc.gridy = 0;
       gbc.gridwidth = 4;
       RevisarUsuario.add(Titulo, gbc);

       JLabel FincaLabel = new JLabel("Finca: " + finca.getNombre_Finca());
       FincaLabel.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 1;

       JLabel UsuarioLabel = new JLabel("Usuario: ");
       UsuarioLabel.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 2;
       RevisarUsuario.add(UsuarioLabel,gbc);

       JTextField UsuarioField = new JTextField(finca.getUsuario_activo());
       UsuarioField.setEnabled(false);
       UsuarioField.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy=3;
       RevisarUsuario.add(UsuarioField,gbc);

       JLabel PassLabel = new JLabel("Contraseña: ");
       PassLabel.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 4;
       RevisarUsuario.add(PassLabel,gbc);

       String pass = "";
       for (int i = 0; i < finca.getUsuarios().size(); i++) {
           if (finca.getUsuario_activo().equals(finca.getUsuarios().get(i).getUser())){
               pass = finca.getUsuarios().get(i).getPass();
           }
       }

       JTextField PassField = new JTextField(pass);
       PassField.setEnabled(false);
       PassField.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy=5;
       RevisarUsuario.add(PassField,gbc);

       Font font = new Font("Raleway", Font.PLAIN, 12);

       JLabel estad_ganado = new JLabel("Cabezas de ganado: " + finca.getCabezaGanados().size());
       estad_ganado.setFont(font);
       gbc.gridy = 6;
       gbc.gridx = 0;
       RevisarUsuario.add(estad_ganado, gbc);

       JLabel estad_secciones = new JLabel("Secciones:  " + finca.getSecciones().size());
       estad_secciones.setFont(font);
       gbc.gridy = 6;
       gbc.gridx = 4;
       RevisarUsuario.add(estad_secciones, gbc);

       JLabel estad_suministradores = new JLabel("Suministradores: " + finca.getSuministradores().size());
       estad_suministradores.setFont(font);
       gbc.gridy = 7;
       gbc.gridx = 4;
       RevisarUsuario.add(estad_suministradores, gbc);

       JLabel estad_suministros = new JLabel("Suministros: " + finca.getSuministros().size());
       estad_suministros.setFont(font);
       gbc.gridy = 7;
       gbc.gridx = 0;
       RevisarUsuario.add(estad_suministros, gbc);

       JLabel estad_trabajadores = new JLabel("Trabajadores: " + finca.getTrabajadores().size());
       estad_trabajadores.setFont(font);
       gbc.gridy = 8;
       gbc.gridx = 4;
       RevisarUsuario.add(estad_trabajadores, gbc);

       JLabel estad_cosechas = new JLabel("Cosechas: " + finca.getCosechas().size());
       estad_cosechas.setFont(font);
       gbc.gridx = 8;
       gbc.gridx =0;
       RevisarUsuario.add(estad_cosechas, gbc);

       JLabel estad_contactos = new JLabel("Contactos: " + finca.getContactos().size());
       estad_contactos.setFont(font);
       gbc.gridy = 9;
       gbc.gridx = 4;
       RevisarUsuario.add(estad_contactos, gbc);

       JButton regresar = new JButton("Regresar");
       regresar.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 10;
       gbc.gridwidth = 1;
       RevisarUsuario.add(regresar, gbc);

       setUndecorated(true);
       setContentPane(RevisarUsuario);
       setTitle("TuFinca");
       setSize(400,390);
       setLocationRelativeTo(null);
       setVisible(true);

       regresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });
   }
}