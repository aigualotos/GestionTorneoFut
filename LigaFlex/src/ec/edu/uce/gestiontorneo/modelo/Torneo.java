package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Torneo extends EntidadCompetitiva {
    private int numeroEquipos;
    private String reglamento;

    // Relación 1 a muchos (Agregación): Lista de equipos inscritos en el torneo
    private List<Equipo> equipos;

    // Relación 1 a muchos (Agregación): Lista de partidos programados en el torneo
    private List<Partido> partidos;

    public Torneo() {
        super();
        this.numeroEquipos = 0;
        this.reglamento = "Estándar";
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public Torneo(int idTorneo, String nombre, int numeroEquipos, String reglamento) {
        super(idTorneo, "Sin nombre");
        setNombre(nombre);
        setNumeroEquipos(numeroEquipos);
        setReglamento(reglamento);
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public Torneo(int idTorneo, String nombre) {
        this(idTorneo, nombre, 2, "Estándar");
    }

    public int getIdTorneo() { return getId(); }

    public boolean setIdTorneo(int idTorneo) {
        if (idTorneo > 0) {
            super.setId(idTorneo);
            return true;
        }
        return false; // Validación: El ID no puede ser negativo o cero
    }

    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false; // Validación: No permite nombres vacíos o nulos
        }
        super.asignarNombre(nombre);
        return true;
    }

    public boolean setNombre(Torneo otro) {
        if (otro == null) {
            return false;
        }
        return setNombre(otro.nombre);
    }

    public int getNumeroEquipos() { return numeroEquipos; }

    public boolean setNumeroEquipos(int numeroEquipos) {
        if (numeroEquipos > 1) { // Validación: Una liga necesita al menos 2 equipos
            this.numeroEquipos = numeroEquipos;
            return true;
        }
        return false;
    }

    public boolean setNumeroEquipos(Torneo otro) {
        if (otro == null) {
            return false;
        }
        return setNumeroEquipos(otro.numeroEquipos);
    }

    public String getReglamento() { return reglamento; }

    public boolean setReglamento(String reglamento) {
        if (reglamento == null || reglamento.trim().isEmpty()) {
            return false;
        }
        this.reglamento = reglamento;
        return true;
    }

    public boolean setReglamento(Torneo otro) {
        if (otro == null) {
            return false;
        }
        return setReglamento(otro.reglamento);
    }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Equipo>
    public List<Equipo> getEquipos() { return equipos; }
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = (equipos != null) ? equipos : new ArrayList<>();
    }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Partido>
    public List<Partido> getPartidos() { return partidos; }
    public void setPartidos(List<Partido> partidos) {
        this.partidos = (partidos != null) ? partidos : new ArrayList<>();
    }

    // Métodos de negocio requeridos por el diagrama de clases
    public void crearTorneo() {
        System.out.println("-> Torneo '" + this.nombre + "' (Modalidad: Liga) creado con cupo para " + this.numeroEquipos + " equipos.");
    }

    public void configurarReg() {
        System.out.println("-> Reglamento '" + this.reglamento + "' configurado correctamente.");
    }

    public void configurarReg(Torneo otro) {
        if (otro == null) {
            System.out.println("[Error] El torneo de referencia no puede ser nulo.");
            return;
        }
        if (setReglamento(otro.reglamento)) {
            configurarReg();
        } else {
            System.out.println("[Error] El reglamento del torneo de referencia no es válido.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Torneo)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Torneo other = (Torneo) obj;
        return this.numeroEquipos == other.numeroEquipos
                && Objects.equals(this.reglamento, other.reglamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEquipos, reglamento);
    }

    @Override
    public String toString() {
        return "Torneo [ID=" + id +
                ", Nombre=" + nombre +
                ", Equipos Permitidos=" + numeroEquipos +
                ", Reglamento=" + reglamento + "]";
    }
}