import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Overview extends JFrame {

    public Overview(TuFinca sistema, Finca finca, export_csv csv){
        setTitle("Su finca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel Overview = new JPanel();
        Overview.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;

        JLabel overviewLabel = new JLabel("Opciones");
        overviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overviewLabel.setVerticalAlignment(SwingConstants.CENTER);
        overviewLabel.setFont(new Font("Raleway", Font.BOLD, 48));
        gbc.gridy = 0;
        gbc.gridx = 2;
        Overview.add(overviewLabel, gbc);

        JButton setupFinca = new JButton("Configurador de su finca");
        setupFinca.setIcon(new ImageIcon("src/graficos/settingsBig.png"));
        setupFinca.setVerticalTextPosition(SwingConstants.BOTTOM);
        setupFinca.setHorizontalTextPosition(SwingConstants.CENTER);
        setupFinca.setFont(new Font("Raleway", Font.BOLD, 24));
        setupFinca.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 4;
        gbc.gridx = 1;
        Overview.add(setupFinca, gbc);

        JButton revisarFinca = new JButton("Vista general de su finca");
        revisarFinca.setIcon(new ImageIcon("src/graficos/Overview.png"));
        revisarFinca.setVerticalTextPosition(SwingConstants.BOTTOM);
        revisarFinca.setHorizontalTextPosition(SwingConstants.CENTER);
        revisarFinca.setFont(new Font("Raleway", Font.BOLD, 24));
        revisarFinca.setForeground(new Color(-11179215)); // Color: -11179215
        gbc.gridy = 4;
        gbc.gridx = 2;
        Overview.add(revisarFinca, gbc);

        JButton finanzas = new JButton("Estados Financieros");
        finanzas.setFont(new Font("Raleway", Font.BOLD, 24));
        finanzas.setVerticalTextPosition(SwingConstants.BOTTOM);
        finanzas.setHorizontalTextPosition(SwingConstants.CENTER);
        finanzas.setIcon(new ImageIcon("src/graficos/finanzas.png"));
        finanzas.setForeground(new Color(-11179215));
        gbc.gridy = 4;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        Overview.add(finanzas, gbc);


        setContentPane(Overview);
        setLocationRelativeTo(null);
        setVisible(true);

        setupFinca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetupFinca(sistema, finca, csv);
                dispose();
            }
        });
        revisarFinca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RevisarSetup(sistema, finca, csv);
                dispose();
            }
        });
        finanzas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerarER generarER = new GenerarER(sistema, csv, finca);
                generarER.setVisible(true);
            }
        });
    }
}
