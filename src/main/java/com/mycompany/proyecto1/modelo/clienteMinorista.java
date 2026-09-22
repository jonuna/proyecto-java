package com.mycompany.proyecto1.modelo;

public class clienteMinorista extends Cliente implements Iidentificacion {
    private String cedula;

    public clienteMinorista(String cedula, String nombre, String direccion, String telefono, String email,
            boolean activo) {
        super(nombre, direccion, telefono, email, activo);
        setCedula(cedula);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            throw new IllegalArgumentException("La cedula debe tener exactamente 10 digitos.");
        }
        this.cedula = cedula.trim();
    }

    @Override
    public String identificacion() {
        return cedula;
    }

    @Override
    public double calcularDescuento(double montoTotal) {
        return montoTotal * 0.05;
    }

    @Override
    public String resumen() {
        String estado = activo ? "activo" : "inactivo";
        return "[" + cedula + "] " + nombre + " - " + direccion + " - " + telefono + " - " + email + " - " + estado;
    }

}
