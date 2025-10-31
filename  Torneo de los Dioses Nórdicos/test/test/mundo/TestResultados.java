package test.mundo;

import umariana.cupi2.torneonordico.mundo.Resultados;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestResultados {
    
    // ==================== PRUEBAS HU-004: HISTORIAL DE COMBATES ====================
    
    @Test
    public void testRegistrarPrimerCombateEnHistorial() {
        System.out.println("=== PRUEBA HU-004: REGISTRAR PRIMER COMBATE ===");
        Resultados resultados = new Resultados();
        resultados.registrarCombate("Espada", "Dragón", true);
        
        assertEquals("Debe tener 1 combate registrado", 1, resultados.getTotalCombates());
        assertEquals("Debe tener 1 victoria", 1, resultados.getTotalVictorias());
        assertEquals("No debe tener derrotas", 0, resultados.getTotalDerrotas());
        System.out.println("✅ Primer combate registrado - Victorias:1, Derrotas:0");
    }
    
    @Test
    public void testRegistroCombateConInformacionCompleta() {
        System.out.println("=== PRUEBA HU-004: INFORMACIÓN COMPLETA COMBATE ===");
        Resultados resultados = new Resultados();
        resultados.registrarCombate("Hacha", "Gigante", false);
        
        String[] ultimosCombates = resultados.getUltimos10Combates();
        boolean registroEncontrado = false;
        
        for (String combate : ultimosCombates) {
            if (combate != null) {
                assertTrue("Registro debe contener arma", combate.contains("Hacha"));
                assertTrue("Registro debe contener criatura", combate.contains("Gigante"));
                assertTrue("Registro debe contener resultado", combate.contains("DERROTA"));
                assertTrue("Registro debe contener timestamp", combate.contains("|"));
                registroEncontrado = true;
                System.out.println("✅ Registro completo: " + combate);
                break;
            }
        }
        assertTrue("Debe encontrar registro del combate", registroEncontrado);
    }
    
    @Test
    public void testLimite10RegistrosEnUltimosCombates() {
        System.out.println("=== PRUEBA HU-004: LÍMITE 10 REGISTROS ===");
        Resultados resultados = new Resultados();
        
        // Registrar 15 combates
        for (int i = 0; i < 15; i++) {
            resultados.registrarCombate("Espada", "Criatura_" + i, i % 2 == 0);
        }
        
        String[] ultimosCombates = resultados.getUltimos10Combates();
        int registrosNoNulos = 0;
        
        for (String combate : ultimosCombates) {
            if (combate != null) {
                registrosNoNulos++;
            }
        }
        
        assertEquals("Debe mantener máximo 10 registros", 10, registrosNoNulos);
        assertEquals("Total combates debe ser 15", 15, resultados.getTotalCombates());
        System.out.println("✅ Límite 10 registros respetado - Últimos:" + registrosNoNulos + ", Total:" + resultados.getTotalCombates());
    }
    
    @Test
    public void testEliminarRegistroAntiguoAlSuperarLimite() {
        System.out.println("=== PRUEBA HU-004: ELIMINAR REGISTRO ANTIGUO ===");
        Resultados resultados = new Resultados();
        
        // Registrar 10 combates iniciales
        for (int i = 0; i < 10; i++) {
            resultados.registrarCombate("Arma_" + i, "Criatura_" + i, true);
        }
        
        String primerRegistro = resultados.getUltimos10Combates()[0];
        
        // Registrar combate #11
        resultados.registrarCombate("Arma_Nueva", "Criatura_Nueva", false);
        String nuevoPrimerRegistro = resultados.getUltimos10Combates()[0];
        
        // Verificar que el primer registro cambió
        assertNotEquals("El primer registro debe cambiar", primerRegistro, nuevoPrimerRegistro);
        assertTrue("Nuevo registro debe contener información nueva", nuevoPrimerRegistro.contains("Arma_Nueva"));
        System.out.println("✅ Registro antiguo eliminado, nuevo registro agregado");
    }
    
    @Test
    public void testTimestampIncluidoEnRegistros() {
        System.out.println("=== PRUEBA HU-004: TIMESTAMP EN REGISTROS ===");
        Resultados resultados = new Resultados();
        resultados.registrarCombate("Arco", "Serpiente", true);
        
        String[] ultimosCombates = resultados.getUltimos10Combates();
        boolean timestampEncontrado = false;
        
        for (String combate : ultimosCombates) {
            if (combate != null) {
                // Verificar formato de fecha/hora (debe contener "/" y ":")
                assertTrue("Registro debe contener fecha", combate.contains("/"));
                assertTrue("Registro debe contener hora", combate.contains(":"));
                assertTrue("Registro debe contener separador", combate.contains("|"));
                timestampEncontrado = true;
                System.out.println("✅ Timestamp incluido: " + combate);
                break;
            }
        }
        assertTrue("Debe encontrar timestamp en registros", timestampEncontrado);
    }
    
    @Test
    public void testEstructurasContenedorasUtilizadas() {
        System.out.println("=== PRUEBA HU-004: ESTRUCTURAS CONTENEDORAS ===");
        Resultados resultados = new Resultados();
        
        // Verificar que se usan ambas estructuras
        resultados.registrarCombate("Espada", "Dragón", true);
        resultados.registrarCombate("Hacha", "Gigante", false);
        
        // ArrayList para historial completo
        ArrayList<String> historialCompleto = resultados.getUltimosCombates(5);
        assertNotNull("ArrayList no debe ser null", historialCompleto);
        
        // Arreglo para últimos 10
        String[] ultimos10 = resultados.getUltimos10Combates();
        assertNotNull("Arreglo no debe ser null", ultimos10);
        assertEquals("Arreglo debe tener tamaño 10", 10, ultimos10.length);
        
        System.out.println("✅ Ambas estructuras funcionando - ArrayList:" + historialCompleto.size() + ", Arreglo:" + ultimos10.length);
    }
    
    @Test
    public void testEstadisticasActualizadasCorrectamente() {
        System.out.println("=== PRUEBA HU-004: ESTADÍSTICAS ACTUALIZADAS ===");
        Resultados resultados = new Resultados();
        
        // Registrar 3 victorias y 2 derrotas
        resultados.registrarCombate("Espada", "Dragón", true);
        resultados.registrarCombate("Hacha", "Gigante", true);
        resultados.registrarCombate("Arco", "Serpiente", true);
        resultados.registrarCombate("Espada", "Troll", false);
        resultados.registrarCombate("Hacha", "Lobo", false);
        
        assertEquals("Debe tener 5 combates totales", 5, resultados.getTotalCombates());
        assertEquals("Debe tener 3 victorias", 3, resultados.getTotalVictorias());
        assertEquals("Debe tener 2 derrotas", 2, resultados.getTotalDerrotas());
        
        System.out.println("✅ Estadísticas correctas - Victorias:" + resultados.getTotalVictorias() + 
                         ", Derrotas:" + resultados.getTotalDerrotas() + 
                         ", Total:" + resultados.getTotalCombates());
    }
    
    @Test
    public void testPorcentajeVictoriasCalculadoCorrectamente() {
        System.out.println("=== PRUEBA: PORCENTAJE VICTORIAS ===");
        Resultados resultados = new Resultados();
        
        // Registrar 4 victorias y 1 derrota (80%)
        resultados.registrarCombate("Espada", "Dragón", true);
        resultados.registrarCombate("Hacha", "Gigante", true);
        resultados.registrarCombate("Arco", "Serpiente", true);
        resultados.registrarCombate("Espada", "Troll", true);
        resultados.registrarCombate("Hacha", "Lobo", false);
        
        double porcentaje = resultados.getPorcentajeVictorias();
        assertTrue("Porcentaje debe ser mayor a 0", porcentaje > 0);
        assertTrue("Porcentaje debe ser menor o igual a 100", porcentaje <= 100);
        
        System.out.println("✅ Porcentaje victorias: " + String.format("%.1f", porcentaje) + "%");
    }
    
    @Test
    public void testLogrosDesbloqueadosProgresivamente() {
        System.out.println("=== PRUEBA: LOGROS DESBLOQUEADOS ===");
        Resultados resultados = new Resultados();
        boolean[] logrosIniciales = resultados.getLogrosDesbloqueados();
        
        // Verificar que inicialmente no hay logros desbloqueados
        for (boolean logro : logrosIniciales) {
            assertFalse("Logros deben estar bloqueados inicialmente", logro);
        }
        
        // Desbloquear primer logro (1 victoria)
        resultados.registrarCombate("Espada", "Dragón", true);
        boolean[] logrosDespues1 = resultados.getLogrosDesbloqueados();
        assertTrue("Primer logro debe desbloquearse", logrosDespues1[0]);
        
        // Desbloquear segundo logro (5 victorias)
        for (int i = 0; i < 4; i++) {
            resultados.registrarCombate("Espada", "Criatura_" + i, true);
        }
        boolean[] logrosDespues5 = resultados.getLogrosDesbloqueados();
        assertTrue("Segundo logro debe desbloquearse", logrosDespues5[1]);
        
        System.out.println("✅ Logros desbloqueados progresivamente");
        System.out.println("   ➤ Primera Victoria: " + logrosDespues5[0]);
        System.out.println("   ➤ Estratega: " + logrosDespues5[1]);
        System.out.println("   ➤ Invencible: " + logrosDespues5[2]);
    }
    
    @Test
    public void testReinicioEstadisticasMantieneTotales() {
        System.out.println("=== PRUEBA: REINICIO ESTADÍSTICAS ===");
        Resultados resultados = new Resultados();
        
        // Registrar algunos combates
        resultados.registrarCombate("Espada", "Dragón", true);
        resultados.registrarCombate("Hacha", "Gigante", false);
        
        int victoriasTotalesAntes = resultados.getVictoriasTotales();
        int derrotasTotalesAntes = resultados.getDerrotasTotales();
        
        // Reiniciar estadísticas
        resultados.reiniciarEstadisticas();
        
        // Verificar que se reinician las estadísticas actuales
        assertEquals("Victorias actuales deben reiniciarse", 0, resultados.getTotalVictorias());
        assertEquals("Derrotas actuales deben reiniciarse", 0, resultados.getTotalDerrotas());
        assertEquals("Total combates debe reiniciarse", 0, resultados.getTotalCombates());
        
        // Verificar que los totales históricos se mantienen
        assertEquals("Victorias totales deben mantenerse", victoriasTotalesAntes, resultados.getVictoriasTotales());
        assertEquals("Derrotas totales deben mantenerse", derrotasTotalesAntes, resultados.getDerrotasTotales());
        
        System.out.println("✅ Reinicio correcto - Actuales: 0V 0D, Totales: " + 
                         resultados.getVictoriasTotales() + "V " + resultados.getDerrotasTotales() + "D");
    }
}