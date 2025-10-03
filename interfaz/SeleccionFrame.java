package umariana.cupi2.torneonordico.interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SeleccionFrame extends JFrame {
    
    private JPanel panelPrincipal;
    private JComboBox<String> comboArma, comboEscenario, comboCriatura;
    private JButton btnIniciar, btnVolver;
    
    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);

    public SeleccionFrame() {
        configurarVentana();
        crearInterfaz();
        configurarEventos();
        setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("Torneo Nórdico - Selección");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
    
    private void crearInterfaz() {
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_AZUL_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel lblTitulo = new JLabel("SELECCIÓN DE ENFRENTAMIENTO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COLOR_ORO);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Panel de selección
        JPanel panelSeleccion = new JPanel(new GridLayout(5, 1, 0, 20));
        panelSeleccion.setBackground(COLOR_AZUL_OSCURO);
        panelSeleccion.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ORO, 2),
            "ELIGE TUS OPCIONES",
            TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), COLOR_ORO
        ));
        panelSeleccion.setPreferredSize(new Dimension(600, 400));
        
        // Arma
        JPanel panelArma = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelArma.setOpaque(false);
        JLabel lblArma = new JLabel("Arma:");
        lblArma.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblArma.setForeground(COLOR_GRIS_CLARO);
        comboArma = new JComboBox<>(new String[]{"", "Espada", "Hacha", "Arco"});
        estiloComboBox(comboArma);
        panelArma.add(lblArma);
        panelArma.add(comboArma);
        
        // Escenario
        JPanel panelEscenario = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelEscenario.setOpaque(false);
        JLabel lblEscenario = new JLabel("Escenario:");
        lblEscenario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblEscenario.setForeground(COLOR_GRIS_CLARO);
        comboEscenario = new JComboBox<>(new String[]{"", "Asgard", "Midgard", "Niflheim"});
        estiloComboBox(comboEscenario);
        panelEscenario.add(lblEscenario);
        panelEscenario.add(comboEscenario);
        
        // Criatura
        JPanel panelCriatura = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelCriatura.setOpaque(false);
        JLabel lblCriatura = new JLabel("Criatura:");
        lblCriatura.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCriatura.setForeground(COLOR_GRIS_CLARO);
        comboCriatura = new JComboBox<>(new String[]{"", "Dragon", "Gigante", "Serpiente"});
        estiloComboBox(comboCriatura);
        panelCriatura.add(lblCriatura);
        panelCriatura.add(comboCriatura);
        
        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        panelBotones.setOpaque(false);
        
        btnIniciar = crearBotonNordico("INICIAR ENFRENTAMIENTO", new Font("Segoe UI", Font.BOLD, 16));
        btnIniciar.setEnabled(false);
        
        btnVolver = crearBotonNordico("VOLVER", new Font("Segoe UI", Font.BOLD, 14));
        
        panelBotones.add(btnVolver);
        panelBotones.add(btnIniciar);
        
        // Agregar componentes
        panelSeleccion.add(panelArma);
        panelSeleccion.add(panelEscenario);
        panelSeleccion.add(panelCriatura);
        panelSeleccion.add(new JLabel());
        panelSeleccion.add(panelBotones);
        
        // Agregar todo al panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelSeleccion, BorderLayout.CENTER);
        
        setContentPane(panelPrincipal);
    }
    
    private void estiloComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setForeground(Color.BLACK);
        combo.setPreferredSize(new Dimension(200, 35));
    }
    
    private JButton crearBotonNordico(String texto, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(COLOR_ORO);
        boton.setBackground(COLOR_AZUL_OSCURO);
        boton.setBorder(BorderFactory.createLineBorder(COLOR_ORO, 2));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(250, 50));
        
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
        
        return boton;
    }
    
    private void configurarEventos() {
        // Validación de selecciones
        comboArma.addActionListener(e -> validarSelecciones());
        comboEscenario.addActionListener(e -> validarSelecciones());
        comboCriatura.addActionListener(e -> validarSelecciones());
        
        btnIniciar.addActionListener(e -> {
            new ResultadosFrame(
                (String) comboArma.getSelectedItem(),
                (String) comboEscenario.getSelectedItem(),
                (String) comboCriatura.getSelectedItem()
            ).setVisible(true);
            dispose();
        });
        
        btnVolver.addActionListener(e -> {
            new PanelInicio().setVisible(true);
            dispose();
        });
    }
    
    private void validarSelecciones() {
        boolean todas = comboArma.getSelectedIndex() > 0 &&
                       comboEscenario.getSelectedIndex() > 0 &&
                       comboCriatura.getSelectedIndex() > 0;
        btnIniciar.setEnabled(todas);
        
        if (todas) {
            btnIniciar.setBackground(COLOR_ORO);
            btnIniciar.setForeground(COLOR_AZUL_OSCURO);
        } else {
            btnIniciar.setBackground(COLOR_AZUL_OSCURO);
            btnIniciar.setForeground(COLOR_ORO);
        }
    }
}