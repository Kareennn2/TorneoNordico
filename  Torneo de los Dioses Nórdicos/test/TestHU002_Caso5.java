import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU002_Caso5 {
    public static void main(String[] args) {
        System.out.println("Test: testEnfrentamientoVictoriaArco");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: enfrentarCriatura");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 5");
        System.out.println("Valores entrada: arma='Arco', vida=50");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(2);
        torneo.generarHorda();
        
        int vidaInicial = torneo.getVidaJugador();
        boolean resultado = torneo.enfrentarCriatura();
        int progreso = torneo.getProgresoHorda();
        
        if (resultado && progreso > 0 && torneo.getVidaJugador() == vidaInicial) {
            System.out.println("Resultado: PRUEBA EXITOSA - Victoria. Progreso incrementado, vida intacta");
            System.out.println("Progreso: " + progreso + "/3, Vida: " + torneo.getVidaJugador());
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - No se obtuvo victoria esperada");
        }
    }
}