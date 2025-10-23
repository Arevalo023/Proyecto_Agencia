package dao;
 
import config.DataSourceProvider;
import model.Usuario;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Optional;
 
/**
* Implementación MySQL del UsuarioDAO.
* Requiere DataSourceProvider.getInstance().getConnection().
*/
public class UsuarioMySQLDAO implements UsuarioDAO {
 
    private static final String SELECT_BY_USERNAME = "SELECT id_usuario, username, password_hash, nombre_completo, email, rol, activo, creado_en FROM usuarios WHERE username = ?  LIMIT 1 ";
 
    private static final String EXISTS_ACTIVE_ADMIN = " SELECT 1 FROM usuarios WHERE rol = 'ADMIN' AND activo = 1LIMIT 1";

    private final DataSourceProvider dsp;
 
    public UsuarioMySQLDAO() {
        this.dsp = DataSourceProvider.getInstance();
    }
 
    public UsuarioMySQLDAO(DataSourceProvider customProvider) {
        this.dsp = customProvider;
    }
 
    @Override
    public Optional<Usuario> findByUsername(String username) {
        if (username == null || username.isBlank()) return Optional.empty();
 
        try (Connection cn = dsp.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_BY_USERNAME)) {
 
            ps.setString(1, username.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            // Aquí podrías usar un logger; por simplicidad imprimimos.
            System.err.println("[UsuarioMySQLDAO] Error findByUsername: " + e.getMessage());
        }
        return Optional.empty();
    }
 
    @Override
    public boolean existsActiveAdmin() {
        try (Connection cn = dsp.getConnection();
             PreparedStatement ps = cn.prepareStatement(EXISTS_ACTIVE_ADMIN);
             ResultSet rs = ps.executeQuery()) {
 
            return rs.next(); // existe al menos uno
        } catch (SQLException e) {
            System.err.println("[UsuarioMySQLDAO] Error existsActiveAdmin: " + e.getMessage());
            return false;
        }
    }
 
    // -------------------- helpers --------------------
 
    private Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        u.setUsername(rs.getString("username"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setNombreCompleto(rs.getString("nombre_completo"));
        u.setEmail(rs.getString("email"));
        u.setRol(rs.getString("rol"));
        u.setActivo(rs.getBoolean("activo"));
 
        // Convertir TIMESTAMP (java.sql.Timestamp) a LocalDateTime
        var ts = rs.getTimestamp("creado_en");
        if (ts != null) {
            LocalDateTime ldt = ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            u.setCreadoEn(ldt);
        }
 
        return u;
    }
}