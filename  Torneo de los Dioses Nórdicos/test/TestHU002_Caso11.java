import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU002_Caso11 {
    public static void main(String[] args) {
        System.out.println("Test: testReinicioJuego");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: reiniciarJuego");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 11");
        System.out.println("Valores entrada: juego en progreso");
        
        TorneoNordico torneo = new TorneoNordico();
        
        // Configurar juego en progreso
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        torneo.enfrentarCriatura();
        
        // Reiniciar juego
        torneo.reiniciarJuego();
        
        boolean reinicioExitoso = !torneo.armaSeleccionada() && 
                                 torneo.getHordaCriaturas().isEmpty() && 
                                 torneo.getVidaJugador() == 50 && 
                                 !torneo.isJuegoActivo() &&
                                 torneo.getProgresoHorda() == 0;
        
        if (reinicioExitoso) {
            System.out.println("Resultado: PRUEBA EXITOSA - Estado inicial: vida=50, sin arma, sin horda");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Juego no reiniciado correctamente");
        }
    }
}