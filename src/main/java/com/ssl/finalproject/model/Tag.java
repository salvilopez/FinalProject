package com.ssl.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(" id de etiqueta")
    private Long id;

    @Column(name = "nombre")
    @ApiModelProperty(" nombre de etiqueta")
    private String nombre;

    @ApiModelProperty(" fecha creacion etiqueta")
    private LocalDate created_at;

    @ApiModelProperty(" fecha actualizacion etiqueta")
    private LocalDate updated_at;

    @ApiModelProperty(" nombre creador de la etiqueta")
    private String creador;

    @ApiModelProperty(" lista de expertos de etiqueta")
    @ManyToMany(mappedBy = "tagList", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Expert> expertList = new ArrayList<>();

    public Tag() {
    }

    public Tag(String nombre, LocalDate created_at, LocalDate updated_at, String creador) {
        this.nombre = nombre;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.creador = creador;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Expert> getExpertList() {
        return expertList;
    }

    public void setExpertList(List<Expert> expertList) {
        this.expertList = expertList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDate getCreated_at() {
        return created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
