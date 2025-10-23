package service;
 
import model.Usuario;
 
/**

* Resultado de autenticación: éxito, mensaje y el usuario autenticado (si aplica).

*/

public class AuthResult {

    private final boolean success;

    private final String message;

    private final Usuario user;
 
    private AuthResult(boolean success, String message, Usuario user) {

        this.success = success;

        this.message = message;

        this.user = user;

    }
 
    public static AuthResult ok(Usuario u) {

        return new AuthResult(true, "OK", u);

    }
 
    public static AuthResult fail(String msg) {

        return new AuthResult(false, msg, null);

    }
 
    public boolean isSuccess() { return success; }

    public String getMessage() { return message; }

    public Usuario getUser() { return user; }

}

 