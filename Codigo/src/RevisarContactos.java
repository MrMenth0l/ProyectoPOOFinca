import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class RevisarContactos extends JFrame {
   private JPanel RevisarContactos;


   public RevisarContactos(TuFinca sistema, Finca finca, export_csv csv){
       List<Contacto> contactoList = finca.getContactos();
       Object[][] Contactos = new Object[contactoList.size()][6];
       String[] header = {"Nombre", "Tipo de contacto", "Direccion", "Correo", "Telefono", "ID_Num"};
       Font font = new Font("Raleway",Font.BOLD,16);

       for (int i = 0; i < contactoList.size(); i++) {
           Contacto contacto = contactoList.get(i);
           Contactos[i][0] = contacto.getNombre();
           Contactos[i][1] = contacto.getTipo_de_contacto();
           Contactos[i][2] = contacto.getDireccion();
           Contactos[i][3] = contacto.getCorreo();
           Contactos[i][4] = contacto.getTelefono();
           Contactos[i][5] = contacto.getID_Num();
       }

       TableModel model = new DefaultTableModel(Contactos, header);
       JTable contactoTable = new JTable(model);
       contactoTable.setPreferredScrollableViewportSize(new Dimension(800, Contactos.length*15));
       Font font12 = new Font("Raleway",Font.PLAIN,12);
       contactoTable.setFont(font12);

       JScrollPane scrollPane = new JScrollPane(contactoTable);
       RevisarContactos = new JPanel();
       RevisarContactos.add(scrollPane);
       JButton ver_mas = new JButton("Modificar");
       JButton regresar = new JButton("regresar");
       RevisarContactos.add(ver_mas);
       RevisarContactos.add(regresar, BorderLayout.PAGE_END);
       ver_mas.setFont(font);
       regresar.setFont(font);

       setContentPane(RevisarContactos);
       setTitle("Los Contactos de tu finca");
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       setLocationRelativeTo(null);
       setVisible(true);

       regresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new RevisarSetup(sistema, finca, csv);
               dispose();
           }
       });

       ver_mas.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new RevisarIndividual(sistema, finca, csv,7);
           }
       });
   }
}