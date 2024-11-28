package ListaSimple;

import Avengers.YoungAvenger;

public class Nodo {
    //Modificacion del Nodo para que trabaje con objeto YoungAvenger
    public YoungAvenger dato;
    public Nodo sig;

    public Nodo(YoungAvenger dato) {
        this.dato = dato;
        this.sig = null;
    }


}
