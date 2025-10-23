package model;
 
import java.time.LocalDateTime;

import java.util.Objects;
 

public class Usuario {
 
    private Integer id;               // id_usuario

    private String username;          // username

    private String passwordHash;      // password_hash

    private String nombreCompleto;    // nombre_completo

    private String email;             // email

    private String rol;               // ADMIN | TECNICO | CONSULTA

    private boolean activo;           // 1/0

    private LocalDateTime creadoEn;   // timestamp
 
    public Usuario() {}
 
    public Usuario(Integer id, String username, String passwordHash, String nombreCompleto,

                   String email, String rol, boolean activo, LocalDateTime creadoEn) {

        this.id = id;

        this.username = username;

        this.passwordHash = passwordHash;

        this.nombreCompleto = nombreCompleto;

        this.email = email;

        this.rol = rol;

        this.activo = activo;

        this.creadoEn = creadoEn;

    }
 
    // Getters/Setters

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
 
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
 
    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
 
    public String getNombreCompleto() { return nombreCompleto; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
 
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
 
    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }
 
    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
 
    public LocalDateTime getCreadoEn() { return creadoEn; }

    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
 
    @Override

    public String toString() {

        return "Usuario{" +

                "id=" + id +

                ", username='" + username + '\'' +

                ", nombreCompleto='" + nombreCompleto + '\'' +

                ", email='" + email + '\'' +

                ", rol='" + rol + '\'' +

                ", activo=" + activo +

                ", creadoEn=" + creadoEn +

                '}';

    }
 
    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(id, usuario.id) &&

               Objects.equals(username, usuario.username);

    }
 
    @Override

    public int hashCode() {

        return Objects.hash(id, username);

    }

}

 