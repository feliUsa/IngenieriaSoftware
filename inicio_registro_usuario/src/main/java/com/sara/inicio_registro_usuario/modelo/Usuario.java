package com.sara.inicio_registro_usuario.modelo;

import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/*Tabla mapeada donde especifica el nombre de la tabla la cual es usuario
 * y va a tener identificador unico el cual sera el email
*/
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Usuario {

    @Id // identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    private String email;
    private String password;


    /*Esta parte se realiza para crear una tabla intermediaria la cual,
     * une columnas de usuarios con la de roles
     */
    @ManyToAny(fetch = FetchType.EAGER)
    @JoinTable(
            name ="usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
            )
    private Collection<Rol> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }

    public Usuario(Long id, String nombre, String email, String password, Collection<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String nombre, String email, String password, Collection<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario() {
    }

    

}
