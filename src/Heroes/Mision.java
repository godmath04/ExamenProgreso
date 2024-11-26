package Heroes;

public class Mision {
    private String id;
    private String nombreMision;
    private int nivelDificultad;
    private String descripcion;

    public Mision(String id, String nombreMision, int nivelDificultad, String descripcion) {
        this.id = id;
        this.nombreMision = nombreMision;
        this.nivelDificultad = nivelDificultad;
        this.descripcion = descripcion;
    }

    //Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public void setNombreMision(String nombreMision) {
        this.nombreMision = nombreMision;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
