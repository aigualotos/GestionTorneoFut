package ec.edu.uce.gestiontorneo.modelo;

public class Jugador {
    private int idJugador;
    private String nombre;
    private int dorsal;
    private String posicion;

    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, int dorsal, String posicion) {
        this.idJugador = idJugador;
        setNombre(nombre);
        setDorsal(dorsal);
        this.posicion = posicion;
    }

    public int getIdJugador() {
        return this.idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
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

    public int getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(int dorsal) {
        if (dorsal < 0) {
            throw new IllegalArgumentException("El dorsal no puede ser menor a cero.");
        }
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return this.posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}