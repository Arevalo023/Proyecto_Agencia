package service;
 
import dao.UsuarioDAO;
import model.Usuario;
import security.InputValidator;
import security.PasswordEncoder;
 
import java.util.Locale;
import java.util.Optional;
 
/**
* Reglas de autenticaci�n.
* Requisito: solo ADMIN puede acceder (ajustable con flag).
*/
public class AuthService {
 
    private final UsuarioDAO usuarioDAO;
    private final PasswordEncoder encoder;
    private final InputValidator validator = new InputValidator();
    private final SessionManager sessions = SessionManager.getInstance();
 
    // Cambia a false si quisieras permitir m�s roles
    private final boolean onlyAdmins = true;
 
    public AuthService(UsuarioDAO usuarioDAO, PasswordEncoder encoder) {
        this.usuarioDAO = usuarioDAO;
        this.encoder = encoder;
    }
 
    public AuthResult login(String username, String passwordPlain) {
        // Validaciones de forma
        if (username == null || username.isBlank() || passwordPlain == null || passwordPlain.isBlank()) {
            return AuthResult.fail("Completa usuario y contrase�a.");
        }
        if (!validator.isValidUsername(username)) {
            return AuthResult.fail("Usuario inv�lido (usa letras/n�meros/._- de 3 a 30).");
        }
        if (!validator.isSafe(username)) {
            return AuthResult.fail("Entrada no permitida.");
        }
 
        // Buscar usuario
        Optional<Usuario> opt = usuarioDAO.findByUsername(username.trim());
        if (opt.isEmpty()) {
            return AuthResult.fail("Usuario no encontrado o inactivo.");
        }
 
        Usuario u = opt.get();
 
        // Activo
        if (!u.isActivo()) {
            return AuthResult.fail("La cuenta est� inactiva.");
        }
 
        // Rol
        if (onlyAdmins) {
            String rol = u.getRol() == null ? "" : u.getRol().toUpperCase(Locale.ROOT).trim();
            if (!"ADMIN".equals(rol)) {
                return AuthResult.fail("Esta cuenta no tiene permiso de administrador.");
            }
        }
 
        // Contrase�a
        if (u.getPasswordHash() == null || u.getPasswordHash().isBlank()) {
            return AuthResult.fail("La cuenta no tiene contrase�a configurada.");
        }
 
        boolean ok = encoder.matches(passwordPlain, u.getPasswordHash());
        if (!ok) {
            return AuthResult.fail("Credenciales inv�lidas.");
        }
 
        // Iniciar sesi�n
        sessions.start(u);
        return AuthResult.ok(u);
    }
 
    public void logout() {
        sessions.clear();
    }
 
    public boolean isAdmin(Usuario u) {
        if (u == null) return false;
        String rol = u.getRol() == null ? "" : u.getRol().toUpperCase(Locale.ROOT).trim();
        return "ADMIN".equals(rol);
    }
}