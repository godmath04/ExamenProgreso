package ListaDoble;
import Heroes.Heroe;
import Heroes.Mision;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ListaDobleGUI {
    private JPanel pGeneral;
    private JTextField textField1;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton ordenarButton;
    private JButton buscarButton;
    private JButton mostrarButton;
    private JTextArea textArea1;

     ListaDoble lis = new ListaDoble();

    public ListaDobleGUI() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Validacion que el campo del id no se encuentre vacio
                String id = textField1.getText().trim(); //Eliminar espacio en blanco
                if(id.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID para el héroe");
                    return;
                }

                // Verificar si el ID ya existe
                if (lis.buscarHeroePorId(id) != null) {
                    JOptionPane.showMessageDialog(null, "El ID ingresado ya existe. Por favor ingrese un ID único.");
                    textField1.setText(""); // Limpiar el campo de texto
                    return; // Detener la ejecución si el ID ya existe
                }

                try {
                    //Solicitar datos si el ID es valido
                    String nombreH = JOptionPane.showInputDialog("Ingrese el nombre del Héroe");
                    //Revisar nombre valido
                    if (nombreH == null || nombreH.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
                        return;
                    }
                    //Revisar superpoder sea valido
                    String superH = JOptionPane.showInputDialog("Ingrese el superpoder del Héroe");
                    if (superH == null || superH.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un superpoder válido.");
                        return;
                    }
                    double ingresosH;
                    try {
                        ingresosH = Double.parseDouble(JOptionPane.showInputDialog("Ingrese los ingresos del héroe"));
                        if (ingresosH < 0) {
                            JOptionPane.showMessageDialog(null, "El ingreso debe ser un número positivo.");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico válido para los ingresos.");
                        return;
                    }
                    //Agregar bloque para expandir la mision
                    String idMision = JOptionPane.showInputDialog("Ingrese el ID de la Mision");
                    String nombreMision = JOptionPane.showInputDialog("Ingrese el nombre de la misión: ");
                    int nivelDificultad;
                    try {
                        nivelDificultad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nivel de dificultad (1-5)"));
                        if (nivelDificultad < 1 || nivelDificultad > 5) {
                            JOptionPane.showMessageDialog(null, "El nivel de dificultad debe estar entre 1 y 5.");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido para el nivel de dificultad.");
                        return;
                    }
                    String descripcion = JOptionPane.showInputDialog("Ingrese una descripción para la misión");

                    //Crear y agregar el nuevo heroe
                    Mision nuevaMision = new Mision(idMision, nombreMision, nivelDificultad, descripcion);
                    Heroe nuevoHeroe = new Heroe(id, nombreH, superH, nuevaMision, ingresosH);
                    lis.agregarListaDoble(nuevoHeroe, textArea1);
                    textField1.setText("");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                try {
                    lis.eliminarListaDoble(id, textArea1);
                    textField1.setText("");
                }catch (Exception ex){
                    throw new RuntimeException(ex);

                }

            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    lis.ordenarListaDoble(textArea1);
                }catch (Exception ex){
                    throw  new RuntimeException(ex);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID para buscar.");
                    return;
                }

                NodoDoble nodo = lis.buscarHeroePorId(id); // Método que buscará el nodo con el héroe
                if (nodo == null) {
                    JOptionPane.showMessageDialog(null, "Héroe con ID: " + id + " no encontrado.");
                } else {
                    Heroe heroe = nodo.dato;

                    // Mostrar los datos actuales del héroe y pedir nuevas entradas
                    String nuevoNombre = JOptionPane.showInputDialog("Nombre actual: " + heroe.getNombre() + "\nNuevo nombre:", heroe.getNombre());
                    if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) nuevoNombre = heroe.getNombre();

                    String nuevoSuperpoder = JOptionPane.showInputDialog("Superpoder actual: " + heroe.getSuperpoder() + "\nNuevo superpoder:", heroe.getSuperpoder());
                    if (nuevoSuperpoder == null || nuevoSuperpoder.trim().isEmpty()) nuevoSuperpoder = heroe.getSuperpoder();

                    //String nuevaMision = JOptionPane.showInputDialog("Misión actual: " + heroe.getMisionId() + "\nNueva misión:", heroe.getMisionId());
                    //if (nuevaMision == null || nuevaMision.trim().isEmpty()) nuevaMision = heroe.getMisionId();

                    String nuevoPagoMensualStr = JOptionPane.showInputDialog("Pago mensual actual: $" + heroe.getPagoMensual() + "\nNuevo pago mensual:", heroe.getPagoMensual());
                    double nuevoPagoMensual;
                    try {
                        nuevoPagoMensual = Double.parseDouble(nuevoPagoMensualStr.trim());
                        if (nuevoPagoMensual <= 0) throw new NumberFormatException("El pago debe ser mayor a 0.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida para el pago mensual. No se actualizará.");
                        nuevoPagoMensual = heroe.getPagoMensual();
                    }

                    // Actualizar datos del héroe
                    heroe.setNombre(nuevoNombre);
                    heroe.setSuperpoder(nuevoSuperpoder);
                    //heroe.setMisionId(nuevaMision);
                    heroe.setPagoMensual(nuevoPagoMensual);

                    JOptionPane.showMessageDialog(null, "Datos del héroe actualizados correctamente.");
                    lis.mostrarListaDoble(textArea1); // Refrescar la lista mostrada


                }
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lis.mostrarInformeCompleto(textArea1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al generar el informe: " + ex.getMessage());
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ListaDobleGUI");
        frame.setContentPane(new ListaDobleGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
