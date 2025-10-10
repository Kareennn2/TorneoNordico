import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class TestHU001_Caso5 {
    public static void main(String[] args) {
        System.out.println("Test: testValidarSinSeleccionArma");
        System.out.println("Clase: PanelArma");
        System.out.println("Método: validarSeleccion");
        System.out.println("HU: HU-001");
        System.out.println("Escenario: 4");
        System.out.println("Valores entrada: comboArmas=''");
        
        TorneoNordico torneo = new TorneoNordico();
        
        if (!torneo.armaSeleccionada()) {
            System.out.println("Resultado: PRUEBA EXITOSA - Falso. Botón deshabilitado");
        } else {
            System.out.println("Resultado: PRUEBA FALLIDA - Verdadero. Botón habilitado incorrectamente");
        }
    }
}