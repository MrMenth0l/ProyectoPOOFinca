import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AgregarSuministro extends JFrame {
    private JPanel AgregarSuministro;
    private JTextField NombreField;
    private JButton agregarButton;
    private JTextField ExistenciaField;
    private JTextField DiasField;
    private JComboBox TipoField;
    private JButton Regresar;

    public AgregarSuministro(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Agregar Suministro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);


        JPanel AgregarSuministro = new JPanel();
        AgregarSuministro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel titleLabel = new JLabel("Agregar Suministro");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        AgregarSuministro.add(titleLabel, gbc);


        JLabel nombreLabel = new JLabel("Nombre de suministro");
        nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        AgregarSuministro.add(nombreLabel, gbc);


        NombreField = new JTextField();
        NombreField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        AgregarSuministro.add(NombreField, gbc);


        JLabel tipoLabel = new JLabel("Tipo de suministro");
        tipoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        AgregarSuministro.add(tipoLabel, gbc);


        String[] tipos = {"Veterinario", "Quimico", "Concentrado", "Mecanico", "Pestizida", "Fertilizante", "Mixto"};
        TipoField = new JComboBox<>(tipos);
        TipoField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        AgregarSuministro.add(TipoField, gbc);


        JLabel existenciaLabel = new JLabel("Cantidad en existencia");
        existenciaLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        AgregarSuministro.add(existenciaLabel, gbc);


        ExistenciaField = new JTextField();
        ExistenciaField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        AgregarSuministro.add(ExistenciaField, gbc);


        JLabel diasLabel = new JLabel("Dias desde compra");
        diasLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        AgregarSuministro.add(diasLabel, gbc);


        DiasField = new JTextField();
        DiasField.setFont(new Font("Raleway", Font.PLAIN, 14));
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        AgregarSuministro.add(DiasField, gbc);


        agregarButton = new JButton("Agregar");
        agregarButton.setFont(new Font("Raleway", Font.BOLD, 16));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        AgregarSuministro.add(agregarButton, gbc);


        gbc.gridy = 10;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        AgregarSuministro.add(Box.createVerticalGlue(), gbc);


        Regresar = new JButton("Regresar");
        Regresar.setFont(new Font("Raleway", Font.BOLD, 14));
        Regresar.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        AgregarSuministro.add(Regresar, gbc);


        add(AgregarSuministro);

        setVisible(true);
        setContentPane(AgregarSuministro);
        setSize(300,380);
        setLocationRelativeTo(null);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Suministro suministro = new Suministro();
                suministro.setNombre(NombreField.getText());
                suministro.setTipo((String) TipoField.getSelectedItem());
                suministro.setCantidad(Integer.valueOf(ExistenciaField.getText()));
                suministro.setDiasDesdeCompra(Integer.valueOf(DiasField.getText()));
                suministro.setID_Num(finca.getSuministros().size()+1);
                finca.addSuministros(suministro);
                try {
                    csv.exportData(suministro.getDatos(),suministro.getFile(),finca);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(AgregarSuministro.this, "Suministro: " + suministro.getNombre() + "\nAgregado correctamente");
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
