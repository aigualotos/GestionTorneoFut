package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.Equipo;
import ec.edu.uce.gestiontorneo.modelo.Jugador;
import org.junit.Test;
import static org.junit.Assert.*;

public class EquipoTest {
    @Test
    public void testCrearEquipo() {
        Equipo equipo = new Equipo(1, "Liga de Quito", "escudo.png");
        assertEquals(1, equipo.getIdEquipo());
        assertEquals("Liga de Quito", equipo.getNombreEquipo());
        assertNotNull(equipo.getEscudo());
    }

    @Test
    public void testModificarNombreEquipo() {
        Equipo equipo = new Equipo(1, "Liga de Quito", "escudo.png");
        // Cambia el nombre y verifica que el setter y getter funcionen bien
        equipo.setNombreEquipo("Aucas");
        assertEquals("Aucas", equipo.getNombreEquipo());
    }

    @Test
    public void testPlantillaIniciaVacia() {
        Equipo equipo = new Equipo(1, "Liga de Quito", "escudo.png");
        // Asegura que al crear el equipo, su lista de jugadores no sea nula y esté vacía
        assertNotNull(equipo.getPlantilla());
        assertTrue(equipo.getPlantilla().isEmpty());
    }

    @Test
    public void testAgregarJugadorAPlantilla() {
        // Crea un jugador de prueba, lo añade al equipo y verifica que la lista crezca
        Jugador nuevoJugador = new Jugador(1, "Carlos", 10, "Portero");
        Equipo equipo = new Equipo(1, "Liga de Quito", "escudo.png");
        equipo.agregarJugador(nuevoJugador);

        assertEquals(1, equipo.getPlantilla().size());
        assertEquals("Carlos", equipo.getPlantilla().get(0).getNombre());
    }
}