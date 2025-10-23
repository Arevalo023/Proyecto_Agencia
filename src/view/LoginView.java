package view;
 
import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class LoginView extends JFrame implements ActionListener {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private LoginController controller;
 
    public LoginView() {
        setTitle("Login - Agencia de Automóviles");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
 
        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();
        btnLogin = new JButton("Iniciar Sesión");
 
        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Contraseña:"));
        add(txtContrasena);
        add(btnLogin);
 
        controller = new LoginController(this);
        btnLogin.addActionListener(this);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = txtUsuario.getText();
        String pass = new String(txtContrasena.getPassword());
        controller.autenticar(usuario, pass);
    }
 
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}