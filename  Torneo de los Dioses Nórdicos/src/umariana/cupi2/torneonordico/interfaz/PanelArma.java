package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelArma extends javax.swing.JPanel {

    private TorneoNordico torneo;
    private JComboBox<String> comboArmas;
    private JLabel lblDanio;
    private JLabel lblPrecision;
    private JLabel lblVelocidad;
    private JButton btnSeleccionar;
    private JLabel lblMensaje;

    // Colores nórdicos
    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);
    private final Color COLOR_ROJO_OSCURO = new Color(120, 30, 30);

    public PanelArma(TorneoNordico torneo) {
        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Selección de Arma",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        // Panel superior para selección de arma
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Elige tu arma vikinga:");
        lblTitulo.setForeground(COLOR_ORO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        comboArmas = new JComboBox<>(torneo.getArmas());
        comboArmas.setBackground(COLOR_GRIS_CLARO);
        comboArmas.setFont(new Font("Arial", Font.PLAIN, 12));

        panelSuperior.add(lblTitulo);
        panelSuperior.add(comboArmas);

        // Panel central para estadísticas
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 5, 5));
        panelCentral.setBackground(COLOR_AZUL_OSCURO);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        agregarEtiquetaEstadistica(panelCentral, "Daño:", "");
        lblDanio = crearEtiquetaValor();
        panelCentral.add(lblDanio);

        agregarEtiquetaEstadistica(panelCentral, "Precisión:", "");
        lblPrecision = crearEtiquetaValor();
        panelCentral.add(lblPrecision);

        agregarEtiquetaEstadistica(panelCentral, "Velocidad:", "");
        lblVelocidad = crearEtiquetaValor();
        panelCentral.add(lblVelocidad);

        // Panel inferior para botón
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(COLOR_AZUL_FONDO);

        btnSeleccionar = new JButton("Seleccionar Arma");
        btnSeleccionar.setBackground(COLOR_ORO);
        btnSeleccionar.setForeground(COLOR_AZUL_OSCURO);
        btnSeleccionar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSeleccionar.setFocusPainted(false);

        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(COLOR_GRIS_CLARO);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        panelInferior.add(btnSeleccionar);

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Actualizar estadísticas iniciales
        actualizarEstadisticas();
    }

    private void agregarEtiquetaEstadistica(JPanel panel, String texto, String valor) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_ORO);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(etiqueta);
    }

    private JLabel crearEtiquetaValor() {
        JLabel etiqueta = new JLabel("0");
        etiqueta.setForeground(COLOR_GRIS_CLARO);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 12));
        return etiqueta;
    }

    private void configurarEventos() {
        comboArmas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstadisticas();
            }
        });

        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArma();
            }
        });
    }

    private void actualizarEstadisticas() {
        int indice = comboArmas.getSelectedIndex();
        if (indice >= 0) {
            lblDanio.setText(torneo.getDanioArma(indice) + " puntos");
            lblPrecision.setText(torneo.getPrecisionArma(indice) + "%");
            lblVelocidad.setText(torneo.getVelocidadArma(indice) + "/10");
        }
    }

    private void seleccionarArma() {
        int indice = comboArmas.getSelectedIndex();
        torneo.seleccionarArma(indice);

        lblMensaje.setForeground(COLOR_ORO);
        lblMensaje.setText("¡" + torneo.getArmaSeleccionada() + " seleccionada!");

        // Deshabilitar selección una vez elegida
        comboArmas.setEnabled(false);
        btnSeleccionar.setEnabled(false);
    }

    public boolean isArmaSeleccionada() {
        return torneo.armaSeleccionada();
    }

    public void habilitarSeleccion() {
        comboArmas.setEnabled(true);
        btnSeleccionar.setEnabled(true);
        lblMensaje.setText(" ");
        comboArmas.setSelectedIndex(0); // Resetear selección
        actualizarEstadisticas(); // Actualizar stats
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
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
    }
    // </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
