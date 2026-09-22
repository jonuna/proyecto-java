package com.mycompany.proyecto1;

import com.mycompany.proyecto1.modelo.CatalogoProductos;
import com.mycompany.proyecto1.modelo.Producto;
import com.mycompany.proyecto1.modelo.muestraTomadaCasa;
import com.mycompany.proyecto1.modelo.muestraTomadaLab;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("      SISTEMA DE GESTIÓN - CATÁLOGO DE PRODUCTOS         ");
        System.out.println("=========================================================");
        System.out.println();

        CatalogoProductos catalogo = new CatalogoProductos();

        // 1. AGREGAR PRODUCTOS
        System.out.println(">>> 1. AGREGANDO PRODUCTOS <<<");
        Producto p1 = new muestraTomadaCasa("BM-001", "Biologia molecular", "gen-s", 80.0, 15.0, true, 5.6, "PCK6318",
                "sangre");
        Producto p2 = new muestraTomadaLab("M-001", "Microbiologia", "Stafilococcus Aerus", 20.0, 15.0, true, "esputo");
        Producto p3 = new muestraTomadaLab("M-002", "Hemograma", "Conteo sanguineo", 15.0, 12.0, true, "sangre");

        catalogo.agregarProducto(p1);
        catalogo.agregarProducto(p2);
        catalogo.agregarProducto(p3);

        // Prueba de bloqueo de duplicados
        System.out.println("\n>>> PRUEBA: AGREGAR DUPLICADO <<<");
        Producto pDuplicado = new muestraTomadaLab("M-001", "Cultivo Extra", "Intento de duplicado", 25.0, 12.0, true,
                "saliva");
        catalogo.agregarProducto(pDuplicado);

        // 2. LISTAR PRODUCTOS
        System.out.println("\n>>> 2. LISTANDO CATÁLOGO <<<");
        for (Producto p : catalogo.listarProductos()) {
            System.out.println(p.resumen());
        }

        // 3. BUSCAR PRODUCTO
        System.out.println("\n>>> 3. BUSCANDO PRODUCTO (BM-001) <<<");
        Producto encontrado = catalogo.buscarProducto("BM-001");
        if (encontrado != null) {
            System.out.println("Encontrado exitosamente: " + encontrado.resumen());
        }

        // 4. ACTUALIZAR PRODUCTO
        System.out.println("\n>>> 4. ACTUALIZANDO PRODUCTO (M-002) <<<");
        Producto pActualizado = new muestraTomadaLab("M-002", "Hemograma Completo", "Conteo sanguineo detallado", 18.0,
                12.0, true, "sangre");
        catalogo.actualizarProducto("M-002", pActualizado);

        System.out.println("Catálogo tras actualización:");
        catalogo.listarProductos().forEach(p -> System.out.println(p.resumen()));

        // 5. ELIMINAR PRODUCTO
        System.out.println("\n>>> 5. ELIMINANDO PRODUCTO (M-001) <<<");
        catalogo.eliminarProducto("M-001");

        System.out.println("\nCatálogo Final:");
        catalogo.listarProductos().forEach(p -> System.out.println(p.resumen()));
    }
}
