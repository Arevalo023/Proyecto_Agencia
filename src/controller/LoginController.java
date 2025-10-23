package controller;
 
import modelo.Usuario;
import modelo.UsuarioDAO;
import view.LoginView;
 
public class LoginController {
    private UsuarioDAO usuarioDAO;
    private LoginView vista;
 
    public LoginController(LoginView vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
    }
 
    public void autenticar(String user, String pass) {
        Usuario u = usuarioDAO.verificarUsuario(user, pass);
        if (u != null) {
            vista.mostrarMensaje("Bienvenido, " + u.getNombre()  );
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos");
        }
    }
}