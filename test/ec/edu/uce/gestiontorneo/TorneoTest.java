package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.Torneo;
import org.junit.Test;
import static org.junit.Assert.*;

public class TorneoTest {

    @Test
    public void testCrearYObtenerDatosTorneo() {
        // Actualizado: pasamos un entero para el número de equipos (ej. 8)
        Torneo torneo = new Torneo(1, "LigaPro UCE", 8, "Reglamento oficial");

        assertEquals(1, torneo.getIdTorneo());
        assertEquals("LigaPro UCE", torneo.getNombre());
        assertEquals(8, torneo.getNumeroEquipos());
        assertEquals("Reglamento oficial", torneo.getReglamento());
    }

    @Test
    public void testValidacionDatosInvalidosTorneo() {
        Torneo torneo = new Torneo();

        // Validamos que rechace IDs negativos, nombres vacíos y número de equipos inválidos (<= 1)
        assertFalse(torneo.setIdTorneo(-5));
        assertFalse(torneo.setNombre("   "));
        assertFalse(torneo.setNumeroEquipos(1)); // Una liga no puede tener 1 o 0 equipos
    }

    @Test
    public void testMetodosTorneo() {
        Torneo torneo = new Torneo();

        torneo.crearTorneo();
        torneo.configurarReg();
    }

    @Test
    public void testSobrecargaSettersYConfigurarConObjetoTorneo() {
        Torneo origen = new Torneo(1, "LigaPro UCE", 8, "Reglamento oficial");
        Torneo destino = new Torneo();

        assertTrue(destino.setNombre(origen));
        assertTrue(destino.setNumeroEquipos(origen));
        assertTrue(destino.setReglamento(origen));
        destino.configurarReg(origen);

        assertEquals("LigaPro UCE", destino.getNombre());
        assertEquals(8, destino.getNumeroEquipos());
        assertEquals("Reglamento oficial", destino.getReglamento());
    }

    @Test
    public void testEqualsYHashCodeTorneo() {
        Torneo t1 = new Torneo(1, "LigaPro UCE", 8, "Reglamento oficial");
        Torneo t2 = new Torneo(1, "LigaPro UCE", 8, "Reglamento oficial");
        Torneo t3 = new Torneo(2, "Copa Quito", 6, "Reglamento base");

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, null);
    }
}