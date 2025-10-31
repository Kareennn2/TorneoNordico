package umariana.cupi2.torneonordico.interfaz;

import umariana.cupi2.torneonordico.mundo.TorneoNordico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import umariana.cupi2.torneonordico.mundo.Resultados;

public class PanelInventario extends javax.swing.JPanel {

    private TorneoNordico torneo;
    private JButton[] botonesInventario;
    private JComboBox<String> comboObjetos;
    private JButton btnAgregar;
    private JButton btnUsar;
    private JLabel lblEspacio;
    private JLabel lblEfectoActivo;

    private final Color COLOR_ORO = new Color(212, 175, 55);
    private final Color COLOR_VERDE = new Color(50, 150, 50);
    private final Color COLOR_ROJO = new Color(200, 50, 50);
    private final Color COLOR_AZUL = new Color(65, 105, 225);
    private final Color COLOR_GRIS_CLARO = new Color(200, 200, 210);
    private final Color COLOR_AZUL_FONDO = new Color(20, 30, 60);
    private final Color COLOR_AZUL_OSCURO = new Color(30, 40, 70);

    public PanelInventario(TorneoNordico torneo) {
        initComponents();
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(COLOR_AZUL_FONDO);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ORO, 2),
                "Inventario de Objetos",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12), COLOR_GRIS_CLARO
        ));

        inicializarComponentes();
        actualizarInventario();
    }

    private void inicializarComponentes() {
        // Panel superior para información
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1, 5, 5));
        panelSuperior.setBackground(COLOR_AZUL_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblEspacio = new JLabel("Espacio: 0/5");
        lblEspacio.setForeground(COLOR_GRIS_CLARO);
        lblEspacio.setFont(new Font("Arial", Font.BOLD, 12));

        lblEfectoActivo = new JLabel("Sin efectos activos");
        lblEfectoActivo.setForeground(COLOR_ORO);
        lblEfectoActivo.setFont(new Font("Arial", Font.PLAIN, 11));

        panelSuperior.add(lblEspacio);
        panelSuperior.add(lblEfectoActivo);

        // Panel central para slots de inventario
        JPanel panelCentral = new JPanel(new GridLayout(1, 5, 5, 5));
        panelCentral.setBackground(COLOR_AZUL_FONDO);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botonesInventario = new JButton[5];
        for (int i = 0; i < 5; i++) {
            botonesInventario[i] = new JButton("Vacío");
            botonesInventario[i].setBackground(COLOR_AZUL_OSCURO);
            botonesInventario[i].setForeground(COLOR_GRIS_CLARO);
            botonesInventario[i].setFont(new Font("Arial", Font.PLAIN, 8));
            final int index = i;
            botonesInventario[i].addActionListener(e -> usarObjeto(index));
            panelCentral.add(botonesInventario[i]);
        }

        // Panel inferior para agregar objetos
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.setBackground(COLOR_AZUL_FONDO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboObjetos = new JComboBox<>(torneo.getObjetosDisponibles());
        comboObjetos.setBackground(COLOR_GRIS_CLARO);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(COLOR_VERDE);
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAgregar.addActionListener(e -> agregarObjeto());

        btnUsar = new JButton("Usar ");
        btnUsar.setBackground(COLOR_ORO);
        btnUsar.setForeground(Color.BLACK);
        btnUsar.setFont(new Font("Arial", Font.BOLD, 12));
        btnUsar.addActionListener(e -> usarObjetoSeleccionado());

        panelInferior.add(new JLabel("Objeto:"));
        panelInferior.add(comboObjetos);
        panelInferior.add(btnAgregar);
        panelInferior.add(btnUsar);

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void agregarObjeto() {
        if (torneo.isInventarioLleno()) {
            JOptionPane.showMessageDialog(this, "¡Inventario lleno! Usa o elimina objetos primero.", "Inventario Lleno", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String objetoSeleccionado = (String) comboObjetos.getSelectedItem();
        boolean exito = torneo.agregarAlInventario(objetoSeleccionado);

        if (exito) {
            actualizarInventario();
            JOptionPane.showMessageDialog(this, "¡" + objetoSeleccionado + " agregado al inventario!", "Objeto Agregado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el objeto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void usarObjeto(int indice) {
        String resultado = torneo.usarObjeto(indice);
        actualizarInventario();

        // Mostrar mensaje del efecto
        JOptionPane.showMessageDialog(this, resultado, "Objeto Usado", JOptionPane.INFORMATION_MESSAGE);

        // Actualizar label de efectos activos
        lblEfectoActivo.setText("Efectos: " + torneo.getEfectosActivos());
        if (torneo.getEfectosActivos().contains("Sin efectos")) {
            lblEfectoActivo.setForeground(COLOR_GRIS_CLARO);
        } else {
            lblEfectoActivo.setForeground(COLOR_ORO);
        }
    }

    private void usarObjetoSeleccionado() {
        // Buscar primer objeto disponible y usarlo
        String[] inventario = torneo.getInventario();
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) {
                usarObjeto(i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No hay objetos en el inventario para usar.", "Inventario Vacío", JOptionPane.WARNING_MESSAGE);
    }

    public void actualizarInventario() {
        String[] inventario = torneo.getInventario();
        int cantidad = torneo.getCantidadObjetos();

        // Actualizar slots
        for (int i = 0; i < botonesInventario.length; i++) {
            if (inventario[i] != null) {
                botonesInventario[i].setText(inventario[i]);
                // Colores según tipo de objeto
                if (inventario[i].contains("Poción")) {
                    botonesInventario[i].setBackground(COLOR_VERDE);
                } else if (inventario[i].contains("Amuleto")) {
                    botonesInventario[i].setBackground(COLOR_AZUL);
                } else if (inventario[i].contains("Runa")) {
                    botonesInventario[i].setBackground(COLOR_ROJO);
                }
                botonesInventario[i].setForeground(Color.WHITE);
            } else {
                botonesInventario[i].setText("Vacío");
                botonesInventario[i].setBackground(COLOR_AZUL_OSCURO);
                botonesInventario[i].setForeground(COLOR_GRIS_CLARO);
            }
        }

        // Actualizar información
        lblEspacio.setText("Espacio: " + cantidad + "/5");

        if (cantidad >= 5) {
            lblEspacio.setForeground(COLOR_ROJO);
        } else if (cantidad >= 3) {
            lblEspacio.setForeground(COLOR_ORO);
        } else {
            lblEspacio.setForeground(COLOR_VERDE);
        }
    }

    public void reiniciarPanel() {
        actualizarInventario();
        lblEfectoActivo.setText("Sin efectos activos");
        lblEfectoActivo.setForeground(COLOR_ORO);
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
