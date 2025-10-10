import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU002_Caso9 {
    public static void main(String[] args) {
        System.out.println("Test: testProgresoHordaCompleto");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: enfrentarCriatura");
        System.out.println("HU: HU-002");
        System.out.println("Escenario: 9");
        System.out.println("Valores entrada: 3 enfrentamientos exitosos");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0); // Espada
        torneo.generarHorda();
        
        // Realizar 3 enfrentamientos exitosos
        for (int i = 0; i < 3; i++) {
            torneo.enfrentarCriatura();
        }
        
        if (torneo.getProgresoHorda() == 3 && !torneo.isJuegoActivo() && torneo.isHordaCompletada()) {
            System.out.println("Resultado: PRUEBA EXITOSA - Horda completada. Juego inactivo");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Horda no completada correctamente");
        }
    }
}