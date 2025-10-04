package umariana.cupi2.torneonordico.interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import umariana.cupi2.torneonordico.mundo.TorneoNordico;

public class ResultadosFrame extends JFrame {
    
    private JTextArea areaResultado;
    private JButton btnNuevaPartida, btnVerPuntos, btnVolverInicio;
    private TorneoNordico torneo;
    
    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);

    public ResultadosFrame(String arma, String escenario, String criatura) {
        this.torneo = new TorneoNordico();
        configurarVentana();
        crearInterfaz();
        configurarEventos();
        ejecutarEnfrentamiento(arma, escenario, criatura);
        setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("Torneo Nórdico - Resultados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }
    
    private void crearInterfaz() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_AZUL_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel lblTitulo = new JLabel("RESULTADOS DEL ENFRENTAMIENTO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COLOR_ORO);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Panel de resultados
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBackground(COLOR_AZUL_OSCURO);
        panelResultados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ORO, 2),
            "RESULTADO",
            TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), COLOR_ORO
        ));
        
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setBackground(new Color(40, 50, 80));
        areaResultado.setForeground(COLOR_GRIS_CLARO);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollResultados = new JScrollPane(areaResultado);
        panelResultados.add(scrollResultados, BorderLayout.CENTER);
        
        // Panel de opciones
        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 0, 15));
        panelOpciones.setBackground(COLOR_AZUL_OSCURO);
        panelOpciones.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ORO, 2),
            "OPCIONES",
            TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), COLOR_ORO
        ));
        panelOpciones.setPreferredSize(new Dimension(250, 0));
        
        btnNuevaPartida = crearBotonNordico("NUEVA PARTIDA", new Font("Segoe UI", Font.BOLD, 14));
        btnVerPuntos = crearBotonNordico("VER PUNTOS", new Font("Segoe UI", Font.BOLD, 14));
        btnVolverInicio = crearBotonNordico("VOLVER AL INICIO", new Font("Segoe UI", Font.BOLD, 14));
        
        panelOpciones.add(btnNuevaPartida);
        panelOpciones.add(btnVerPuntos);
        panelOpciones.add(btnVolverInicio);
        
        // Agregar todo al panel principal
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelCentral = new JPanel(new BorderLayout(20, 0));
        panelCentral.setBackground(COLOR_AZUL_FONDO);
        panelCentral.add(panelResultados, BorderLayout.CENTER);
        panelCentral.add(panelOpciones, BorderLayout.EAST);
        
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        setContentPane(panelPrincipal);
    }
    
    private JButton crearBotonNordico(String texto, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(COLOR_ORO);
        boton.setBackground(COLOR_AZUL_OSCURO);
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
        
        return boton;
    }
    
    private void configurarEventos() {
        btnNuevaPartida.addActionListener(e -> {
            new SeleccionFrame().setVisible(true);
            dispose();
        });
        
        btnVerPuntos.addActionListener(e -> {
            String puntos = torneo.obtenerPuntos();
            JOptionPane.showMessageDialog(this, puntos, "Puntos de Batalla", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnVolverInicio.addActionListener(e -> {
            new PanelInicio().setVisible(true);
            dispose();
        });
    }
    
    private void ejecutarEnfrentamiento(String arma, String escenario, String criatura) {
        String resultado = torneo.ejecutarEnfrentamiento(arma, escenario, criatura);
        areaResultado.setText(resultado);
    }
}