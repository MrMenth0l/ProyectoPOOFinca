import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AgregarCosecha extends JFrame {
    private JTextField CosechaField;
    private JButton agregarButton;
    private JTextField SizeField;
    private JTextField GeoField;
    private JPanel AgregarCosecha;
    private JComboBox EstadoField;
    private JComboBox EpocaField;
    private JComboBox MedidaField;
    private JButton Regresar;

    public AgregarCosecha(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Agregar Cosecha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        AgregarCosecha = new JPanel();
        AgregarCosecha.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);


        JLabel Titulo = new JLabel("Agregar Cosecha");
        Titulo.setFont(new Font("Raleway", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        AgregarCosecha.add(Titulo, gbc);


        JLabel tipoLabel = new JLabel("Tipo de cosecha");
        tipoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        AgregarCosecha.add(tipoLabel, gbc);


        CosechaField = new JTextField();
        CosechaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.ipadx = 150;
        AgregarCosecha.add(CosechaField, gbc);


        JLabel tamanoLabel = new JLabel("Tamaño de la cosecha");
        tamanoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        AgregarCosecha.add(tamanoLabel, gbc);


        SizeField = new JTextField();
        SizeField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        AgregarCosecha.add(SizeField, gbc);


        MedidaField = new JComboBox<>(new String[]{
            "Manzanas", "Hectareas", "Metros²", "Yardas²", "Varas²"
        });
        MedidaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridx = 1;
        AgregarCosecha.add(MedidaField, gbc);


        JLabel estadoLabel = new JLabel("Estado de la cosecha");
        estadoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        AgregarCosecha.add(estadoLabel, gbc);


        EstadoField = new JComboBox<>(new String[]{
            "Vacia", "En crecimiento", "Siembra", "Lista para cosechar"
        });
        EstadoField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        AgregarCosecha.add(EstadoField, gbc);


        JLabel epocaLabel = new JLabel("Epoca en la que planta esta cosecha");
        epocaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        AgregarCosecha.add(epocaLabel, gbc);


        EpocaField = new JComboBox<>(new String[]{
            "Verano", "Primavera", "Invierno", "Otoño"
        });
        EpocaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 8;
        AgregarCosecha.add(EpocaField, gbc);


        JLabel geoLabel = new JLabel("En que geografia la crece");
        geoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        AgregarCosecha.add(geoLabel, gbc);


        GeoField = new JTextField();
        GeoField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 10;
        AgregarCosecha.add(GeoField, gbc);


        JButton agregarButton = new JButton("Agregar");
        agregarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        AgregarCosecha.add(agregarButton, gbc);


        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 14));
        Regresar.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridx = 3;
        AgregarCosecha.add(Regresar, gbc);

        setUndecorated(true);
        setVisible(true);
        setContentPane(AgregarCosecha);
        setTitle("TuFinca");
        setSize(700,400);
        setLocationRelativeTo(null);


        String [] estados = {"Vacia", "En crecimiento", "Sembra", "Lista para cosechar"};


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cosecha cosecha = new Cosecha();
                cosecha.setTipo(CosechaField.getText());
                cosecha.setTamaño(Integer.parseInt(SizeField.getText()));
                cosecha.setMedida((String) MedidaField.getSelectedItem());
                cosecha.setEstado((String) EstadoField.getSelectedItem());
                cosecha.setEpoca((String) EpocaField.getSelectedItem());
                cosecha.setGeografia(GeoField.getText());
                cosecha.setID_Num(finca.getCosechas().size()+1);
                finca.addCosecha(cosecha);
                try {
                    csv.exportData(cosecha.getDatos(), cosecha.getFile(),finca);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AgregarCosecha.this, "Cosecha: " + cosecha.getTipo() + "\nAgregada correctamente");
                dispose();
                new SetupFinca(sistema,finca, csv);
            }
        });
        this.Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
