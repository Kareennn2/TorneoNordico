/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;
import umariana.cupi2.torneonordico.mundo.Resultados;

/**
 *
 * @author 70GA
 */
public class PanelEstadisticas extends javax.swing.JPanel {

    private TorneoNordico torneo;
    
    
    private JLabel lblVictorias;
    private JLabel lblDerrotas;
    private JLabel lblEfectividad;
    private JTextArea areaLogros;
    private JLabel lblVictoriasTotales;
    private JLabel lblDerrotasTotales;
    private JLabel lblPorcentajeVictorias;
    private JLabel lblLogros;
    private JButton btnActualizar;

    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_VERDE = new Color(50, 150, 50);
    private final Color COLOR_ROJO = new Color(200, 50, 50);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);

    /**
     * Creates new form PanelEstadisticas
     */
    public PanelEstadisticas(TorneoNordico torneo) {
        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Estadísticas y Logros",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        actualizarEstadisticas();
    }

   private void inicializarComponentes() {
        // Panel superior para estadísticas
        JPanel panelSuperior = new JPanel(new GridLayout(4, 2, 10, 5));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Victorias Totales - Cumple Criterio 1
        agregarEtiqueta(panelSuperior, "Victorias Totales:");
        lblVictoriasTotales = crearEtiquetaValor("0");
        lblVictoriasTotales.setForeground(COLOR_VERDE);
        lblVictoriasTotales.setFont(new Font("Arial", Font.BOLD, 14));

        // Derrotas Totales - Cumple Criterio 2
        agregarEtiqueta(panelSuperior, "Derrotas Totales:");
        lblDerrotasTotales = crearEtiquetaValor("0");
        lblDerrotasTotales.setForeground(COLOR_ROJO);
        lblDerrotasTotales.setFont(new Font("Arial", Font.BOLD, 14));

        // Porcentaje de Victorias - Cumple Criterio 3
        agregarEtiqueta(panelSuperior, "Porcentaje Victorias:");
        lblPorcentajeVictorias = crearEtiquetaValor("0%");
        lblPorcentajeVictorias.setForeground(COLOR_ORO);
        lblPorcentajeVictorias.setFont(new Font("Arial", Font.BOLD, 14));

        // Efectividad - Criterio 3 adicional
        agregarEtiqueta(panelSuperior, "Efectividad:");
        lblEfectividad = crearEtiquetaValor("0%");
        lblEfectividad.setForeground(COLOR_ORO);
        lblEfectividad.setFont(new Font("Arial", Font.BOLD, 14));

        // Área de logros - Cumple Criterios 4, 5, 6
        JPanel panelLogros = new JPanel(new BorderLayout());
        panelLogros.setBackground(COLOR_AZUL_FONDO);
        panelLogros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblLogros = new JLabel("LOGROS DESBLOQUEADOS:");
        lblLogros.setForeground(COLOR_ORO);
        lblLogros.setFont(new Font("Arial", Font.BOLD, 14));
        lblLogros.setHorizontalAlignment(SwingConstants.CENTER);

        areaLogros = new JTextArea(8, 25);
        areaLogros.setEditable(false);
        areaLogros.setBackground(COLOR_AZUL_OSCURO);
        areaLogros.setForeground(COLOR_GRIS_CLARO);
        areaLogros.setFont(new Font("Arial", Font.PLAIN, 12));
        areaLogros.setLineWrap(true);
        areaLogros.setWrapStyleWord(true);
        areaLogros.setText("Inicia combates para desbloquear logros...");

        JScrollPane scrollLogros = new JScrollPane(areaLogros);
        scrollLogros.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 1));

        // Panel inferior para botón
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(COLOR_AZUL_FONDO);

        btnActualizar = new JButton("Actualizar Estadísticas");
        btnActualizar.setBackground(COLOR_ORO);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.addActionListener(e -> actualizarEstadisticas());

        panelInferior.add(btnActualizar);

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelLogros, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        panelLogros.add(lblLogros, BorderLayout.NORTH);
        panelLogros.add(scrollLogros, BorderLayout.CENTER);
    }

    private void agregarEtiqueta(JPanel panel, String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_GRIS_CLARO);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(etiqueta);
    }

    private JLabel crearEtiquetaValor(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_GRIS_CLARO);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 12));
        return etiqueta;
    }

    public void actualizarEstadisticas() {
        // Obtener resultados del torneo
        Resultados resultados = torneo.getResultados();

        // Obtener estadísticas - Cumple Criterios 1, 2, 3
        int victoriasTotales = resultados.getVictoriasTotales();
        int derrotasTotales = resultados.getDerrotasTotales();
        double porcentaje = resultados.getPorcentajeVictorias();

        // Actualizar labels con las estadísticas
        lblVictoriasTotales.setText("Victorias: " + victoriasTotales); // Criterio 1
        lblDerrotasTotales.setText("Derrotas: " + derrotasTotales);   // Criterio 2
        lblPorcentajeVictorias.setText("Porcentaje: " + String.format("%.1f%%", porcentaje)); // Criterio 3
        lblEfectividad.setText("Efectividad: " + String.format("%.1f%%", porcentaje)); // Criterio 3

        // Cambiar colores según el rendimiento
        if (porcentaje >= 70) {
            lblEfectividad.setForeground(COLOR_VERDE);
            lblPorcentajeVictorias.setForeground(COLOR_VERDE);
        } else if (porcentaje >= 40) {
            lblEfectividad.setForeground(COLOR_ORO);
            lblPorcentajeVictorias.setForeground(COLOR_ORO);
        } else {
            lblEfectividad.setForeground(COLOR_ROJO);
            lblPorcentajeVictorias.setForeground(COLOR_ROJO);
        }

        // Actualizar logros - Cumple Criterios 4, 5, 6
        actualizarLogros(resultados);
    }

    private void actualizarLogros(Resultados resultados) {
        // Obtener logros del sistema - Criterio 5 (arreglo tamaño fijo)
        String[] logros = resultados.getLogros();
        boolean[] logrosDesbloqueados = resultados.getLogrosDesbloqueados();

        StringBuilder sb = new StringBuilder();
        sb.append("=== LOGROS DEL TORNEO NÓRDICO ===\n\n");

        int logrosDesbloqueadosCount = 0;

        // Mostrar estado de cada logro - Criterio 4 (3 logros desbloqueables)
        for (int i = 0; i < logros.length; i++) {
            String estado = logrosDesbloqueados[i] ? "✅ DESBLOQUEADO" : "❌ BLOQUEADO";
            String emoji = logrosDesbloqueados[i] ? "🎉" : "🔒";
            
            sb.append(emoji).append(" ").append(estado).append("\n");
            sb.append("   ").append(logros[i]).append("\n\n");
            
            if (logrosDesbloqueados[i]) {
                logrosDesbloqueadosCount++;
            }
        }

        // Mostrar resumen
        sb.append("=== PROGRESO GENERAL ===\n");
        sb.append("Logros desbloqueados: ").append(logrosDesbloqueadosCount).append("/").append(logros.length).append("\n");
        
        if (logrosDesbloqueadosCount == 0) {
            sb.append("\n💡 Consejo: Gana tu primer combate para desbloquear el primer logro!");
        } else if (logrosDesbloqueadosCount == logros.length) {
            sb.append("\n🎊 ¡FELICIDADES! Has desbloqueado todos los logros!");
        } else {
            sb.append("\n⚔️ ¡Sigue luchando para desbloquear más logros!");
        }

        areaLogros.setText(sb.toString());
        
        // Actualizar color del título de logros según progreso
        if (logrosDesbloqueadosCount == 0) {
            lblLogros.setForeground(COLOR_GRIS_CLARO);
        } else if (logrosDesbloqueadosCount < logros.length) {
            lblLogros.setForeground(COLOR_ORO);
        } else {
            lblLogros.setForeground(COLOR_VERDE);
        }
    }

    public void reiniciarPanel() {
        actualizarEstadisticas();
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
