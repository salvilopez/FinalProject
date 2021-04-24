package com.ssl.finalproject.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id user")
    private Long id;

    @ApiModelProperty(" email de usuario")
    @Column(name = "email",unique = true)
    private String email;

    @ApiModelProperty(" password  de usuario")
    @Column(name = "password")
    private String password;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @ApiModelProperty(" img de usuario")
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private String img;

    @ApiModelProperty(" nombre de usuario")
    private String nombre;

    public User() {
    }

    public User(String email, String password, String img, String nombre) {
        this.email = email;
        this.password = password;
        this.img = img;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
