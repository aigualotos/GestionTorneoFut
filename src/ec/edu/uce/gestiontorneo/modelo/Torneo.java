package ec.edu.uce.gestiontorneo.modelo;

public class Torneo {
    private int idTorneo;
    private String nombre;
    private int numeroEquipos; // Reemplaza al formato abierto; fijo como Liga
    private String reglamento;

    public Torneo() {
        this.idTorneo = 0;
        this.nombre = "Sin nombre";
        this.numeroEquipos = 0;
        this.reglamento = "Estándar";
    }

    public Torneo(int idTorneo, String nombre, int numeroEquipos, String reglamento) {
        this.idTorneo = idTorneo;
        setNombre(nombre);
        setNumeroEquipos(numeroEquipos);
        setReglamento(reglamento);
    }

    public int getIdTorneo() { return idTorneo; }

    public boolean setIdTorneo(int idTorneo) {
        if (idTorneo > 0) {
            this.idTorneo = idTorneo;
            return true;
        }
        return false; // Validación: El ID no puede ser negativo o cero
    }

    public String getNombre() { return nombre; }

    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false; // Validación: No permite nombres vacíos o nulos
        }
        this.nombre = nombre;
        return true;
    }

    public int getNumeroEquipos() { return numeroEquipos; }

    public boolean setNumeroEquipos(int numeroEquipos) {
        if (numeroEquipos > 1) { // Validación: Una liga necesita al menos 2 equipos
            this.numeroEquipos = numeroEquipos;
            return true;
        }
        return false;
    }

    public String getReglamento() { return reglamento; }

    public boolean setReglamento(String reglamento) {
        if (reglamento == null || reglamento.trim().isEmpty()) {
            return false;
        }
        this.reglamento = reglamento;
        return true;
    }

    // Métodos de negocio requeridos por el diagrama de clases
    public void crearTorneo() {
        System.out.println("-> Torneo '" + this.nombre + "' (Modalidad: Liga) creado con cupo para " + this.numeroEquipos + " equipos.");
    }

    public void configurarReg() {
        System.out.println("-> Reglamento '" + this.reglamento + "' configurado correctamente.");
    }
}