package ec.edu.uce.gestiontorneo.modelo;

import java.util.Objects;

public class RegistroDisciplinario {
    protected int id;

    public RegistroDisciplinario() {
        this.id = 0;
    }

    public RegistroDisciplinario(int id) {
        this();
        setId(id);
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

    public boolean mismoId(RegistroDisciplinario otro) {
        if (otro == null) {
            return false;
        }
        return this.id == otro.id;
    }

    public boolean mismoId(int id) {
        return this.id == id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegistroDisciplinario)) {
            return false;
        }
        RegistroDisciplinario other = (RegistroDisciplinario) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RegistroDisciplinario [ID=" + id + "]";
    }
}