

package com.proyecto.demo.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Ruptura {
    
     @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
     private String nombre;
      private String idUsuario;
     private int numeroDeRuptura;
     private String explicacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
     @OneToOne
     private Comentario tipoCristaleria;
     
     private float costoRuptura;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeRuptura() {
        return numeroDeRuptura;
    }

    public void setNumeroDeRuptura(int numeroDeRuptura) {
        this.numeroDeRuptura = numeroDeRuptura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
     
     

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
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

    public Comentario getTipoCristaleria() {
        return tipoCristaleria;
    }

    public void setTipoCristaleria(Comentario tipoCristaleria) {
        this.tipoCristaleria = tipoCristaleria;
    }

    public float getCostoRuptura() {
        return costoRuptura;
    }

    public void setCostoRuptura(float costoRuptura) {
        this.costoRuptura = costoRuptura;
    }
     
     
    

}
