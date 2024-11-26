package ListaDoble;
import Heroes.Heroe;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


                    //Crear y agregar el nuevo heroe
                    Heroe nuevoHeroe = new Heroe(id, nombreH, superH,"Mision X", ingresosH);
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
                NodoDoble nodo = lis.buscarListaDoble(id);
                if (nodo == null) {
                    JOptionPane.showMessageDialog(null, "Heroe no encontrado");
                } else {
                    Heroe heroe = nodo.dato;
                    JOptionPane.showMessageDialog(null, "Heroe encontrado:\n" +
                            "ID: " + heroe.getId() + "\n" +
                            "Nombre: " + heroe.getNombre() + "\n" +
                            "Superpoder: " + heroe.getSuperpoder() + "\n" +
                            "Pago mensual: " + heroe.getPagoMensual());
                }

            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    lis.mostrarListaDoble(textArea1);
                }catch (Exception ex){
                    throw new RuntimeException(ex);
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
