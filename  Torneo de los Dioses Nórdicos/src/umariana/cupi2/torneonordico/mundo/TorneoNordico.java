package umariana.cupi2.torneonordico.mundo;

import java.util.ArrayList;
import java.util.Random;

public class TorneoNordico {

    // Estructuras contenedoras de tamaño fijo 
    private final String[] ARMAS = {"Espada", "Hacha", "Arco"};
    private final int[] DANIO_ARMAS = {8, 10, 6};
    private final int[] PRECISION_ARMAS = {85, 75, 95};
    private final int[] VELOCIDAD_ARMAS = {7, 5, 8};

    private final String[] CRIATURAS = {"Dragón", "Gigante", "Serpiente", "Troll", "Lobo"};
    private final int[] VIDA_CRIATURAS = {30, 25, 20, 35, 15};

    // Estructuras contenedoras de tamaño variable 
    private ArrayList<String> hordaCriaturas;
    private ArrayList<String> historialArmas;

    // Atributos del juego
    private String armaSeleccionada;
    private int indiceArmaSeleccionada;
    private int criaturaActual;
    private int vidaJugador;
    private boolean juegoActivo;

    private Random random;

    public TorneoNordico() {
        random = new Random();
        hordaCriaturas = new ArrayList<>();
        historialArmas = new ArrayList<>();
        vidaJugador = 50;
        juegoActivo = false;
        criaturaActual = 0;
    }

    // Sistema de Arsenal de Armas
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

    public boolean armaSeleccionada() {
        return armaSeleccionada != null;
    }

    //Horda de Criaturas
    public void generarHorda() {
        hordaCriaturas.clear();
        criaturaActual = 0;
        juegoActivo = true;

        //Instrucciones repetitivas para generar horda 
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

        // Simular enfrentamiento
        int danioBase = getDanioArma(indiceArmaSeleccionada);
        int precision = getPrecisionArma(indiceArmaSeleccionada);

        // Verificar si el ataque es exitoso
        if (random.nextInt(100) < precision) {
            // Ataque exitoso
            criaturaActual++;

            // Verificar si se completó la horda
            if (criaturaActual >= hordaCriaturas.size()) {
                juegoActivo = false;
            }
            return true;
        } else {
            // Ataque fallido - el jugador recibe daño
            vidaJugador -= 10;
            if (vidaJugador <= 0) {
                juegoActivo = false;
            }
            return false;
        }
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
        hordaCriaturas.clear();
        historialArmas.clear();
    }
}
