package com.ssl.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expert")
public class Expert {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "created_at")
    private Instant created_at;

    @Column(name = "updated_at")
    private Instant updated_at;
////*******************************************************+++++++++++++++++++++++++++++++++++++++++++++
@Column(name = "estado_motivo")
    private String estado_motivo;
////*******************************************************+++++++++++++++++++++++++++++++++++++++++++++
@Column(name = "disponibilidad")
   private String disponibilidad;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "autonomo")
    private Boolean autonomo;

    @Column(name = "contacto_telefono")
    private Integer contacto_telefono;

    @Column(name = "contacto_email")
    private String contacto_email;

    @Column(name = "contacto_ciudad")
   private String contacto_ciudad;

    @Column(name = "contacto_linkedin")
   private String contacto_linkedin;

    @Column(name = "condiciones_porcentaje")
   private Double condiciones_porcentaje;

    @Column(name = "condiciones_precio_hora")
    private Double condiciones_precio_hora;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "nif")
    private String nif;

    @Column(name = "credenciales_correo")
    private String credenciales_correo;

    @Column(name = "credenciales_correo_password")
    private String credenciales_correo_password;

    @Column(name = "credenciales_zoom")
    private String credenciales_zoom;

    @Column(name = "credenciales_zoom_password")
    private String credenciales_zoom_password;

    @Column(name = "fichero_foto",length = 6000)
    private String fichero_foto;

    @Column(name = "fichero_cv",length = 6000)
    private String fichero_cv;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "origen")
    private String origen;

    @Column(name = "estado")
    private String estado;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "expert_tag",
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonIgnoreProperties("expertList")
    private List<Tag> tagList= new ArrayList<>();


    public Expert(String nombre, Instant created_at, Instant updated_at, String estado_motivo, String disponibilidad, String modalidad, Boolean autonomo, Integer contacto_telefono, String contacto_email, String contacto_ciudad, String contacto_linkedin, Double condiciones_porcentaje, Double condiciones_precio_hora, Integer puntuacion, String nif, String credenciales_correo, String credenciales_correo_password, String credenciales_zoom, String credenciales_zoom_password, String fichero_foto, String fichero_cv, String observaciones, String origen, String estado) {
        this.nombre = nombre;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.estado_motivo = estado_motivo;
        this.disponibilidad = disponibilidad;
        this.modalidad = modalidad;
        this.autonomo = autonomo;
        this.contacto_telefono = contacto_telefono;
        this.contacto_email = contacto_email;
        this.contacto_ciudad = contacto_ciudad;
        this.contacto_linkedin = contacto_linkedin;
        this.condiciones_porcentaje = condiciones_porcentaje;
        this.condiciones_precio_hora = condiciones_precio_hora;
        this.puntuacion = puntuacion;
        this.nif = nif;
        this.credenciales_correo = credenciales_correo;
        this.credenciales_correo_password = credenciales_correo_password;
        this.credenciales_zoom = credenciales_zoom;
        this.credenciales_zoom_password = credenciales_zoom_password;
        this.fichero_foto = fichero_foto;
        this.fichero_cv = fichero_cv;
        this.observaciones = observaciones;
        this.origen = origen;
        this.estado = estado;
    }

    public Expert() {
    }

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

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public String getEstado_motivo() {
        return estado_motivo;
    }

    public void setEstado_motivo(String estado_motivo) {
        this.estado_motivo = estado_motivo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Boolean getAutonomo() {
        return autonomo;
    }

    public void setAutonomo(Boolean autonomo) {
        this.autonomo = autonomo;
    }

    public Integer getContacto_telefono() {
        return contacto_telefono;
    }

    public void setContacto_telefono(Integer contacto_telefono) {
        this.contacto_telefono = contacto_telefono;
    }

    public String getContacto_email() {
        return contacto_email;
    }

    public void setContacto_email(String contacto_email) {
        this.contacto_email = contacto_email;
    }

    public String getContacto_ciudad() {
        return contacto_ciudad;
    }

    public void setContacto_ciudad(String contacto_ciudad) {
        this.contacto_ciudad = contacto_ciudad;
    }

    public String getContacto_linkedin() {
        return contacto_linkedin;
    }

    public void setContacto_linkedin(String contacto_linkedin) {
        this.contacto_linkedin = contacto_linkedin;
    }

    public Double getCondiciones_porcentaje() {
        return condiciones_porcentaje;
    }

    public void setCondiciones_porcentaje(Double condiciones_porcentaje) {
        this.condiciones_porcentaje = condiciones_porcentaje;
    }

    public Double getCondiciones_precio_hora() {
        return condiciones_precio_hora;
    }

    public void setCondiciones_precio_hora(Double condiciones_precio_hora) {
        this.condiciones_precio_hora = condiciones_precio_hora;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCredenciales_correo() {
        return credenciales_correo;
    }

    public void setCredenciales_correo(String credenciales_correo) {
        this.credenciales_correo = credenciales_correo;
    }

    public String getCredenciales_correo_password() {
        return credenciales_correo_password;
    }

    public void setCredenciales_correo_password(String credenciales_correo_password) {
        this.credenciales_correo_password = credenciales_correo_password;
    }

    public String getCredenciales_zoom() {
        return credenciales_zoom;
    }

    public void setCredenciales_zoom(String credenciales_zoom) {
        this.credenciales_zoom = credenciales_zoom;
    }

    public String getCredenciales_zoom_password() {
        return credenciales_zoom_password;
    }

    public void setCredenciales_zoom_password(String credenciales_zoom_password) {
        this.credenciales_zoom_password = credenciales_zoom_password;
    }

    public String getFichero_foto() {
        return fichero_foto;
    }

    public void setFichero_foto(String fichero_foto) {
        this.fichero_foto = fichero_foto;
    }

    public String getFichero_cv() {
        return fichero_cv;
    }

    public void setFichero_cv(String fichero_cv) {
        this.fichero_cv = fichero_cv;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", name='" + nombre + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", estado_motivo='" + estado_motivo + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", autonomo=" + autonomo +
                ", contacto_telefono=" + contacto_telefono +
                ", contacto_email='" + contacto_email + '\'' +
                ", contacto_ciudad='" + contacto_ciudad + '\'' +
                ", contacto_linkedin='" + contacto_linkedin + '\'' +
                ", condiciones_porcentaje=" + condiciones_porcentaje +
                ", condiciones_precio_hora=" + condiciones_precio_hora +
                ", puntuacion=" + puntuacion +
                ", nif='" + nif + '\'' +
                ", credenciales_correo='" + credenciales_correo + '\'' +
                ", credenciales_correo_password='" + credenciales_correo_password + '\'' +
                ", credenciales_zoom='" + credenciales_zoom + '\'' +
                ", credenciales_zoom_password='" + credenciales_zoom_password + '\'' +
                ", fichero_foto='" + fichero_foto + '\'' +
                ", fichero_cv='" + fichero_cv + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", origen='" + origen + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
