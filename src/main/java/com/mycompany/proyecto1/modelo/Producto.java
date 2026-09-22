package com.mycompany.proyecto1.modelo;

import java.util.Locale;

public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private double impuestoXciento;
    private boolean activo;

    // con (Atl + insert) se despliega una pantalla donde puede hacer un constructor
    // solo haciendo un clic
    // este es un constructor
    public Producto(String codigo, String nombre) {
        this(codigo, nombre, "", 0.0, 0.0, true);
    }

    // este es un constructor que utiliza los metodos setters y getters
    public Producto(String codigo, String nombre, String descripcion, double precio, double impuestoXciento,
            boolean activo) {
        setCodigo(codigo);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setImpuesto(impuestoXciento);
        setActivo(activo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = textoObligatorio(codigo, "El campo no puede estar vacio");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = textoObligatorio(nombre, "El campo no puede estar vacio");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = textoOpcional(descripcion, "La descripcion no puede ser null");
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (!Double.isFinite(precio)) {
            throw new IllegalArgumentException("El precio debe ser un numero finito");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negaivo");
        }
        this.precio = precio;
    }

    public double getImpuesto() {
        return impuestoXciento;
    }

    public void setImpuesto(double impuestoXciento) {
        if (!Double.isFinite(impuestoXciento)) {
            throw new IllegalArgumentException("El precio debe ser un numero finito");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negaivo");
        }
        this.impuestoXciento = impuestoXciento;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String resumen() {
        String estado = activo ? "activo" : "inactivo";
        return String.format(
                Locale.US, "[%s] %s - $%.2f (impuesto %.1f%%) - %s", codigo, nombre, precio, impuestoXciento, estado);
    }

    protected static String textoObligatorio(String valor, String mensaje) {
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
