import com.toedter.calendar.JCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Calendario extends JFrame{

    public Calendario(TuFinca sistema, Finca finca, export_csv csv) {
        JPanel Calendario = new JPanel();
        Calendario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JCalendar calendar = new JCalendar();
        calendar.setFont(new Font("Raleway",Font.PLAIN,14));
        calendar.setSize(new Dimension(300,300));
        calendar.setSundayForeground(new Color(-11179215));
        calendar.setWeekdayForeground(new Color(-11179215));

        gbc.gridx = 1;
        gbc.gridy = 0;
        Calendario.add(calendar, gbc);

        JButton regresar = new JButton("Regresar");
        regresar.setFont(new Font("Raleway",Font.BOLD,14));
        gbc.gridy=2;
        Calendario.add(regresar,gbc);

        calendar.addPropertyChangeListener("calendar", evt -> {
            Date selectedDate = calendar.getDate();
            SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
            String dateOnly = dateFormat.format(selectedDate);

            //new TareasDia(sistema,finca,csv,dateOnly);
        });

        regresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });

        setContentPane(Calendario);
        setSize(600,320);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}