package Heroes;

public class Impuesto {

    //Metodo para caluclar el impuesto segun el impuest
    public double calcularImpuesto(double ingresoAnual){
        if (ingresoAnual <= 60000) {
            return 0;
        } else if (ingresoAnual <= 120000) {
            return (ingresoAnual - 60000) * 0.12;
        } else if (ingresoAnual <= 240000) {
            return (ingresoAnual - 120000) * 0.25 + 7200;
        } else {
            return (ingresoAnual - 240000) * 0.35 + 37200;
        }
    }

    // Método para calcular el ingreso neto
    public double calcularIngresoNeto(Heroe heroe) {
        double ingresoAnual = heroe.getPagoMensual() * 12;
        double aporteAnual = heroe.getPagoMensual() * 0.10 * 12; // 10% mensual
        double impuesto = calcularImpuesto(ingresoAnual);
        return ingresoAnual - aporteAnual - impuesto;
    }


}
