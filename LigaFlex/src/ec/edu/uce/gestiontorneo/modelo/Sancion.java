package ec.edu.uce.gestiontorneo.modelo;

import java.util.Objects;

public class Sancion extends RegistroDisciplinario {
    private String motivo;
    private int partidosSuspension;
    private boolean estadoSancion;

    // Relación 1 a 1 (Asociación): Jugador sancionado
    private Jugador jugador;

    // Relación 1 a 1 (Agregación): Acta de partido donde se originó la sanción
    private ActaPartido actaPartido;

    public Sancion() {
        super();
        this.motivo = "Sin motivo";
        this.partidosSuspension = 0;
        this.estadoSancion = false;
    }

    public Sancion(int idSancion, String motivo, int partidosSuspension, boolean estadoSancion) {
        super(idSancion);
        setMotivo(motivo);
        setPartidosSuspension(partidosSuspension);
        this.estadoSancion = estadoSancion;
    }

    public Sancion(int idSancion, String motivo) {
        this(idSancion, motivo, 1, true);
    }

    public int getIdSancion() {
        return getId();
    }

    public void setIdSancion(int idSancion) {
        setId(idSancion);
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo no puede estar vacío o en blanco.");
        }
        this.motivo = motivo;
    }

    public void setMotivo(Sancion otra) {
        if (otra == null) {
            throw new IllegalArgumentException("La sanción no puede ser nula.");
        }
        setMotivo(otra.motivo);
    }

    public int getPartidosSuspension() {
        return this.partidosSuspension;
    }

    public void setPartidosSuspension(int partidosSuspension) {
        if (partidosSuspension < 0) {
            throw new IllegalArgumentException("Los partidos de suspensión no pueden ser menores a cero.");
        }
        this.partidosSuspension = partidosSuspension;
    }

    public void setPartidosSuspension(Sancion otra) {
        if (otra == null) {
            throw new IllegalArgumentException("La sanción no puede ser nula.");
        }
        setPartidosSuspension(otra.partidosSuspension);
    }

    public boolean isEstadoSancion() {
        return this.estadoSancion;
    }

    public void setEstadoSancion(boolean estadoSancion) {
        this.estadoSancion = estadoSancion;
    }

    public void setEstadoSancion(Sancion otra) {
        if (otra == null) {
            throw new IllegalArgumentException("La sanción no puede ser nula.");
        }
        setEstadoSancion(otra.estadoSancion);
    }

    // Getter y Setter de la relación 1 a 1 (Asociación) con Jugador
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    // Getter y Setter de la relación 1 a 1 (Agregación) con ActaPartido
    public ActaPartido getActaPartido() {
        return actaPartido;
    }

    public void setActaPartido(ActaPartido actaPartido) {
        this.actaPartido = actaPartido;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sancion)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Sancion other = (Sancion) obj;
        return this.partidosSuspension == other.partidosSuspension
                && this.estadoSancion == other.estadoSancion
                && Objects.equals(this.motivo, other.motivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), motivo, partidosSuspension, estadoSancion);
    }

    @Override
    public String toString() {
        return "Sancion [ID=" + getId() +
                ", Motivo=" + motivo +
                ", Partidos Suspensión=" + partidosSuspension +
                ", Activa=" + estadoSancion + "]";
    }
}