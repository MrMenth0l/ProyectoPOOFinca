import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel Login;
    private JTextField UsuarioField1;
    private JPasswordField PassField1;
    private JLabel Pass;
    private JLabel Usuario;
    private JButton IngresarButton;
    private JTextField FincaField;
    private JButton regresarButton;
    private boolean codigo_correcto;

    public Login(TuFinca sistema, export_csv csv){
        Login = new JPanel();
        Login.setLayout(new BoxLayout(Login, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Iniciar sesion", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setForeground(new Color(-11179215));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(titleLabel);

        Login.add(Box.createVerticalStrut(20));


        JLabel fincaLabel = new JLabel("Nombre de finca");
        fincaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        fincaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(fincaLabel);


        FincaField = new JTextField(15);
        FincaField.setFont(new Font("Raleway", Font.PLAIN, 16));
        FincaField.setMaximumSize(FincaField.getPreferredSize());
        FincaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(FincaField);

        Login.add(Box.createVerticalStrut(10));

        Usuario = new JLabel("Usuario");
        Usuario.setFont(new Font("Raleway", Font.PLAIN, 16));
        Usuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(Usuario);

        UsuarioField1 = new JTextField(15);
        UsuarioField1.setFont(new Font("Raleway", Font.PLAIN, 16));
        UsuarioField1.setMaximumSize(UsuarioField1.getPreferredSize());
        UsuarioField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(UsuarioField1);

        Login.add(Box.createVerticalStrut(10));


        Pass = new JLabel("Contraseña");
        Pass.setFont(new Font("Raleway", Font.PLAIN, 16));
        Pass.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(Pass);

        PassField1 = new JPasswordField(15);
        PassField1.setFont(new Font("Raleway", Font.PLAIN, 16));
        PassField1.setMaximumSize(PassField1.getPreferredSize());
        PassField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(PassField1);

        Login.add(Box.createVerticalStrut(10));

        IngresarButton = new JButton("Ingresar");
        IngresarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        IngresarButton.setForeground(new Color(-11179215));
        IngresarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.add(IngresarButton);

        Login.add(Box.createVerticalStrut(10));

        regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Raleway", Font.BOLD, 14));
        regresarButton.setForeground(new Color(-11179215));
        regresarButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        Login.add(regresarButton);

        Login.add(Box.createVerticalStrut(10));

        setContentPane(Login);
        setTitle("Ingresar Cuenta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250,340);
        setLocationRelativeTo(null);
        setVisible(true);


        IngresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!sistema.getFincas().isEmpty()) {
                    for (int i = 0; i < sistema.getFincas().size(); i++) {
                        if (sistema.getFincas().get(i).getNombre_Finca().equals(FincaField.getText())) {
                            codigo_correcto = true;
                            for (int j = 0; j < sistema.getFincas().get(i).getUsuarios().size(); j++) {
                                if (UsuarioField1.getText().equals(sistema.getFincas().get(i).getUsuarios().get(j).getUser())) {
                                    if (PassField1.getText().equals(sistema.getFincas().get(i).getUsuarios().get(j).getPass())) {
                                        Finca finca = sistema.getFincas().get(i);
                                        Usuario usuario_activo = sistema.getFincas().get(i).getUsuarios().get(j);
                                        JOptionPane.showMessageDialog(Login.this, "Bienvenido " + UsuarioField1.getText());
                                        dispose();
                                        //new Overview(sistema, finca, csv);
                                        finca.setUsuario_activo(usuario_activo.getUser());
                                        return;
                                    } else {
                                        if (j == sistema.getFincas().get(i).getUsuarios().size() - 1) {
                                            JOptionPane.showMessageDialog(Login.this, "Contraseña incorrecta");
                                            break;
                                        }

                                    }
                                } else {
                                    if (j == sistema.getFincas().get(i).getUsuarios().size() - 1) {
                                        JOptionPane.showMessageDialog(Login.this, "Usuario incorrecto");
                                        break;
                                    }

                                }
                            }
                        }
                    }if (!codigo_correcto){JOptionPane.showMessageDialog(Login.this, "Codigo de finca incorrecto");}

                }else {JOptionPane.showMessageDialog(Login.this, "Finca no registrada, crear cuenta");
                    dispose();
                    new Main(sistema, csv);
                }


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
