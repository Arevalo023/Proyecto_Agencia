package security;
 
import java.util.regex.Pattern;
 
/**

* Validaciones simples de entrada para el login.

*/

public class InputValidator {
 
    // Usuario: letras/números/._- entre 3 y 30

    private static final Pattern USERNAME_RX = Pattern.compile("^[a-zA-Z0-9._-]{3,30}$");
 
    public boolean isValidUsername(String s) {

        if (s == null) return false;

        return USERNAME_RX.matcher(s.trim()).matches();

    }
 
    /**

     * Chequeo básico para evitar caracteres peligrosos en formularios (anti-inyección muy simple).

     * No reemplaza el uso de PreparedStatement.

     */

    public boolean isSafe(String s) {

        if (s == null) return false;

        String t = s.trim();

        // Evitar comillas, punto y coma, comentarios SQL, etc.

        return !(t.contains("'") || t.contains("\"") || t.contains(";") || t.contains("--") || t.contains("/*") || t.contains("*/"));

    }

}

 