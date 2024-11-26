package Heroes;

public class Impuesto {

    //Metodo para caluclar el impuesto segun el impuest
    public double calcularImpuesto(double ingresoAnual){
        if (ingresoAnual <= 60000) {
            return 0;
        } else if (ingresoAnual <= 120000) {
            return ((ingresoAnual - 60000) * 0.12);
        } else if (ingresoAnual <= 240000) {
            return (ingresoAnual - 120000) * 0.25;
        } else {
            return (ingresoAnual - 240000) * 0.35;
        }
    }

    //Metodo para calcular el aporte al fondo de heroes
    public double calcularAporteFondoHeroes(double pagoMensual) {
        return pagoMensual * 0.10; // 10% del pago mensual
    };

    // Método para calcular el informe completo
    public String generarInforme(Heroe heroe) {
        double ingresoAnual = heroe.getPagoMensual() * 12;
        double aporteFondoAnual = calcularAporteFondoHeroes(heroe.getPagoMensual()) * 12;
        double impuestoGobierno = calcularImpuesto(ingresoAnual);
        double ingresoNeto = ingresoAnual - aporteFondoAnual - impuestoGobierno;

        return "Nombre del Héroe: " + heroe.getNombre() + "\n" +
                "Superpoder Principal: " + heroe.getSuperpoder() + "\n" +
                "Pago Mensual: $" + heroe.getPagoMensual() + "\n" +
                "Aporte al Fondo de Héroes (anual): $" + aporteFondoAnual + "\n" +
                "Impuesto del Gobierno: $" + impuestoGobierno + "\n" +
                "Pago Neto a Recibir (anual): $" + ingresoNeto + "\n";
    }


}
