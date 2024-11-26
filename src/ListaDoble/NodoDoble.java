package ListaDoble;

import Heroes.Heroe;

public class NodoDoble {
    //Modificacion del Nodo para que trabaje con objeto Heroe
    public Heroe dato;
    public NodoDoble ant;
    public NodoDoble sig;

    public NodoDoble(Heroe dato) {
        this.dato = dato;
        this.ant = null;
        this.sig = null;
    }


}
