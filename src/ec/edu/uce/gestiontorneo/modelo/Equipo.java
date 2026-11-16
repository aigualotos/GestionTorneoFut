package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int idEquipo;
    private String nombreEquipo;
    private String escudo;
    private List<Jugador> plantilla;

    public Equipo() {
        this.plantilla = new ArrayList<>();
    }

    public Equipo(int idEquipo, String nombreEquipo, String escudo) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.escudo = escudo;
        this.plantilla = new ArrayList<>();
    }

    // Métodos de negocio del diagrama de clases
    public void registrarEquipo() {
        System.out.println("-> Club " + this.nombreEquipo + " registrado en el sistema exitosamente.");
    }

    public void consultarPlantilla() {
        System.out.println("\n--- PLANTILLA DEL EQUIPO: " + this.nombreEquipo + " ---");
        if (plantilla.isEmpty()) {
            System.out.println("El equipo aún no tiene jugadores registrados.");
        } else {
            for (Jugador j : plantilla) {
                System.out.println("Dorsal [" + j.getDorsal() + "] - " + j.getNombre() + " (" + j.getPosicion() + ")");
            }
        }
    }

    public void agregarJugador(Jugador jugador) {
        if (plantilla.size() < 25) {
            plantilla.add(jugador);
            System.out.println("Jugador " + jugador.getNombre() + " añadido a la plantilla.");
        } else {
            System.out.println("[Error] No se puede agregar. El equipo ya alcanzó el límite de 25 jugadores.");
        }
    }

    public void eliminarJugador(int idJugador) {
        boolean removido = plantilla.removeIf(j -> j.getIdJugador() == idJugador);
        if (removido) {
            System.out.println("Jugador eliminado de la plantilla.");
        } else {
            System.out.println("[Error] No se encontró un jugador con ese ID en este equipo.");
        }
    }

    // Getters y Setters
    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }
    public String getEscudo() { return escudo; }
    public void setEscudo(String escudo) { this.escudo = escudo; }
    public List<Jugador> getPlantilla() { return plantilla; }
}