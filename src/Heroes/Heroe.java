package Heroes;

//La clase heroe almacenara la informacion de los heroes
public class Heroe {
    private String id;
    private String nombre;
    private String superpoder;
    //Objeto mision para que vayan ligados
    private Mision mision;
    private double pagoMensual;

    public Heroe(String id, String nombre, String superpoder, Mision mision, double pagoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.superpoder = superpoder;
        this.mision = mision;
        this.pagoMensual = pagoMensual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSuperpoder() {
        return superpoder;
    }

    public void setSuperpoder(String superpoder) {
        this.superpoder = superpoder;
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }
}
