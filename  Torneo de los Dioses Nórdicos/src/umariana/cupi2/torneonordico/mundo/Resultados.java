package umariana.cupi2.torneonordico.mundo;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Resultados {

    private ArrayList<String> historialCombates;
    private int victorias;
    private int derrotas;
    private String[] ultimos10Combates;
    private int contadorCombates;
    private int victoriasTotales;
    private int derrotasTotales;
    private boolean[] logrosDesbloqueados;

    public Resultados() {
        historialCombates = new ArrayList<>();
        victorias = 0;
        derrotas = 0;
        ultimos10Combates = new String[10];
        contadorCombates = 0;
        victoriasTotales = 0;
        derrotasTotales = 0;
        logrosDesbloqueados = new boolean[3];
    }

    /**
     * Registra un combate en el historial
     *
     * @param arma El arma utilizada en el combate
     * @param criatura La criatura enfrentada
     * @param victoria true si el jugador ganó, false si perdió
     */
    public void registrarCombate(String arma, String criatura, boolean victoria) {
        // Obtener fecha y hora actual
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM HH:mm");
        String fechaHora = formatter.format(new Date());

        String resultado = victoria ? "VICTORIA" : "DERROTA";
        String registro = fechaHora + " | " + arma + " vs " + criatura + " - " + resultado;

        // Agregar a historial completo (ArrayList tamaño variable)
        historialCombates.add(registro);

        // Agregar a últimos 10 (arreglo tamaño fijo)
        ultimos10Combates[contadorCombates % 10] = registro;
        contadorCombates++;

        if (victoria) {
            victorias++;
            victoriasTotales++;
        } else {
            derrotas++;
            derrotasTotales++;
        }

        verificarLogros();
    }

    /**
     * Verifica y desbloquea logros según las estadísticas
     */
    private void verificarLogros() {
        // Logro 0: Primera Victoria
        if (victoriasTotales >= 1 && !logrosDesbloqueados[0]) {
            logrosDesbloqueados[0] = true;
        }

        // Logro 1: Estratega (5 victorias)
        if (victoriasTotales >= 5 && !logrosDesbloqueados[1]) {
            logrosDesbloqueados[1] = true;
        }

        // Logro 2: Invencible (10 victorias totales)
        if (victoriasTotales >= 10 && !logrosDesbloqueados[2]) {
            logrosDesbloqueados[2] = true;
        }
    }

    /**
     * Obtiene los últimos N combates del historial
     *
     * @param cantidad Número de combates a obtener
     * @return Lista con los últimos combates
     */
    public ArrayList<String> getUltimosCombates(int cantidad) {
        ArrayList<String> ultimos = new ArrayList<>();
        int inicio = Math.max(0, historialCombates.size() - cantidad);

        // Instrucciones repetitivas para recorrer lista
        for (int i = inicio; i < historialCombates.size(); i++) {
            ultimos.add(historialCombates.get(i));
        }
        return ultimos;
    }

    public String[] getUltimos10Combates() {
        return ultimos10Combates;
    }

    public int getTotalVictorias() {
        return victorias;
    }

    public int getTotalDerrotas() {
        return derrotas;
    }

    /**
     * Cuenta las victorias en el historial completo
     *
     * @return Número total de victorias
     */
    public int getVictorias() {
        int victoriasCount = 0;
        // Usando instrucción repetitiva para contar victorias
        for (String combate : historialCombates) {
            if (combate.contains("VICTORIA")) {
                victoriasCount++;
            }
        }
        return victoriasCount;
    }

    public int getDerrotas() {
        return historialCombates.size() - getVictorias();
    }

    /**
     * Reinicia las estadísticas actuales (no las totales)
     */
    public void reiniciarEstadisticas() {
        historialCombates.clear();
        victorias = 0;
        derrotas = 0;
        // No reiniciamos los totales para mantener estadísticas históricas
    }

    public int getTotalCombates() {
        return historialCombates.size();
    }

    public int getVictoriasTotales() {
        return victoriasTotales;
    }

    public int getDerrotasTotales() {
        return derrotasTotales;
    }

    /**
     * Calcula el porcentaje de victorias
     *
     * @return Porcentaje de victorias (0.0 a 100.0)
     */
    public double getPorcentajeVictorias() {
        int total = victoriasTotales + derrotasTotales;
        if (total == 0) {
            return 0.0;
        }
        return (victoriasTotales * 100.0) / total;
    }

    public String[] getLogros() {
        return new String[]{
            "Primera Victoria - Gana tu primer combate",
            "Estratega - Gana 5 combates en total",
            "Invencible - Gana 10 combates en total"
        };
    }

    public boolean[] getLogrosDesbloqueados() {
        return logrosDesbloqueados;
    }

    public int getContadorCombates() {
        return contadorCombates;
    }
}
