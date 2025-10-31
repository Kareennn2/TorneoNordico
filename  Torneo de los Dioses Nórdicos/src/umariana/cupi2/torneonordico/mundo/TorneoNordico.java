package umariana.cupi2.torneonordico.mundo;

import java.util.ArrayList;
import java.util.Random;

public class TorneoNordico {

    // Estructuras contenedoras de tamaño fijo 
    private final String[] ARMAS = {"Espada", "Hacha", "Arco"};
    private final String[] ESCENARIOS = {"Bosque Nórdico", "Montaña Helada", "Pantano Oscuro"};
    private final int[] DANIO_ARMAS = {8, 10, 6};
    private final int[] PRECISION_ARMAS = {85, 75, 95};
    private final int[] VELOCIDAD_ARMAS = {7, 5, 8};
    private final int[] VISIBILIDAD_ESCENARIOS = {70, 90, 50};
    private final int[] VENTAJA_TERRENO = {0, -10, 10};
    private final String[] CRIATURAS = {"Dragón", "Gigante", "Serpiente", "Troll", "Lobo"};
    private final int[] VIDA_CRIATURAS = {30, 25, 20, 35, 15};
    private final int[][] SINERGIAS_ARMAS_ESCENARIOS = {
        {0, 1, 0}, // Espada: neutral en Bosque, bonus en Montaña, neutral en Pantano
        {0, 0, 1}, // Hacha: neutral en Bosque, neutral en Montaña, bonus en Pantano  
        {1, 0, 0} // Arco: bonus en Bosque, neutral en Montaña, neutral en Pantano
    };

    // Estructuras contenedoras de tamaño variable 
    private ArrayList<String> hordaCriaturas;
    private ArrayList<String> historialArmas;

    // Atributos del juego
    private String armaSeleccionada;
    private int indiceArmaSeleccionada;
    private int criaturaActual;
    private int vidaJugador;
    private boolean juegoActivo;
    private String escenarioActual;
    private int indiceEscenarioActual;
    private String[] inventario;
    private int cantidadObjetos;
    private boolean efectoActivo;
    private String tipoEfectoActivo;
    private int duracionEfecto;
    private int bonusDanioTemporal;
    private int reduccionDanioTemporal;
    private int turnosBonusRestantes;
    private Resultados resultados;

    private Random random;

    public TorneoNordico() {
        random = new Random();
        hordaCriaturas = new ArrayList<>();
        historialArmas = new ArrayList<>();
        resultados = new Resultados();
        inventario = new String[5];
        cantidadObjetos = 0;
        efectoActivo = false;
        vidaJugador = 50;
        juegoActivo = false;
        criaturaActual = 0;
        bonusDanioTemporal = 0;
        reduccionDanioTemporal = 0;
        turnosBonusRestantes = 0;
    }

    // ==================== SISTEMA DE ARSENAL DE ARMAS ====================
    public String[] getArmas() {
        return ARMAS;
    }

    public int getDanioArma(int indice) {
        return DANIO_ARMAS[indice];
    }

    public int getPrecisionArma(int indice) {
        return PRECISION_ARMAS[indice];
    }

    public int getVelocidadArma(int indice) {
        return VELOCIDAD_ARMAS[indice];
    }

    public void seleccionarArma(int indice) {
        if (indice >= 0 && indice < ARMAS.length) {
            armaSeleccionada = ARMAS[indice];
            indiceArmaSeleccionada = indice;
            historialArmas.add(armaSeleccionada);
        }
    }

    public String getArmaSeleccionada() {
        return armaSeleccionada;
    }

    /**
     *
     * @return
     */
    public boolean armaSeleccionada() {
        return armaSeleccionada != null && indiceArmaSeleccionada >= 0;
    }

    /**
     * Verifica si el arma actual tiene sinergia con el escenario actual
     *
     * @return true si hay sinergia, false en caso contrario
     */
    public boolean tieneSinergiaArmaEscenario() {
        if (indiceArmaSeleccionada < 0 || indiceEscenarioActual < 0) {
            return false;
        }
        return SINERGIAS_ARMAS_ESCENARIOS[indiceArmaSeleccionada][indiceEscenarioActual] == 1;
    }

    /**
     * Obtiene el bonus de daño por sinergia arma-escenario
     *
     * @return 5 puntos de daño extra si hay sinergia, 0 en caso contrario
     */
    public int getBonusSinergia() {
        return tieneSinergiaArmaEscenario() ? 5 : 0;
    }

    /**
     * Verifica si el arma actual es efectiva contra la criatura especificada
     *
     * @param criatura Nombre de la criatura a verificar
     * @return true si el arma es efectiva, false en caso contrario
     */
    public boolean esArmaEfectiva(String criatura) {
        if (armaSeleccionada == null || criatura == null) {
            return false;
        }
        String armaEfectiva = DEBILIDADES_CRIATURAS.get(criatura);
        return armaSeleccionada.equals(armaEfectiva);
    }

    /**
     * Obtiene el arma recomendada contra una criatura específica
     *
     * @param criatura Nombre de la criatura
     * @return Arma recomendada o "Ninguna" si no hay debilidad definida
     */
    public String getArmaRecomendada(String criatura) {
        return DEBILIDADES_CRIATURAS.getOrDefault(criatura, "Ninguna");
    }

    // ==================== HORDA DE CRIATURAS ====================
    public void generarHorda() {
        if (!armaSeleccionada()) {
            return; // No generar horda si no hay arma seleccionada
        }

        hordaCriaturas.clear();
        criaturaActual = 0;
        juegoActivo = true;

        generarEscenarioAleatorio(); // ¡NUEVO!

        for (int i = 0; i < 3; i++) {
            int indiceCriatura = random.nextInt(CRIATURAS.length);
            hordaCriaturas.add(CRIATURAS[indiceCriatura]);
        }
    }

    public ArrayList<String> getHordaCriaturas() {
        return hordaCriaturas;
    }

    public String getCriaturaActual() {
        if (criaturaActual < hordaCriaturas.size()) {
            return hordaCriaturas.get(criaturaActual);
        }
        return null;
    }

    public int getProgresoHorda() {
        return criaturaActual;
    }

    public int getTotalCriaturas() {
        return hordaCriaturas.size();
    }

    public boolean enfrentarCriatura() {
        if (!juegoActivo || criaturaActual >= hordaCriaturas.size()) {
            return false;
        }

        String criaturaActualNombre = hordaCriaturas.get(criaturaActual);
        int danioBase = getDanioArma(indiceArmaSeleccionada) + bonusDanioTemporal;
        int precisionBase = getPrecisionArma(indiceArmaSeleccionada);
        int bonusSinergia = getBonusSinergia(); // Bonus por sinergia arma-escenario

        int bonusDebilidad = getBonusDebilidad(criaturaActualNombre); // Bonus por debilidad criatura

        // Aplicar modificadores
        int danioFinal = Math.max(1, danioBase + getModificadorTerreno());
        int precisionFinal = precisionBase * getModificadorVisibilidad() / 100;

        boolean ataqueExitoso = random.nextInt(100) < precisionFinal;

        if (ataqueExitoso) {
            // REGISTRAR INFORMACIÓN DE SINERGIAS/DEBILIDADES EN EL COMBATE
            String detallesExtra = "";
            if (bonusSinergia > 0) {
                detallesExtra += " (+" + bonusSinergia + " sinergia)";
            }
            if (bonusDebilidad > 0) {
                detallesExtra += " (+" + bonusDebilidad + " debilidad)";
            }
            // COMBATE EXITOSO - derrotar criatura actual

            resultados.registrarCombate(armaSeleccionada, criaturaActualNombre, true);
            criaturaActual++;

            // Verificar si se completó la horda
            if (criaturaActual >= hordaCriaturas.size()) {
                juegoActivo = false;
            }
        } else {
            // COMBATE FALLIDO - recibir daño
            int danioRecibido = Math.max(1, 10 - reduccionDanioTemporal);
            vidaJugador -= danioRecibido;
            resultados.registrarCombate(armaSeleccionada, criaturaActualNombre, false);

            if (vidaJugador <= 0) {
                juegoActivo = false;
            }
        }

        // Reducir duración de efectos
        if (turnosBonusRestantes > 0) {
            turnosBonusRestantes--;
            if (turnosBonusRestantes == 0) {
                bonusDanioTemporal = 0;
                reduccionDanioTemporal = 0;
            }
        }

        return ataqueExitoso;
    }

    /**
     * MAPA de debilidades criatura-arma Estructura contenedora clave-valor para
     * relaciones criatura-arma Cada criatura es débil a 1 arma específica
     */
    private final java.util.Map<String, String> DEBILIDADES_CRIATURAS = java.util.Map.of(
            "Dragón", "Arco", // Dragón es débil al Arco
            "Gigante", "Espada", // Gigante es débil a la Espada
            "Serpiente", "Hacha", // Serpiente es débil al Hacha
            "Troll", "Arco", // Troll es débil al Arco
            "Lobo", "Espada" // Lobo es débil a la Espada
    );

    /**
     * Obtiene el bonus de daño por debilidad de criatura
     *
     * @param criatura Nombre de la criatura
     * @return 8 puntos de daño extra si el arma es efectiva, 0 en caso
     * contrario
     */
    public int getBonusDebilidad(String criatura) {
        return esArmaEfectiva(criatura) ? 8 : 0;
    }

    public String[] getObjetosDisponibles() {
        return new String[]{"Poción de Vida", "Amuleto de Protección", "Runa de Poder"};
    }

    public int getVidaJugador() {
        return vidaJugador;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public boolean isHordaCompletada() {
        return criaturaActual >= hordaCriaturas.size() && hordaCriaturas.size() > 0;
    }

    public void reiniciarJuego() {
        vidaJugador = 50;
        juegoActivo = false;
        criaturaActual = 0;
        armaSeleccionada = null;
        indiceArmaSeleccionada = -1;
        escenarioActual = null;
        indiceEscenarioActual = -1;
        hordaCriaturas.clear();
        historialArmas.clear();
        efectoActivo = false;
        tipoEfectoActivo = null;
    }

    public String[] getEscenarios() {
        return ESCENARIOS;
    }

    public void generarEscenarioAleatorio() {
        int indice = random.nextInt(ESCENARIOS.length);
        escenarioActual = ESCENARIOS[indice];
        indiceEscenarioActual = indice;
    }

    public String getEscenarioActual() {
        return escenarioActual;
    }

    public int getModificadorVisibilidad() {
        return VISIBILIDAD_ESCENARIOS[indiceEscenarioActual];
    }

    public int getModificadorTerreno() {
        return VENTAJA_TERRENO[indiceEscenarioActual];
    }

    // ==================== SISTEMA DE INVENTARIO ====================
    /**
     * Agrega un objeto al inventario si hay espacio disponible
     *
     * @param objeto El objeto a agregar
     * @return true si se agregó exitosamente, false si el inventario está lleno
     */
    public boolean agregarAlInventario(String objeto) {
        if (cantidadObjetos >= inventario.length) {
            return false; // Inventario lleno
        }

        // Buscar primer slot vacío
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = objeto;
                cantidadObjetos++;
                return true;
            }
        }
        return false;
    }

    /**
     * Usa un objeto del inventario en la posición especificada
     *
     * @param indice La posición del objeto en el inventario (0-4)
     * @return Mensaje con el resultado del uso del objeto
     */
    public String usarObjeto(int indice) {
        if (indice < 0 || indice >= inventario.length || inventario[indice] == null) {
            return "ERROR: No hay objeto en este slot";
        }

        String objeto = inventario[indice];
        String mensaje = "";

        switch (objeto) {
            case "Poción de Vida":
                int vidaAnterior = vidaJugador;
                vidaJugador = Math.min(50, vidaJugador + 25);
                mensaje = "¡Poción usada! Vida +25 (" + vidaAnterior + " → " + vidaJugador + ")";
                break;

            case "Amuleto de Protección":
                reduccionDanioTemporal = 5;
                turnosBonusRestantes = 3;
                mensaje = "¡Amuleto activado! -5 de daño recibido por 3 turnos";
                break;

            case "Runa de Poder":
                bonusDanioTemporal = 4;
                turnosBonusRestantes = 2;
                mensaje = "¡Runa activada! +4 de daño por 2 turnos";
                break;
        }

        if (!mensaje.isEmpty()) {
            inventario[indice] = null;
            cantidadObjetos--;
        }

        return mensaje;
    }

    public String[] getInventario() {
        return inventario;
    }

    public int getCantidadObjetos() {
        return cantidadObjetos;
    }

    public boolean isInventarioLleno() {
        return cantidadObjetos >= inventario.length;
    }

    public String getEfectosActivos() {
        if (turnosBonusRestantes == 0) {
            return "Sin efectos activos";
        }

        StringBuilder efectos = new StringBuilder();
        if (bonusDanioTemporal > 0) {
            efectos.append("+").append(bonusDanioTemporal).append(" daño (").append(turnosBonusRestantes).append(" turnos) ");
        }
        if (reduccionDanioTemporal > 0) {
            efectos.append("-").append(reduccionDanioTemporal).append(" daño recibido (").append(turnosBonusRestantes).append(" turnos)");
        }
        return efectos.toString();
    }

    // ==================== SISTEMA DE RESULTADOS ====================
    public String[] getLogros() {
        return new String[]{
            "Primera Victoria - Gana tu primer combate",
            "Estratega - Gana 5 combates en total",
            "Invencible - Vence una horda completa sin perder"
        };
    }

    public Resultados getResultados() {
        return resultados;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
