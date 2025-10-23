package service;

import model.Usuario;
 
import java.time.Duration;

import java.time.Instant;

import java.util.Optional;
 
/**

* Maneja la sesión en memoria (desktop).

* Política: expira tras N minutos de inactividad (touch en cada acción).

*/

public class SessionManager {
 
    private static final SessionManager INSTANCE = new SessionManager();
 
    // Configurable: minutos de inactividad para expirar

    private long expiryMinutes = 20;
 
    private Usuario current;

    private Instant lastActivity;
 
    private SessionManager() {}
 
    public static SessionManager getInstance() {

        return INSTANCE;

    }
 
    public synchronized void setExpiryMinutes(long minutes) {

        if (minutes < 1) minutes = 1;

        this.expiryMinutes = minutes;

    }
 
    public synchronized void start(Usuario u) {

        this.current = u;

        this.lastActivity = Instant.now();

    }
 
    public synchronized Optional<Usuario> getCurrent() {

        if (current == null) return Optional.empty();

        if (isExpired()) {

            clear();

            return Optional.empty();

        }

        return Optional.of(current);

    }
 
    public synchronized void touch() {

        this.lastActivity = Instant.now();

    }
 
    public synchronized boolean isExpired() {

        if (current == null || lastActivity == null) return true;

        Duration d = Duration.between(lastActivity, Instant.now());

        return d.toMinutes() >= expiryMinutes;

    }
 
    public synchronized void clear() {

        this.current = null;

        this.lastActivity = null;

    }

}

 