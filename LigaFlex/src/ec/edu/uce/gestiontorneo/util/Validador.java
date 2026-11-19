package ec.edu.uce.gestiontorneo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Validador {

    // Validación numérica básica por consola
    public static int validarEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Error. Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    // Validación usando Pattern y Matcher
    public static boolean validarFormatoTexto(String texto) {
        // Ejemplo: Solo permite letras y espacios (para nombres de equipos o jugadores)
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}