
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mycompany.proyecto1.pilaYcola.PilaYCola.ColaProductos;
import com.mycompany.proyecto1.pilaYcola.PilaYCola.PilaProductos;
import com.mycompany.proyecto1.pilaYcola.PilaYCola.RepositorioProductos;
import com.mycompany.proyecto1.modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class tester {
    private Producto pruebaMicrobiologia;
    private Producto pruebaBiologiaMolecular;

    @BeforeEach
    void configurar() {
        pruebaMicrobiologia = new Producto("M-001", "Camp Test");
        pruebaBiologiaMolecular = new Producto("BM-001", "Covid rapida");
    }

    @Test
    void pilaNuevaEstaVacia() {
        PilaProductos pila = new PilaProductos(2);

        assertTrue(pila.estaVacia());
        assertEquals(0, pila.getCantidad());
    }

    @Test
    void pilaRespetaOrdenLifo() {
        PilaProductos pila = new PilaProductos(2);
        pila.apilar(pruebaMicrobiologia);
        pila.apilar(pruebaBiologiaMolecular);

        assertSame(pruebaBiologiaMolecular, pila.desapilar());
        assertSame(pruebaMicrobiologia, pila.desapilar());
        assertTrue(pila.estaVacia());
    }

    @Test
    void pilaInformaDesbordamientoYSubdesbordamiento() {
        PilaProductos pila = new PilaProductos(1);
        pila.apilar(pruebaMicrobiologia);

        assertThrows(IllegalStateException.class, () -> pila.apilar(pruebaBiologiaMolecular));
        pila.desapilar();
        assertThrows(IllegalStateException.class, pila::desapilar);
    }

    @Test
    void colaRespetaOrdenFifoInclusoAlDarLaVuelta() {
        ColaProductos cola = new ColaProductos(2);
        cola.encolar(pruebaMicrobiologia);
        cola.encolar(pruebaBiologiaMolecular);

        assertSame(pruebaMicrobiologia, cola.desencolar());
        Producto pruebaInmunologia = new Producto("I-001", "HLA");
        cola.encolar(pruebaInmunologia);

        assertSame(pruebaBiologiaMolecular, cola.desencolar());
        assertSame(pruebaInmunologia, cola.desencolar());
        assertTrue(cola.estaVacia());
    }

    @Test
    void colaInformaDesbordamientoYSubdesbordamiento() {
        ColaProductos cola = new ColaProductos(1);
        cola.encolar(pruebaMicrobiologia);

        assertThrows(IllegalStateException.class, () -> cola.encolar(pruebaBiologiaMolecular));
        cola.desencolar();
        assertThrows(IllegalStateException.class, cola::desencolar);
    }

    @Test
    void repositorioAgregaYRetiraElProductoMasReciente() {
        RepositorioProductos repositorio = new RepositorioProductos(2);
        repositorio.agregar(pruebaMicrobiologia);
        repositorio.agregar(pruebaBiologiaMolecular);

        assertEquals(2, repositorio.cantidad());
        assertSame(pruebaBiologiaMolecular, repositorio.retirarUltimo());
        assertSame(pruebaMicrobiologia, repositorio.retirarUltimo());
        assertTrue(repositorio.estaVacio());
    }

    @Test
    void estructurasRechazanCapacidadInvalidaYProductosNulos() {
        assertThrows(IllegalArgumentException.class, () -> new PilaProductos(0));
        assertThrows(IllegalArgumentException.class, () -> new ColaProductos(-1));

        PilaProductos pila = new PilaProductos(1);
        ColaProductos cola = new ColaProductos(1);
        assertThrows(IllegalArgumentException.class, () -> pila.apilar(null));
        assertThrows(IllegalArgumentException.class, () -> cola.encolar(null));
    }
}
