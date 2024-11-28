package ListaDoble;

import Heroes.Heroe;

public class Nodo {
    //Modificacion del Nodo para que trabaje con objeto Heroe
    public Heroe dato;
    public Nodo sig;

    public Nodo(Heroe dato) {
        this.dato = dato;
        this.sig = null;
    }


}
