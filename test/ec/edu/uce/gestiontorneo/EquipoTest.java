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

    @Test
    public void testSobrecargaAgregarYEliminarJugadorPorObjeto() {
        Equipo equipo = new Equipo(1, "Liga de Quito", "escudo.png");
        Jugador j1 = new Jugador(10, "Jugador Uno", 9, "Delantero");
        Jugador j2 = new Jugador(11, "Jugador Dos", 8, "Volante");

        equipo.agregarJugador(java.util.Arrays.asList(j1, j2));
        assertEquals(2, equipo.getPlantilla().size());

        equipo.eliminarJugador(j1);
        assertEquals(1, equipo.getPlantilla().size());
        assertEquals("Jugador Dos", equipo.getPlantilla().get(0).getNombre());
    }

    @Test
    public void testEqualsYHashCodeEquipo() {
        Equipo e1 = new Equipo(1, "Liga de Quito", "escudo.png");
        Equipo e2 = new Equipo(1, "Liga de Quito", "escudo.png");
        Equipo e3 = new Equipo(2, "Aucas", "otro.png");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1, e3);
        assertNotEquals(e1, null);
    }
}