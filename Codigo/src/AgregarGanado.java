import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AgregarGanado extends JFrame {
    private JPanel AgregarGanado;
    private JTextField NombreField;
    private JTextField RazaField;
    private JTextField EdadField;
    private JButton agregarButton;
    private JComboBox EspecieField;
    private JLabel Especie;
    private JButton Regresar;
    private int ID_Num;

    public AgregarGanado(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Agregar Ganado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Remove window decorations for full screen effect

        // Create a JPanel with GridBagLayout
        AgregarGanado = new JPanel();
        AgregarGanado.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel titleLabel = new JLabel("Agregar Cabeza de ganado");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        AgregarGanado.add(titleLabel, gbc);

        // Nombre Label
        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 1;
        AgregarGanado.add(nombreLabel, gbc);

        // Nombre TextField
        NombreField = new JTextField();
        NombreField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipadx = 150;
        AgregarGanado.add(NombreField, gbc);

        // Especie Label
        JLabel especieLabel = new JLabel("Especie");
        especieLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        AgregarGanado.add(especieLabel, gbc);

        // Especie ComboBox
        EspecieField = new JComboBox<>(new String[]{
            "Bovino", "Aviar", "Equino", "Ovino", "Caprino"
        });
        EspecieField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 4;
        AgregarGanado.add(EspecieField, gbc);

        // Raza Label
        JLabel razaLabel = new JLabel("Raza");
        razaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        AgregarGanado.add(razaLabel, gbc);

        // Raza TextField
        RazaField = new JTextField();
        RazaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 6;
        AgregarGanado.add(RazaField, gbc);

        // Edad Label
        JLabel edadLabel = new JLabel("Edad (En años)");
        edadLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 7;
        AgregarGanado.add(edadLabel, gbc);

        // Edad TextField
        EdadField = new JTextField();
        EdadField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 8;
        AgregarGanado.add(EdadField, gbc);

        // Add Button
        agregarButton = new JButton("Agregar");
        agregarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        AgregarGanado.add(agregarButton, gbc);

        // Regresar Button
        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 14));
        Regresar.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        AgregarGanado.add(Regresar, gbc);

        // Add panel to the frame

        // Make frame visible
        setVisible(true);
        setContentPane(this.AgregarGanado);
        setTitle("TuFinca");
        setSize(300,380);
        setLocationRelativeTo(null);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CabezaGanado cabezaGanado = new CabezaGanado();
                Seccion seccion = new Seccion();
                seccion.setSeccion("General");
                cabezaGanado.setNombre(NombreField.getText());
                cabezaGanado.setEdad((Integer.parseInt(EdadField.getText())));
                cabezaGanado.setRaza(RazaField.getText());
                cabezaGanado.setEspecie((String) EspecieField.getSelectedItem());
                cabezaGanado.setSeccion(seccion);
                ID_Num = finca.getCabezaGanados().size()+1;
                cabezaGanado.setID_Num(ID_Num);
                finca.addCabezaGanado(cabezaGanado);
                System.out.println(finca.getNombre_Finca());
                try {
                    csv.exportData(cabezaGanado.getDatos(),cabezaGanado.getFile(),finca);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AgregarGanado.this, "Cabeza de ganado: " + cabezaGanado.getNombre() + "\nAgregada correctamente");
                dispose();
                new SetupFinca(sistema,finca, csv);

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
