package security;
 
import org.mindrot.jbcrypt.BCrypt;
 
/**
* Envoltorio de BCrypt para hashear y verificar contraseñas.
* Requiere jBCrypt en el classpath.
*/
public class PasswordEncoder {
 
    // Entre 10 y 12 es razonable para desktop
    private final int strength;
 
    public PasswordEncoder() {
        this(10);
    }
 
    public PasswordEncoder(int strength) {
        this.strength = Math.max(4, Math.min(strength, 14));
    }
 
    public String hash(String plain) {
        if (plain == null) throw new IllegalArgumentException("La contraseña no puede ser null");
        String salt = BCrypt.gensalt(strength);
        return BCrypt.hashpw(plain, salt);
    }
 
    public boolean matches(String plain, String hash) {
        if (plain == null || hash == null || hash.isBlank()) return false;
        try {
            return BCrypt.checkpw(plain, hash);
        } catch (Exception e) {
            return false;
        }
    }
}