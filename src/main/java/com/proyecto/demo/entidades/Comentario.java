

package com.proyecto.demo.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comentario {
    
      @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
      
     private String tipo;
    @OneToOne
     private Publicacion barraPerteneciente;
    
         private String barraPertenecienteNombre;
    
    private String idUsuario;
    
     private int enStock;
     @ManyToOne
      Cristal cristalRepo;
     private float precio;
     
     private float precioTotal;
     
     private String descripcion;
        @OneToOne
    private Foto foto;
        
     @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    public String getId() {
        return id;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public Cristal getCristalRepo() {
        return cristalRepo;
    }

    public void setCristalRepo(Cristal cristalRepo) {
        this.cristalRepo = cristalRepo;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPrecioTotal() {
        this.precioTotal = this.precio*this.enStock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Publicacion getBarraPerteneciente() {
        return barraPerteneciente;
    }

    public void setBarraPerteneciente(Publicacion barraPerteneciente) {
        //this.barraPertenecienteNombre=barraPerteneciente.getNombre();
        
        this.barraPerteneciente = barraPerteneciente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBarraPertenecienteNombre() {
        return barraPertenecienteNombre;
    }

    public void setBarraPertenecienteNombre(String barraPertenecienteNombre) {
        this.barraPertenecienteNombre = barraPertenecienteNombre;
    }

    public int getEnStock() {
        return enStock;
    }

    public void setEnStock(int enStock) {
        this.enStock = enStock;
        
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
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
    
    
        
        

}
