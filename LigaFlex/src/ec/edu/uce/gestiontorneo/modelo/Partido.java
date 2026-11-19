package ec.edu.uce.gestiontorneo.modelo;

public class Partido {
    private int idPartido;
    private String fechaHora;
    private String estado;
    private int marcadorLocal;
    private int marcadorVisita;

    // Relación 1 a 1 (Agregación): Equipo local que disputa el partido
    private Equipo equipoLocal;

    // Relación 1 a 1 (Agregación): Equipo visitante que disputa el partido
    private Equipo equipoVisitante;

    // Relación 1 a 1 (Composición): Acta oficial asociada al ciclo de vida del partido
    private ActaPartido actaPartido;

    public Partido() {
        this.estado = "Pendiente";
        this.marcadorLocal = 0;
        this.marcadorVisita = 0;
    }

    public Partido(int idPartido, String fechaHora, String estado, int marcadorLocal, int marcadorVisita) {
        setIdPartido(idPartido);
        setFechaHora(fechaHora);
        setEstado(estado);
        setMarcadorLocal(marcadorLocal);
        setMarcadorVisita(marcadorVisita);
    }

    // Métodos de negocio del diagrama de clases
    public void programarPartido(String fechaHora) {
        setFechaHora(fechaHora);
        setEstado("Programado");
        System.out.println("-> Partido #" + this.idPartido + " programado para: " + this.fechaHora);
    }

    public void registrarMarcador(int golesLocal, int golesVisita) {
        setMarcadorLocal(golesLocal);
        setMarcadorVisita(golesVisita);
        System.out.println("-> Marcador actualizado: Local " + this.marcadorLocal + " - " + this.marcadorVisita + " Visita");
    }

    public void finalizarPartido() {
        setEstado("Finalizado");
        System.out.println("-> Partido #" + this.idPartido + " finalizado. Resultado: "
                + this.marcadorLocal + " - " + this.marcadorVisita);
    }

    // Getters y Setters con validaciones
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        if (idPartido <= 0) {
            throw new IllegalArgumentException("El ID del partido debe ser mayor a cero.");
        }
        this.idPartido = idPartido;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        if (fechaHora == null || fechaHora.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha y hora no pueden estar vacías.");
        }
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado del partido no puede estar vacío.");
        }
        this.estado = estado;
    }

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public void setMarcadorLocal(int marcadorLocal) {
        if (marcadorLocal < 0) {
            throw new IllegalArgumentException("El marcador local no puede ser negativo.");
        }
        this.marcadorLocal = marcadorLocal;
    }

    public int getMarcadorVisita() {
        return marcadorVisita;
    }

    public void setMarcadorVisita(int marcadorVisita) {
        if (marcadorVisita < 0) {
            throw new IllegalArgumentException("El marcador visita no puede ser negativo.");
        }
        this.marcadorVisita = marcadorVisita;
    }

    // Getter y Setter de la relación 1 a 1 (Agregación) con Equipo local
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    // Getter y Setter de la relación 1 a 1 (Agregación) con Equipo visitante
    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    // Getter y Setter de la relación 1 a 1 (Composición) con ActaPartido
    public ActaPartido getActaPartido() {
        return actaPartido;
    }

    public void setActaPartido(ActaPartido actaPartido) {
        this.actaPartido = actaPartido;
    }
}