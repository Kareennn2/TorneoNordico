import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import java.util.ArrayList;

public class TestHU002_Caso1 {
    public static void main(String[] args) {
        System.out.println("Test: testGenerarHordaConArma");
        System.out.println("Clase: PanelHorda");
        System.out.println("Método: validarGenerarHorda");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 1");
        System.out.println("Valores entrada: armaSeleccionada='Espada'");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();
        
        if (horda.size() == 3 && torneo.armaSeleccionada()) {
            System.out.println("Resultado: PRUEBA EXITOSA - Verdadero. Horda generada con 3 criaturas");
            System.out.println("Criaturas: " + horda);
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Falso. Horda no generada correctamente");
        }
    }
}