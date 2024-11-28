package ListaSimple;

import Avengers.YoungAvenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaSimpleGUI {
    private JPanel pGeneral;
    private JTextField textField1;
    private JButton agregarButton;
    private JButton ordenarButton;
    private JButton modificarButton;
    private JButton mostrarButton;
    private JTextArea textArea1;
    private JComboBox poderEspecial;
    private JComboBox nivelHabilidad;
    private JComboBox misionActiva;
    private JTextArea textArea2;
    private JButton contarMisionesButton;

    ListaSimple lis = new ListaSimple();

    public ListaSimpleGUI() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el texto ingresado y eliminar espacios en blanco
                String input = textField1.getText().trim();

                // Validar si el campo está vacío
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, ingrese un código para el Young Avenger.",
                            "Campo vacío",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return; // Salir del método
                }

                // Validar si el valor ingresado no es un número entero
                int codigo;
                try {
                    codigo = Integer.parseInt(input); // Intentar convertir a entero
                    //Validamos que no sean letras
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, ingrese un número válido para el código del Young Avenger.",
                            "Valor inválido",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Validar si el código ya existe
                if (lis.buscarHeroePorCodigo(codigo) != null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "El código ingresado ya existe. Por favor, ingrese un código único.",
                            "Código duplicado",
                            JOptionPane.WARNING_MESSAGE
                    );
                    textField1.setText(""); // Limpiar el campo de texto
                    return;
                }

                // Solicitar datos adicionales
                String nombreH = JOptionPane.showInputDialog("Ingrese el nombre del Héroe");
                if (nombreH == null || nombreH.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Debe ingresar un nombre válido."
                    );
                    return;
                }

                // Obtener poder seleccionado a partir de los combo box
                String superH = (String) poderEspecial.getSelectedItem();
                int nivelH = nivelHabilidad.getSelectedIndex() + 1; // Convertir a nivel (1-5)
                String misionAc = (String) misionActiva.getSelectedItem();

                // Crear y agregar el nuevo héroe
                YoungAvenger nuevoHeroe = new YoungAvenger(codigo, nombreH, superH, nivelH, misionAc);
                lis.agregarLista(nuevoHeroe, textArea1);
                textField1.setText(""); // Limpiar el campo de texto

            }
        });

        //Ordenar lista doble
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lis.ordenarLista(textArea2);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Buscar por codigo y modificador
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, ingrese un codigo para buscar."
                    );
                    return;
                }
                try {
                    //Convertir a entero
                    int codigo = Integer.parseInt(id);
                    //Buscar nodo con el heroe
                    Nodo nodo = lis.buscarHeroePorCodigo(codigo);

                    if (nodo == null) { // Si no se encuentra el héroe
                        JOptionPane.showMessageDialog(
                                null,
                                "YoungAvenger con codigo: " + id + " no encontrado."
                        );
                        return; // Salir de la acción si no se encuentra el ID
                    }

                    // Continuar con la modificación si el YoungAvenger existe
                    YoungAvenger heroe = nodo.dato;

                    //Mostrar confirmacion para que el usuario modifique
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "Young Avenger encontrado: " + heroe.getNombre() +
                                    "\n¿Desea modificar los datos del Young Avenger?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        String nuevoNombre = JOptionPane.showInputDialog(
                                "Nombre actual: " + heroe.getNombre() + "\nNuevo nombre:",
                                heroe.getNombre()
                        );

                        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                            heroe.setNombre(nuevoNombre.trim());
                        }

                        //Traemos los combobox definidos con los valores que se encuentran actualmente
                        poderEspecial.setSelectedItem(heroe.getPoderEspecial());
                        nivelHabilidad.setSelectedIndex(heroe.getNivelhabilidad() - 1);
                        misionActiva.setSelectedItem(heroe.getMisionAct());

                        //Mostramoos un JOptionPane con Combobox
                        Object[] opciones = {
                                "Poder Especial:", poderEspecial,
                                "Nivel de Habilidad:", nivelHabilidad,
                                "Misión Activa:", misionActiva
                        };
                        int resultado = JOptionPane.showConfirmDialog(
                                null,
                                opciones,
                                "Modificar Atributos",
                                JOptionPane.OK_CANCEL_OPTION
                        );

                        //Modificacion
                        if (resultado == JOptionPane.OK_OPTION) {
                            // Guardar los valores seleccionados
                            String nuevoPoder = (String) poderEspecial.getSelectedItem();
                            int nuevoNivel = nivelHabilidad.getSelectedIndex() + 1;
                            String nuevaMision = (String) misionActiva.getSelectedItem();

                            heroe.setPoderEspecial(nuevoPoder);
                            heroe.setNivelhabilidad(nuevoNivel);
                            heroe.setMisionAct(nuevaMision);

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Datos del Young Avenger actualizados correctamente."
                            );
                            lis.mostrarLista(textArea1); // Refrescar la lista mostrada
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "El codigo ingresado no es valido"
                            );
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "El código ingresado no es válido. Por favor, ingrese un número."
                    );
                }
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Combobox con los poderes especiales - Forma2
                JComboBox<String> habilidadComboBox = new JComboBox<>(new String[]{
                        "Teletransportacion",
                        "Manipulacion_Energia",
                        "Magia",
                        "Super_Fuerza",
                        "Arqueria"
                });

                // Mostrar un diálogo para seleccionar el poder especial
                int result = JOptionPane.showConfirmDialog(
                        null,
                        habilidadComboBox,
                        "Seleccione el poder especial para filtrar",
                        JOptionPane.OK_CANCEL_OPTION
                );

                // Procesar si el usuario presiona OK
                if (result == JOptionPane.OK_OPTION) {
                    String habilidadSeleccionada = (String) habilidadComboBox.getSelectedItem();

                    if (habilidadSeleccionada != null && !habilidadSeleccionada.trim().isEmpty()) {
                        // Filtrar héroes que no tienen el poder especial seleccionado
                        StringBuilder resultado = new StringBuilder("Young Avengers SIN el poder especial: " + habilidadSeleccionada + "\n");
                        Nodo actual = lis.ini; // Inicio de la lista

                        while (actual != null) {
                            YoungAvenger heroe = actual.dato;
                            if (!heroe.getPoderEspecial().equalsIgnoreCase(habilidadSeleccionada)) {
                                resultado.append("Código: ").append(heroe.getCodigo())
                                        .append(", Nombre: ").append(heroe.getNombre())
                                        .append(", Poder: ").append(heroe.getPoderEspecial())
                                        .append(", Nivel: ").append(heroe.getNivelhabilidad())
                                        .append(", Misión: ").append(heroe.getMisionAct())
                                        .append("\n");
                            }
                            actual = actual.sig; // Avanzar al siguiente nodo
                        }

                        // Mostrar el resultado en textArea2
                        if (resultado.length() == 0) {
                            textArea2.setText("No se encontraron Young Avengers SIN ese poder especial.");
                        } else {
                            textArea2.setText(resultado.toString());
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Debe seleccionar un poder especial válido."
                        );
                    }
                }
            }
        });
        contarMisionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el poder especial seleccionado del comboBox
                String poderSeleccionado = (String) poderEspecial.getSelectedItem();

                if (poderSeleccionado == null || poderSeleccionado.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un poder especial válido.");
                    return;
                }

                // Llamar al método recursivo para contar las misiones asociadas al poder especial
                int conteo = lis.contarMisionesPorPoderEspecial(lis.ini, poderSeleccionado);

                // Mostrar el resultado en el TextArea o en un JOptionPane
                String mensaje = "Total de misiones para el poder especial '" + poderSeleccionado + "': " + conteo;
                textArea2.setText(mensaje); // Muestra en el TextArea
                JOptionPane.showMessageDialog(null, mensaje); // O muestra un diálogo
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListaSimpleGUI");
        frame.setContentPane(new ListaSimpleGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Configura el JFrame a pantalla completa
        frame.setUndecorated(false); // Cambia a true si deseas ocultar los bordes de la ventana
        frame.setVisible(true);
    }


}
