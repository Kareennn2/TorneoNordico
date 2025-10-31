package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;
import umariana.cupi2.torneonordico.mundo.Resultados;

public class PanelHistorial extends javax.swing.JPanel {

    private TorneoNordico torneo;
    private JTextArea areaHistorial;
    private JLabel lblEstadisticas;
    private JLabel lblTotalCombates;
    private JButton btnActualizar;

    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_VERDE = new Color(50, 150, 50);
    private final Color COLOR_ROJO = new Color(200, 50, 50);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);

    public PanelHistorial(TorneoNordico torneo) {

        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Historial de Combates",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        actualizarHistorial();
    }

    private void inicializarComponentes() {
        // Panel superior para estadísticas
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 5));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblEstadisticas = new JLabel("Estadísticas: 0V - 0D");
        lblEstadisticas.setForeground(COLOR_ORO);
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));

        lblTotalCombates = new JLabel("Total: 0 combates");
        lblTotalCombates.setForeground(COLOR_GRIS_CLARO);
        lblTotalCombates.setFont(new Font("Arial", Font.PLAIN, 12));

        panelSuperior.add(lblEstadisticas);
        panelSuperior.add(lblTotalCombates);

        // Área de historial
        areaHistorial = new JTextArea(8, 25);
        areaHistorial.setEditable(false);
        areaHistorial.setBackground(COLOR_AZUL_OSCURO);
        areaHistorial.setForeground(COLOR_GRIS_CLARO);
        areaHistorial.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaHistorial.setText("Aquí se mostrarán los últimos 10 combates...");

        JScrollPane scrollHistorial = new JScrollPane(areaHistorial);
        scrollHistorial.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 1));

        // Panel inferior para botón
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(COLOR_AZUL_FONDO);

        btnActualizar = new JButton("Actualizar Historial");
        btnActualizar.setBackground(COLOR_ORO);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.addActionListener(e -> actualizarHistorial());

        panelInferior.add(btnActualizar);

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollHistorial, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarHistorial() {
        
        Resultados resultados = torneo.getResultados();

        // Obtener últimos 10 combates
        String[] ultimosCombates = resultados.getUltimos10Combates();

        StringBuilder sb = new StringBuilder();
        sb.append("ÚLTIMOS 10 COMBATES:\n");

        int combatesMostrados = 0;

        // Usando instrucción repetitiva para procesar el arreglo
        for (int i = 0; i < ultimosCombates.length; i++) {
            if (ultimosCombates[i] != null) {
                sb.append((i + 1)).append(". ").append(ultimosCombates[i]).append("\n");
                combatesMostrados++;
            }
        }

        if (combatesMostrados == 0) {
            sb.append("Aún no hay combates registrados.\n");
            sb.append("¡Genera una horda y comienza a luchar!");
        }

        areaHistorial.setText(sb.toString());
        
         
        // Actualizar estadísticas
        int victorias = resultados.getVictorias();
        int derrotas = resultados.getDerrotas();
        int total = resultados.getTotalCombates();

        lblEstadisticas.setText("Estadísticas: " + victorias + "V - " + derrotas + "D");
        lblTotalCombates.setText("Total: " + total + " combates");

        // Cambiar color según el rendimiento
        if (victorias > derrotas) {
            lblEstadisticas.setForeground(COLOR_VERDE);
        } else if (derrotas > victorias) {
            lblEstadisticas.setForeground(COLOR_ROJO);
        } else {
            lblEstadisticas.setForeground(COLOR_ORO);
        }
    }

    public void reiniciarPanel() {
        actualizarHistorial();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
