package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;

public class PanelEscenario extends javax.swing.JPanel {

    private TorneoNordico torneo;
    private JLabel lblEscenario;
    private JLabel lblVisibilidad;
    private JLabel lblTerreno;
    private JLabel lblSinergiaActiva;
    private JTextArea areaDescripcion;

    // Colores 
    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_VERDE_BOSQUE = new Color(34, 139, 34);
    private final Color COLOR_AZUL_GLACIAR = new Color(135, 206, 235);
    private final Color COLOR_MARRENO_PANTANO = new Color(139, 69, 19);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);

    public PanelEscenario(TorneoNordico torneo) {
        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Escenario del Torneo",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        actualizarEscenario();
    }

    private void inicializarComponentes() {
        // Panel superior para información del escenario
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        agregarEtiqueta(panelSuperior, "Escenario:");
        lblEscenario = crearEtiquetaValor("No generado");
        lblEscenario.setForeground(COLOR_ORO);
        lblEscenario.setFont(new Font("Arial", Font.BOLD, 14));

        agregarEtiqueta(panelSuperior, "Visibilidad:");
        lblVisibilidad = crearEtiquetaValor("0%");

        agregarEtiqueta(panelSuperior, "Modificador Terreno:");
        lblTerreno = crearEtiquetaValor("0");

        // Área de descripción
        areaDescripcion = new JTextArea(4, 20);
        areaDescripcion.setEditable(false);
        areaDescripcion.setBackground(COLOR_GRIS_CLARO);
        areaDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        areaDescripcion.setText("Genera una horda para descubrir el escenario...");
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);

        JScrollPane scrollDescripcion = new JScrollPane(areaDescripcion);
        scrollDescripcion.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 1));

        JPanel panelSinergia = new JPanel(new FlowLayout());
        panelSinergia.setBackground(COLOR_AZUL_FONDO);

        lblSinergiaActiva = new JLabel("Sinergia activa: No");
        lblSinergiaActiva.setForeground(COLOR_GRIS_CLARO);
        lblSinergiaActiva.setFont(new Font("Arial", Font.BOLD, 11));

        panelSinergia.add(lblSinergiaActiva);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollDescripcion, BorderLayout.CENTER);
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

    public void actualizarEscenario() {
        String escenario = torneo.getEscenarioActual();

        if (escenario != null) {
            lblEscenario.setText(escenario);
            lblVisibilidad.setText(torneo.getModificadorVisibilidad() + "%");

            int modificador = torneo.getModificadorTerreno();
            lblTerreno.setText((modificador >= 0 ? "+" : "") + modificador + " daño");

            boolean tieneSinergia = torneo.tieneSinergiaArmaEscenario();
            if (tieneSinergia) {
                lblSinergiaActiva.setText("⚡ Sinergia activa: +5 daño");
                lblSinergiaActiva.setForeground(COLOR_ORO);
            } else {
                lblSinergiaActiva.setText("Sinergia activa: No");
                lblSinergiaActiva.setForeground(COLOR_GRIS_CLARO);
            }

            // Cambiar color según escenario y mostrar descripción
            switch (escenario) {
                case "Bosque Nórdico":
                    lblEscenario.setForeground(COLOR_VERDE_BOSQUE);
                    areaDescripcion.setText("BOSQUE NÓRDICO\n"
                            + "• Visibilidad media (70%)\n"
                            + "• Terreno neutral\n"
                            + "• Ideal para emboscadas");
                    break;
                case "Montaña Helada":
                    lblEscenario.setForeground(COLOR_AZUL_GLACIAR);
                    areaDescripcion.setText("MONTAÑA HELADA\n"
                            + "• Excelente visibilidad (90%)\n"
                            + "• Terreno desfavorable (-10 daño)\n"
                            + "• Ventaja para ataques a distancia");
                    break;
                case "Pantano Oscuro":
                    lblEscenario.setForeground(COLOR_MARRENO_PANTANO);
                    areaDescripcion.setText("PANTANO OSCURO\n"
                            + "• Mala visibilidad (50%)\n"
                            + "• Terreno favorable (+10 daño)\n"
                            + "• Ideal para combate cuerpo a cuerpo");
                    break;
            }
        } else {
            lblEscenario.setText("No generado");
            lblVisibilidad.setText("0%");
            lblTerreno.setText("0");
            lblEscenario.setForeground(COLOR_ORO);
            areaDescripcion.setText("Genera una horda para descubrir el escenario...");
            lblSinergiaActiva.setText("Sinergia activa: No");
            lblSinergiaActiva.setForeground(COLOR_GRIS_CLARO);

        }
    }

    public void reiniciarPanel() {
        actualizarEscenario();
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
