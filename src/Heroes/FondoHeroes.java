package Heroes;

public class FondoHeroes {
    private double totalAportes;

    //Constructor
    public FondoHeroes(double totalAportes) {
        this.totalAportes = 0.0;
    }

    //Metodo principales
    public void registrarAporte(Heroe heroe){
        double aporte = heroe.getPagoMensual() * 0.10;
        totalAportes += aporte;
    }

    public double getTotalAportes(){
        return totalAportes;
    }
}
