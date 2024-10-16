import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AgregarSeccion extends JFrame {
    private JPanel AgregarSeccion;
    private JTextField NombreField;
    private JButton agregarButton;
    private JTextField SizeField;
    private JTextField FuncionField;
    private JTextField EstadoField;
    private JComboBox MedidaField;
    private JButton agregarCabezasDeGanadoButton;
    private JButton Regresar;

    public AgregarSeccion(TuFinca sistema, Finca finca, export_csv csv){

        setTitle("Agregar Sección");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        AgregarSeccion = new JPanel();
        AgregarSeccion.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel titleLabel = new JLabel("Agregar Seccion");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 19;
        AgregarSeccion.add(titleLabel, gbc);

        // Nombre/Identificador Label
        JLabel nombreLabel = new JLabel("Nombre/Identificador");
        nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 1;
        AgregarSeccion.add(nombreLabel, gbc);

        // Nombre/Identificador Field
        NombreField = new JTextField();
        NombreField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 2;
        AgregarSeccion.add(NombreField, gbc);

        // Tamaño Label
        JLabel sizeLabel = new JLabel("Tamaño");
        sizeLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 3;
        AgregarSeccion.add(sizeLabel, gbc);

        SizeField = new JTextField();
        SizeField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipadx = 50;
        AgregarSeccion.add(SizeField, gbc);


        MedidaField = new JComboBox<>(new String[]{
            "Manzanas", "Hectareas", "Metros²", "Yardas²", "Varas²"
        });
        MedidaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridx = 1;
        AgregarSeccion.add(MedidaField, gbc);

        // Funcion Label
        JLabel funcionLabel = new JLabel("Funcion");
        funcionLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 19;
        AgregarSeccion.add(funcionLabel, gbc);

        // Funcion Field
        FuncionField = new JTextField();
        FuncionField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 6;
        AgregarSeccion.add(FuncionField, gbc);

        // Estado Label
        JLabel estadoLabel = new JLabel("Estado");
        estadoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 7;
        AgregarSeccion.add(estadoLabel, gbc);

        // Estado Field
        EstadoField = new JTextField();
        EstadoField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 8;
        AgregarSeccion.add(EstadoField, gbc);

        // Agregar Button
        agregarButton = new JButton("Agregar");
        agregarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridy = 9;
        gbc.gridwidth = 17;
        AgregarSeccion.add(agregarButton, gbc);

        // Agregar Cabezas de Ganado Button
        agregarCabezasDeGanadoButton = new JButton("Agregar Cabezas de ganado a seccion");
        agregarCabezasDeGanadoButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridx = 17;
        gbc.gridwidth = 2;
        AgregarSeccion.add(agregarCabezasDeGanadoButton, gbc);

        // Regresar Button
        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 14));
        Regresar.setForeground(new Color(-11179215));
        gbc.gridx = 18;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        AgregarSeccion.add(Regresar, gbc);

        // Vertical Spacer
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 18;
        gbc.fill = GridBagConstraints.VERTICAL;
        AgregarSeccion.add(Box.createVerticalStrut(20), gbc);


        Seccion seccion = new Seccion();
        setUndecorated(true);
        setContentPane(AgregarSeccion);
        setTitle("TuFinca");
        setSize(650,400);
        setLocationRelativeTo(null);
        setVisible(true);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seccion.setSeccion(NombreField.getText());
                seccion.setTamaño(Integer.valueOf(SizeField.getText()));
                seccion.setMedida((String) MedidaField.getSelectedItem());
                seccion.setFuncion(FuncionField.getText());
                seccion.setEstado(EstadoField.getText());
                seccion.setID_Num(finca.getSecciones().size()+1);
                finca.addSeccion(seccion);
                try {
                    csv.exportData(seccion.getDatos(), seccion.getFile(),finca);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AgregarSeccion.this, "Seccion: " + seccion.getSeccion() + "\nAgregada correctamente");
                dispose();
                //new SetupFinca(sistema,finca, csv);
            }
        });
        agregarCabezasDeGanadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AsignarGanado(sistema, finca, csv,seccion);
            }
        });
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
