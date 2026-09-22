package com.mycompany.proyecto1.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Proforma {
    private String numero;
    private Cliente cliente;
    private final List<ItemProforma> items = new ArrayList<>();

    public Proforma(String numero, Cliente cliente) {
        setNumero(numero);
        setCliente(cliente);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El numero no puede estar vacio");
        }
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El campo cliente no puede ser null");
        }
        this.cliente = cliente;
    }

    public List<ItemProforma> getItems() {
        return List.copyOf(items);
    }

    public void agregarItem(ItemProforma item) {
        if (item == null) {
            throw new IllegalArgumentException("El item no puede ser null");
        }
        if (!item.getProducto().getActivo()) {
            throw new IllegalArgumentException("El producto debe estar activo");
        }
        items.add(item);
    }

    public double calcularSubtotal() {
        return items.stream().mapToDouble(ItemProforma::calcularSubtotal).sum();
    }

    public double calcularImpuesto() {
        return items.stream().mapToDouble(ItemProforma::calcularImpuesto).sum();
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(ItemProforma::calcularTotal).sum();
    }

    public String resumen() {
        String unidad = items.size() == 1 ? "item" : "items";
        return String.format(
                Locale.US,
                "Proforma %s - %s - %d %s - $%.2f",
                numero,
                cliente.getNombre(),
                items.size(),
                unidad,
                calcularTotal());
    }
}
