import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupFinca extends JFrame {
    private JButton CosechaAgregar;
    private JButton GanadoAgregar;
    private JButton SeccionAgregar;
    private JButton SuministradorAgregar;
    private JButton SuministroAgregar;
    private JButton RevisarFinca;
    private JButton Regresar;
    private JButton asignarGanadoASeccionButton;
    private JButton TrabajadorAgregar;
    private JButton ContactoAgregar;

    public SetupFinca(TuFinca sistema, Finca finca, export_csv csv) {
        setTitle("Configurador de su Finca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel Setup = new JPanel();
        Setup.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;

        GanadoAgregar = new JButton("Agregar cabeza de ganado");
        GanadoAgregar.setIcon(new ImageIcon("src/graficos/vaca_icon.png"));
        GanadoAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        Setup.add(GanadoAgregar, gbc);

        SuministradorAgregar = new JButton("Agregar Suministrador");
        SuministradorAgregar.setIcon(new ImageIcon("src/graficos/suministrador.png"));
        SuministradorAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 3;
        gbc.gridx = 1;
        Setup.add(SuministradorAgregar, gbc);

        CosechaAgregar = new JButton("Agregar Cosecha");
        CosechaAgregar.setIcon(new ImageIcon("src/graficos/cosecha.png"));
        CosechaAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 2;
        gbc.gridx = 2;
        Setup.add(CosechaAgregar, gbc);

        SuministroAgregar = new JButton("Agregar Suministro");
        SuministroAgregar.setIcon(new ImageIcon("src/graficos/suministro.png"));
        SuministroAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 3;
        gbc.gridx = 2;
        Setup.add(SuministroAgregar, gbc);

        SeccionAgregar = new JButton("Agregar seccion de su finca");
        SeccionAgregar.setIcon(new ImageIcon("src/graficos/seccion.png"));
        SeccionAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 2;
        gbc.gridx = 1;
        Setup.add(SeccionAgregar, gbc);

        TrabajadorAgregar = new JButton("Agregar trabajador");
        TrabajadorAgregar.setIcon(new ImageIcon("src/graficos/trabajador.png"));
        TrabajadorAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 1;
        gbc.gridx = 2;
        Setup.add(TrabajadorAgregar, gbc);

        asignarGanadoASeccionButton = new JButton("Asignar ganado a seccion");
        asignarGanadoASeccionButton.setIcon(new ImageIcon("src/graficos/asignar.png"));
        asignarGanadoASeccionButton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 4;
        gbc.gridx = 1;
        Setup.add(asignarGanadoASeccionButton, gbc);

        ContactoAgregar = new JButton("Agregar contacto");
        ContactoAgregar.setIcon(new ImageIcon("src/graficos/contacto.png"));
        ContactoAgregar.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 4;
        gbc.gridx = 2;
        Setup.add(ContactoAgregar, gbc);

        JLabel welcomeLabel = new JLabel("Bienvenido al configurador de su Finca: ");
        welcomeLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        Setup.add(welcomeLabel, gbc);

        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 18));
        Regresar.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 5;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        Setup.add(Regresar, gbc);

        RevisarFinca = new JButton("Revisar estado de finca actual");
        RevisarFinca.setFont(new Font("Raleway", Font.BOLD, 18));
        RevisarFinca.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridx = 1;
        Setup.add(RevisarFinca, gbc);

        JPanel opciones_panel = new JPanel();
        opciones_panel.setLayout(new BoxLayout(opciones_panel, BoxLayout.X_AXIS));
        JButton opciones = new JButton();
        opciones.setPreferredSize(new Dimension(80, 60));
        opciones.setMaximumSize(new Dimension(80, 60));
        opciones.setMinimumSize(new Dimension(80, 60));
        opciones.setIcon(new ImageIcon("src/graficos/options.png"));
        opciones.setContentAreaFilled(false);
        opciones.setBorderPainted(false);
        opciones.setOpaque(false);
        opciones.setFocusPainted(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        Setup.add(opciones);
        gbc.gridx = 2;
        Setup.add(opciones_panel,gbc);

        JPopupMenu user_action = new JPopupMenu("ver mas");
        JButton calendario = new JButton("Calendario");
        calendario.setFont(new Font("Raleway", Font.BOLD, 14));
        calendario.setIcon(new ImageIcon("src/graficos/CalendarioSmall.png"));
        JButton usuario = new JButton("Usuario");
        usuario.setIcon(new ImageIcon("src/graficos/Usuario.png"));
        usuario.setFont(new Font("Raleway", Font.BOLD, 14));
        JButton tarea = new JButton("Tareas");
        tarea.setIcon(new ImageIcon("src/graficos/tareas.png"));
        tarea.setFont(new Font("Raleway", Font.BOLD, 14));
        user_action.add(usuario);
        user_action.add(calendario);
        user_action.add(tarea);
        opciones.setFont(new Font("Raleway", Font.BOLD, 18));
        opciones.setComponentPopupMenu(user_action);
        opciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_action.show(opciones,0,opciones.getWidth()/2);
            }
        });

        setContentPane(Setup);
        setLocationRelativeTo(null);
        setVisible(true);

        GanadoAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarGanado(sistema, finca, csv);
            }
        });
        SeccionAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarSeccion(sistema, finca, csv);
            }
        });
        SuministradorAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarSuministrador(sistema, finca, csv);
            }
        });
        SuministroAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarSuministro(sistema, finca, csv);
            }
        });
        CosechaAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarCosecha(sistema, finca, csv);
            }
        });
        RevisarFinca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new RevisarSetup(sistema, finca, csv);dispose();}
        });
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new Overview(sistema,finca, csv);dispose();}
        });
        asignarGanadoASeccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AsignarGanado(sistema, finca, csv);
            }
        });
        TrabajadorAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarTrabajador(sistema,finca,csv);
            }
        });
        ContactoAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarContacto(sistema, finca, csv);
            }
        });
        usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarUsuario(sistema, finca, csv);
            }
        });
        calendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Calendario(sistema, finca, csv);
            }
        });
        tarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarTarea(sistema, finca, csv);
            }
        });
    }
}