import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarSeccion extends JFrame {
    private JPanel RevisarSeccion;
    private JButton regresarButton;

    public RevisarSeccion(TuFinca sistema, Finca finca, export_csv csv){
        List<Seccion> seccionList = finca.getSecciones();
        Object[][] secciones = new Object[seccionList.size()][7];
        String[] header = {" Nombre", "Tamaño","Medida", "Funcion", "Estado", "Cabezas", "ID_Num"};

        for (int i = 0; i < seccionList.size(); i++) {
            Seccion seccion = seccionList.get(i);
            secciones[i][0] = seccion.getSeccion();
            secciones[i][1] = String.valueOf(seccion.getTamaño());
            secciones[i][2] = seccion.getMedida();
            secciones[i][3] = seccion.getFuncion();
            secciones[i][4] = seccion.getEstado();
            secciones[i][5] = String.valueOf(seccion.getCabezasSize());
            secciones[i][6] = seccion.getID_Num();
        }

        TableModel model = new DefaultTableModel(secciones, header);
        JTable seccionTable = new JTable(model);
        seccionTable.setPreferredScrollableViewportSize(new Dimension(800, secciones.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        seccionTable.setFont(font12);

        JScrollPane scrollPane = new JScrollPane(seccionTable);
        RevisarSeccion = new JPanel();
        RevisarSeccion.add(scrollPane);
        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarSeccion.add(ver_mas);
        RevisarSeccion.add(regresar, BorderLayout.PAGE_END);
        Font font = new Font("Raleway",Font.BOLD,16);
        ver_mas.setFont(font);
        regresar.setFont(font);

        setContentPane(RevisarSeccion);
        setTitle("Las secciones de tu finca");
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
                new RevisarIndividual(sistema, finca, csv,2);
            }
        });
    }
}