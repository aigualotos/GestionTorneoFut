package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.ActaPartido;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActaPartidoTest {

    @Test
    public void testCrearActaPartido() {
        ActaPartido acta = new ActaPartido(1, new ArrayList<>(Arrays.asList("15'", "42'")), 3, 1);

        assertEquals(1, acta.getIdActa());
        assertEquals(2, acta.getMinutosGoles().size());
        assertEquals(3, acta.getTarjetasAmarillas());
        assertEquals(1, acta.getTarjetasRojas());
    }

    @Test
    public void testListaGolesIniciaVacia() {
        ActaPartido acta = new ActaPartido();
        assertNotNull(acta.getMinutosGoles());
        assertTrue(acta.getMinutosGoles().isEmpty());
    }

    @Test
    public void testRegistrarEventosYReporte() {
        ActaPartido acta = new ActaPartido(10, new ArrayList<>(), 1, 0);
        acta.registrarEventos("78'", 2, 1);

        assertEquals(1, acta.getMinutosGoles().size());
        assertEquals("78'", acta.getMinutosGoles().get(0));
        assertEquals(3, acta.getTarjetasAmarillas());
        assertEquals(1, acta.getTarjetasRojas());

        acta.generarReporte();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTarjetasAmarillasNoNegativas() {
        ActaPartido acta = new ActaPartido();
        acta.setTarjetasAmarillas(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTarjetasRojasNoNegativas() {
        ActaPartido acta = new ActaPartido();
        acta.setTarjetasRojas(-2);
    }
}