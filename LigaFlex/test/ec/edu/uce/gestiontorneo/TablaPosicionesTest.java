package ec.edu.uce.gestiontorneo;

import ec.edu.uce.gestiontorneo.modelo.TablaPosiciones;
import org.junit.Test;
import static org.junit.Assert.*;

public class TablaPosicionesTest {

    @Test
    public void testCalcularDiferenciaDeGolesAutomatica() {
        // Creamos un registro con 10 goles a favor y 4 en contra
        TablaPosiciones tabla = new TablaPosiciones(1, 6, 3, 10, 4);

        // La diferencia (10 - 4) debe calcularse automáticamente como 6
        assertEquals(6, tabla.getGolDiferencia());
    }

    @Test
    public void testValidacionDatosNegativosTabla() {
        TablaPosiciones tabla = new TablaPosiciones();

        // Verificamos que el sistema rechace valores negativos en las estadísticas
        assertFalse(tabla.setPuntos(-3));
        assertFalse(tabla.setGolesFavor(-1));
        assertFalse(tabla.setGolesContra(-5));
    }

    @Test
    public void testMetodosTablaPosiciones() {
        TablaPosiciones tabla = new TablaPosiciones();

        tabla.calcularPuntos();
        tabla.actualizarRanking();
    }
}