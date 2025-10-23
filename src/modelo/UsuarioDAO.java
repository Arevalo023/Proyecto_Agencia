package modelo;
 
import model.ConexionBD;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
 
public class UsuarioDAO {
 
    public Usuario verificarUsuario(String usuario, String contrasena) {
        Connection con = ConexionBD.getConexion();
        Usuario u = null;
 
        try {
            String sql = "SELECT * FROM usuarios WHERE usuarios = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                String hash = rs.getString("contrasena");
                if (BCrypt.checkpw(contrasena, hash)) {
                    u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                    );
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return u;
    }
}