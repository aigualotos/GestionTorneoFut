package ec.edu.uce.gestiontorneo.modelo;

import java.util.ArrayList;
import java.util.List;

public class ActaPartido {
    private int idActa;
    private List<String> minutosGoles;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

    // Relación 1 a 1 (Composición): Partido al que pertenece el acta
    private Partido partido;

    // Relación 1 a muchos (Agregación): Lista de sanciones registradas en el acta
    private List<Sancion> sanciones;

    public ActaPartido() {
        this.minutosGoles = new ArrayList<>();
        this.sanciones = new ArrayList<>();
    }

    public ActaPartido(int idActa, List<String> minutosGoles, int tarjetasAmarillas, int tarjetasRojas) {
        setIdActa(idActa);
        this.minutosGoles = (minutosGoles != null) ? minutosGoles : new ArrayList<>();
        setTarjetasAmarillas(tarjetasAmarillas);
        setTarjetasRojas(tarjetasRojas);
        this.sanciones = new ArrayList<>();
    }

    // Métodos de negocio del diagrama de clases
    public void registrarEventos(String minutoGol, int amarillas, int rojas) {
        if (minutoGol != null && !minutoGol.trim().isEmpty()) {
            this.minutosGoles.add(minutoGol);
        }
        setTarjetasAmarillas(this.tarjetasAmarillas + amarillas);
        setTarjetasRojas(this.tarjetasRojas + rojas);
        System.out.println("-> Eventos registrados en el acta #" + this.idActa);
    }

    public void generarReporte() {
        System.out.println("\n--- REPORTE DE ACTA DE PARTIDO #" + this.idActa + " ---");
        System.out.println("Goles (minutos): " + (minutosGoles.isEmpty() ? "Sin goles" : minutosGoles));
        System.out.println("Tarjetas Amarillas: " + this.tarjetasAmarillas);
        System.out.println("Tarjetas Rojas: " + this.tarjetasRojas);
    }

    // Getters y Setters con validaciones
    public int getIdActa() {
        return idActa;
    }

    public void setIdActa(int idActa) {
        if (idActa <= 0) {
            throw new IllegalArgumentException("El ID del acta debe ser mayor a cero.");
        }
        this.idActa = idActa;
    }

    public List<String> getMinutosGoles() {
        return minutosGoles;
    }

    public void setMinutosGoles(List<String> minutosGoles) {
        this.minutosGoles = (minutosGoles != null) ? minutosGoles : new ArrayList<>();
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        if (tarjetasAmarillas < 0) {
            throw new IllegalArgumentException("Las tarjetas amarillas no pueden ser negativas.");
        }
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        if (tarjetasRojas < 0) {
            throw new IllegalArgumentException("Las tarjetas rojas no pueden ser negativas.");
        }
        this.tarjetasRojas = tarjetasRojas;
    }

    // Getter y Setter de la relación 1 a 1 (Composición) con Partido
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    // Getter y Setter de la relación 1 a muchos (Agregación) con List<Sancion>
    public List<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSanciones(List<Sancion> sanciones) {
        this.sanciones = (sanciones != null) ? sanciones : new ArrayList<>();
    }
}