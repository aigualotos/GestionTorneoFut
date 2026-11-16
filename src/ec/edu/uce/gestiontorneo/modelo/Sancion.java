package ec.edu.uce.gestiontorneo.modelo;

public class Sancion {
    private int idSancion;
    private String motivo;
    private int partidosSuspension;
    private boolean estadoSancion;

    public Sancion() {
    }

    public Sancion(int idSancion, String motivo, int partidosSuspension, boolean estadoSancion) {
        this.idSancion = idSancion;
        setMotivo(motivo);
        setPartidosSuspension(partidosSuspension);
        this.estadoSancion = estadoSancion;
    }

    public int getIdSancion() {
        return this.idSancion;
    }

    public void setIdSancion(int idSancion) {
        this.idSancion = idSancion;
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

    public int getPartidosSuspension() {
        return this.partidosSuspension;
    }

    public void setPartidosSuspension(int partidosSuspension) {
        if (partidosSuspension < 0) {
            throw new IllegalArgumentException("Los partidos de suspensión no pueden ser menores a cero.");
        }
        this.partidosSuspension = partidosSuspension;
    }

    public boolean isEstadoSancion() {
        return this.estadoSancion;
    }

    public void setEstadoSancion(boolean estadoSancion) {
        this.estadoSancion = estadoSancion;
    }
}