package ListaSimple;

import Avengers.YoungAvenger;

import javax.swing.*;

public class ListaSimple {
    public Nodo ini;
    public int tam;

    public ListaSimple() {
        ini = null;
        tam = 0;
    }

    //Metodo para mostra lista
    public void mostrarLista(JTextArea textArea) {
        if (ini == null) {
            textArea.setText("La lista esta vacia");
            return;
        } else {
            StringBuilder listaStr = new StringBuilder("Lista de Young Avengers: \n");
            Nodo actual = ini;
            while (actual != null) {
                //Modificacion para que construya los atributos del heroe
                YoungAvenger youngAvenger = actual.dato;
                listaStr.append(" Codigo ").append(actual.dato.getCodigo())
                        .append(", Nombre: ").append(actual.dato.getNombre())
                        .append(", Poder Especial: ").append(actual.dato.getPoderEspecial())
                        .append(", Nivel de habilidad: ").append(actual.dato.getNivelhabilidad())
                        .append(", Misión: ").append(youngAvenger.getMisionAct() != null ? youngAvenger.getMisionAct() : "Ninguna")
                        .append("\n\n");
                actual = actual.sig;
            }
            textArea.setText(listaStr.toString());
        }
    }

    //Actualizar
    public void actualizarLista(JTextArea textArea) {
        mostrarLista(textArea);
    }

    //Ordenar la lista mediante metodo burbuja
    public void ordenarLista(JTextArea textArea) {
        //Verificar si esta vacio o tiene un solo elemento
        if (ini == null || ini.sig == null) {
            JOptionPane.showMessageDialog(null, "La lista ya está ordenada o está vacía.");
            return;
        }
        //Detectar intercambios
        boolean swapped;
        do {
            swapped = false;
            Nodo actual = ini;
            //Recorrer la lista
            while (actual.sig != null) {
                if (actual.dato.getNivelhabilidad() > actual.sig.dato.getNivelhabilidad()) {
                    //Intercambiar los datos
                    YoungAvenger temp = actual.dato;
                    actual.dato = actual.sig.dato;
                    actual.sig.dato = temp;
                    swapped = true;
                }
                actual = actual.sig;
            }
        } while (swapped);
        actualizarLista(textArea);
        JOptionPane.showMessageDialog(null, "La lista ha sido ordenada por Código mediante método burbuja");
    }

    //Buscar el nodo mediante codigo
    public Nodo buscarHeroePorCodigo(int codigo) {
        Nodo actual = ini;
        while (actual != null) {
            if (actual.dato.getCodigo() == codigo) {
                // Devolver el nodo si se encuentra el héroe
                return actual;
            }
            actual = actual.sig;
        }
        return null;
    }

    //Agregar young Avenger a la lista
    public void agregarLista(YoungAvenger youngAvenger, JTextArea textArea) {
        //Verificacion del ID ya existe
        if (buscarHeroePorCodigo(youngAvenger.getCodigo()) != null) {
            JOptionPane.showMessageDialog(null, "Error: El código ya existe. Ingrese un código único.");
            return;
        }

        Nodo nuevo = new Nodo(youngAvenger);
        if (ini == null) {
            ini = nuevo;
        } else {
            Nodo actual = ini;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;

        }
        tam++;
        mostrarLista(textArea);
        actualizarLista(textArea);

    }

    public int contarMisionesPorPoderEspecial(Nodo nodo, String poderEspecial) {
        if (nodo == null) {
            // Caso base: si no hay más nodos, el conteo es 0
            return 0;
        }

        // Evaluar si el héroe actual tiene el poder especial seleccionado
        YoungAvenger heroe = nodo.dato;
        int conteoActual;
        if (poderEspecial.equalsIgnoreCase(heroe.getPoderEspecial())) {
            conteoActual = 1;
        } else {
            conteoActual = 0;
        }
        // Llamada recursiva para el siguiente nodo
        return conteoActual + contarMisionesPorPoderEspecial(nodo.sig, poderEspecial);
    }


}
