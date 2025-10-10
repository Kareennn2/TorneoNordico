import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso8 {
    public static void main(String[] args) {
        System.out.println("Test: testEstadisticasArco");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: getVelocidadArma");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 8");
        System.out.println("Valores entrada: índice=2 (Arco)");
        
        TorneoNordico torneo = new TorneoNordico();
        int danio = torneo.getDanioArma(2);
        int precision = torneo.getPrecisionArma(2);
        int velocidad = torneo.getVelocidadArma(2);
        
        if (danio == 6 && precision == 95 && velocidad == 8) {
            System.out.println("Resultado: PRUEBA EXITOSA - daño=6, precisión=95, velocidad=8");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Estadísticas incorrectas");
        }
    }
}