import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AgregarTrabajador extends JFrame{
   private JPanel AgregarTrabajador;
   private JLabel nombreLabel;
   private JTextField nombreField;
   private JLabel tipoLabel;
   private JTextField rolField;
   private JLabel sueldoLabel;
   private JTextField sueldoField;
   private JLabel telefonoLabel;
   private JTextField telefonoField;
   private  JButton agregar;
   private JButton regresar;




   public AgregarTrabajador(TuFinca sistema, Finca finca, export_csv csv){
       setTitle("Agregar Contacto");
       setLayout(new GridBagLayout());


       AgregarTrabajador = new JPanel();
       AgregarTrabajador.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.insets = new Insets(5, 5, 5, 5);


       JLabel titleLabel = new JLabel("Agregar Trabajador: ");
       titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
       titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 2;
       AgregarTrabajador.add(titleLabel, gbc);


       nombreLabel = new JLabel("Nombre del Trabajador: ");
       nombreLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridx = 0;
       gbc.gridy = 1;
       AgregarTrabajador.add(nombreLabel,gbc);


       nombreField = new JTextField();
       nombreField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy=2;
       AgregarTrabajador.add(nombreField, gbc);


       tipoLabel = new JLabel("Rol del trabajador: ");
       tipoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy =3;
       AgregarTrabajador.add(tipoLabel, gbc);


       rolField = new JTextField();
       rolField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 4;
       AgregarTrabajador.add(rolField,gbc);


       sueldoLabel = new JLabel("Salario mensual en QTZ: ");
       sueldoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 5;
       AgregarTrabajador.add(sueldoLabel,gbc);


       sueldoField = new JTextField();
       sueldoField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 6;
       AgregarTrabajador.add(sueldoField,gbc);


       telefonoLabel = new JLabel("Telefono del trabajador:");
       telefonoLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 7;
       AgregarTrabajador.add(telefonoLabel,gbc);


       telefonoField = new JTextField();
       telefonoField.setFont(new Font("Raleway", Font.PLAIN, 16));
       gbc.gridy = 8;
       AgregarTrabajador.add(telefonoField,gbc);


       agregar = new JButton("Agregar");
       agregar.setFont(new Font("Raleway", Font.BOLD, 16));
       gbc.gridy = 9;
       gbc.gridwidth = 2;
       AgregarTrabajador.add(agregar, gbc);


       regresar = new JButton("Regresar");
       regresar.setFont(new Font("Raleway", Font.BOLD, 14));
       regresar.setForeground(new Color(-11179215));
       gbc.gridy = 10;
       gbc.gridwidth = 1;
       gbc.anchor = GridBagConstraints.EAST;
       AgregarTrabajador.add(regresar, gbc);


       gbc.gridx = 0;
       gbc.gridy = 10;
       gbc.gridwidth = 1;
       gbc.fill = GridBagConstraints.VERTICAL;
       AgregarTrabajador.add(Box.createVerticalStrut(20), gbc);


       setUndecorated(true);
       setContentPane(AgregarTrabajador);
       setSize(400,500);
       setLocationRelativeTo(null);
       setVisible(true);


       agregar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Trabajador trabajador = new Trabajador();
               trabajador.setNombre(nombreField.getText());
               trabajador.setRol(rolField.getText());
               trabajador.setSueldo(Double.parseDouble(sueldoField.getText()));
               trabajador.setTelefono(telefonoField.getText());
               finca.addTrabajador(trabajador);
               trabajador.setID_Num(finca.getContactos().size());
               try {
                   csv.exportData(trabajador.getDatos(), trabajador.getFile(),finca);
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
               JOptionPane.showMessageDialog(AgregarTrabajador.this, "Trabajador: " + trabajador.getNombre() + "\nAgregado correctamente");
               dispose();
               new SetupFinca(sistema,finca,csv);


           }});
       regresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });
   }
}
