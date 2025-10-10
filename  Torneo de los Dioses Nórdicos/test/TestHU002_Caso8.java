import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU002_Caso8 {
    public static void main(String[] args) {
        System.out.println("Test: testEnfrentamientoDerrotaArco");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: enfrentarCriatura");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 8");
        System.out.println("Valores entrada: arma='Arco', vida=50");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(2);
        torneo.generarHorda();
        
        int vidaInicial = torneo.getVidaJugador();
        boolean resultado = torneo.enfrentarCriatura();
        int vidaFinal = torneo.getVidaJugador();
        
        if (!resultado && vidaFinal < vidaInicial) {
            System.out.println("Resultado: PRUEBA EXITOSA - Derrota. Vida reducida en " + (vidaInicial - vidaFinal) + " puntos");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - No se obtuvo derrota esperada");
        }
    }
}