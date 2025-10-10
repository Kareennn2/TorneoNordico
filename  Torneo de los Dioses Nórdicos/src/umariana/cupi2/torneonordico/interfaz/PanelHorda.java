package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelHorda extends javax.swing.JPanel {

    private TorneoNordico torneo;
    private JButton btnGenerarHorda;
    private JButton btnEnfrentar;
    private JLabel lblProgreso;
    private JLabel lblCriaturaActual;
    private JLabel lblVidaJugador;
    private JLabel lblMensaje;
    private JTextArea areaHorda;

    // Colores 
    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);
    private final Color COLOR_VERDE = new Color(50, 150, 50);
    private final Color COLOR_ROJO = new Color(200, 50, 50);

    public PanelHorda(TorneoNordico torneo) {
        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Horda de Criaturas",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        configurarEventos();
        actualizarEstado();
    }

    private void inicializarComponentes() {
        // Panel superior para controles
        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 10, 5));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnGenerarHorda = new JButton("Generar Horda");
        btnEnfrentar = new JButton("Enfrentar Criatura");

        // Configurar botones
        configurarBoton(btnGenerarHorda);
        configurarBoton(btnEnfrentar);

        // Etiquetas de información
        lblProgreso = crearEtiquetaInformacion("Progreso: 0/0");
        lblVidaJugador = crearEtiquetaInformacion("Vida: 50");

        panelSuperior.add(btnGenerarHorda);
        panelSuperior.add(lblProgreso);
        panelSuperior.add(btnEnfrentar);
        panelSuperior.add(lblVidaJugador);

        // Panel central para información de horda
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(COLOR_AZUL_OSCURO);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblCriaturaActual = new JLabel("Horda no generada");
        lblCriaturaActual.setForeground(COLOR_ORO);
        lblCriaturaActual.setFont(new Font("Arial", Font.BOLD, 14));
        lblCriaturaActual.setHorizontalAlignment(SwingConstants.CENTER);

        areaHorda = new JTextArea(5, 20);
        areaHorda.setEditable(false);
        areaHorda.setBackground(COLOR_GRIS_CLARO);
        areaHorda.setFont(new Font("Arial", Font.PLAIN, 12));
        areaHorda.setText("Aquí se mostrará la horda de criaturas...");

        JScrollPane scrollHorda = new JScrollPane(areaHorda);
        scrollHorda.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 1));

        panelCentral.add(lblCriaturaActual, BorderLayout.NORTH);
        panelCentral.add(scrollHorda, BorderLayout.CENTER);

        // Panel inferior para mensajes
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(COLOR_AZUL_FONDO);

        lblMensaje = new JLabel("Selecciona un arma y genera una horda para comenzar");
        lblMensaje.setForeground(COLOR_GRIS_CLARO);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        panelInferior.add(lblMensaje);

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(COLOR_ORO);
        boton.setForeground(COLOR_AZUL_OSCURO);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
    }

    private JLabel crearEtiquetaInformacion(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_GRIS_CLARO);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 12));
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        return etiqueta;
    }

    private void configurarEventos() {
        btnGenerarHorda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarHorda();
            }
        });

        btnEnfrentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enfrentarCriatura();
            }
        });
    }

    private void generarHorda() {
        if (!torneo.armaSeleccionada()) {
            mostrarMensaje("Primero debes seleccionar un arma", COLOR_ROJO);
            return;
        }

        torneo.generarHorda();
        actualizarEstado();
        mostrarMensaje("¡Horda generada! Enfrenta a las criaturas", COLOR_VERDE);
    }

    private void enfrentarCriatura() {
        if (!torneo.isJuegoActivo()) {
            mostrarMensaje("No hay horda activa. Genera una horda primero.", COLOR_ROJO);
            return;
        }

        boolean victoria = torneo.enfrentarCriatura();

        if (victoria) {
            mostrarMensaje("¡Victoria! Has derrotado a la criatura", COLOR_VERDE);
        } else {
            mostrarMensaje("¡Ataque fallido! Has recibido daño", COLOR_ROJO);
        }

        actualizarEstado();

        // Verificar fin del juego
        if (!torneo.isJuegoActivo()) {
            if (torneo.isHordaCompletada()) {
                mostrarMensaje("¡FELICIDADES! Has vencido toda la horda", COLOR_ORO);
            } else if (torneo.getVidaJugador() <= 0) {
                mostrarMensaje("GAME OVER - Te has quedado sin vida", COLOR_ROJO);
            }
        }
    }

    private void actualizarEstado() {
        // Actualizar información de la horda
        if (torneo.getHordaCriaturas().size() > 0) {
            StringBuilder sb = new StringBuilder();
            java.util.List<String> horda = torneo.getHordaCriaturas();

            // Usando instrucciones repetitivas para mostrar horda (nivel 3)
            for (int i = 0; i < horda.size(); i++) {
                String estado = (i < torneo.getProgresoHorda()) ? "[VENCIDO] " : "[PENDIENTE] ";
                sb.append(estado).append(horda.get(i)).append("\n");
            }
            areaHorda.setText(sb.toString());

            lblCriaturaActual.setText("Criatura actual: "
                    + (torneo.getCriaturaActual() != null ? torneo.getCriaturaActual() : "Horda completada"));
        } else {
            areaHorda.setText("Aquí se mostrará la horda de criaturas...");
            lblCriaturaActual.setText("Horda no generada");
        }

        // Actualizar progreso y vida
        lblProgreso.setText("Progreso: " + torneo.getProgresoHorda() + "/" + torneo.getTotalCriaturas());
        lblVidaJugador.setText("Vida: " + torneo.getVidaJugador());

        // Actualizar estado de botones
        btnEnfrentar.setEnabled(torneo.isJuegoActivo());
    }

    private void mostrarMensaje(String mensaje, Color color) {
        lblMensaje.setForeground(color);
        lblMensaje.setText(mensaje);
    }

    public void reiniciarPanel() {
        actualizarEstado();
        lblMensaje.setText("Selecciona un arma y genera una horda para comenzar");
        lblMensaje.setForeground(COLOR_GRIS_CLARO);
        btnGenerarHorda.setEnabled(torneo.armaSeleccionada());
        btnEnfrentar.setEnabled(false);
    }

    public void actualizarDesdeExterno() {
        actualizarEstado();

        // Habilitar/deshabilitar botones según estado del arma
        btnGenerarHorda.setEnabled(torneo.armaSeleccionada());
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
