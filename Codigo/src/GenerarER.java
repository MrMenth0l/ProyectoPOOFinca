import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class GenerarER extends JFrame {

    private JPanel inputPanel;
    private JTextField dateField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JComboBox<String> typeComboBox;
    private JButton addButton;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JButton generateButton;
    private JButton exportButton;

    private List<Transaccion> transacciones;
    private TuFinca sistema;
    private export_csv exportador;
    private Finca finca;

    public GenerarER(TuFinca sistema, export_csv csv, Finca finca) {
        this.sistema = sistema;
        this.exportador = csv;
        this.finca = finca;
        this.transacciones = new ArrayList<>();

        setTitle("Generar Estado de Resultados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Agregar Transacción"));

        inputPanel.add(new JLabel("Fecha (dd/MM/yyyy):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Descripción:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Monto:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Tipo:"));
        typeComboBox = new JComboBox<>(new String[]{"Ingreso", "Egreso"});
        inputPanel.add(typeComboBox);

        addButton = new JButton("Agregar Transacción");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Descripción", "Monto", "Tipo"}, 0);
        transactionTable = new JTable(tableModel);
        add(new JScrollPane(transactionTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generar Estado de Resultados");
        exportButton = new JButton("Exportar a CSV");
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTransaccion();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarEstadoDeResultados();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarACSV();
            }
        });
    }

    private void agregarTransaccion() {
        String fecha = dateField.getText();
        String descripcion = descriptionField.getText();
        String montoStr = amountField.getText();
        String tipo = (String) typeComboBox.getSelectedItem();

        if (fecha.isEmpty() || descripcion.isEmpty() || montoStr.isEmpty() || tipo == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Transaccion transaccion = new Transaccion(fecha, descripcion, monto, tipo);
        transacciones.add(transaccion);
        tableModel.addRow(new Object[]{fecha, descripcion, monto, tipo});

        dateField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        typeComboBox.setSelectedIndex(0);
    }

    private void generarEstadoDeResultados() {
        double ingresos = 0;
        double egresos = 0;

        for (Transaccion t : transacciones) {
            if (t.getTipo().equals("Ingreso")) {
                ingresos += t.getMonto();
            } else if (t.getTipo().equals("Egreso")) {
                egresos += t.getMonto();
            }
        }

        double utilidadNeta = ingresos - egresos;

        String mensaje = String.format("Ingresos: Q%.2f\nEgresos: Q%.2f\nUtilidad Neta: Q%.2f", ingresos, egresos, utilidadNeta);
        JOptionPane.showMessageDialog(this, mensaje, "Estado de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportarACSV() {
        StringBuilder csvData = new StringBuilder();
        csvData.append("Fecha,Descripción,Monto,Tipo\n");
        for (Transaccion t : transacciones) {
            csvData.append(String.join(",", t.getFecha(), t.getDescripcion(), String.valueOf(t.getMonto()), t.getTipo())).append("\n");
        }

        String nombreArchivo = "EstadoResultados.csv";
        try {
            File archivo = new File(nombreArchivo);
            exportador.exportData(csvData.toString(), archivo, finca);
            JOptionPane.showMessageDialog(this, "Datos exportados a " + nombreArchivo, "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

class Transaccion {
    private String fecha;
    private String descripcion;
    private double monto;
    private String tipo;

    public Transaccion(String fecha, String descripcion, double monto, String tipo) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipo() {
        return tipo;
    }
}