import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AgregarSuministrador extends JFrame{
    private JPanel AgregarSuministrador;
    private JTextField NombreField;
    private JButton agregarButton;
    private JTextField ProductoField;
    private JTextField PrecioField;
    private JTextField EsperaField;
    private JButton Regresar;

    public AgregarSuministrador(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Agregar Suministrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        AgregarSuministrador = new JPanel();
        AgregarSuministrador.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel titleLabel = new JLabel("Agregar Suministrador");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        AgregarSuministrador.add(titleLabel, gbc);

        // Nombre del suministrador Label
        JLabel nombreLabel = new JLabel("Nombre del suministrador");
        nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 1;
        AgregarSuministrador.add(nombreLabel, gbc);

        // Nombre del suministrador Field
        NombreField = new JTextField();
        NombreField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 2;
        AgregarSuministrador.add(NombreField, gbc);

        // Que producto provee Label
        JLabel productoLabel = new JLabel("Que producto provee");
        productoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 3;
        AgregarSuministrador.add(productoLabel, gbc);

        // Que producto provee Field
        ProductoField = new JTextField();
        ProductoField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 4;
        AgregarSuministrador.add(ProductoField, gbc);

        // Precio en quetzales Label
        JLabel precioLabel = new JLabel("Precio en quetzales");
        precioLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 5;
        AgregarSuministrador.add(precioLabel, gbc);

        // Precio en quetzales Field
        PrecioField = new JTextField();
        PrecioField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 6;
        AgregarSuministrador.add(PrecioField, gbc);

        // Dias de espera promedio Label
        JLabel esperaLabel = new JLabel("Dias de espera promedio");
        esperaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 7;
        AgregarSuministrador.add(esperaLabel, gbc);

        // Dias de espera promedio Field
        EsperaField = new JTextField();
        EsperaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 8;
        AgregarSuministrador.add(EsperaField, gbc);

        // Agregar Button
        agregarButton = new JButton("Agregar");
        agregarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        AgregarSuministrador.add(agregarButton, gbc);

        // Regresar Button
        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 14));
        Regresar.setForeground(new Color(-11179215));
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        AgregarSuministrador.add(Regresar, gbc);

        // Vertical Spacer
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        AgregarSuministrador.add(Box.createVerticalStrut(20), gbc);

        setUndecorated(true);
        setContentPane(AgregarSuministrador);
        setTitle("TuFinca");
        setSize(400,390);
        setLocationRelativeTo(null);
        setVisible(true);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Suministrador suministrador = new Suministrador();
                suministrador.setNombre(NombreField.getText());
                suministrador.setProducto(ProductoField.getText());
                suministrador.setPrecio(Integer.parseInt(PrecioField.getText()));
                suministrador.setDias_espera(Integer.parseInt(EsperaField.getText()));
                suministrador.setID_Num(finca.getSuministradores().size()+1);
                finca.addSuministrador(suministrador);
                try {
                    csv.exportData(suministrador.getDatos(),suministrador.getFile(), finca);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AgregarSuministrador.this, "Suministrador: " + suministrador.getNombre() + "\nAgregado correctamente");
                dispose();
                //new SetupFinca(sistema,finca,csv);
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
