import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU002_Caso10 {
    public static void main(String[] args) {
        System.out.println("Test: testGameOverPorVidaCero");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: enfrentarCriatura");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 10");
        System.out.println("Valores entrada: vida=10, enfrentamiento fallido");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        
        // Forzar Game Over
        while (torneo.getVidaJugador() > 0 && torneo.isJuegoActivo()) {
            torneo.enfrentarCriatura();
        }
        
        if (!torneo.isJuegoActivo() && torneo.getVidaJugador() <= 0) {
            System.out.println("Resultado: PRUEBA EXITOSA - Game Over. Vida=0, juego inactivo");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - No ocurrió Game Over como se esperaba");
        }
    }
}