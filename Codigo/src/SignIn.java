import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SignIn extends JFrame {
    private JPanel SignIn;
    private JTextField FincaField;
    private JTextField UsuarioField;
    private JPasswordField PassField;
    private JPasswordField PassField2;
    private JButton IngresarButton;
    private JButton regresarButton;
    private String usuario;
    private String pass;

    public SignIn(TuFinca sistema, export_csv csv){
        SignIn = new JPanel();
        SignIn.setLayout(new BoxLayout(SignIn, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Crear cuenta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setForeground(new Color(-11179215));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(titleLabel);

        SignIn.add(Box.createVerticalStrut(20));

        JLabel fincaLabel = new JLabel("Codigo de Finca");
        fincaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        fincaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(fincaLabel);

        FincaField = new JTextField(15);
        FincaField.setFont(new Font("Raleway", Font.PLAIN, 16));
        FincaField.setMaximumSize(FincaField.getPreferredSize());
        FincaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(FincaField);

        SignIn.add(Box.createVerticalStrut(10));

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(userLabel);

        UsuarioField = new JTextField(15);
        UsuarioField.setFont(new Font("Raleway", Font.PLAIN, 16));
        UsuarioField.setMaximumSize(UsuarioField.getPreferredSize());
        UsuarioField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(UsuarioField);

        SignIn.add(Box.createVerticalStrut(10));

        JLabel emailLabel = new JLabel("Ingrese un correo electronico(Opcional)");
        emailLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(emailLabel);

        JTextField emailField = new JTextField(15);
        emailField.setFont(new Font("Raleway", Font.PLAIN, 16));
        emailField.setMaximumSize(emailField.getPreferredSize());
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(emailField);

        SignIn.add(Box.createVerticalStrut(10));

        JLabel passLabel = new JLabel("Ingrese su contraseña");
        passLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(passLabel);

        PassField = new JPasswordField(15);
        PassField.setFont(new Font("Raleway", Font.PLAIN, 16));
        PassField.setMaximumSize(PassField.getPreferredSize());
        PassField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(PassField);

        SignIn.add(Box.createVerticalStrut(10));

        JLabel confirmPassLabel = new JLabel("Ingrese su contraseña de nuevo");
        confirmPassLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        confirmPassLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(confirmPassLabel);

        PassField2 = new JPasswordField(15);
        PassField2.setFont(new Font("Raleway", Font.PLAIN, 16));
        PassField2.setMaximumSize(PassField2.getPreferredSize());
        PassField2.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(PassField2);

        SignIn.add(Box.createVerticalStrut(20));

        IngresarButton = new JButton("Ingresar");
        IngresarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        IngresarButton.setForeground(new Color(-11179215));
        IngresarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignIn.add(IngresarButton);

        SignIn.add(Box.createVerticalStrut(10));

        regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Raleway", Font.BOLD, 14));
        regresarButton.setForeground(new Color(-11179215));
        regresarButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        SignIn.add(regresarButton);

        SignIn.add(Box.createVerticalStrut(10));
        setContentPane(SignIn);
        setTitle("Crear cuenta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,450);
        setLocationRelativeTo(null);
        setVisible(true);
        IngresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Finca finca =new Finca();
                Usuario usuario_activo = new Usuario();
                for (int i = 0; i < sistema.getFincas().size() ; i++) {
                    if (FincaField.getText().equals(sistema.getFincas().get(i).getNombre_Finca())){
                        finca = sistema.getFincas().get(i);
                    }
                }
                if(PassField.getText().equals(PassField2.getText())) {
                    if (!FincaField.getText().isBlank()) {
                        if (!UsuarioField.getText().isBlank()) {
                            if (!PassField.getText().isBlank()) {
                                finca.setNombre_Finca(FincaField.getText());
                                usuario_activo.setUser(UsuarioField.getText());
                                usuario_activo.setPass(PassField.getText());
                                usuario_activo.getFile();
                                sistema.addFinca(finca);
                                try {
                                    csv.exportFinca(finca);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                JOptionPane.showMessageDialog(SignIn.this, "Bienvenido " + usuario_activo.getUser());
                                finca.addUsuario(usuario_activo);
                                try {
                                    csv.exportData(usuario_activo.getUser() + "," + usuario_activo.getPass(), usuario_activo.getFile(), finca);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                new Overview(sistema, finca, csv);
                                finca.setUsuario_activo(usuario_activo.getUser());
                                dispose();
                            }else {JOptionPane.showMessageDialog(SignIn.this,"Ingrese su contraseña");}
                        }else {JOptionPane.showMessageDialog(SignIn.this,"Ingrese su usuario");}
                    }else {JOptionPane.showMessageDialog(SignIn.this,"Ingrese su codigo de finca");}
                }else{JOptionPane.showMessageDialog(SignIn.this,"Ingrese bien su contraseña");}
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main(sistema, csv);
                dispose();
            }
        });
    }
}