import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    public LoginGUI() {
        setTitle("Inicio de Sesión - Trámite Documentario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Logo
        ImageIcon logoIcon = new ImageIcon("recursos/Universidad_de_Lima_logo.svg.png");
        Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel de login
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Ingresar");

        loginPanel.add(userLabel); loginPanel.add(userField);
        loginPanel.add(passLabel); loginPanel.add(passField);
        loginPanel.add(new JLabel()); loginPanel.add(loginBtn);

        add(logoLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> {
            String usuario = userField.getText();
            String contrasena = new String(passField.getPassword());
            if (usuario.equals("admin") && contrasena.equals("1234")) {
                dispose();
                new SistemaGUI();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(400, 320);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}