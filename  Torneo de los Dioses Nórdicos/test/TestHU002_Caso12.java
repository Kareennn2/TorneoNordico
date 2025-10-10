import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import java.util.ArrayList;

public class TestHU002_Caso12 {
    public static void main(String[] args) {
        System.out.println("Test: testListaCriaturas");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: generarHorda");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 12");
        System.out.println("Valores entrada: arma seleccionada");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(1); // Hacha
        torneo.generarHorda();
        ArrayList<String> horda = torneo.getHordaCriaturas();
        
        if (!horda.isEmpty() && horda.size() == 3) {
            System.out.println("Resultado: PRUEBA EXITOSA - Lista con 3 criaturas no vacía");
            System.out.println("Tipo estructura: " + horda.getClass().getSimpleName());
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Lista de criaturas incorrecta");
        }
    }
}