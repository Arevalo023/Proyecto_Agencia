package dao;
 
import model.Usuario;
 
import java.util.Optional;
 
/**

* Contrato de acceso a datos para la entidad Usuario.

*/

public interface UsuarioDAO {
 
    /**

     * Busca un usuario por username (solo activos o no? — aquí devolvemos cualquiera y

     * la regla de activo/rol se valida en el servicio).

     */

    Optional<Usuario> findByUsername(String username);
 
    /**

     * Retorna true si existe al menos un usuario activo con rol ADMIN.

     * Útil para un check inicial.

     */

    boolean existsActiveAdmin();

}

 