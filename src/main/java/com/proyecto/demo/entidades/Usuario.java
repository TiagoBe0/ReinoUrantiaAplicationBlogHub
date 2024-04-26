package com.proyecto.demo.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.proyecto.demo.enums.Rol;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String apellido;
    private String mail;
    private String clave;
    
    @ManyToOne
    private Zona zona;
    
    
    @OneToMany
    private List<Usuario> amigos;
    @OneToMany
    private List<Publicacion> barras;
    
    @OneToMany
    private List<Comentario> todasLasCristalerias;
     
    @OneToMany
    private List<Ruptura> todasLasRupturas;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    @OneToOne
    private Foto foto;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public List<Publicacion> getBarras() {
        return barras;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public void setBarras(List<Publicacion> barras) {
        this.barras = barras;
    }

    public List<Comentario> getTodasLasCristalerias() {
        return todasLasCristalerias;
    }

    public void setTodasLasCristalerias(List<Comentario> todasLasCristalerias) {
        this.todasLasCristalerias = todasLasCristalerias;
    }

    public List<Ruptura> getTodasLasRupturas() {
        return todasLasRupturas;
    }

    public void setTodasLasRupturas(List<Ruptura> todasLasRupturas) {
        this.todasLasRupturas = todasLasRupturas;
    }
    
    
    
    public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", clave=" + clave + ", zona=" + zona + ", alta=" + alta + ", baja=" + baja + '}';
    }

    
    
}
