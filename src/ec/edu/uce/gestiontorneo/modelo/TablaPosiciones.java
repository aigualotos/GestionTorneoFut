package ec.edu.uce.gestiontorneo.modelo;

public class TablaPosiciones {
    private int idTabla;
    private int puntos;
    private int partidosJugados;
    private int golesFavor;
    private int golesContra;
    private int golDiferencia;

    public TablaPosiciones() {
        this.idTabla = 0;
        this.puntos = 0;
        this.partidosJugados = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.golDiferencia = 0;
    }

    public TablaPosiciones(int idTabla, int puntos, int partidosJugados, int golesFavor, int golesContra) {
        this.idTabla = idTabla;
        setPuntos(puntos);
        setPartidosJugados(partidosJugados);
        setGolesFavor(golesFavor);
        setGolesContra(golesContra);
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
}