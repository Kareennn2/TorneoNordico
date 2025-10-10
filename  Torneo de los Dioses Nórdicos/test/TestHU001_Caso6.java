import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso6 {
    public static void main(String[] args) {
        System.out.println("Test: testEstadisticasArmas");
        System.out.println("Clase: TorneoNordico");
        System.out.println("Método: getDanioArma");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 6");
        System.out.println("Valores entrada: índice=0 (Espada)");
        
        TorneoNordico torneo = new TorneoNordico();
        int danio = torneo.getDanioArma(0);
        int precision = torneo.getPrecisionArma(0);
        int velocidad = torneo.getVelocidadArma(0);
        
        if (danio == 8 && precision == 85 && velocidad == 7) {
            System.out.println("Resultado: PRUEBA EXITOSA - daño=8, precisión=85, velocidad=7");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Estadísticas incorrectas");
        }
    }
}