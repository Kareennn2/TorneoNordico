import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso7 {
    public static void main(String[] args) {
        System.out.println("Test: testEstadisticasHacha");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: getPrecisionArma");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 7");
        System.out.println("Valores entrada: índice=1 (Hacha)");
        
        TorneoNordico torneo = new TorneoNordico();
        int danio = torneo.getDanioArma(1);
        int precision = torneo.getPrecisionArma(1);
        int velocidad = torneo.getVelocidadArma(1);
        
        if (danio == 10 && precision == 75 && velocidad == 5) {
            System.out.println("Resultado: PRUEBA EXITOSA - daño=10, precisión=75, velocidad=5");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Estadísticas incorrectas");
        }
    }
}