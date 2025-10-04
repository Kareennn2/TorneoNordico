package umariana.cupi2.torneonordico.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelInicio extends javax.swing.JFrame {

    private JButton btnJugar;
    private JButton btnAyuda;
    private JButton btnSalir;

    public PanelInicio() {
        initComponents();
        aplicarEstiloNordico();
    }

    private void aplicarEstiloNordico() {
        // Configuración básica de la ventana
        setTitle("Torneo de los Dioses Nórdicos");
        setLocationRelativeTo(null);

        // Colores nórdicos
        Color COLOR_ORO = new Color(212, 175, 55);
        Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
        Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
        Color COLOR_AZUL_FONDO = new Color(20, 30, 60);

        // Panel principal con fondo
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_AZUL_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(50, 50, 50, 50));

        // Panel de contenido centrado
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);

        // Título
        JLabel titulo = new JLabel("TORNEO NÓRDICO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(COLOR_ORO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 40, 0));

        // Subtítulo
        JLabel subtitulo = new JLabel("El destino de los Dioses está en tus manos");
        subtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        subtitulo.setForeground(COLOR_GRIS_CLARO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(0, 0, 60, 0));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 0, 20));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(300, 200));

        // Botón JUGAR - INICIALIZAR ANTES DE USAR
        btnJugar = new JButton("JUGAR");
        configurarBoton(btnJugar);
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SeleccionFrame().setVisible(true);
            }
        });

        // Botón AYUDA - INICIALIZAR ANTES DE USAR
        btnAyuda = new JButton("AYUDA");
        configurarBoton(btnAyuda);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarAyuda();
            }
        });

        // Botón SALIR - INICIALIZAR ANTES DE USAR
        btnSalir = new JButton("SALIR");
        configurarBoton(btnSalir);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ensamblar componentes
        panelBotones.add(btnJugar);
        panelBotones.add(btnAyuda);
        panelBotones.add(btnSalir);

        panelContenido.add(titulo);
        panelContenido.add(subtitulo);
        panelContenido.add(panelBotones);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        setContentPane(panelPrincipal);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void configurarBoton(JButton boton) {
        Color COLOR_ORO = new Color(212, 175, 55);
        Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
        
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setForeground(COLOR_ORO);
        boton.setBackground(COLOR_AZUL_OSCURO);
        boton.setPreferredSize(new Dimension(250, 60));
        boton.setMaximumSize(new Dimension(250, 60));
        boton.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 2));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_ORO);
                boton.setForeground(COLOR_AZUL_OSCURO);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_AZUL_OSCURO);
                boton.setForeground(COLOR_ORO);
            }
        });
    }

    private void mostrarAyuda() {
        String mensajeAyuda = "<html><div style='text-align: center;'>"
                + "<h2 style='color: #D4AF37;'>TORNEO NÓRDICO - AYUDA</h2>"
                + "<p style='color: black; font-size: 14px;'>"
                + "<b>OBJETIVO:</b><br>"
                + "Derrotar criaturas nórdicas usando armas y escenarios estratégicos<br><br>"
                + "<b>CÓMO JUGAR:</b><br>"
                + "1.Selecciona un arma, escenario y criatura<br>"
                + "2.Inicia el enfrentamiento<br>"
                + "3.¡Gana puntos y mejora tu estrategia!<br><br>"
                + "<b style='color: #D4AF37;'>¡Que los dioses nórdicos te favorezcan!</b>"
                + "</p></div></html>";

        JOptionPane.showMessageDialog(this, mensajeAyuda, "Ayuda - Torneo Nórdico",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelInicio().setVisible(true);
            }
        });
    }
}