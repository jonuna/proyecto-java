
package com.mycompany.proyecto1.modelo;

public abstract class Cliente {
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String email;
    protected boolean activo;

    public Cliente(String nombre, String direccion, String telefono, String email,
            boolean activo) {
        setNombre(nombre);
        setDireccion(direccion);
        setTelefono(telefono);
        setEmail(email);
        setActivo(activo);
    }

    protected String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = textoObligatorio(nombre, "El campo no puede estar vacio");
    }

    protected String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = textoOpcional(direccion, "El campo no puede estar vacio");
    }

    protected String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = textoOpcional(telefono, "El campo no puede estar vacio");
    }

    protected String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailNormalizado = textoOpcional(email, "El email no puede ser null");
        int arroba = emailNormalizado.lastIndexOf('@');
        boolean dominioValido = arroba >= 1
                && arroba < emailNormalizado.length() - 1
                && emailNormalizado.substring(arroba + 1).contains(".");
        if (!emailNormalizado.isEmpty() && !dominioValido) {
            throw new IllegalArgumentException("El email no tiene un formato valido");
        }
        this.email = emailNormalizado;
    }

    protected boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public abstract double calcularDescuento(double montoTotal);

    public abstract String resumen();

    private static String textoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    private static String textoOpcional(String valor, String mensaje) {
        if (valor == null) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }
}
