import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import modela.Expediente;
import modela.Interesado;

public class SistemaGUI extends JFrame {
    private DefaultListModel<Expediente> expedientesModel = new DefaultListModel<>();
    private JList<Expediente> expedientesList = new JList<>(expedientesModel);

    // Nuevo modelo y lista para finalizados
    private DefaultListModel<Expediente> finalizadosModel = new DefaultListModel<>();
    private JList<Expediente> finalizadosList = new JList<>(finalizadosModel);

    private JTextArea seguimientoArea = new JTextArea(8, 30);

    public SistemaGUI() {
        setTitle("Trámite Documentario - Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de registro
        JPanel registroPanel = new JPanel(new GridLayout(0,2));
        JTextField idField = new JTextField();
        JComboBox<String> prioridadField = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        JTextField dniField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField telField = new JTextField();
        JTextField emailField = new JTextField();
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"ulima", "externo"});
        JTextField asuntoField = new JTextField();
        JTextField docRefField = new JTextField();

        registroPanel.add(new JLabel("ID Expediente:")); registroPanel.add(idField);
        registroPanel.add(new JLabel("Prioridad:")); registroPanel.add(prioridadField);
        registroPanel.add(new JLabel("DNI:")); registroPanel.add(dniField);
        registroPanel.add(new JLabel("Nombres:")); registroPanel.add(nombreField);
        registroPanel.add(new JLabel("Teléfono:")); registroPanel.add(telField);
        registroPanel.add(new JLabel("Email:")); registroPanel.add(emailField);
        registroPanel.add(new JLabel("Tipo:")); registroPanel.add(tipoCombo);
        registroPanel.add(new JLabel("Asunto:")); registroPanel.add(asuntoField);
        registroPanel.add(new JLabel("Doc. Referencia:")); registroPanel.add(docRefField);

        JButton registrarBtn = new JButton("Registrar Expediente");
        registroPanel.add(registrarBtn);

        // Panel de expedientes y acciones
        JPanel listaPanel = new JPanel(new BorderLayout());
        listaPanel.add(new JLabel("Expedientes Pendientes:"), BorderLayout.NORTH);
        listaPanel.add(new JScrollPane(expedientesList), BorderLayout.CENTER);

        JButton movimientoBtn = new JButton("Registrar Movimiento");
        JButton finalizarBtn = new JButton("Finalizar Trámite");
        JButton seguimientoBtn = new JButton("Ver Seguimiento");
        JPanel accionesPanel = new JPanel();
        accionesPanel.add(movimientoBtn);
        accionesPanel.add(finalizarBtn);
        accionesPanel.add(seguimientoBtn);

        listaPanel.add(accionesPanel, BorderLayout.SOUTH);

        // Panel de seguimiento (ahora con lista de finalizados)
        JPanel seguimientoPanel = new JPanel(new BorderLayout());
        seguimientoPanel.add(new JLabel("Expedientes Finalizados:"), BorderLayout.NORTH);
        finalizadosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        seguimientoPanel.add(new JScrollPane(finalizadosList), BorderLayout.CENTER);

        // Panel de alertas
        JTextArea alertasArea = new JTextArea(5, 30);
        alertasArea.setEditable(false);
        JPanel alertasPanel = new JPanel(new BorderLayout());
        alertasPanel.add(new JLabel("Alertas de Expedientes No Atendidos:"), BorderLayout.NORTH);
        alertasPanel.add(new JScrollPane(alertasArea), BorderLayout.CENTER);

        // Layout principal
        JPanel centro = new JPanel(new GridLayout(1,2));
        centro.add(listaPanel);
        centro.add(seguimientoPanel);

        add(registroPanel, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(alertasPanel, BorderLayout.SOUTH);

        // Acciones
        registrarBtn.addActionListener(e -> {
            Interesado interesado = new Interesado(
                dniField.getText(), nombreField.getText(), telField.getText(), emailField.getText(), (String)tipoCombo.getSelectedItem()
            );
            Expediente exp = new Expediente(
                idField.getText(), (String)prioridadField.getSelectedItem(), asuntoField.getText(), docRefField.getText(), interesado
            );
            expedientesModel.addElement(exp);
            actualizarAlertas(alertasArea);
            JOptionPane.showMessageDialog(this, "Expediente registrado.");
        });

        movimientoBtn.addActionListener(e -> {
            Expediente exp = expedientesList.getSelectedValue();
            if (exp != null && exp.getFechaFin() == null) {
                String dependencia = JOptionPane.showInputDialog(this, "Dependencia:");
                String accion = JOptionPane.showInputDialog(this, "Acción realizada:");
                if (dependencia != null && accion != null && !dependencia.isEmpty() && !accion.isEmpty()) {
                    exp.agregarSeguimiento(dependencia, accion);
                    JOptionPane.showMessageDialog(this, "Movimiento registrado.");
                }
            }
        });

        // Al finalizar un expediente, quitarlo de pendientes y agregarlo a finalizados
        finalizarBtn.addActionListener(e -> {
            Expediente exp = expedientesList.getSelectedValue();
            if (exp != null && exp.getFechaFin() == null) {
                exp.finalizarExpediente();
                String doc = JOptionPane.showInputDialog(this, "Documento producto del trámite:");
                if (doc != null && !doc.isEmpty()) {
                    exp.agregarDocumentoGenerado(doc);
                }
                String comprobante = "Comprobante de cierre: Expediente " + exp.getId() +
                    " cerrado el " + exp.getFechaFin() + " por " + exp.getInteresado().getNombres();
                exp.setComprobanteCierre(comprobante);

                // Quitar de pendientes y agregar a finalizados
                expedientesModel.removeElement(exp);
                finalizadosModel.addElement(exp);

                expedientesList.clearSelection();
                actualizarAlertas(alertasArea);
                actualizarFinalizados();
                JOptionPane.showMessageDialog(this, "Trámite finalizado.\n" + comprobante);
            }
        });

        // Botón de seguimiento para pendientes
        seguimientoBtn.addActionListener(e -> mostrarSeguimiento(expedientesList.getSelectedValue()));

        // Doble click en finalizados para ver seguimiento
        finalizadosList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Expediente exp = finalizadosList.getSelectedValue();
                    mostrarSeguimiento(exp);
                }
            }
        });

        // Timer para alertas automáticas
        new Timer(5000, e -> actualizarAlertas(alertasArea)).start();

        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarAlertas(JTextArea alertasArea) {
        List<Expediente> pendientes = Collections.list(expedientesModel.elements());
        pendientes.removeIf(e -> e.getFechaFin() != null);
        pendientes.sort(Comparator.comparing(Expediente::getPrioridad).thenComparing(Expediente::getFechaInicio));
        StringBuilder sb = new StringBuilder();
        for (Expediente e : pendientes) {
            sb.append(e.getId()).append(" - ").append(e.getPrioridad())
              .append(" - ").append(e.getFechaInicio()).append(" - ").append(e.getInteresado().getNombres()).append("\n");
        }
        alertasArea.setText(sb.length() == 0 ? "No hay expedientes pendientes." : sb.toString());
    }

    // Nueva función para mostrar expedientes finalizados
    private void actualizarFinalizados() {
        List<Expediente> finalizados = Collections.list(finalizadosModel.elements());
        StringBuilder sb = new StringBuilder();
        for (Expediente e : finalizados) {
            sb.append(e.getId()).append(" - ").append(e.getPrioridad())
              .append(" - Cerrado: ").append(e.getFechaFin())
              .append("\n").append(e.getComprobanteCierre()).append("\n\n");
        }
        // No es necesario si usas finalizadosModel, pero puedes actualizar si lo deseas
    }

    // Nueva función para mostrar seguimiento de cualquier expediente
    private void mostrarSeguimiento(Expediente exp) {
        if (exp != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(exp.getId()).append("\nPrioridad: ").append(exp.getPrioridad())
              .append("\nInteresado: ").append(exp.getInteresado().getNombres()).append(" (").append(exp.getInteresado().getTipo()).append(")")
              .append("\nAsunto: ").append(exp.getAsunto())
              .append("\nDoc. Referencia: ").append(exp.getDocumentoReferencia())
              .append("\nInicio: ").append(exp.getFechaInicio())
              .append("\nFinalización: ").append(exp.getFechaFin() == null ? "En trámite" : exp.getFechaFin())
              .append("\n\nMovimientos:\n");
            for (String m : exp.getSeguimientos()) sb.append(" - ").append(m).append("\n");
            sb.append("\nDocumentos generados:\n");
            for (String d : exp.getDocumentosGenerados()) sb.append(" - ").append(d).append("\n");
            if (exp.getFechaFin() != null) {
                sb.append("\n").append(exp.getComprobanteCierre());
            }
            JTextArea detalle = new JTextArea(sb.toString(), 20, 50);
            detalle.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(detalle), "Seguimiento de Expediente", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}