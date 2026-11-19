package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador extends RegistroDisciplinario {
    private String nombre;
    private int dorsal;
    private String posicion;

    // Relación 1 a 1 (Asociación): Equipo al que pertenece el jugador
    private Equipo equipo;

    // Relación 1 a muchos (Agregación): Lista de sanciones asociadas al jugador
    private List<Sancion> sanciones;

    public Jugador() {
        super();
        this.nombre = "Sin nombre";
        this.dorsal = 0;
        this.posicion = "Sin posición";
        this.sanciones = new ArrayList<>();
    }

    public Jugador(int idJugador, String nombre, int dorsal, String posicion) {
        super(idJugador);
        setNombre(nombre);
        setDorsal(dorsal);
        this.posicion = posicion;
        this.sanciones = new ArrayList<>();
    }

    public Jugador(int idJugador, String nombre) {
        this(idJugador, nombre, 0, "Sin posición");
    }

    public int getIdJugador() {
        return getId();
    }

    public void setIdJugador(int idJugador) {
        setId(idJugador);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o en blanco.");
        }
        this.nombre = nombre;
    }

    public void setNombre(Jugador otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        setNombre(otro.nombre);
    }

    public int getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(int dorsal) {
        if (dorsal < 0) {
            throw new IllegalArgumentException("El dorsal no puede ser menor a cero.");
        }
        this.dorsal = dorsal;
    }

    public void setDorsal(Jugador otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        setDorsal(otro.dorsal);
    }

    public String getPosicion() {
        return this.posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setPosicion(Jugador otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }
        setPosicion(otro.posicion);
    }

    // Getter y Setter de la relación 1 a 1 (Asociación) con Equipo
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Sancion>
    public List<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSanciones(List<Sancion> sanciones) {
        this.sanciones = (sanciones != null) ? sanciones : new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Jugador)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Jugador other = (Jugador) obj;
        return this.dorsal == other.dorsal
                && Objects.equals(this.nombre, other.nombre)
                && Objects.equals(this.posicion, other.posicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombre, dorsal, posicion);
    }

    @Override
    public String toString() {
        return "Jugador [ID=" + getId() +
                ", Nombre=" + nombre +
                ", Dorsal=" + dorsal +
                ", Posición=" + posicion + "]";
    }
}