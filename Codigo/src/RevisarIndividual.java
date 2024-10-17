import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RevisarIndividual extends JFrame {
    private JPanel RevisarIndividual;
    private JPanel datos;
    public RevisarIndividual(TuFinca sistema, Finca finca, export_csv csv, int categoria){
        RevisarIndividual = new JPanel();
        datos = new JPanel();
        switch (categoria){
            case 1:
                List<String> ganado_finca = new ArrayList<>();
                for (int i = 0; i < finca.getCabezaGanados().size(); i++) {
                    ganado_finca.add(finca.getCabezaGanados().get(i).getNombre()+"-"+finca.getCabezaGanados().get(i).getID_Num());
                }
                JComboBox GanadoField = new JComboBox<>(ganado_finca.toArray());
                JButton button = new JButton("Revisar cabeza");
                JLabel b = new JLabel("Seleccione que cabeza quiere revisar");

                Object[][] cabeza_overview = new Object[5][2];
                String[] header = {"Atributo", "Valor"};

                Font font = new Font("Raleway",Font.PLAIN,16);
                Font font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                GanadoField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(GanadoField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar cabeza de ganado individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id_num = GanadoField.getSelectedItem().toString();
                        for (int j = 0; j < finca.getCabezaGanados().size(); j++) {
                            if (id_num.contains(finca.getCabezaGanados().get(j).getID_Num())) {
                                CabezaGanado cabeza = finca.getCabezaGanados().get(j);
                                cabeza_overview[0][0] = "Nombre: ";
                                cabeza_overview[0][1] = finca.getCabezaGanados().get(j).getNombre();
                                cabeza_overview[1][0] = "Edad";
                                cabeza_overview[1][1] = finca.getCabezaGanados().get(j).getEdad();
                                cabeza_overview[2][0] = "Raza";
                                cabeza_overview[2][1] = finca.getCabezaGanados().get(j).getRaza();
                                cabeza_overview[3][0] = "Fecha de embarazo";
                                cabeza_overview[4][0] = "Fecha esperada de parto";
                                if (cabeza.isEmbarazada()) {
                                    cabeza_overview[3][1] = finca.getCabezaGanados().get(j).getEmbarazo_inicio("");
                                    cabeza_overview[4][1] = finca.getCabezaGanados().get(j).getEmbarazo_alerta(" ");
                                }else {
                                    cabeza_overview[3][1] = "No aplica";
                                    cabeza_overview[4][1] = "No aplica";
                                }

                                String ID_Num = finca.getCabezaGanados().get(j).getID_Num();

                                TableModel model = new DefaultTableModel(cabeza_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, cabeza_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,100);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar cabeza " + finca.getCabezaGanados().get(j).getNombre());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover cabeza"+ finca.getCabezaGanados().get(j).getNombre());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);


                                setSize(700,cabeza_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        cabeza.setNombre((String) overview_table.getValueAt(0,1));
                                        String edad = String.valueOf(overview_table.getValueAt(1,1));
                                        cabeza.setEdad((Integer.parseInt(edad)));
                                        cabeza.setRaza((String) overview_table.getValueAt(2,1));
                                        cabeza.setID_Num(ID_Num);
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, cabeza.getID_Num() +" Modificada correctamente");

                                        File datos_cambiados = new File("Cabezas_temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+cabeza.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> ganado = Arrays.asList(datos.get(0).split(","));
                                            if (ganado.getLast().equals(cabeza.getID_Num())){
                                                try {
                                                    csv.exportData(cabeza.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Cabezas_de_Ganado.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Cabezas_de_Ganado1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/Cabezas_temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Cabezas_de_Ganado.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Cabezas_de_Ganado1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });

                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        File datos_cambiados = new File("Cabezas_temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+cabeza.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> ganado = Arrays.asList(datos.get(0).split(","));
                                            if (!ganado.get(4).equals(cabeza.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Cabezas_de_Ganado.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Cabezas_de_Ganado1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/Cabezas_temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Cabezas_de_Ganado.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Cabezas_de_Ganado1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerCabezaGanado(cabeza);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;


            case 2:
                Seccion seccion;
                List<String> seccion_finca = new ArrayList<>();
                for (int i = 0; i < finca.getSecciones().size(); i++) {
                    seccion_finca.add(finca.getSecciones().get(i).getSeccion()+"-"+finca.getSecciones().get(i).getID_Num());
                }
                JComboBox SeccionField = new JComboBox<>(seccion_finca.toArray());
                 button = new JButton("Revisar seccion");
                b = new JLabel("Seleccione que seccion quiere revisar");

                Object[][] seccion_overview = new Object[5][2];
                header = new String[]{"Atributo", "Valor"};

                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                SeccionField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(SeccionField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar seccion individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getSecciones().size(); j++) {
                            if (SeccionField.toString().contains(finca.getSecciones().get(j).getID_Num())) {
                                Seccion seccion = finca.getSecciones().get(j);
                                seccion_overview[0][0] = "Nombre: ";
                                seccion_overview[0][1] = seccion.getSeccion();
                                seccion_overview[1][0] = "Tamaño con medida";
                                seccion_overview[1][1] = String.valueOf(seccion.getTamaño()) + seccion.getMedida();
                                seccion_overview[2][0] = "Funcion";
                                seccion_overview[2][1] = seccion.getFuncion();
                                seccion_overview[3][0] = "Estado";
                                seccion_overview[3][1] = seccion.getEstado();
                                seccion_overview[4][0] = "Cabezas";
                                seccion_overview[4][1] = seccion.getCabezas();

                                TableModel model = new DefaultTableModel(seccion_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, seccion_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,100);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar seccion " + finca.getSecciones().get(j).getSeccion());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover seccion "+ finca.getSecciones().get(j).getSeccion());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,seccion_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        seccion.setSeccion((String) overview_table.getValueAt(0,1));
                                        seccion.setFuncion((String) overview_table.getValueAt(2,1));
                                        seccion.setEstado((String) overview_table.getValueAt(3,1));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Seccion: " + seccion.getID_Num() + " Modificada correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+seccion.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> seccion_read = Arrays.asList(datos.get(0).split(","));
                                            if (seccion_read.getLast().equals(seccion.getID_Num())){
                                                try {
                                                    csv.exportData(seccion.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Secciones.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Secciones1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Secciones.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Secciones1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });

                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                    File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+seccion.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> seccion_read = Arrays.asList(datos.get(0).split(","));
                                            if (!seccion_read.getLast().equals(seccion.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Secciones.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Secciones1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Secciones.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Secciones1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerSeccion(seccion);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;
            case 3:
                List<String> suministradores_finca = new ArrayList<>();
                for (int i = 0; i < finca.getSuministradores().size(); i++) {
                    suministradores_finca.add(finca.getSuministradores().get(i).getNombre()+"-"+finca.getSuministradores().get(i).getID_Num());
                }
                JComboBox SuministradorField = new JComboBox<>(suministradores_finca.toArray());
                button = new JButton("Revisar suministrador");
                b = new JLabel("Seleccione que suministrador quiere revisar");

                Object[][] suministrador_overview = new Object[4][2];
                header = new String[]{"Atributo", "Valor"};

                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                SuministradorField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(SuministradorField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar suministrador individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getSuministradores().size(); j++) {
                            if (SuministradorField.toString().contains(finca.getSuministradores().get(j).getID_Num())) {
                                Suministrador suministrador = finca.getSuministradores().get(j);
                                suministrador_overview[0][0] = "Nombre: ";
                                suministrador_overview[0][1] = suministrador.getNombre();
                                suministrador_overview[1][0] = "Producto";
                                suministrador_overview[1][1] = suministrador.getProducto();
                                suministrador_overview[2][0] = "Precio";
                                suministrador_overview[2][1] = suministrador.getPrecio();
                                suministrador_overview[3][0] = "Dias de espera";
                                suministrador_overview[3][1] = suministrador.getDias_espera();

                                TableModel model = new DefaultTableModel(suministrador_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, suministrador_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,200);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar Suministrador " + finca.getSuministradores().get(j).getNombre());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover Suministrador"+ finca.getSuministradores().get(j).getNombre());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,suministrador_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        suministrador.setNombre((String) overview_table.getValueAt(0,1));
                                        suministrador.setProducto((String) overview_table.getValueAt(1,1));
                                        String precio = String.valueOf(overview_table.getValueAt(2,1));
                                        suministrador.setPrecio(Integer.parseInt(precio));
                                        String Dias_espera = String.valueOf(overview_table.getValueAt(3,1));
                                        suministrador.setDias_espera(Integer.parseInt(Dias_espera));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Seccion: " + suministrador.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+suministrador.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> suministrador_read = Arrays.asList(datos.get(0).split(","));
                                            if (suministrador_read.getLast().equals(suministrador.getID_Num())){
                                                try {
                                                    csv.exportData(suministrador.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Suministradores.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Suministradores1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Suministradores.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Suministradores1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });

                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Seccion: " + suministrador.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+suministrador.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> suministrador_read = Arrays.asList(datos.get(0).split(","));
                                            if (!suministrador_read.getLast().equals(suministrador.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Suministradores.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Suministradores1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Suministradores.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Suministradores1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerSuministrador(suministrador);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;
            case 4:
                List<String> suministros_finca = new ArrayList<>();
                for (int i = 0; i < finca.getSuministros().size(); i++) {
                    suministros_finca.add(finca.getSuministros().get(i).getNombre()+"-"+finca.getSuministros().get(i).getID_Num());
                }
                JComboBox SuministroField = new JComboBox<>(suministros_finca.toArray());
                 button = new JButton("Revisar suministro");
                b = new JLabel("Seleccione que suministro quiere revisar");

                Object[][] suministro_overview = new Object[4][2];
                header = new String[]{"Atributo", "Valor"};

                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                SuministroField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(SuministroField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar suministro individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getSuministros().size(); j++) {
                            if (SuministroField.toString().contains(finca.getSuministros().get(j).getID_Num())) {
                                Suministro suministro = finca.getSuministros().get(j);
                                suministro_overview[0][0] = "Nombre: ";
                                suministro_overview[0][1] = suministro.getNombre();
                                suministro_overview[1][0] = "Tipo";
                                suministro_overview[1][1] = suministro.getTipo();
                                suministro_overview[2][0] = "Stock";
                                suministro_overview[2][1] = suministro.getCantidad();
                                suministro_overview[3][0] = "Dias desde compra";
                                suministro_overview[3][1] = suministro.getDiasDesdeCompra();

                                TableModel model = new DefaultTableModel(suministro_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, suministro_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,200);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar suministro " + finca.getSuministros().get(j).getNombre());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover suministro"+ finca.getSuministros().get(j).getNombre());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,suministro_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        suministro.setNombre((String) overview_table.getValueAt(0,1));
                                        suministro.setTipo((String) overview_table.getValueAt(1,1));
                                        String cantidad = String.valueOf(overview_table.getValueAt(2,1));
                                        suministro.setCantidad(Integer.parseInt(cantidad));
                                        String DiasDesdeCompra = String.valueOf(overview_table.getValueAt(2,1));
                                        suministro.setDiasDesdeCompra(Integer.parseInt(DiasDesdeCompra));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Suministro: " + suministro.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+suministro.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> suministro_read = Arrays.asList(datos.get(0).split(","));
                                            if (suministro_read.getLast().equals(suministro.getID_Num())){
                                                try {
                                                    csv.exportData(suministro.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Suministros.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Suministros1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Suministros.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Suministros1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });

                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Suministro: " + suministro.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+suministro.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> suministro_read = Arrays.asList(datos.get(0).split(","));
                                            if (!suministro_read.getLast().equals(suministro.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Suministros.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Suministros1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Suministros.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Suministros1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerSuministro(suministro);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;
            case 5:
                List<String> cosechas_finca = new ArrayList<>();
                for (int i = 0; i < finca.getCosechas().size(); i++) {
                    cosechas_finca.add(finca.getCosechas().get(i).getTipo()+"-"+finca.getCosechas().get(i).getID_Num());
                }
                JComboBox CosechaField = new JComboBox<>(cosechas_finca.toArray());
                 button = new JButton("Revisar cosecha");
                b = new JLabel("Seleccione que cosecha quiere revisar");

                Object[][] cosecha_overview = new Object[5][2];
                header = new String[]{"Atributo", "Valor"};

                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                CosechaField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(CosechaField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar cosecha individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        for (int j = 0; j < finca.getCosechas().size(); j++) {
                            if (CosechaField.toString().contains(finca.getCosechas().get(j).getID_Num())) {
                                Cosecha cosecha = finca.getCosechas().get(j);
                                cosecha_overview[0][0] = "Nombre: ";
                                cosecha_overview[0][1] = cosecha.getTipo();
                                cosecha_overview[1][0] = "Tamaño con medida";
                                cosecha_overview[1][1] = String.valueOf(cosecha.getTamaño()) + cosecha.getMedida();
                                cosecha_overview[2][0] = "Estado";
                                cosecha_overview[2][1] = cosecha.getEstado();
                                cosecha_overview[3][0] = "Epoca";
                                cosecha_overview[3][1] = cosecha.getEpoca();
                                cosecha_overview[4][0] = "Geografia";
                                cosecha_overview[4][1] = cosecha.getGeografia();

                                TableModel model = new DefaultTableModel(cosecha_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, cosecha_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,200);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar cosecha " + finca.getCosechas().get(j).getTipo());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover cosecha "+ finca.getCosechas().get(j).getTipo());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,cosecha_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        cosecha.setTipo((String) overview_table.getValueAt(0,1));
                                        cosecha.setEstado((String) overview_table.getValueAt(2,1));
                                        cosecha.setEpoca((String) overview_table.getValueAt(3,1));
                                        cosecha.setGeografia((String) overview_table.getValueAt(4,1));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Cosecha: " + cosecha.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+cosecha.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> cosecha_read = Arrays.asList(datos.get(0).split(","));
                                            if (cosecha_read.getLast().equals(cosecha.getID_Num())){
                                                try {
                                                    csv.exportData(cosecha.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Cosechas.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Cosechas1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Cosechas.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Cosechas1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });
                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Cosecha: " + cosecha.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+cosecha.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> cosecha_read = Arrays.asList(datos.get(0).split(","));
                                            if (!cosecha_read.getLast().equals(cosecha.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Cosechas.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Cosechas1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Cosechas.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Cosechas1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerCosecha(cosecha);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;
            case 6:
                List<String> trabajadores_finca = new ArrayList<>();
                for (int i = 0; i < finca.getTrabajadores().size(); i++) {
                    trabajadores_finca.add(finca.getTrabajadores().get(i).getNombre()+"-"+finca.getTrabajadores().get(i).getID_Num());
                }
                JComboBox TrabajadorField = new JComboBox<>(trabajadores_finca.toArray());
                 button = new JButton("Revisar Trabajador");
                b = new JLabel("Seleccione que trabajador quiere revisar");

                Object[][] trabajador_overview = new Object[5][2];
                header = new String[]{"Atributo", "Valor"};


                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                TrabajadorField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(TrabajadorField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar trabajador individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getTrabajadores().size(); j++) {
                            if (TrabajadorField.toString().contains(finca.getTrabajadores().get(j).getID_Num())) {
                                Trabajador trabajador = finca.getTrabajadores().get(j);
                                trabajador_overview[0][0] = "Nombre: ";
                                trabajador_overview[0][1] = trabajador.getNombre();
                                trabajador_overview[1][0] = "Rol";
                                trabajador_overview[1][1] = trabajador.getRol();
                                trabajador_overview[2][0] = "Salario";
                                trabajador_overview[2][1] = trabajador.getSueldo();
                                trabajador_overview[3][0] = "Telefono";
                                trabajador_overview[3][1] = trabajador.getTelefono();
                                trabajador_overview[4][0] = "Tareas asignadas";
                                trabajador_overview[4][1] = trabajador.getTareas().size();

                                Object[][] task_overview = new Object[trabajador.getTareas().size()][3];

                                TableModel model = new DefaultTableModel(trabajador_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, trabajador_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,200);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar Trabajador " + finca.getTrabajadores().get(j).getNombre());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover trabajador "+ finca.getTrabajadores().get(j).getNombre());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,trabajador_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        trabajador.setNombre((String) overview_table.getValueAt(0,1));
                                        trabajador.setRol((String) overview_table.getValueAt(1,1));
                                        String Sueldo = String.valueOf(overview_table.getValueAt(2,1));
                                        trabajador.setSueldo(Double.parseDouble(Sueldo));
                                        trabajador.setTelefono((String) overview_table.getValueAt(3,1));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Trabajador: " + trabajador.getID_Num() + " Modificado correctamente");


                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+trabajador.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> trabajador_read = Arrays.asList(datos.get(0).split(","));
                                            if (trabajador_read.getLast().equals(trabajador.getID_Num())){
                                                try {
                                                    csv.exportData(trabajador.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Trabajadores.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Trabajadores1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Trabajadores.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Trabajadores1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });
                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Trabajador: " + trabajador.getID_Num() + " Modificado correctamente");


                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+trabajador.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> trabajador_read = Arrays.asList(datos.get(0).split(","));
                                            if (!trabajador_read.getLast().equals(trabajador.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Trabajadores.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Trabajadores1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Trabajadores.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Trabajadores1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerTrabajador(trabajador);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;

            case 7:
                List<String> contactos_finca = new ArrayList<>();
                for (int i = 0; i < finca.getContactos().size(); i++) {
                    contactos_finca.add(finca.getContactos().get(i).getNombre()+"-"+finca.getContactos().get(i).getID_Num());
                }
                JComboBox ContactosField = new JComboBox<>(contactos_finca.toArray());
                 button = new JButton("Revisar Contacto");
                b = new JLabel("Seleccione que contacto quiere revisar");

                Object[][] contacto_overview = new Object[5][2];
                header = new String[]{"Atributo", "Valor"};

                font = new Font("Raleway",Font.PLAIN,16);
                font12 = new Font("Raleway",Font.PLAIN,12);
                datos.setFont(font12);
                button.setFont(font);
                b.setFont(font);
                ContactosField.setFont(font12);

                RevisarIndividual.add(datos, BorderLayout.CENTER);
                RevisarIndividual.add(button, BorderLayout.PAGE_END);
                RevisarIndividual.add(b,BorderLayout.NORTH);
                RevisarIndividual.add(ContactosField, BorderLayout.NORTH);

                setContentPane(RevisarIndividual);
                setTitle("Revisar trabajador individual");
                setSize(500,200);
                setLocationRelativeTo(null);
                setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < finca.getContactos().size(); j++) {
                            if (ContactosField.toString().contains(finca.getContactos().get(j).getID_Num())) {
                                Contacto contacto = finca.getContactos().get(j);
                                contacto_overview[0][0] = "Nombre: ";
                                contacto_overview[0][1] = contacto.getNombre();
                                contacto_overview[1][0] = "Tipo de contacto";
                                contacto_overview[1][1] = contacto.getTipo_de_contacto();
                                contacto_overview[2][0] = "Direccion";
                                contacto_overview[2][1] = contacto.getDireccion();
                                contacto_overview[3][0] = "Correo";
                                contacto_overview[3][1] = contacto.getCorreo();
                                contacto_overview[4][0] = "Telefono";
                                contacto_overview[4][1] = contacto.getTelefono();

                                TableModel model = new DefaultTableModel(contacto_overview,header);
                                JTable overview_table = new JTable(model);
                                overview_table.setPreferredScrollableViewportSize(new Dimension(600, contacto_overview.length*30));
                                overview_table.setFont(font12);
                                overview_table.setRowHeight(30);
                                overview_table.setSize(400,200);
                                JScrollPane scrollPane = new JScrollPane(overview_table);
                                JButton modificar = new JButton("Modificar Contacto " + finca.getContactos().get(j).getNombre());
                                modificar.setFont(font);
                                RevisarIndividual.add(scrollPane);
                                RevisarIndividual.add(modificar, BorderLayout.PAGE_END);

                                JButton remover = new JButton("Remover Contacto "+ finca.getContactos().get(j).getNombre());
                                remover.setFont(font);
                                RevisarIndividual.add(remover,BorderLayout.PAGE_END);

                                JButton regresar = new JButton("Regresar");
                                regresar.setFont(font);
                                RevisarIndividual.add(regresar,BorderLayout.PAGE_END);
                                setSize(700,contacto_overview.length*30 + 150);

                                modificar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        contacto.setNombre((String) overview_table.getValueAt(0,1));
                                        contacto.setTipo_de_contacto((String) overview_table.getValueAt(1,1));
                                        contacto.setDireccion((String) overview_table.getValueAt(2,1));
                                        contacto.setCorreo((String) overview_table.getValueAt(3,1));
                                        contacto.setTelefono((String) overview_table.getValueAt(4,1));
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Contacto: " + contacto.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+contacto.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> contacto_read = Arrays.asList(datos.get(0).split(","));
                                            if (contacto_read.getLast().equals(contacto.getID_Num())){
                                                try {
                                                    csv.exportData(contacto.getDatos(), datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }else {
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Contactos.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Contactos1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Contactos.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Contactos1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        dispose();
                                    }
                                });
                                regresar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                    }
                                });

                                remover.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(RevisarIndividual.this, "Contacto: " + contacto.getID_Num() + " Modificado correctamente");

                                        File datos_cambiados = new File("temp.csv");
                                        Scanner sc1 = null;
                                        try {
                                            sc1 = new Scanner(new File(finca.getFincaPath()+"/"+contacto.getFile()));
                                        } catch (FileNotFoundException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        sc1.useDelimiter(",");
                                        while (sc1.hasNext()){
                                            List<String> datos = new ArrayList<String>();
                                            datos.add(sc1.nextLine());
                                            List<String> contacto_read = Arrays.asList(datos.get(0).split(","));
                                            if (!contacto_read.getLast().equals(contacto.getID_Num())){
                                                try {
                                                    csv.exportData(datos.get(0),datos_cambiados,finca);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            }
                                        }
                                        File datos_pasados = new File(finca.getFincaPath()+"/Contactos.csv");
                                        datos_pasados.renameTo(new File(finca.getFincaPath()+"/Contactos1.csv"));
                                        datos_cambiados = new File(finca.getFincaPath()+"/temp.csv");
                                        try {
                                            Files.move(datos_cambiados.toPath(), Path.of(finca.getFincaPath() + "/Contactos.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        try {
                                            Files.deleteIfExists(Paths.get(finca.getFincaPath()+"/Contactos1.csv"));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        finca.removerContacto(contacto);
                                        dispose();
                                    }
                                });
                            }
                        }
                    }
                });
                break;

            default:
                break;
        }
    }
}