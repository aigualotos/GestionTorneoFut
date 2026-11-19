package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.util.Validador;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidadorTest {

    @Test
    public void testValidarNombreEquipoValido() {
        // Prueba que un nombre correcto devuelva true
        boolean resultado = Validador.validarFormatoTexto("Liga de Quito");
        assertTrue(resultado);
    }

    @Test
    public void testValidarNombreEquipoInvalido() {
        // Prueba que un texto vacío devuelva false
        boolean resultado = Validador.validarFormatoTexto("");
        assertFalse(resultado);
    }
}