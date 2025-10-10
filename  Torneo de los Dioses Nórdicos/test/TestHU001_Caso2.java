import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso2 {
    public static void main(String[] args) {
        System.out.println("Test: testValidarSeleccionArmaEspada");
        System.out.println("Clase: PanelArma");
        System.out.println("Método: validarSeleccion");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 1");
        System.out.println("Valores entrada: comboArmas='Espada'");
        
        TorneoNordico torneo = new TorneoNordico();
        torneo.seleccionarArma(0);
        
        if (torneo.armaSeleccionada() && "Espada".equals(torneo.getArmaSeleccionada())) {
            System.out.println("Resultado: PRUEBA EXITOSA - Verdadero. Estadísticas mostradas");
            System.out.println("Daño: " + torneo.getDanioArma(0) + ", Precisión: " + torneo.getPrecisionArma(0) + "%, Velocidad: " + torneo.getVelocidadArma(0));
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Falso. Estadísticas no mostradas");
        }
    }
}