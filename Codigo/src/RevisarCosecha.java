import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarCosecha extends JFrame {
    private JPanel RevisarCosecha;

    public RevisarCosecha(TuFinca sistema, Finca finca, export_csv csv){
        List<Cosecha> seccionList = finca.getCosechas();
        Object[][] Cosechas = new Object[seccionList.size()][7];
        String[] header = {"Nombre", "Tamaño","Medida", "Estado", "Epoca", "Geografia", "ID_Num"};

        for (int i = 0; i < seccionList.size(); i++) {
            Cosecha cosecha = seccionList.get(i);
            Cosechas[i][0] = cosecha.getTipo();
            Cosechas[i][1] = String.valueOf(cosecha.getTamaño());
            Cosechas[i][2] = cosecha.getMedida();
            Cosechas[i][3] = cosecha.getEstado();
            Cosechas[i][4] = cosecha.getEpoca();
            Cosechas[i][5] = cosecha.getGeografia();
            Cosechas[i][6] = cosecha.getID_Num();
        }

        TableModel model = new DefaultTableModel(Cosechas, header);
        JTable cosechaTable = new JTable(model);
        cosechaTable.setPreferredScrollableViewportSize(new Dimension(800, Cosechas.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        cosechaTable.setFont(font12);

        JScrollPane scrollPane = new JScrollPane(cosechaTable);
        RevisarCosecha = new JPanel();
        RevisarCosecha.add(scrollPane);
        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarCosecha.add(ver_mas);
        RevisarCosecha.add(regresar, BorderLayout.PAGE_END);
        Font font = new Font("Raleway",1,16);
        ver_mas.setFont(font);
        regresar.setFont(font);

        setContentPane(RevisarCosecha);
        setTitle("Las Cosechas de tu finca");
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
                new RevisarIndividual(sistema, finca, csv,5);
            }
        });

    }
}