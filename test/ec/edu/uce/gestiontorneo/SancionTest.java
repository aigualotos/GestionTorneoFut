package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.Sancion;
import org.junit.Test;
import static org.junit.Assert.*;

public class SancionTest {

    @Test
    public void testCrearSancion() {
        Sancion sancion = new Sancion(101, "Doble amarilla", 1, true);
        assertEquals(101, sancion.getIdSancion());
        assertEquals("Doble amarilla", sancion.getMotivo());
        assertEquals(1, sancion.getPartidosSuspension());
        assertTrue(sancion.isEstadoSancion());
    }

    @Test
    public void testModificarEstadoSancion() {
        Sancion sancion = new Sancion(101, "Tarjeta roja", 2, true);
        sancion.setEstadoSancion(false);
        assertFalse(sancion.isEstadoSancion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartidosSuspensionNoNegativos() {
        Sancion sancion = new Sancion();
        sancion.setPartidosSuspension(-2); // Debe lanzar excepción
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMotivoNoVacio() {
        Sancion sancion = new Sancion();
        sancion.setMotivo(""); // Vacío debe lanzar excepción
    }
}