import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarSuministros extends JFrame {
    private JPanel RevisarSuministros;

    public RevisarSuministros(TuFinca sistema, Finca finca, export_csv csv){
        List<Suministro> suministradorList = finca.getSuministros();
        Object[][] Suministros = new Object[suministradorList.size()][5];
        String[] header = {"Nombre", "Tipo", "Stock", "Dias desde compra", "ID_Num"};

        for (int i = 0; i < suministradorList.size(); i++) {
            Suministro suministro = suministradorList.get(i);
            Suministros[i][0] = suministro.getNombre();
            Suministros[i][2] = String.valueOf(suministro.getCantidad());
            Suministros[i][1] = suministro.getTipo();
            Suministros[i][3] = String.valueOf(suministro.getDiasDesdeCompra());
            Suministros[i][4] = suministro.getID_Num();
        }

        TableModel model = new DefaultTableModel(Suministros, header);
        JTable suministrosTable = new JTable(model);
        suministrosTable.setPreferredScrollableViewportSize(new Dimension(800, Suministros.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        suministrosTable.setFont(font12);

        JScrollPane scrollPane = new JScrollPane(suministrosTable);
        RevisarSuministros = new JPanel();
        RevisarSuministros.add(scrollPane);
        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarSuministros.add(ver_mas);
        RevisarSuministros.add(regresar, BorderLayout.PAGE_END);
        Font font = new Font("Raleway",Font.BOLD,16);
        ver_mas.setFont(font);
        regresar.setFont(font);

        setContentPane(RevisarSuministros);
        setTitle("Los Suministros de tu finca");
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
                new RevisarIndividual(sistema, finca, csv,4);
            }
        });


    }
}
