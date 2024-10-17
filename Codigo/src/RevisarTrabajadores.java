import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevisarTrabajadores extends JFrame{
    private JPanel RevisarTrabajadores;

    public RevisarTrabajadores(TuFinca sistema, Finca finca, export_csv csv){
        List<Trabajador> trabajadorList = finca.getTrabajadores();
        Object[][] Trabajadores = new Object[trabajadorList.size()][5];
        String[] header = {"Nombre", "Rol", "Sueldo", "Telefono", "ID_Num"};
        Font font = new Font("Raleway",Font.BOLD,16);

        for (int i = 0; i < trabajadorList.size(); i++) {
            Trabajador trabajador = trabajadorList.get(i);
            Trabajadores[i][0] = trabajador.getNombre();
            Trabajadores[i][2] = trabajador.getRol();
            Trabajadores[i][1] = String.valueOf(trabajador.getSueldo());
            Trabajadores[i][3] = trabajador.getTelefono();
            Trabajadores[i][4] = trabajador.getID_Num();
        }

        TableModel model = new DefaultTableModel(Trabajadores, header);
        JTable trabajadorTable = new JTable(model);
        trabajadorTable.setPreferredScrollableViewportSize(new Dimension(800, Trabajadores.length*15));
        Font font12 = new Font("Raleway",Font.PLAIN,12);
        trabajadorTable.setFont(font12);

        JScrollPane scrollPane = new JScrollPane(trabajadorTable);
        RevisarTrabajadores = new JPanel();
        RevisarTrabajadores.add(scrollPane);

        JButton ver_mas = new JButton("Modificar");
        JButton regresar = new JButton("regresar");
        RevisarTrabajadores.add(ver_mas);
        RevisarTrabajadores.add(regresar, BorderLayout.PAGE_END);
        ver_mas.setFont(font);
        regresar.setFont(font);

        JButton performance = new JButton("Desempeño");
        performance.setFont(font);
        RevisarTrabajadores.add(performance);

        setContentPane(RevisarTrabajadores);
        setTitle("Los Trabajadores de tu finca");
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
                new RevisarIndividual(sistema, finca, csv,6);
            }
        });

    }
}
