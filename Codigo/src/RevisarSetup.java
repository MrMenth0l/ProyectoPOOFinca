import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevisarSetup extends JFrame{
    private JButton revisarGanadoButton;
    private JButton revisarSeccionesDeFincaButton;
    private JButton revisarSuministradoresButton;
    private JButton revisarSuministrosButton;
    private JButton revisarCosechasbutton;
    private JButton revisarTrabajadoresbutton;
    private JButton revisarContactosbutton;
    private JButton Regresar;
    private JPanel RevisarSetup;

    public RevisarSetup(TuFinca sistema, Finca finca, export_csv csv) {
        setTitle("Configurador de su Finca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel RevisarSetup = new JPanel();
        RevisarSetup.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;

        revisarGanadoButton = new JButton("Cabezas de ganado");
        revisarGanadoButton.setIcon(new ImageIcon("src/graficos/vaca_icon.png"));
        revisarGanadoButton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        RevisarSetup.add(revisarGanadoButton, gbc);

        revisarSuministradoresButton = new JButton("Suministradores de su finca");
        revisarSuministradoresButton.setIcon(new ImageIcon("src/graficos/suministrador.png"));
        revisarSuministradoresButton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 3;
        RevisarSetup.add(revisarSuministradoresButton, gbc);

        revisarCosechasbutton = new JButton("Cosechas de su finca");
        revisarCosechasbutton.setIcon(new ImageIcon("src/graficos/cosecha.png"));
        revisarCosechasbutton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 4;
        RevisarSetup.add(revisarCosechasbutton, gbc);

        revisarSuministrosButton = new JButton("Suministros de su finca");
        revisarSuministrosButton.setIcon(new ImageIcon("src/graficos/suministro.png"));
        revisarSuministrosButton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 3;
        gbc.gridx = 2;
        RevisarSetup.add(revisarSuministrosButton, gbc);

        revisarSeccionesDeFincaButton = new JButton("Secciones de su finca");
        revisarSeccionesDeFincaButton.setIcon(new ImageIcon("src/graficos/seccion.png"));
        revisarSeccionesDeFincaButton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridy = 2;
        gbc.gridx = 1;
        RevisarSetup.add(revisarSeccionesDeFincaButton, gbc);

        revisarTrabajadoresbutton = new JButton("Trabajadores de su finca");
        revisarTrabajadoresbutton.setIcon(new ImageIcon("src/graficos/trabajador.png"));
        revisarTrabajadoresbutton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridx = 2;
        gbc.gridy = 2;
        RevisarSetup.add(revisarTrabajadoresbutton,gbc);

        revisarContactosbutton = new JButton("Contactos de su finca");
        revisarContactosbutton.setIcon(new ImageIcon("src/graficos/contacto.png"));
        revisarContactosbutton.setFont(new Font("Raleway", Font.PLAIN, 18));
        gbc.gridx = 2;
        gbc.gridy = 1;
        RevisarSetup.add(revisarContactosbutton,gbc);

        JLabel welcomeLabel = new JLabel("Revisar su Finca: ");
        welcomeLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        RevisarSetup.add(welcomeLabel, gbc);

        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 18));
        Regresar.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 6;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        RevisarSetup.add(Regresar, gbc);

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
        RevisarSetup.add(opciones);
        gbc.gridx = 2;
        RevisarSetup.add(opciones_panel,gbc);

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

        JButton finanzas = new JButton("Revisar finanzas");
        finanzas.setFont(new Font("Raleway", Font.PLAIN, 18));
        finanzas.setIcon(new ImageIcon("src/graficos/finanzasSmall.png"));
        gbc.gridy = 4;
        gbc.gridx = 2;
        RevisarSetup.add(finanzas,gbc);


        opciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_action.show(opciones,0,opciones.getWidth()/2);
            }
        });

        setContentPane(RevisarSetup);
        setLocationRelativeTo(null);
        setVisible(true);

        revisarGanadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarCabezas(sistema, finca, csv);
                dispose();
            }
        });

        revisarSeccionesDeFincaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarSeccion(sistema,finca, csv);
                dispose();
            }
        });

        revisarSuministradoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarSuministradores(sistema, finca, csv);
                dispose();
            }
        });

        revisarSuministrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarSuministros(sistema, finca, csv);
                dispose();
            }
        });

        revisarCosechasbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarCosecha(sistema, finca, csv);
                dispose();
            }
        });

        revisarTrabajadoresbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarTrabajadores(sistema, finca, csv);
                dispose();
            }
        });

        revisarContactosbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarContactos(sistema, finca, csv);
                dispose();
            }
        });

        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Overview(sistema,finca, csv);
                dispose();
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
