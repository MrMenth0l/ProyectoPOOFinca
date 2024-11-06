import javax.swing.*;
import java.awt.*;

public class SetupFinanzas extends JFrame {
    public SetupFinanzas(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Su finca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel setupFinanzas = new JPanel();
        setupFinanzas.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;


        JButton generarEstadoResultados = new JButton("Generar Estado de Resultados");
        generarEstadoResultados.setFont(new Font("Raleway", Font.BOLD, 24));



        JButton revisarEstadoResultados = new JButton("Revisar Estado de Resultados");
        revisarEstadoResultados.setFont(new Font("Raleway", Font.BOLD, 24));

        generarEstadoResultados.addActionListener(e -> {
            new GenerarER(sistema,csv);
            dispose();
        });
        revisarEstadoResultados.addActionListener(e -> {
            new RevisarER(sistema,csv);
            dispose();
        });
    }
}
