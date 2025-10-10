import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso3 {
    public static void main(String[] args) {
        System.out.println("Test: testValidarSeleccionArmaHacha");
        System.out.println("Clase: PanelArma");
        System.out.println("Método: validarSeleccion");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 2");
        System.out.println("Valores entrada: comboArmas='Hacha'");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(1);
        
        if (torneo.armaSeleccionada() && "Hacha".equals(torneo.getArmaSeleccionada())) {
            System.out.println("Resultado: PRUEBA EXITOSA - Verdadero. Estadísticas mostradas");
            System.out.println("Daño: " + torneo.getDanioArma(1) + ", Precisión: " + torneo.getPrecisionArma(1) + "%, Velocidad: " + torneo.getVelocidadArma(1));
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Falso. Estadísticas no mostradas");
        }
    }
}