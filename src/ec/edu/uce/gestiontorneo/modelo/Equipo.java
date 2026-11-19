package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo extends EntidadCompetitiva {
    private String escudo;

    // Relación 1 a muchos (Agregación): Lista de jugadores que conforman la plantilla del equipo
    private List<Jugador> plantilla;

    public Equipo() {
        super();
        this.escudo = "Sin escudo";
        this.plantilla = new ArrayList<>();
    }

    public Equipo(int idEquipo, String nombreEquipo, String escudo) {
        super(idEquipo, nombreEquipo);
        this.escudo = escudo;
        this.plantilla = new ArrayList<>();
    }

    public Equipo(int idEquipo, String nombreEquipo) {
        this(idEquipo, nombreEquipo, "Sin escudo");
    }

    // Métodos de negocio del diagrama de clases
    public void registrarEquipo() {
        System.out.println("-> Club " + this.nombre + " registrado en el sistema exitosamente.");
    }

    public void consultarPlantilla() {
        System.out.println("\n--- PLANTILLA DEL EQUIPO: " + this.nombre + " ---");
        if (plantilla.isEmpty()) {
            System.out.println("El equipo aún no tiene jugadores registrados.");
        } else {
            for (Jugador j : plantilla) {
                System.out.println("Dorsal [" + j.getDorsal() + "] - " + j.getNombre() + " (" + j.getPosicion() + ")");
            }
        }
    }

    public void agregarJugador(Jugador jugador) {
        if (jugador == null) {
            System.out.println("[Error] El jugador no puede ser nulo.");
            return;
        }
        if (plantilla.size() < 25) {
            plantilla.add(jugador);
            System.out.println("Jugador " + jugador.getNombre() + " añadido a la plantilla.");
        } else {
            System.out.println("[Error] No se puede agregar. El equipo ya alcanzó el límite de 25 jugadores.");
        }
    }

    public void agregarJugador(List<Jugador> jugadores) {
        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("[Error] La lista de jugadores está vacía o es nula.");
            return;
        }
        for (Jugador jugador : jugadores) {
            agregarJugador(jugador);
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

    public void eliminarJugador(Jugador jugador) {
        if (jugador == null) {
            System.out.println("[Error] El jugador no puede ser nulo.");
            return;
        }
        boolean removido = plantilla.remove(jugador);
        if (removido) {
            System.out.println("Jugador " + jugador.getNombre() + " eliminado de la plantilla.");
        } else {
            System.out.println("[Error] No se encontró ese jugador en este equipo.");
        }
    }

    // Getters y Setters
    public int getIdEquipo() { return getId(); }
    public void setIdEquipo(int idEquipo) { setId(idEquipo); }

    public String getNombreEquipo() { return getNombre(); }
    public void setNombreEquipo(String nombreEquipo) { asignarNombre(nombreEquipo); }

    public String getEscudo() { return escudo; }
    public void setEscudo(String escudo) { this.escudo = escudo; }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Jugador>
    public List<Jugador> getPlantilla() { return plantilla; }
    public void setPlantilla(List<Jugador> plantilla) {
        this.plantilla = (plantilla != null) ? plantilla : new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Equipo)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Equipo other = (Equipo) obj;
        return Objects.equals(this.escudo, other.escudo)
                && Objects.equals(this.plantilla, other.plantilla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), escudo, plantilla);
    }

    @Override
    public String toString() {
        return "Equipo [ID=" + id +
                ", Nombre=" + nombre +
                ", Escudo/Desc=" + escudo +
                ", Jugadores Inscritos=" + plantilla.size() + "]";
    }
}