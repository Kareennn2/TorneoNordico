import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso4 {
    public static void main(String[] args) {
        System.out.println("Test: testValidarSeleccionArmaArco");
        System.out.println("Clase: PanelArma");
        System.out.println("Método: validarSeleccion");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 3");
        System.out.println("Valores entrada: comboArmas='Arco'");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(2);
        
        if (torneo.armaSeleccionada() && "Arco".equals(torneo.getArmaSeleccionada())) {
            System.out.println("Resultado: PRUEBA EXITOSA - Verdadero. Estadísticas mostradas");
            System.out.println("Daño: " + torneo.getDanioArma(2) + ", Precisión: " + torneo.getPrecisionArma(2) + "%, Velocidad: " + torneo.getVelocidadArma(2));
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Falso. Estadísticas no mostradas");
        }
    }
}