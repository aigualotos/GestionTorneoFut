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
}