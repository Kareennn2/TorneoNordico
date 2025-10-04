/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umariana.cupi2.torneonordico.mundo;

import java.util.Random;

/**
 *
 * @author 70GA
 */
public class TorneoNordico {

    private int puntos;
    private int victorias;
    private int derrotas;
    private Random random;

    public TorneoNordico() {
        this.puntos = 100; // Puntos iniciales
        this.victorias = 0;
        this.derrotas = 0;
        this.random = new Random();
    }

    // MÉTODO 1: Ejecutar enfrentamiento
    public String ejecutarEnfrentamiento(String arma, String escenario, String criatura) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("⚔️ RESULTADO DEL ENFRENTAMIENTO ⚔️\n\n");
        resultado.append("🗡️ Arma seleccionada: ").append(arma).append("\n");
        resultado.append("🏰 Escenario: ").append(escenario).append("\n");
        resultado.append("🐉 Criatura: ").append(criatura).append("\n\n");
        resultado.append("--------------------------------\n\n");

        // Lógica simple del enfrentamiento
        boolean victoria = calcularVictoria(arma, escenario, criatura);

        if (victoria) {
            victorias++;
            puntos += 30;
            resultado.append("¡POR ODÍN! HAS VENCIDO 🏆\n");
            resultado.append("¡Has vencido a ").append(criatura).append("!\n");
            resultado.append("+30 puntos de gloria\n");
        } else {
            derrotas++;
            puntos = Math.max(0, puntos - 25);
            resultado.append("El Ragnarok te alcanzó 💀\n");
            resultado.append("Derrota contra ").append(criatura).append("\n");
            resultado.append("-40 puntos\n");
        }

        resultado.append("\nPuntos actuales: ").append(puntos);
        return resultado.toString();
    }

    // MÉTODO 2: Obtener puntos
    public String obtenerPuntos() {
        return "🏆 SISTEMA DE PUNTOS 🏆\n\n"
                + "• Puntos actuales: " + puntos + "\n"
                + "• Victorias: " + victorias + "\n"
                + "• Derrotas: " + derrotas + "\n\n"
                + "¡Sigue luchando para aumentar tu gloria!";
    }

    // MÉTODO AUXILIAR: Calcular victoria (súper simple)
    private boolean calcularVictoria(String arma, String escenario, String criatura) {
        // Lógica básica - Espada vence a Dragón, etc.

        double probabilidad = 0.3;

        // Ventajas por combinaciones específicas
        if (arma.equals("Espada") && criatura.equals("Dragon")) {
            probabilidad = 0.5;
        } else if (arma.equals("Hacha") && criatura.equals("Gigante")) {
            probabilidad = 0.4;
        } else if (arma.equals("Arco") && criatura.equals("Serpiente")) {
            probabilidad = 0.45;
        }

        // Ventaja por escenario
        if (escenario.equals("Asgard")) {
            probabilidad += 0.1;
        } else if (escenario.equals("Niflheim")) {
            probabilidad -= 0.15;
        }

        // Asegurar que la probabilidad esté entre 0.1 y 0.8
        probabilidad = Math.max(0.1, Math.min(0.8, probabilidad));

        boolean gano = random.nextDouble() < probabilidad;

        // Resultado aleatorio basado en la probabilidad
        return random.nextDouble() < probabilidad;
    }

    // Métodos para obtener estadísticas (opcionales)
    public int getPuntos() {
        return puntos;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;

    }

}
