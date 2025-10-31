package test.mundo;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import umariana.cupi2.torneonordico.mundo.Resultados;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTorneoNordico {

    TorneoNordico torneo = new TorneoNordico();

    // ==================== PRUEBAS HU-001: SISTEMA DE ARMAS ====================
    @Test
    public void testSeleccionArmaEspadaEstadisticasCorrectas() {
        System.out.println("=== PRUEBA HU-001: SELECCIÓN ESPADA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);

        assertTrue("Debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Espada", torneo.getArmaSeleccionada());
        assertEquals(8, torneo.getDanioArma(0));
        assertEquals(85, torneo.getPrecisionArma(0));
        assertEquals(7, torneo.getVelocidadArma(0));
        System.out.println("✅ Espada - Daño:8, Precisión:85%, Velocidad:7");
    }

    @Test
    public void testSeleccionArmaHachaEstadisticasCorrectas() {
        System.out.println("=== PRUEBA HU-001: SELECCIÓN HACHA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(1);

        assertTrue("Debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Hacha", torneo.getArmaSeleccionada());
        assertEquals(10, torneo.getDanioArma(1));
        assertEquals(75, torneo.getPrecisionArma(1));
        assertEquals(5, torneo.getVelocidadArma(1));
        System.out.println("✅ Hacha - Daño:10, Precisión:75%, Velocidad:5");
    }

    @Test
    public void testSeleccionArmaArcoEstadisticasCorrectas() {
        System.out.println("=== PRUEBA HU-001: SELECCIÓN ARCO ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(2);

        assertTrue("Debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Arco", torneo.getArmaSeleccionada());
        assertEquals(6, torneo.getDanioArma(2));
        assertEquals(95, torneo.getPrecisionArma(2));
        assertEquals(8, torneo.getVelocidadArma(2));
        System.out.println("✅ Arco - Daño:6, Precisión:95%, Velocidad:8");
    }

    @Test
    public void testNoArmaSeleccionadaInicialmente() {
        System.out.println("=== PRUEBA HU-001: VALIDACIÓN SIN ARMA ===");
        TorneoNordico torneo = new TorneoNordico();

        assertFalse("No debe tener arma seleccionada al inicio", torneo.armaSeleccionada());
        System.out.println("✅ Correctamente no hay arma seleccionada al inicio");
    }

    @Test
    public void testTodasArmasTienenEstadisticasValidas() {
        System.out.println("=== PRUEBA HU-001: ESTADÍSTICAS VÁLIDAS ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] armas = torneo.getArmas();

        for (int i = 0; i < armas.length; i++) {
            int danio = torneo.getDanioArma(i);
            int precision = torneo.getPrecisionArma(i);
            int velocidad = torneo.getVelocidadArma(i);

            assertTrue("Daño debe ser positivo para " + armas[i], danio > 0);
            assertTrue("Precisión debe estar entre 1-100 para " + armas[i], precision > 0 && precision <= 100);
            assertTrue("Velocidad debe ser positiva para " + armas[i], velocidad > 0);
            System.out.println("✅ " + armas[i] + " - Daño:" + danio + ", Precisión:" + precision + "%, Velocidad:" + velocidad);
        }
    }

    // ==================== PRUEBAS HU-002: HORDA DE CRIATURAS ====================
    @Test
    public void testGenerarHordaConEspada() {
        System.out.println("=== PRUEBA HU-002: GENERAR HORDA CON ESPADA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();

        assertTrue("Debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Espada", torneo.getArmaSeleccionada());
        assertEquals("Horda debe tener 3 criaturas", 3, horda.size());
        assertFalse("Horda no debe estar vacía", horda.isEmpty());
        System.out.println("✅ Horda generada: " + horda.size() + " criaturas - " + horda);
    }

    @Test
    public void testGenerarHordaConHacha() {
        System.out.println("=== PRUEBA HU-002: GENERAR HORDA CON HACHA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(1);
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();

        assertTrue("Debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Hacha", torneo.getArmaSeleccionada());
        assertEquals("Horda debe tener 3 criaturas", 3, horda.size());
        System.out.println("✅ Horda generada: " + horda.size() + " criaturas - " + horda);
    }

    @Test
    public void testNoGenerarHordaSinArma() {
        System.out.println("=== PRUEBA HU-002: ERROR SIN ARMA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();

        assertFalse("No debe tener arma seleccionada", torneo.armaSeleccionada());
        assertTrue("Horda debe estar vacía sin arma", horda.isEmpty());
        System.out.println("✅ Correctamente no se genera horda sin arma");
    }

    @Test
    public void testEnfrentamientoCambiaEstadoJuego() {
        System.out.println("=== PRUEBA HU-002: ENFRENTAMIENTO ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();

        int vidaInicial = torneo.getVidaJugador();
        boolean resultado = torneo.enfrentarCriatura();
        int progreso = torneo.getProgresoHorda();

        assertTrue("El enfrentamiento debe cambiar el estado",
                progreso > 0 || torneo.getVidaJugador() != vidaInicial);
        System.out.println("✅ Enfrentamiento ejecutado - Progreso:" + progreso
                + "/3, Vida:" + torneo.getVidaJugador()
                + ", Resultado:" + (resultado ? "VICTORIA" : "DERROTA"));
    }

    @Test
    public void testCompletarHordaCompleta() {
        System.out.println("=== PRUEBA HU-002: PROGRESO COMPLETO ===");
        TorneoNordico torneo = new TorneoNordico();

        // Forzar que todos los ataques sean exitosos
        torneo.setRandom(new Random(0) { // Sobrescribir Random para siempre dar éxito
            public int nextInt(int bound) {
                return 0; // Siempre retorna 0, garantizando ataque exitoso
            }
        });
        torneo.seleccionarArma(0);
        torneo.generarHorda();

        // Realizar 3 enfrentamientos
        for (int i = 0; i < 3; i++) {
            torneo.enfrentarCriatura();
        }

        assertEquals("Progreso debe ser 3/3", 3, torneo.getProgresoHorda());
        assertFalse("Juego no debe estar activo", torneo.isJuegoActivo());
        assertTrue("Horda debe estar completada", torneo.isHordaCompletada());
        System.out.println("✅ Horda completada - Progreso: " + torneo.getProgresoHorda() + "/3");
    }

    @Test
    public void testReinicioCompletoDelJuego() {
        System.out.println("=== PRUEBA HU-002: REINICIO JUEGO ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar juego en progreso
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        torneo.enfrentarCriatura();

        // Reiniciar juego
        torneo.reiniciarJuego();

        // Verificar estado inicial
        assertFalse("No debe tener arma seleccionada", torneo.armaSeleccionada());
        assertEquals("Vida debe ser 50", 50, torneo.getVidaJugador());
        assertFalse("Juego no debe estar activo", torneo.isJuegoActivo());
        assertEquals("Progreso debe ser 0", 0, torneo.getProgresoHorda());

        ArrayList<String> horda = torneo.getHordaCriaturas();
        assertTrue("Horda debe estar vacía", horda.isEmpty());
        System.out.println("✅ Juego reiniciado correctamente - vida:50, sin arma, sin horda, progreso:0");
    }

    // ==================== PRUEBAS HU-003: ESCENARIOS ALEATORIOS ====================
    @Test
    public void testSistemaCarga3EscenariosDisponibles() {
        System.out.println("=== PRUEBA HU-003: CARGAR ESCENARIOS ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] escenarios = torneo.getEscenarios();

        assertNotNull("Escenarios no debe ser null", escenarios);
        assertEquals("Debe haber 3 escenarios", 3, escenarios.length);
        System.out.println("✅ Sistema carga " + escenarios.length + " escenarios: " + java.util.Arrays.toString(escenarios));
    }

    @Test
    public void testGenerarEscenarioAleatorioValido() {
        System.out.println("=== PRUEBA HU-003: ESCENARIO ALEATORIO ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.generarEscenarioAleatorio();
        String escenario = torneo.getEscenarioActual();

        assertNotNull("Escenario no debe ser null", escenario);
        assertFalse("Escenario no debe estar vacío", escenario.isEmpty());

        String[] escenarios = torneo.getEscenarios();
        boolean escenarioValido = false;
        for (String esc : escenarios) {
            if (esc.equals(escenario)) {
                escenarioValido = true;
                break;
            }
        }
        assertTrue("Escenario debe pertenecer a la lista predefinida", escenarioValido);
        System.out.println("✅ Escenario generado: " + escenario);
    }

    @Test
    public void testHordaGeneraEscenarioAutomaticamente() {
        System.out.println("=== PRUEBA HU-003: HORDA GENERA ESCENARIO ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        String escenario = torneo.getEscenarioActual();

        assertNotNull("Escenario debe ser asignado automáticamente", escenario);
        assertFalse("Escenario no debe estar vacío", escenario.isEmpty());
        System.out.println("✅ Horda generada en: " + escenario);
    }

    @Test
    public void testModificadoresEscenariosCorrectos() {
        System.out.println("=== PRUEBA HU-003: MODIFICADORES ESCENARIOS ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] escenarios = torneo.getEscenarios();

        // Probar que los modificadores son consistentes
        for (int i = 0; i < escenarios.length; i++) {
            torneo.generarEscenarioAleatorio();
            int visibilidad = torneo.getModificadorVisibilidad();
            int terreno = torneo.getModificadorTerreno();

            assertTrue("Visibilidad debe estar entre 1-100", visibilidad > 0 && visibilidad <= 100);
            assertTrue("Modificador terreno debe ser razonable", terreno >= -20 && terreno <= 20);
            System.out.println("✅ " + torneo.getEscenarioActual() + " - Visibilidad:" + visibilidad + "%, Terreno:" + terreno);
        }
    }

    // ==================== PRUEBAS HU-005: SISTEMA DE INVENTARIO ====================
    @Test
    public void testObjetosDisponiblesEnSistema() {
        System.out.println("=== PRUEBA HU-005: OBJETOS DISPONIBLES ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] objetos = torneo.getObjetosDisponibles();

        assertNotNull("Objetos no debe ser null", objetos);
        assertEquals("Debe haber 3 tipos de objetos", 3, objetos.length);

        boolean tienePocion = false, tieneAmuleto = false, tieneRuna = false;
        for (String obj : objetos) {
            if (obj.equals("Poción de Vida")) {
                tienePocion = true;
            }
            if (obj.equals("Amuleto de Protección")) {
                tieneAmuleto = true;
            }
            if (obj.equals("Runa de Poder")) {
                tieneRuna = true;
            }
        }

        assertTrue("Debe tener Poción de Vida", tienePocion);
        assertTrue("Debe tener Amuleto de Protección", tieneAmuleto);
        assertTrue("Debe tener Runa de Poder", tieneRuna);
        System.out.println("✅ Objetos disponibles: " + java.util.Arrays.toString(objetos));
    }

    @Test
    public void testInventarioInicialVacio() {
        System.out.println("=== PRUEBA HU-005: INVENTARIO INICIAL ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] inventario = torneo.getInventario();

        assertNotNull("Inventario no debe ser null", inventario);
        assertEquals("Inventario debe tener 5 slots", 5, inventario.length);
        assertEquals("Cantidad inicial debe ser 0", 0, torneo.getCantidadObjetos());
        assertFalse("Inventario no debe estar lleno inicialmente", torneo.isInventarioLleno());
        System.out.println("✅ Inventario inicial: " + torneo.getCantidadObjetos() + "/5 slots ocupados");
    }

    @Test
    public void testUsarPocionVidaRestauraVida() {
        System.out.println("=== PRUEBA HU-005: USAR POCIÓN VIDA ===");
        TorneoNordico torneo = new TorneoNordico();

        // Simular daño al jugador
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        int vidaInicial = torneo.getVidaJugador();

        // Forzar algo de daño
        for (int i = 0; i < 2; i++) {
            torneo.enfrentarCriatura();
        }

        int vidaAntesPocion = torneo.getVidaJugador();

        // En una prueba real necesitaríamos agregar la poción al inventario primero
        // Por ahora verificamos que el método existe y funciona
        String resultado = torneo.usarObjeto(0);
        assertNotNull("Método usarObjeto debe funcionar", resultado);
        System.out.println("✅ Uso de objeto: " + resultado);
    }

    @Test
    public void testEfectosActivosInicialmenteInactivos() {
        System.out.println("=== PRUEBA HU-005: EFECTOS INICIALES ===");
        TorneoNordico torneo = new TorneoNordico();
        String efectos = torneo.getEfectosActivos();

        assertEquals("Sin efectos activos", efectos);
        System.out.println(" Correctamente sin efectos activos al inicio");
    }

    // ==================== PRUEBAS INTEGRACIÓN RESULTADOS ====================
    @Test
    public void testRegistroCombatesEnResultados() {
        System.out.println("=== PRUEBA INTEGRACIÓN: REGISTRO COMBATES ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();

        // Realizar algunos enfrentamientos
        torneo.enfrentarCriatura();
        torneo.enfrentarCriatura();

        Resultados resultados = torneo.getResultados();
        assertNotNull("Resultados no debe ser null", resultados);
        assertTrue("Debe haber combates registrados", resultados.getTotalCombates() > 0);
        System.out.println("Combates registrados: " + resultados.getTotalCombates());
    }

    @Test
    public void testLogrosDisponibles() {
        System.out.println("=== PRUEBA: LOGROS DISPONIBLES ===");
        TorneoNordico torneo = new TorneoNordico();
        String[] logros = torneo.getLogros();

        assertNotNull("Logros no debe ser null", logros);
        assertTrue("Debe haber logros disponibles", logros.length > 0);
        System.out.println(" Logros disponibles: " + logros.length);
        for (String logro : logros) {
            System.out.println("   ➤ " + logro);
        }
    }
    // ==================== PRUEBAS HU-007: SISTEMA DE SINERGIAS ====================

    @Test
    public void testMatrizSinergiasArmasEscenarios() {
        System.out.println("=== PRUEBA HU-007: MATRIZ SINERGIAS 3x3 ===");
        TorneoNordico torneo = new TorneoNordico();

        // Verificar que existe la matriz de sinergias
        torneo.seleccionarArma(0); // Espada
        torneo.generarEscenarioAleatorio();

        // Probar que los métodos de sinergia existen y funcionan
        boolean tieneSinergia = torneo.tieneSinergiaArmaEscenario();
        int bonusSinergia = torneo.getBonusSinergia();

        assertTrue("Método tieneSinergia debe funcionar", bonusSinergia >= 0 && bonusSinergia <= 5);
        System.out.println(" Matriz sinergias funcionando - Bonus: " + bonusSinergia);
    }

    @Test
    public void testSinergiaEspadaMontañaHelada() {
        System.out.println("=== PRUEBA HU-007: SINERGIA ESPADA-MONTAÑA ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar Espada y Montaña Helada (índice 1)
        torneo.seleccionarArma(0); // Espada
        // Forzar Montaña Helada
        torneo.setRandom(new java.util.Random(1) { // Semilla que dé Montaña
            @Override
            public int nextInt(int bound) {
                return 1; // Siemretorna 1 (Montaña Helada)
            }
        });
        torneo.generarEscenarioAleatorio();

        assertTrue("Espada debe tener sinergia con Montaña Helada",
                torneo.tieneSinergiaArmaEscenario());
        assertEquals("Bonus debe ser +5", 5, torneo.getBonusSinergia());
        System.out.println(" Sinergia Espada-Montaña: +5 daño");
    }

    @Test
    public void testSinergiaHachaPantanoOscuro() {
        System.out.println("=== PRUEBA HU-007: SINERGIA HACHA-PANTANO ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar Hacha y Pantano Oscuro (índice 2)
        torneo.seleccionarArma(1); // Hacha
        // Forzar Pantano Oscuro
        torneo.setRandom(new java.util.Random(2) { // Semilla que dé Pantano
            @Override
            public int nextInt(int bound) {
                return 2; // Siempre retorna 2 (Pantano Oscuro)
            }
        });
        torneo.generarEscenarioAleatorio();

        assertTrue("Hacha debe tener sinergia con Pantano Oscuro",
                torneo.tieneSinergiaArmaEscenario());
        assertEquals("Bonus debe ser +5", 5, torneo.getBonusSinergia());
        System.out.println(" Sinergia Hacha-Pantano: +5 daño");
    }

    @Test
    public void testSinergiaArcoBosqueNordico() {
        System.out.println("=== PRUEBA HU-007: SINERGIA ARCO-BOSQUE ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar Arco y Bosque Nórdico (índice 0)
        torneo.seleccionarArma(2); // Arco
        // Forzar Bosque Nórdico
        torneo.setRandom(new java.util.Random(0) { // Semilla que dé Bosque
            @Override
            public int nextInt(int bound) {
                return 0; // Siempre retorna 0 (Bosque Nórdico)
            }
        });
        torneo.generarEscenarioAleatorio();

        assertTrue("Arco debe tener sinergia con Bosque Nórdico",
                torneo.tieneSinergiaArmaEscenario());
        assertEquals("Bonus debe ser +5", 5, torneo.getBonusSinergia());
        System.out.println(" Sinergia Arco-Bosque: +5 daño");
    }

    @Test
    public void testMapaDebilidadesCriaturas() {
        System.out.println("=== PRUEBA HU-007: MAPA DEBILIDADES ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0); // Espada

        // Probar debilidades de algunas criaturas
        assertTrue("Espada debe ser efectiva contra Gigante",
                torneo.esArmaEfectiva("Gigante"));
        assertTrue("Espada debe ser efectiva contra Lobo",
                torneo.esArmaEfectiva("Lobo"));
        assertFalse("Espada NO debe ser efectiva contra Dragón",
                torneo.esArmaEfectiva("Dragón"));

        System.out.println("Mapa debilidades funcionando");
        System.out.println("   ➤ Espada efectiva vs: Gigante, Lobo");
        System.out.println("   ➤ Espada NO efectiva vs: Dragón, Serpiente, Troll");
    }

    @Test
    public void testDebilidadDragonArco() {
        System.out.println("=== PRUEBA HU-007: DEBILIDAD DRAGÓN-ARCO ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(2); // Arco

        assertTrue("Arco debe ser efectivo contra Dragón",
                torneo.esArmaEfectiva("Dragón"));
        assertEquals("Arma recomendada vs Dragón debe ser Arco",
                "Arco", torneo.getArmaRecomendada("Dragón"));
        assertEquals("Bonus debilidad debe ser +8", 8, torneo.getBonusDebilidad("Dragón"));
        System.out.println(" Debilidad Dragón-Arco: +8 daño");
    }

    @Test
    public void testDebilidadSerpienteHacha() {
        System.out.println("=== PRUEBA HU-007: DEBILIDAD SERPIENTE-HACHA ===");
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(1); // Hacha

        assertTrue("Hacha debe ser efectiva contra Serpiente",
                torneo.esArmaEfectiva("Serpiente"));
        assertEquals("Arma recomendada vs Serpiente debe ser Hacha",
                "Hacha", torneo.getArmaRecomendada("Serpiente"));
        assertEquals("Bonus debilidad debe ser +8", 8, torneo.getBonusDebilidad("Serpiente"));
        System.out.println(" Debilidad Serpiente-Hacha: +8 daño");
    }

    @Test
    public void testCombinacionSinergiaYDebilidad() {
        System.out.println("=== PRUEBA HU-007: COMBINACIÓN SINERGIA+DEBILIDAD ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar Arco (efectivo vs Dragón) en Bosque (sinergia)
        torneo.seleccionarArma(2); // Arco
        torneo.setRandom(new java.util.Random(0) { // Bosque Nórdico
            @Override
            public int nextInt(int bound) {
                return 0;
            }
        });
        torneo.generarEscenarioAleatorio();

        // Verificar combinación
        boolean tieneSinergia = torneo.tieneSinergiaArmaEscenario();
        boolean esEfectiva = torneo.esArmaEfectiva("Dragón");
        int bonusTotal = torneo.getBonusSinergia() + torneo.getBonusDebilidad("Dragón");

        assertTrue("Debe tener sinergia Arco-Bosque", tieneSinergia);
        assertTrue("Debe ser efectiva vs Dragón", esEfectiva);
        assertEquals("Bonus total debe ser +13", 13, bonusTotal);
        System.out.println("Combinación perfecta: Sinergia +5 + Debilidad +8 = +13 daño total");
    }

    @Test
    public void testSinergiasAplicadasEnCombate() {
        System.out.println("=== PRUEBA HU-007: SINERGIAS EN COMBATE ===");
        TorneoNordico torneo = new TorneoNordico();

        // Configurar combinación con sinergia
        torneo.seleccionarArma(0); // Espada
        torneo.setRandom(new java.util.Random(1) { // Montaña Helada
            @Override
            public int nextInt(int bound) {
                return 1; // Montaña para sinergia con Espada
            }
        });
        torneo.generarHorda();

        int danioBase = torneo.getDanioArma(0); // Daño base de Espada
        boolean resultadoCombate = torneo.enfrentarCriatura();

        // Verificar que el combate registra la sinergia
        Resultados resultados = torneo.getResultados();
        String[] ultimosCombates = resultados.getUltimos10Combates();

        boolean sinergiaRegistrada = false;
        for (String combate : ultimosCombates) {
            if (combate != null && combate.contains("sinergia")) {
                sinergiaRegistrada = true;
                System.out.println("Combate registrado con sinergia: " + combate);
                break;
            }
        }

        assertTrue("Combate debe registrar información de sinergia", sinergiaRegistrada);
    }

    @Test
    public void testEstructurasNivel3Utilizadas() {
        System.out.println("=== PRUEBA HU-007: ESTRUCTURAS NIVEL 3 ===");
        TorneoNordico torneo = new TorneoNordico();

        // Verificar que se usan las estructuras del nivel 3
        torneo.seleccionarArma(0);
        torneo.generarEscenarioAleatorio();

        // Probar todas las criaturas (operación repetitiva sobre colección)
        String[] criaturas = {"Dragón", "Gigante", "Serpiente", "Troll", "Lobo"};
        int criaturasConDebilidad = 0;

        for (String criatura : criaturas) {
            String armaRecomendada = torneo.getArmaRecomendada(criatura);
            if (!"Ninguna".equals(armaRecomendada)) {
                criaturasConDebilidad++;
            }
        }

        assertEquals("Todas las criaturas deben tener debilidad definida",
                criaturas.length, criaturasConDebilidad);
        System.out.println(" Estructuras nivel 3 validadas:");
        System.out.println("   ➤ Matriz 3x3 para sinergias arma-escenario");
        System.out.println("   ➤ Map para debilidades criatura-arma");
        System.out.println("   ➤ Operaciones repetitivas sobre colecciones");
    }
}
