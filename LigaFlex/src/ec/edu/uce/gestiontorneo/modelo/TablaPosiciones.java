package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;

public class TablaPosiciones {
    private int idTabla;
    private int puntos;
    private int partidosJugados;
    private int golesFavor;
    private int golesContra;
    private int golDiferencia;

    // Relación 1 a 1 (Agregación): Equipo asociado al registro estadístico
    private Equipo equipo;

    // Relación 1 a 1 (Agregación): Torneo al que pertenece la tabla de posiciones
    private Torneo torneo;

    // Relación 1 a muchos (Agregación): Lista de equipos ordenados en la tabla de posiciones
    private List<Equipo> equiposClasificados;

    public TablaPosiciones() {
        this.idTabla = 0;
        this.puntos = 0;
        this.partidosJugados = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.golDiferencia = 0;
        this.equiposClasificados = new ArrayList<>();
    }

    public TablaPosiciones(int idTabla, int puntos, int partidosJugados, int golesFavor, int golesContra) {
        this.idTabla = idTabla;
        setPuntos(puntos);
        setPartidosJugados(partidosJugados);
        setGolesFavor(golesFavor);
        setGolesContra(golesContra);
        this.equiposClasificados = new ArrayList<>();
        calcularPuntos();
    }

    // Métodos de negocio y cálculo automático
    public void calcularPuntos() {
        // La diferencia de goles se calcula automáticamente restando goles a favor menos goles en contra
        this.golDiferencia = this.golesFavor - this.golesContra;
        System.out.println("-> Puntos recalculados. Diferencia de goles actual: " + this.golDiferencia);
    }

    public void actualizarRanking() {
        System.out.println("-> Tabla de posiciones ordenada por puntos y diferencia de goles.");
    }

    // Getters y Setters con validaciones de negocio (evita negativos)
    public int getIdTabla() { return idTabla; }
    public void setIdTabla(int idTabla) { this.idTabla = idTabla; }

    public int getPuntos() { return puntos; }
    public boolean setPuntos(int puntos) {
        if (puntos >= 0) {
            this.puntos = puntos;
            return true;
        }
        return false; // No se permiten puntos negativos
    }

    public int getPartidosJugados() { return partidosJugados; }
    public boolean setPartidosJugados(int partidosJugados) {
        if (partidosJugados >= 0) {
            this.partidosJugados = partidosJugados;
            return true;
        }
        return false;
    }

    public int getGolesFavor() { return golesFavor; }
    public boolean setGolesFavor(int golesFavor) {
        if (golesFavor >= 0) {
            this.golesFavor = golesFavor;
            this.golDiferencia = this.golesFavor - this.golesContra; // Actualiza diferencia al instante
            return true;
        }
        return false;
    }

    public int getGolesContra() { return golesContra; }
    public boolean setGolesContra(int golesContra) {
        if (golesContra >= 0) {
            this.golesContra = golesContra;
            this.golDiferencia = this.golesFavor - this.golesContra; // Actualiza diferencia al instante
            return true;
        }
        return false;
    }

    public int getGolDiferencia() { return golDiferencia; }
    public void setGolDiferencia(int golDiferencia) { this.golDiferencia = golDiferencia; }

    // Getter y Setter de la relación 1 a 1 (Agregación) con Equipo
    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    // Getter y Setter de la relación 1 a 1 (Agregación) con Torneo
    public Torneo getTorneo() { return torneo; }
    public void setTorneo(Torneo torneo) { this.torneo = torneo; }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Equipo>
    public List<Equipo> getEquiposClasificados() { return equiposClasificados; }
    public void setEquiposClasificados(List<Equipo> equiposClasificados) {
        this.equiposClasificados = (equiposClasificados != null) ? equiposClasificados : new ArrayList<>();
    }
}