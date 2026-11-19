package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RelacionesYDuplicadosTest {

    @Test
    public void testRelacionesEntreEntidades() {
        Torneo torneo = new Torneo(1, "Liga UCE", 4, "Reglamento oficial");
        Equipo local = new Equipo(10, "Local FC", "Escudo Local");
        Equipo visita = new Equipo(20, "Visita FC", "Escudo Visita");
        Jugador jugador = new Jugador(100, "Mateo Silva", 9, "Delantero");
        Sancion sancion = new Sancion(50, "Juego brusco", 1, true);
        Partido partido = new Partido(300, "2026-11-20 15:00", "Programado", 0, 0);
        ActaPartido acta = new ActaPartido(400, Arrays.asList("23'"), 1, 0);
        TablaPosiciones tabla = new TablaPosiciones(1, 3, 1, 2, 1);

        jugador.setEquipo(local);
        jugador.setSanciones(Arrays.asList(sancion));
        local.agregarJugador(jugador);

        torneo.setEquipos(Arrays.asList(local, visita));
        torneo.setPartidos(Arrays.asList(partido));

        partido.setEquipoLocal(local);
        partido.setEquipoVisitante(visita);
        partido.setActaPartido(acta);

        acta.setPartido(partido);
        acta.setSanciones(Arrays.asList(sancion));

        sancion.setJugador(jugador);
        sancion.setActaPartido(acta);

        tabla.setEquipo(local);
        tabla.setTorneo(torneo);
        tabla.setEquiposClasificados(Arrays.asList(local, visita));

        assertEquals(local, jugador.getEquipo());
        assertEquals(1, jugador.getSanciones().size());
        assertEquals(1, local.getPlantilla().size());
        assertEquals(2, torneo.getEquipos().size());
        assertEquals(1, torneo.getPartidos().size());
        assertEquals(local, partido.getEquipoLocal());
        assertEquals(visita, partido.getEquipoVisitante());
        assertEquals(acta, partido.getActaPartido());
        assertEquals(partido, acta.getPartido());
        assertEquals(1, acta.getSanciones().size());
        assertEquals(jugador, sancion.getJugador());
        assertEquals(acta, sancion.getActaPartido());
        assertEquals(local, tabla.getEquipo());
        assertEquals(torneo, tabla.getTorneo());
        assertEquals(2, tabla.getEquiposClasificados().size());
    }

    @Test
    public void testEvitarIdsDuplicadosEnListas() {
        List<Equipo> equipos = new ArrayList<>();
        Equipo e1 = new Equipo(1, "Club Uno", "Escudo 1");
        Equipo eDup = new Equipo(1, "Club Repetido", "Escudo 2");

        equipos.add(e1);
        boolean existeId = equipos.stream().anyMatch(e -> e.getIdEquipo() == eDup.getIdEquipo());
        if (!existeId) {
            equipos.add(eDup);
        }

        assertTrue(existeId);
        assertEquals(1, equipos.size());
    }
}