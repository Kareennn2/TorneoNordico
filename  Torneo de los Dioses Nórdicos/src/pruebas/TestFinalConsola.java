package pruebas;

import java.util.Random;

public class TestFinalConsola {
    

    static class JuegoPrueba {
        private int puntos = 100;
        private int victorias = 0;
        private int derrotas = 0;
        private Random random = new Random();
        
        public String enfrentar(String arma, String escenario, String criatura) {

            double prob = calcularProbabilidad(arma, escenario, criatura);
            boolean gano = random.nextDouble() < prob;
            
            if (gano) {
                victorias++;
                puntos += 30; 
                return "✅ VICTORIA con " + arma + " vs " + criatura + " en " + escenario + " | +30 puntos";
            } else {
                derrotas++;
                puntos = Math.max(0, puntos - 40); // 40 puntos por derrota
                return "❌ DERROTA con " + arma + " vs " + criatura + " en " + escenario + " | -40 puntos";
            }
        }
        
        private double calcularProbabilidad(String arma, String escenario, String criatura) {
            double prob = 0.3; // 30% base 
            
            // Mejores combinaciones - PROBABILIDADES MÁS BAJAS
            if (arma.equals("Espada") && criatura.equals("Dragon")) prob = 0.5; // 50% 
            if (arma.equals("Hacha") && criatura.equals("Gigante")) prob = 0.4; // 40% 
            if (arma.equals("Arco") && criatura.equals("Serpiente")) prob = 0.45; // 45% 
            
            // Escenarios - BONUS MÁS PEQUEÑOS
            if (escenario.equals("Asgard")) prob += 0.1; // +10% 
            if (escenario.equals("Niflheim")) prob -= 0.15; // -15% 
            
            return Math.max(0.1, Math.min(0.8, prob)); // Máximo 80% 
        }
        
        public String getEstado() {
            return "Puntos: " + puntos + "Victorias: " + victorias + "Derrotas: " + derrotas;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(" INICIANDO PRUEBAS EN CONSOLA PURA ");

        
        // Crear juego de prueba
        JuegoPrueba juego = new JuegoPrueba();
        System.out.println("Juego creado - Solo consola");
        System.out.println("Estado inicial: " + juego.getEstado());
        
        System.out.println("\n⚔️ REALIZANDO ENFRENTAMIENTOS:");
        
        // Prueba 1 - Combinación buena
        System.out.println("1. " + juego.enfrentar("Espada", "Asgard", "Dragon"));
        System.out.println("   " + juego.getEstado());
        
        // Prueba 2 - Combinación regular
        System.out.println("2. " + juego.enfrentar("Hacha", "Midgard", "Gigante"));
        System.out.println("   " + juego.getEstado());
        
        // Prueba 3 - Combinación difícil
        System.out.println("3. " + juego.enfrentar("Arco", "Niflheim", "Serpiente"));
        System.out.println("   " + juego.getEstado());
        
        // Prueba 4 - Múltiples para ver probabilidades
        System.out.println("\n PROBABILIDADES (10 enfrentamientos rápidos):");
        int victorias = 0;
        for (int i = 1; i <= 10; i++) {
            String resultado = juego.enfrentar("Espada", "Midgard", "Dragon");
            System.out.println("   " + (i + 3) + ". " + resultado);
            if (resultado.contains("VICTORIA")) victorias++;
        }
        
        System.out.println("\nESTADÍSTICAS DE 10 ENFRENTAMIENTOS:");
        System.out.println("   Victorias: " + victorias + "/10 (" + (victorias * 10) + "%)");
        
        System.out.println("\n ESTADO FINAL:");
        System.out.println(juego.getEstado());
        
        System.out.println("PRUEBAS COMPLETADAS - SOLO CONSOLA");
        
        // Mostrar las probabilidades actuales
        System.out.println("\nPROBABILIDADES ACTUALES:");
        System.out.println("   Base: 30%");
        System.out.println("   Espada vs Dragon: 50%");
        System.out.println("   Hacha vs Gigante: 40%");
        System.out.println("   Arco vs Serpiente: 45%");
        System.out.println("   Asgard: +10% bonus");
        System.out.println("   Niflheim: -15% penalización");
    }
}