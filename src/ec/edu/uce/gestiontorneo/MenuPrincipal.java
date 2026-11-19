package ec.edu.uce.gestiontorneo;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import ec.edu.uce.gestiontorneo.modelo.ActaPartido;
import ec.edu.uce.gestiontorneo.modelo.Equipo;
import ec.edu.uce.gestiontorneo.modelo.Jugador;
import ec.edu.uce.gestiontorneo.modelo.Partido;
import ec.edu.uce.gestiontorneo.modelo.Torneo;
import ec.edu.uce.gestiontorneo.util.Validador;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        menuUnificado(scanner);

        scanner.close();
    }

    // Listas en memoria para almacenar entidades y evitar IDs duplicados
    private static List<Torneo> listaTorneos = new ArrayList<>();
    private static List<Equipo> listaEquipos = new ArrayList<>();
    private static List<Partido> listaPartidos = new ArrayList<>();
    private static List<String> listaHistorialPartidos = new ArrayList<>();

    private static void menuUnificado(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== LIGAFLEX - MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Torneos");
            System.out.println("2. Gestión de Equipos");
            System.out.println("3. Gestión de Partidos");
            System.out.println("4. Consultas");
            System.out.println("5. Salir del Sistema");
            System.out.print("Seleccione una opción: ");

            opcion = Validador.validarEntero(scanner, "");

            switch (opcion) {
                case 1:
                    subgrupoTorneosytabla(scanner);
                    break;
                case 2:
                    subgrupoGestionEquipos(scanner);
                    break;
                case 3:
                    subgrupoPartidosyActas(scanner);
                    break;
                case 4:
                    subgrupoConsultas(scanner);
                    break;
                case 5:
                    System.out.println("\nSaliendo del sistema de gestión. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n[Error] Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // Submenú 1: Torneos y Tabla de Posiciones
    private static void subgrupoTorneosytabla(Scanner scanner) {
        int opcionTorneo;
        do {
            System.out.println("\n--- GESTIÓN DE TORNEOS ---");
            System.out.println("1. Crear Torneo");
            System.out.println("2. Modificar Torneo");
            System.out.println("3. Eliminar Torneo");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");

            opcionTorneo = Validador.validarEntero(scanner, "");

            switch (opcionTorneo) {
                case 1:
                    System.out.println("\n--- CREAR NUEVO TORNEO ---");
                    System.out.print("Ingrese el ID del torneo (número positivo): ");
                    int idTorneo = Validador.validarEntero(scanner, "");

                    boolean idRepetido = false;
                    for (Torneo t : listaTorneos) {
                        if (t.getIdTorneo() == idTorneo) {
                            idRepetido = true;
                            break;
                        }
                    }

                    if (idRepetido) {
                        System.out.println("[Error] Ya existe un torneo registrado con el ID " + idTorneo + ". Intente con otro.");
                        break;
                    }

                    System.out.print("Ingrese el nombre del torneo: ");
                    String nombre = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(nombre)) {
                        System.out.println("[Error] El nombre del torneo solo debe contener letras y espacios.");
                        break;
                    }

                    System.out.print("Ingrese el número de equipos (mínimo 2): ");
                    int numeroEquipos = Validador.validarEntero(scanner, "");

                    System.out.print("Ingrese el reglamento: ");
                    String reglamento = scanner.nextLine();

                    Torneo nuevoTorneo = new Torneo();
                    if (nuevoTorneo.setIdTorneo(idTorneo) && nuevoTorneo.setNumeroEquipos(numeroEquipos)) {
                        if (!nuevoTorneo.setNombre(nombre) || !nuevoTorneo.setReglamento(reglamento)) {
                            System.out.println("[Error] Nombre o reglamento inválido.");
                            break;
                        }

                        nuevoTorneo.crearTorneo();
                        nuevoTorneo.configurarReg();

                        listaTorneos.add(nuevoTorneo);
                        System.out.println("[Éxito] Torneo creado y registrado en memoria.");
                    } else {
                        System.out.println("[Error] Datos inválidos (ID menor o igual a 0 o número de equipos menor a 2).");
                    }
                    break;

                case 2:
                    System.out.println("\n--- MODIFICAR TORNEO ---");
                    if (listaTorneos.isEmpty()) {
                        System.out.println("[Aviso] No hay torneos registrados para modificar.");
                        break;
                    }
                    System.out.print("Ingrese el ID del torneo que desea modificar: ");
                    int idModificar = Validador.validarEntero(scanner, "");

                    Torneo torneoEncontrado = null;
                    for (Torneo t : listaTorneos) {
                        if (t.getIdTorneo() == idModificar) {
                            torneoEncontrado = t;
                            break;
                        }
                    }

                    if (torneoEncontrado != null) {
                        System.out.print("Ingrese el nuevo nombre del torneo: ");
                        String nuevoNombre = scanner.nextLine();
                        if (Validador.validarFormatoTexto(nuevoNombre) && torneoEncontrado.setNombre(nuevoNombre)) {
                            System.out.println("[Éxito] Torneo modificado correctamente.");
                        } else {
                            System.out.println("[Error] Nombre inválido.");
                        }
                    } else {
                        System.out.println("[Error] No se encontró ningún torneo con ese ID.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- ELIMINAR TORNEO ---");
                    if (listaTorneos.isEmpty()) {
                        System.out.println("[Aviso] No hay torneos registrados para eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el ID del torneo que desea eliminar: ");
                    int idEliminar = Validador.validarEntero(scanner, "");

                    boolean eliminado = listaTorneos.removeIf(t -> t.getIdTorneo() == idEliminar);
                    if (eliminado) {
                        System.out.println("[Éxito] Torneo eliminado correctamente.");
                    } else {
                        System.out.println("[Error] No se encontró un torneo con ese ID.");
                    }
                    break;

                case 4:
                    System.out.println("\nVolviendo al menú principal...");
                    break;

                default:
                    System.out.println("\n[Error] Opción inválida.");
            }
        } while (opcionTorneo != 4);
    }

    // Submenú 2: Gestión de Equipos y Plantillas
    private static void subgrupoGestionEquipos(Scanner scanner) {
        int opcionEquipo;
        do {
            System.out.println("\n--- GESTIÓN DE EQUIPOS Y PLANTILLAS ---");
            System.out.println("1. Inscribir Equipo");
            System.out.println("2. Modificar Equipo");
            System.out.println("3. Listar Equipos (Con Escudo/Descripción)");
            System.out.println("4. Eliminar Equipo");
            System.out.println("5. Gestionar Plantilla de Jugadores");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            opcionEquipo = Validador.validarEntero(scanner, "");

            switch (opcionEquipo) {
                case 1:
                    System.out.println("\n--- INSCRIBIR NUEVO EQUIPO ---");
                    System.out.print("Ingrese el ID del equipo (número positivo): ");
                    int id = Validador.validarEntero(scanner, "");

                    boolean idRepetido = false;
                    for (Equipo e : listaEquipos) {
                        if (e.getIdEquipo() == id) {
                            idRepetido = true;
                            break;
                        }
                    }

                    if (idRepetido) {
                        System.out.println("[Error] Ya existe un equipo con el ID " + id + ".");
                        break;
                    }

                    System.out.print("Ingrese el nombre del club: ");
                    String nombreEquipo = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(nombreEquipo)) {
                        System.out.println("[Error] Nombre de equipo inválido.");
                        break;
                    }

                    System.out.print("Ingrese la descripción o escudo: ");
                    String escudo = scanner.nextLine();

                    try {
                        Equipo nuevoEquipo = new Equipo();
                        nuevoEquipo.setIdEquipo(id);
                        nuevoEquipo.setNombreEquipo(nombreEquipo);
                        nuevoEquipo.setEscudo(escudo);

                        listaEquipos.add(nuevoEquipo);
                        System.out.println("[Éxito] Equipo '" + nombreEquipo + "' inscrito correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[Error de Validación] " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- MODIFICAR EQUIPO ---");
                    if (listaEquipos.isEmpty()) {
                        System.out.println("[Aviso] No hay equipos registrados.");
                        break;
                    }
                    System.out.print("Ingrese el ID del equipo a modificar: ");
                    int idMod = Validador.validarEntero(scanner, "");

                    Equipo eqMod = null;
                    for (Equipo e : listaEquipos) {
                        if (e.getIdEquipo() == idMod) {
                            eqMod = e;
                            break;
                        }
                    }

                    if (eqMod != null) {
                        System.out.print("Ingrese el nuevo nombre del equipo: ");
                        String nuevoNombre = scanner.nextLine();
                        if (Validador.validarFormatoTexto(nuevoNombre)) {
                            try {
                                eqMod.setNombreEquipo(nuevoNombre);
                                System.out.println("[Éxito] Equipo modificado correctamente.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("[Error de Validación] " + e.getMessage());
                            }
                        } else {
                            System.out.println("[Error] Nombre inválido.");
                        }
                    } else {
                        System.out.println("[Error] No se encontró un equipo con ese ID.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- LISTA DE EQUIPOS INSCRITOS ---");
                    if (listaEquipos.isEmpty()) {
                        System.out.println("[Aviso] No hay equipos registrados todavía.");
                    } else {
                        for (Equipo e : listaEquipos) {
                            System.out.println("• ID: " + e.getIdEquipo() +
                                    " | Club: " + e.getNombreEquipo() +
                                    " | Escudo/Desc: " + e.getEscudo());
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- ELIMINAR EQUIPO ---");
                    if (listaEquipos.isEmpty()) {
                        System.out.println("[Aviso] No hay equipos registrados.");
                        break;
                    }
                    System.out.print("Ingrese el ID del equipo a eliminar: ");
                    int idDel = Validador.validarEntero(scanner, "");

                    boolean eliminado = listaEquipos.removeIf(e -> e.getIdEquipo() == idDel);
                    if (eliminado) {
                        System.out.println("[Éxito] Equipo eliminado correctamente.");
                    } else {
                        System.out.println("[Error] No se encontró el equipo con ese ID.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- ACCEDER A PLANTILLA DE JUGADORES ---");
                    if (listaEquipos.isEmpty()) {
                        System.out.println("[Aviso] No hay equipos registrados para gestionar plantillas.");
                        break;
                    }
                    System.out.print("Ingrese el ID del equipo cuya plantilla desea gestionar: ");
                    int idBusqueda = Validador.validarEntero(scanner, "");

                    Equipo equipoEncontrado = null;
                    for (Equipo e : listaEquipos) {
                        if (e.getIdEquipo() == idBusqueda) {
                            equipoEncontrado = e;
                            break;
                        }
                    }

                    if (equipoEncontrado == null) {
                        System.out.println("[Error] No existe ningún equipo con el ID " + idBusqueda + ".");
                    } else {
                        subgrupoGestionPlantilla(scanner, equipoEncontrado);
                    }
                    break;

                case 6:
                    System.out.println("\nVolviendo al menú principal...");
                    break;

                default:
                    System.out.println("\n[Error] Opción inválida.");
            }
        } while (opcionEquipo != 6);
    }

    private static void subgrupoGestionPlantilla(Scanner scanner, Equipo equipoSeleccionado) {
        int opcionPlantilla;
        do {
            System.out.println("\n--- GESTIÓN DE PLANTILLA: " + equipoSeleccionado.getNombreEquipo() + " ---");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Listar Jugadores (Plantilla)");
            System.out.println("3. Eliminar Jugador por ID");
            System.out.println("0. Volver al Menú de Equipos");
            System.out.print("Seleccione una opción: ");

            opcionPlantilla = Validador.validarEntero(scanner, "");

            switch (opcionPlantilla) {
                case 1:
                    System.out.println("\n--- AGREGAR JUGADOR A LA PLANTILLA ---");

                    if (equipoSeleccionado.getPlantilla().size() >= 25) {
                        System.out.println("[Error] Este equipo ya tiene el cupo lleno (25 jugadores).");
                        break;
                    }

                    System.out.print("Ingrese el ID del jugador: ");
                    int idJugador = Validador.validarEntero(scanner, "");

                    boolean jugadorDuplicado = false;
                    for (Jugador j : equipoSeleccionado.getPlantilla()) {
                        if (j.getIdJugador() == idJugador) {
                            jugadorDuplicado = true;
                            break;
                        }
                    }

                    if (jugadorDuplicado) {
                        System.out.println("[Error] Ya existe un jugador con el ID " + idJugador + " en este equipo.");
                        break;
                    }

                    System.out.print("Ingrese el nombre completo del jugador: ");
                    String nombreJugador = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(nombreJugador)) {
                        System.out.println("[Error] El nombre solo debe contener letras y espacios.");
                        break;
                    }

                    System.out.print("Ingrese el número de camiseta (Dorsal): ");
                    int dorsal = Validador.validarEntero(scanner, "");

                    System.out.print("Ingrese la posición (Ej. Arquero, Defensa, Delantero): ");
                    String posicion = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(posicion)) {
                        System.out.println("[Error] Posición inválida.");
                        break;
                    }

                    try {
                        Jugador nuevoJugador = new Jugador();
                        nuevoJugador.setIdJugador(idJugador);
                        nuevoJugador.setNombre(nombreJugador);
                        nuevoJugador.setDorsal(dorsal);
                        nuevoJugador.setPosicion(posicion);

                        equipoSeleccionado.agregarJugador(nuevoJugador);
                    } catch (IllegalArgumentException e) {
                        System.out.println("[Error de Validación] " + e.getMessage());
                    }
                    break;

                case 2:
                    equipoSeleccionado.consultarPlantilla();
                    break;

                case 3:
                    System.out.println("\n--- ELIMINAR JUGADOR ---");
                    System.out.print("Ingrese el ID del jugador que desea eliminar: ");
                    int idDel = Validador.validarEntero(scanner, "");
                    equipoSeleccionado.eliminarJugador(idDel);
                    break;

                case 0:
                    System.out.println("\nVolviendo al menú de equipos...");
                    break;

                default:
                    System.out.println("\n[Error] Opción inválida.");
            }
        } while (opcionPlantilla != 0);
    }

    // Submenú 3: Partidos y Actas
    private static void subgrupoPartidosyActas(Scanner scanner) {
        int opcionPartido;
        do {
            System.out.println("\n--- GESTIÓN DE PARTIDOS Y ACTAS ---");
            System.out.println("1. Programar Partido");
            System.out.println("2. Registrar Acta de Partido (Marcador, Goles y Sanciones)");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            opcionPartido = Validador.validarEntero(scanner, "");

            switch (opcionPartido) {
                case 1:
                    System.out.println("\n--- PROGRAMAR NUEVO PARTIDO ---");
                    System.out.print("Ingrese el ID del partido: ");
                    int idPartido = Validador.validarEntero(scanner, "");

                    boolean partidoDuplicado = false;
                    for (Partido p : listaPartidos) {
                        if (p.getIdPartido() == idPartido) {
                            partidoDuplicado = true;
                            break;
                        }
                    }

                    if (partidoDuplicado) {
                        System.out.println("[Error] Ya existe un partido registrado con el ID " + idPartido + ".");
                        break;
                    }

                    System.out.print("Ingrese el nombre del equipo Local: ");
                    String equipoLocal = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(equipoLocal)) {
                        System.out.println("[Error] Nombre de equipo local inválido.");
                        break;
                    }

                    System.out.print("Ingrese el nombre del equipo Visitante: ");
                    String equipoVisitante = scanner.nextLine();

                    if (!Validador.validarFormatoTexto(equipoVisitante)) {
                        System.out.println("[Error] Nombre de equipo visitante inválido.");
                        break;
                    }

                    System.out.print("Ingrese la fecha del encuentro (Ej. 2026-06-15): ");
                    String fecha = scanner.nextLine();

                    try {
                        Partido partido = new Partido();
                        partido.setIdPartido(idPartido);
                        partido.programarPartido(fecha);

                        listaPartidos.add(partido);
                        listaHistorialPartidos.add("Partido ID: " + idPartido +
                                "\n Local: " + equipoLocal +
                                "\n Visitante: " + equipoVisitante +
                                "\n Fecha: " + fecha +
                                "\n Estado: " + partido.getEstado());

                        System.out.println("[Éxito] Partido programado correctamente entre " + equipoLocal + " y " + equipoVisitante + " para la fecha " + fecha + ".");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[Error de Validación] " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("\n--- REGISTRAR ACTA DE PARTIDO (ÁRBITRO) ---");
                        System.out.print("Ingrese el ID del acta: ");
                        int idActa = Validador.validarEntero(scanner, "");

                        ActaPartido acta = new ActaPartido();
                        acta.setIdActa(idActa);

                        System.out.print("Ingrese el minuto del gol (Ej. 45'+2 o pulse Enter si no hubo): ");
                        String minutoGol = scanner.nextLine();

                        System.out.print("Ingrese cantidad de tarjetas amarillas: ");
                        int amarillas = Validador.validarEntero(scanner, "");

                        System.out.print("Ingrese cantidad de tarjetas rojas: ");
                        int rojas = Validador.validarEntero(scanner, "");

                        acta.registrarEventos(minutoGol, amarillas, rojas);
                        acta.generarReporte();

                        System.out.println("[Éxito] Acta procesada y guardada correctamente.");

                        String resumenPartido = "Acta / Partido ID: " + idActa +
                                "\n Goles Totales / Eventos: " + (minutoGol.trim().isEmpty() ? "Sin goles" : minutoGol) +
                                "\n Tarjetas Amarillas: " + amarillas +
                                "\n Tarjetas Rojas: " + rojas;

                        listaHistorialPartidos.add(resumenPartido);

                    } catch (IllegalArgumentException e) {
                        System.out.println("[Error de Validación] " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\nVolviendo al menú principal...");
                    break;

                default:
                    System.out.println("\n[Error] Opción inválida. Intente de nuevo.");
            }
        } while (opcionPartido != 3);
    }

    // Submenú 4: Módulo de Consultas (Aficionado / Resultados)
    private static void subgrupoConsultas(Scanner scanner) {
        int opcionConsulta;
        do {
            System.out.println("\n========================================");
            System.out.println("     MÓDULO DE CONSULTAS (AFICIONADO)   ");
            System.out.println("========================================");
            System.out.println("1. Consultar Torneos");
            System.out.println("2. Consultar Equipos");
            System.out.println("3. Consultar Resultados y Partidos");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción de consulta: ");

            opcionConsulta = Validador.validarEntero(scanner, "");

            switch (opcionConsulta) {
                case 1:
                    System.out.println("\n--- LISTA DE TORNEOS ACTIVOS ---");
                    if (listaTorneos.isEmpty()) {
                        System.out.println("[Aviso] No hay torneos registrados en el sistema todavía.");
                    } else {
                        for (Torneo t : listaTorneos) {
                            System.out.println("• ID: " + t.getIdTorneo() +
                                    " | Nombre: " + t.getNombre() +
                                    " | Equipos Permitidos: " + t.getNumeroEquipos() +
                                    " | Reglamento: " + t.getReglamento());
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- EQUIPOS INSCRITOS Y SUS PLANTILLAS ---");
                    if (listaEquipos.isEmpty()) {
                        System.out.println("[Aviso] No hay equipos registrados en el sistema todavía.");
                    } else {
                        for (Equipo e : listaEquipos) {
                            System.out.println("\n----------------------------------------");
                            System.out.println("• ID: " + e.getIdEquipo() +
                                    " | Club: " + e.getNombreEquipo() +
                                    " | Escudo/Desc: " + e.getEscudo());

                            if (e.getPlantilla() == null || e.getPlantilla().isEmpty()) {
                                System.out.println("  ↳ Plantilla: (Aún sin jugadores inscritos)");
                            } else {
                                System.out.println("  ↳ Plantilla (" + e.getPlantilla().size() + " jugadores):");
                                for (Jugador j : e.getPlantilla()) {
                                    System.out.println("    - Dorsal #" + j.getDorsal() + " | " + j.getNombre() + " (" + j.getPosicion() + ")");
                                }
                            }
                        }
                        System.out.println("----------------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\n--- CALENDARIO Y RESULTADOS DE PARTIDOS ---");
                    if (listaHistorialPartidos.isEmpty()) {
                        System.out.println("[Aviso] No hay partidos programados ni actas registradas todavía.");
                    } else {
                        System.out.println("========================================");
                        for (String resultado : listaHistorialPartidos) {
                            System.out.println(resultado);
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;

                case 0:
                    System.out.println("\nVolviendo al menú principal...");
                    break;

                default:
                    System.out.println("\n[Error] Opción no válida. Intente de nuevo.");
            }
        } while (opcionConsulta != 0);
    }
}