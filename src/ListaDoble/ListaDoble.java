package ListaDoble;

import Heroes.Heroe;

import javax.swing.*;

public class ListaDoble {
    public NodoDoble ini;
    public NodoDoble fin;
    public int tam;

    public ListaDoble() {
        ini = null;
        fin = null;
        tam = 0;
    }
    public void mostrarListaDoble(JTextArea textArea){
        if (ini == null){
            textArea.setText("La lista esta vacia");
            return;
        }else {
            StringBuilder listaStr = new StringBuilder("Lista de Héroes: \n");
            NodoDoble actual = ini;
            while (actual != null){
                //Modificacion para que construya los atributos del heroe
                listaStr.append("ID: ").append(actual.dato.getId())
                        .append(", Nombre: ").append(actual.dato.getNombre())
                        .append(", Superpoder: ").append(actual.dato.getSuperpoder())
                        .append(", Pago mensual: ").append(actual.dato.getPagoMensual())
                        .append("\n");
                actual = actual.sig;
            }
            /*
            REVISAR LUEGO
            //Impresion inversa
            listaStr.append("Lista en orden inverso:\n");
            actual = fin;
            while (actual != null){
                listaStr.append(actual.dato)
                        .append("");
                actual = actual.ant;
            }
            l             */
            textArea.setText(listaStr.toString());
        }
    }
    //Actualizar
    public void actualizarListaDoble(JTextArea textArea){
        mostrarListaDoble(textArea);
    }

    public void ordenarListaDoble(JTextArea textArea){
        if (ini == null || ini.sig == null){
            JOptionPane.showMessageDialog(null, "La lista ya está ordenada o está vacía.");
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            NodoDoble actual = ini;
            while (actual.sig != null){
                if (actual.dato.getPagoMensual() > actual.sig.dato.getPagoMensual()){
                    Heroe temp = actual.dato;
                    actual.dato = actual.sig.dato;
                    actual.sig.dato = temp;
                    swapped = true;
                }
                actual = actual.sig;
            }
        }while (swapped);
        actualizarListaDoble(textArea);
        JOptionPane.showMessageDialog(null, "La lista ha sido ordenada por pago mensual.");

    }

    public NodoDoble buscarListaDoble(String id){
        NodoDoble actual = ini;
        while (actual != null){
            if (actual.dato.getId().equals(id)){
                return actual;
            }
            actual = actual.sig;
        }
        return null;
    }

    public boolean eliminarListaDoble(String id, JTextArea textArea){
        if (ini == null){
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
            return false;
        }

        NodoDoble actual=ini;
        while (actual!= null && !actual.dato.getId().equals(id)){
            actual = actual.sig;
        }

        if (actual == null){ //elemento no sea encontrado
            JOptionPane.showMessageDialog(null, "Heroe no encontrado");
            return false;
        }
        if (actual == ini){
            ini  = ini.sig;
            if (ini != null){
                ini.ant = null;
            }
        }else if (actual== fin){
            fin=fin.ant;
            if (fin!=null){
                fin.sig = null;
            }
        }else {
            //Verificar que no queden sueltos
            actual.ant.sig = actual.sig;
            actual.sig.ant = actual.ant;
        }
        tam --;
        actualizarListaDoble(textArea);
        return true;
    }

    public void agregarListaDoble(Heroe heroe, JTextArea textArea){
        NodoDoble nuevo = new NodoDoble(heroe);
        if (ini == null){
            ini = fin = nuevo;
        }else {
            fin.sig = nuevo;
            nuevo.ant = fin;
            fin = nuevo;
        }
        tam++;
        mostrarListaDoble(textArea);
        actualizarListaDoble(textArea);

    }


}
