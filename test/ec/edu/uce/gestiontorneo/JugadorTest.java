package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.Jugador;
import org.junit.Test;
import static org.junit.Assert.*;

public class JugadorTest {

    @Test
    public void testCrearJugador() {
        Jugador jugador = new Jugador(1, "Carlos Tenorio", 11, "Delantero");
        assertEquals(1, jugador.getIdJugador());
        assertEquals("Carlos Tenorio", jugador.getNombre());
        assertEquals(11, jugador.getDorsal());
        assertEquals("Delantero", jugador.getPosicion());
    }

    @Test
    public void testModificarNombreJugador() {
        Jugador jugador = new Jugador(1, "Carlos Tenorio", 11, "Delantero");
        jugador.setNombre("Alex Aguinaga");
        assertEquals("Alex Aguinaga", jugador.getNombre());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDorsalNoNegativo() {
        Jugador jugador = new Jugador();
        jugador.setDorsal(-5); // Debe lanzar excepción
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNombreNoVacio() {
        Jugador jugador = new Jugador();
        jugador.setNombre("   "); // Espacios en blanco deben lanzar excepción
    }
}