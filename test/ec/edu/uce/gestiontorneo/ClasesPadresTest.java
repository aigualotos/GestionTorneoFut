package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.EntidadCompetitiva;
import ec.edu.uce.gestiontorneo.modelo.RegistroDisciplinario;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClasesPadresTest {
    @Test
    public void testRegistroDisciplinarioSobrecargaYEquals() {
        RegistroDisciplinario r1 = new RegistroDisciplinario(15);
        RegistroDisciplinario r2 = new RegistroDisciplinario(15);
        RegistroDisciplinario r3 = new RegistroDisciplinario(20);

        assertTrue(r1.mismoId(r2));
        assertTrue(r1.mismoId(15));
        assertFalse(r1.mismoId(r3));
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, r3);
    }

    @Test
    public void testEntidadCompetitivaSobrecargaYEquals() {
        EntidadCompetitiva c1 = new EntidadCompetitiva(1, "Club Central");
        EntidadCompetitiva c2 = new EntidadCompetitiva(1, "Club Central");
        EntidadCompetitiva destino = new EntidadCompetitiva();

        destino.asignarNombre(c1);
        assertEquals("Club Central", destino.getNombre());
        assertTrue(c1.mismaEntidad(c2));
        assertTrue(c1.mismaEntidad(1, "Club Central"));
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}