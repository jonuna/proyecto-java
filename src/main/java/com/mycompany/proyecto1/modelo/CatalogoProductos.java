package com.mycompany.proyecto1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogoProductos {

    private List<Producto> listaProductos;
    private Set<String> codigosRegistrados;

    public CatalogoProductos() {
        // Aplicación de genéricos para definir los tipos de datos
        this.listaProductos = new ArrayList<>();
        this.codigosRegistrados = new HashSet<>();
    }

    // AGREGAR
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        // Evita duplicados utilizando la colección Set
        if (codigosRegistrados.contains(producto.getCodigo())) {
            System.out.println(
                    " [!] Error: El producto con código " + producto.getCodigo() + " ya existe en el catálogo.");
            return;
        }

        listaProductos.add(producto);
        codigosRegistrados.add(producto.getCodigo());
        System.out.println(" [+] Producto agregado: " + producto.getNombre());
    }

    // BUSCAR
    public Producto buscarProducto(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null; // Retorna null si no lo encuentra
    }

    // LISTAR
    public List<Producto> listarProductos() {
        return new ArrayList<>(listaProductos); // Se retorna una copia para proteger la colección original
    }

    // ACTUALIZAR
    public boolean actualizarProducto(String codigoViejo, Producto productoActualizado) {
        Producto existente = buscarProducto(codigoViejo);

        if (existente != null) {
            // Validar que el nuevo código no colisione con otro producto ya existente
            if (!codigoViejo.equals(productoActualizado.getCodigo())
                    && codigosRegistrados.contains(productoActualizado.getCodigo())) {
                System.out.println(" [!] Error: El nuevo código ya está en uso por otro producto.");
                return false;
            }

            int index = listaProductos.indexOf(existente);
            listaProductos.set(index, productoActualizado);

            // Actualizar el Set de control de duplicados
            codigosRegistrados.remove(codigoViejo);
            codigosRegistrados.add(productoActualizado.getCodigo());

            System.out.println(" [~] Producto actualizado correctamente.");
            return true;
        }
        System.out.println(" [!] Error: Producto a actualizar no encontrado.");
        return false;
    }

    // ELIMINAR
    public boolean eliminarProducto(String codigo) {
        Producto existente = buscarProducto(codigo);
        if (existente != null) {
            listaProductos.remove(existente);
            codigosRegistrados.remove(codigo);
            System.out.println(" [-] Producto eliminado: " + codigo);
            return true;
        }
        System.out.println(" [!] Error: Producto a eliminar no encontrado.");
        return false;
    }

}
