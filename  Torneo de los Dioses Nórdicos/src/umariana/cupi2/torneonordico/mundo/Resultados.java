package umariana.cupi2.torneonordico.mundo;

import java.util.ArrayList;

public class Resultados {
    
    private ArrayList<String> historialCombates;
    private int victorias;
    private int derrotas;
    
    public Resultados() {
        historialCombates = new ArrayList<>();
        victorias = 0;
        derrotas = 0;
    }
    
    public void registrarCombate(String arma, String criatura, boolean victoria) {
        String resultado = "Arma: " + arma + " vs " + criatura + " - " + 
                          (victoria ? "VICTORIA" : "DERROTA");
        historialCombates.add(resultado);
        
        if (victoria) {
            victorias++;
        } else {
            derrotas++;
        }
    }
    
    public ArrayList<String> getUltimosCombates(int cantidad) {
        ArrayList<String> ultimos = new ArrayList<>();
        int inicio = Math.max(0, historialCombates.size() - cantidad);
        
        //Instrucciones repetitivas para recorrer lista
        for (int i = inicio; i < historialCombates.size(); i++) {
            ultimos.add(historialCombates.get(i));
        }
        return ultimos;
    }
    
    public int getTotalVictorias() {
        return victorias;
    }
    
    public int getTotalDerrotas() {
        return derrotas;
    }
    
    public void reiniciarEstadisticas() {
        historialCombates.clear();
        victorias = 0;
        derrotas = 0;
    }
}