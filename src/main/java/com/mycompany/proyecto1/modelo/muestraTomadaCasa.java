
package com.mycompany.proyecto1.modelo;

public class muestraTomadaCasa extends Producto {
    private double kilometraje;
    private String placa_vehiculo;
    private String tipoMuestra;

    public muestraTomadaCasa(
            String codigo,
            String nombre,
            double kilometraje,
            String placa_vehiculo,
            String tipoMuestra) {
        super(codigo, nombre);
        setKilometraje(kilometraje);
        setPlaca_vehiculo(placa_vehiculo);
        setTipoMuestra(tipoMuestra);
    }

    public muestraTomadaCasa(
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            double impuestoXciento,
            boolean activo,
            double kilometraje,
            String placa_vehiculo,
            String tipoMuestra) {
        super(codigo, nombre, descripcion, precio, impuestoXciento, activo);
        setKilometraje(kilometraje);
        setPlaca_vehiculo(placa_vehiculo);
        setTipoMuestra(tipoMuestra);
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        if (!Double.isFinite(kilometraje) || kilometraje <= 0) {
            throw new IllegalArgumentException("El kilometraje debe ser positivo y finito");
        }
        this.kilometraje = kilometraje;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = super.textoObligatorio(placa_vehiculo, "Colocar la placa del vehiculo");
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = super.textoObligatorio(tipoMuestra, "Se debe especificar el tipo de muestra");
    }

    @Override
    public String resumen() {
        return super.resumen() + "- Tipo de muestra : " + tipoMuestra + "- Placa : " + placa_vehiculo + "- Distancia :"
                + kilometraje;
    }
}
