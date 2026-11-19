package ec.edu.uce.gestiontorneo.modelo;

import java.util.Objects;

public class EntidadCompetitiva {
    protected int id;
    protected String nombre;

    public EntidadCompetitiva() {
        this.id = 0;
        this.nombre = "Sin nombre";
    }

    public EntidadCompetitiva(int id, String nombre) {
        this();
        setId(id);
        asignarNombre(nombre);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        }
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void asignarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o en blanco.");
        }
        this.nombre = nombre;
    }

    public void asignarNombre(EntidadCompetitiva otra) {
        if (otra == null) {
            throw new IllegalArgumentException("La entidad competitiva no puede ser nula.");
        }
        asignarNombre(otra.nombre);
    }

    public boolean mismaEntidad(EntidadCompetitiva otra) {
        if (otra == null) {
            return false;
        }
        return this.id == otra.id && Objects.equals(this.nombre, otra.nombre);
    }

    public boolean mismaEntidad(int id, String nombre) {
        return this.id == id && Objects.equals(this.nombre, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntidadCompetitiva)) {
            return false;
        }
        EntidadCompetitiva other = (EntidadCompetitiva) obj;
        return this.id == other.id && Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "EntidadCompetitiva [ID=" + id + ", Nombre=" + nombre + "]";
    }
}