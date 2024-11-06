import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsignarGanado extends JFrame{
    private JPanel AsignarGanado;
    private JComboBox comboBox1;

    public AsignarGanado(TuFinca sistema, Finca finca, export_csv csv, Seccion seccion){
        AsignarGanado = new JPanel();
        CabezaGanado cabezaGanado = new CabezaGanado();
        List<String> ganado_Finca = new ArrayList<>();
        for (int i = 0; i < finca.getCabezaGanados().size(); i++) {
            ganado_Finca.add(finca.getCabezaGanados().get(i).getNombre());
        }
        JComboBox GanadoField = new JComboBox<>(ganado_Finca.toArray());
        JLabel b = new JLabel("Seleccione que cabeza quiere agregar");
        JButton asignar = new JButton("Asignar cabeza de ganado");
        JButton Regresar = new JButton("Regresar");
        Font font = new Font("Raleway",Font.PLAIN,12);
        Font font14 = new Font("Raleway",Font.PLAIN,14);
        b.setFont(font);
        GanadoField.setFont(font);
        asignar.setFont(font14);
        Regresar.setFont(font14);

        AsignarGanado.add(b);
        AsignarGanado.add(GanadoField);
        AsignarGanado.add(asignar);
        AsignarGanado.add(Regresar, BorderLayout.PAGE_END);

        setUndecorated(true);
        setContentPane(AsignarGanado);
        setTitle("Asignar ganado");
        setSize(500,200);
        setLocationRelativeTo(null);
        setVisible(true);

        asignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getCabezaGanados().size(); j++) {
                            if (finca.getCabezaGanados().get(j).getNombre().equals(GanadoField.getSelectedItem())){
                                CabezaGanado cabezaGanado = finca.getCabezaGanados().get(j);
                                seccion.addCabezaGanado(finca.getCabezaGanados().get(j));
                                cabezaGanado.setSeccion(seccion);
                                try {
                                    csv.exportData(cabezaGanado.getNombre() +","+ cabezaGanado.getSeccionNombre(), cabezaGanado.getFileSeccion(),finca);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }

                JOptionPane.showMessageDialog(AsignarGanado.this, "Cabeza: " + GanadoField.getSelectedItem() + "\nAgregado a la seccion");
                setVisible(false);
            }
        });
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });





    }
    public AsignarGanado(TuFinca sistema, Finca finca, export_csv csv){
        final CabezaGanado[] cabezaGanado = {new CabezaGanado()};
        List<String> secciones_Finca = new ArrayList<>();
        List<String> ganado_Finca = new ArrayList<>();
        for (int i = 0; i < finca.getSecciones().size(); i++) {
            secciones_Finca.add(finca.getSecciones().get(i).getSeccion());
        }
        for (int i = 0; i < finca.getCabezaGanados().size(); i++) {
            ganado_Finca.add(finca.getCabezaGanados().get(i).getNombre());

        }
        JComboBox SeccionesField = new JComboBox(secciones_Finca.toArray());
        JComboBox GanadoField = new JComboBox<>(ganado_Finca.toArray());
        AsignarGanado = new JPanel();
        JLabel a = new JLabel("Seleccione a que seccion desea agregar el ganado");
        JLabel b = new JLabel("Seleccione que cabeza quiere agregar");
        JButton asignar = new JButton("Asignar cabeza de ganado");
        JButton Regresar = new JButton("Regresar");
        Font font = new Font("Raleway",Font.PLAIN,12);
        Font font14 = new Font("Raleway",Font.PLAIN,14);
        b.setFont(font);
        a.setFont(font);
        GanadoField.setFont(font);
        asignar.setFont(font14);
        Regresar.setFont(font14);

        AsignarGanado.add(a);
        AsignarGanado.add(SeccionesField);
        AsignarGanado.add(b);
        AsignarGanado.add(GanadoField);
        AsignarGanado.add(asignar);
        AsignarGanado.add(Regresar, BorderLayout.PAGE_END);

        setUndecorated(true);
        setContentPane(AsignarGanado);
        setTitle("Asignar ganado");
        setSize(500,200);
        setLocationRelativeTo(null);
        setVisible(true);

        asignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < finca.getSecciones().size(); i++) {
                    if (finca.getSecciones().get(i).getSeccion().equals( SeccionesField.getSelectedItem())){
                        Seccion seccion = finca.getSecciones().get(i);
                        for (int j = 0; j < finca.getCabezaGanados().size(); j++) {
                            if (finca.getCabezaGanados().get(j).getNombre().equals(GanadoField.getSelectedItem())){
                                cabezaGanado[0] = finca.getCabezaGanados().get(j);
                                seccion.addCabezaGanado(finca.getCabezaGanados().get(j));
                                cabezaGanado[0].setSeccion(seccion);
                                try {
                                    csv.exportData(cabezaGanado[0].getNombre() +","+ cabezaGanado[0].getSeccionNombre(), cabezaGanado[0].getFileSeccion(),finca);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(AsignarGanado.this, "Cabeza: " + GanadoField.getSelectedItem() + "\nAgregado a seccion: " + SeccionesField.getSelectedItem());
                new SetupFinca(sistema,finca, csv);
                setVisible(false);
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
