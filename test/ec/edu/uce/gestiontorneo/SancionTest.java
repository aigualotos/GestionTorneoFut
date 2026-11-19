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

    @Test
    public void testSobrecargaSettersConObjetoSancion() {
        Sancion origen = new Sancion(101, "Doble amarilla", 2, true);
        Sancion destino = new Sancion();

        destino.setMotivo(origen);
        destino.setPartidosSuspension(origen);
        destino.setEstadoSancion(origen);

        assertEquals("Doble amarilla", destino.getMotivo());
        assertEquals(2, destino.getPartidosSuspension());
        assertTrue(destino.isEstadoSancion());
    }

    @Test
    public void testEqualsYHashCodeSancion() {
        Sancion s1 = new Sancion(101, "Tarjeta roja", 2, true);
        Sancion s2 = new Sancion(101, "Tarjeta roja", 2, true);
        Sancion s3 = new Sancion(102, "Juego brusco", 1, false);

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s3);
        assertNotEquals(s1, null);
    }
}