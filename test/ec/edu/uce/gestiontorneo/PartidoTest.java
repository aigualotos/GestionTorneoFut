package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.Partido;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartidoTest {

    @Test
    public void testCrearPartido() {
        Partido partido = new Partido(1, "2026-11-15 15:00", "Programado", 0, 0);

        assertEquals(1, partido.getIdPartido());
        assertEquals("2026-11-15 15:00", partido.getFechaHora());
        assertEquals("Programado", partido.getEstado());
        assertEquals(0, partido.getMarcadorLocal());
        assertEquals(0, partido.getMarcadorVisita());
    }

    @Test
    public void testFlujoCompletoPartido() {
        Partido partido = new Partido();
        partido.setIdPartido(5);

        partido.programarPartido("2026-11-20 19:30");
        assertEquals("Programado", partido.getEstado());
        assertEquals("2026-11-20 19:30", partido.getFechaHora());

        partido.registrarMarcador(2, 1);
        assertEquals(2, partido.getMarcadorLocal());
        assertEquals(1, partido.getMarcadorVisita());

        partido.finalizarPartido();
        assertEquals("Finalizado", partido.getEstado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarcadorLocalNoNegativo() {
        Partido partido = new Partido();
        partido.setMarcadorLocal(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarcadorVisitaNoNegativo() {
        Partido partido = new Partido();
        partido.setMarcadorVisita(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFechaHoraNoVacia() {
        Partido partido = new Partido();
        partido.setFechaHora("   ");
    }
}