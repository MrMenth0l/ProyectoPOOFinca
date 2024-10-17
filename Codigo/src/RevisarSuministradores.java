import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarSuministradores extends JFrame {
    private JPanel RevisarSuministradores;

    public RevisarSuministradores(TuFinca sistema, Finca finca, export_csv csv){
        List<Suministrador> suministradorList = finca.getSuministradores();
        Object[][] Suministradores = new Object[suministradorList.size()][5];
        String[] header = {"Nombre", "Producto", "Precio", "Dias de espera", "ID_Num"};
        Font font = new Font("Raleway",Font.BOLD,16);

        for (int i = 0; i < suministradorList.size(); i++) {
            Suministrador suministrador = suministradorList.get(i);
            Suministradores[i][0] = suministrador.getNombre();
            Suministradores[i][2] = String.valueOf(suministrador.getPrecio());
            Suministradores[i][1] = suministrador.getProducto();
            Suministradores[i][3] = String.valueOf(suministrador.getDias_espera());
            Suministradores[i][4] = suministrador.getID_Num();
        }

        TableModel model = new DefaultTableModel(Suministradores, header);
        JTable suministradorTable = new JTable(model);
        suministradorTable.setPreferredScrollableViewportSize(new Dimension(800, Suministradores.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        suministradorTable.setFont(font12);

        JScrollPane scrollPane = new JScrollPane(suministradorTable);
        RevisarSuministradores = new JPanel();
        RevisarSuministradores.add(scrollPane);
        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarSuministradores.add(ver_mas);
        RevisarSuministradores.add(regresar, BorderLayout.PAGE_END);
        ver_mas.setFont(font);
        regresar.setFont(font);

        setContentPane(RevisarSuministradores);
        setTitle("Los Suministradores de tu finca");
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
                new RevisarIndividual(sistema, finca, csv,3);
            }
        });

    }
}