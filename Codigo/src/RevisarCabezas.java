import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarCabezas extends JFrame {
    private JPanel RevisarCabezas;
    private JButton regresarButton;
    private JScrollPane scrollPane;
    private JTable ganadoTable;

    public RevisarCabezas(TuFinca sistema, Finca finca, export_csv csv) {
        List<CabezaGanado> ganadoList = finca.getCabezaGanados();
        Object[][] cabezasGanado = new Object[ganadoList.size()][7];
        String[] header = {"Nombre", "Raza","Especie", "Edad","Fecha de embarazo","Fecha de parto(esperado)", "ID_Num"};

        for (int i = 0; i < ganadoList.size(); i++) {
            CabezaGanado cabeza = ganadoList.get(i);
            cabezasGanado[i][0] = cabeza.getNombre();
            cabezasGanado[i][1] = cabeza.getRaza();
            cabezasGanado[i][2] = cabeza.getEspecie();
            cabezasGanado[i][3] = String.valueOf(cabeza.getEdad());
            if (cabeza.isEmbarazada()){
                cabezasGanado[i][4] = cabeza.getEmbarazo_inicio("");
                cabezasGanado[i][5]= cabeza.getEmbarazo_alerta("");
            }else {
                cabezasGanado[i][4] = "no aplica";
                cabezasGanado[i][5]= "no aplica";
            }
            cabezasGanado[i][6] = cabeza.getID_Num();
        }

        TableModel model = new DefaultTableModel(cabezasGanado, header);
        ganadoTable = new JTable(model);
        ganadoTable.setPreferredScrollableViewportSize(new Dimension(800, cabezasGanado.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        ganadoTable.setFont(font12);

        scrollPane = new JScrollPane(ganadoTable);
        RevisarCabezas = new JPanel();

        RevisarCabezas.add(scrollPane);
        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarCabezas.add(ver_mas);
        RevisarCabezas.add(regresar, BorderLayout.PAGE_END);
        Font font = new Font("Raleway",1,16);
        ver_mas.setFont(font);
        regresar.setFont(font);

        setContentPane(RevisarCabezas);
        setTitle("Tus cabezas de ganado");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarSetup(sistema,finca,csv);
                dispose();
            }
        });
        ver_mas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarIndividual(sistema, finca, csv,1);
            }
        });

    }
}
