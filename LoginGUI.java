import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    public LoginGUI() {
        setTitle("Inicio de Sesión - Trámite Documentario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Ingresar");

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(new JLabel()); add(loginBtn);

        loginBtn.addActionListener(e -> {
            String usuario = userField.getText();
            String contrasena = new String(passField.getPassword());
            // Usuario y contraseña fijos, puedes cambiarlos
            if (usuario.equals("admin") && contrasena.equals("1234")) {
                dispose(); // Cierra la ventana de login
                new SistemaGUI(); // Abre el sistema principal
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(350, 180);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}