package Avengers;

//La clase heroe almacenara la informacion de los heroes
public class YoungAvenger {
    private int codigo;
    private String nombre;
    private String poderEspecial;
    private int nivelhabilidad;
    private String misionAct;

    public YoungAvenger(int codigo, String nombre, String poderespecial, int nivelhabilidad, String misionAct) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poderEspecial = poderespecial;
        this.nivelhabilidad = nivelhabilidad;
        this.misionAct = misionAct;
    }


    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoderEspecial() {
        return poderEspecial;
    }

    public void setPoderEspecial(String poderEspecial) {
        this.poderEspecial = poderEspecial;
    }

    public int getNivelhabilidad() {
        this.nivelhabilidad = nivelhabilidad;
        return nivelhabilidad;
    }

    public void setNivelhabilidad(int nivelhabilidad) {
        this.nivelhabilidad = nivelhabilidad;
    }

    public String getMisionAct() {
        return misionAct;
    }

    public void setMisionAct(String misionAct) {
        this.misionAct = misionAct;
    }


}
