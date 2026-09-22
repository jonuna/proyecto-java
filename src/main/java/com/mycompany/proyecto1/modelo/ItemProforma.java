
package com.mycompany.proyecto1.modelo;

import java.util.Locale;

public class ItemProforma {
    private Producto producto;
    private int cantidad;
    private double descuentoXciento;

    public ItemProforma(Producto producto, int cantidad) {
        this(producto, cantidad, 0.0);
    }

    public ItemProforma(Producto producto, int cantidad, double descuentoXciento) {
        setProducto(producto);
        setCantidad(cantidad);
        setDescuentoXciento(descuentoXciento);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (!producto.getActivo()) {
            throw new IllegalArgumentException("No se puede agregar un producto inactivo");
        }
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.cantidad = cantidad;
    }

    public double getDescuentoXciento() {
        return descuentoXciento;
    }

    public void setDescuentoXciento(double descuentoXciento) {
        if (!Double.isFinite(descuentoXciento)) {
            throw new IllegalArgumentException("El descuento debe ser un numero finito");
        }
        if (descuentoXciento < 0.0 || descuentoXciento > 100.0) {
            throw new IllegalArgumentException("El descuento debe estar entre cero y cien");
        }
        this.descuentoXciento = descuentoXciento;
    }

    public double calcularSubtotal() {
        if (!producto.getActivo()) {
            throw new IllegalArgumentException("No se puede calcular un producto inactivo");
        }

        double precioConDescuento = producto.getPrecio() * (1 - descuentoXciento / 100);
        double subTotal = precioConDescuento * cantidad;

        if (!Double.isFinite(subTotal)) {
            throw new IllegalArgumentException("El subtotal excede el rango permitido");
        }
        return subTotal;
    }

    // calcular el impuesto sobre el subtotal descontado

    public double calcularImpuesto() {
        return calcularSubtotal() * producto.getImpuesto() / 100;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularImpuesto();
    }

    public String resumen() {
        return String.format(
                Locale.US,
                "%d x %s - descuento %.1f%% - subtotal $%.2f - impuesto $%.2f - total $%.2f",
                cantidad,
                producto.getNombre(),
                descuentoXciento,
                calcularSubtotal(),
                calcularImpuesto(),
                calcularTotal());
    }
}
