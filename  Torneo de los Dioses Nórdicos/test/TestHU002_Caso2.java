import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import java.util.ArrayList;

public class TestHU002_Caso2 {
    public static void main(String[] args) {
        System.out.println("Test: testGenerarHordaSinArma");
        System.out.println("Clase: PanelHorda");
        System.out.println("Método: validarGenerarHorda");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 2");
        System.out.println("Valores entrada: armaSeleccionada=null");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();
        
        if (horda.isEmpty() && !torneo.armaSeleccionada()) {
            System.out.println("Resultado: PRUEBA EXITOSA - Falso. Mensaje error 'Selecciona arma primero'");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Verdadero. Horda generada sin arma");
        }
    }
}