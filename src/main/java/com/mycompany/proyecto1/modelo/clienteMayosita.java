package com.mycompany.proyecto1.modelo;

public class clienteMayosita extends Cliente implements Iidentificacion {
    private String ruc;

    public clienteMayosita(String ruc, String nombre, String direccion, String telefono, String email,
            boolean activo) {
        super(nombre, direccion, telefono, email, activo);
        setRuc(ruc);
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        if (ruc == null || ruc.length() != 13) {
            throw new IllegalArgumentException("El RUC debe tener exactamente 13 digitos.");
        }
        this.ruc = ruc.trim();
    }

    @Override
    public String identificacion() {
        return ruc;
    }

    @Override
    public double calcularDescuento(double montoTotal) {
        return montoTotal * 0.15;
    }

    @Override
    public String resumen() {
        String estado = activo ? "activo" : "inactivo";
        return "[" + ruc + "] " + nombre + " - " + direccion + " - " + telefono + " - " + email + " - " + estado;
    }

}
