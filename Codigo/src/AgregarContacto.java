import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AgregarContacto extends JFrame{
   private JPanel AgregarContacto;
   private JLabel nombreLabel;
   private JTextField nombreField;
   private JLabel tipoLabel;
   private JComboBox tipoField;
   private JLabel direccionLabel;
   private JTextField direccionField;
   private JLabel correoLabel;
   private JTextField correoField;
   private JLabel telefonoLabel;
   private JTextField telefonoField;
   private  JButton agregar;
   private JButton regresar;




   public AgregarContacto(TuFinca sistema, Finca finca, export_csv csv){
       setTitle("Agregar Contacto");
       setLayout(new GridBagLayout());


       AgregarContacto = new JPanel();
       AgregarContacto.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.insets = new Insets(5, 5, 5, 5);


       JLabel titleLabel = new JLabel("Agregar Contacto: ");
       titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
       titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 2;
       AgregarContacto.add(titleLabel, gbc);


       nombreLabel = new JLabel("Nombre del contacto: ");
       nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridx = 0;
       gbc.gridy = 1;
       AgregarContacto.add(nombreLabel,gbc);


       nombreField = new JTextField();
       nombreField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy=2;
       AgregarContacto.add(nombreField, gbc);


       tipoLabel = new JLabel("Tipo de contacto: ");
       tipoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy =3;
       AgregarContacto.add(tipoLabel, gbc);


       tipoField = new JComboBox<>(new String[]{
           "Cliente", "Vendedor", "Servicio", "Distribuidor", "Consejeria"
       });
       tipoField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 4;
       AgregarContacto.add(tipoField,gbc);


       direccionLabel = new JLabel("Direccion del contacto(si aplica): ");
       direccionLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 5;
       AgregarContacto.add(direccionLabel,gbc);


       direccionField = new JTextField();
       direccionField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 6;
       AgregarContacto.add(direccionField,gbc);


       correoLabel = new JLabel("Correo de contacto: ");
       correoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 7;
       AgregarContacto.add(correoLabel,gbc);


       correoField = new JTextField();
       correoField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 8;
       AgregarContacto.add(correoField,gbc);


       telefonoLabel = new JLabel("Telefono del contacto:");
       telefonoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 9;
       AgregarContacto.add(telefonoLabel,gbc);


       telefonoField = new JTextField();
       telefonoField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 10;
       AgregarContacto.add(telefonoField,gbc);


       agregar = new JButton("Agregar");
       agregar.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 11;
       gbc.gridwidth = 2;
       AgregarContacto.add(agregar, gbc);


       regresar = new JButton("Regresar");
       regresar.setFont(new Font("Raleway", Font.BOLD, 14));
       regresar.setForeground(new Color(-11179215));
       gbc.gridy = 12;
       gbc.gridwidth = 1;
       gbc.anchor = GridBagConstraints.EAST;
       AgregarContacto.add(regresar, gbc);


       gbc.gridx = 0;
       gbc.gridy = 12;
       gbc.gridwidth = 1;
       gbc.fill = GridBagConstraints.VERTICAL;
       AgregarContacto.add(Box.createVerticalStrut(20), gbc);


       setUndecorated(true);
       setContentPane(AgregarContacto);
       setSize(400,500);
       setLocationRelativeTo(null);
       setVisible(true);


       agregar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Contacto contacto = new Contacto();
               contacto.setNombre(nombreField.getText());
               contacto.setTipo_de_contacto((String) tipoField.getSelectedItem());
               contacto.setDireccion(direccionField.getText());
               contacto.setCorreo(correoField.getText());
               contacto.setTelefono(telefonoField.getText());
               finca.addContacto(contacto);
               contacto.setID_Num(finca.getContactos().size());
               try {
                   csv.exportData(contacto.getDatos(), contacto.getFile(),finca);
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
               JOptionPane.showMessageDialog(AgregarContacto.this, "Contacto: " + contacto.getNombre() + "\nAgregado correctamente");
               dispose();
               //new SetupFinca(sistema,finca,csv);


           }});
       regresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });
   }
}
