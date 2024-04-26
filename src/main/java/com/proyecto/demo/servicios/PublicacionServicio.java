

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Publicacion;
import com.proyecto.demo.entidades.Comentario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.BarraRepositorio;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServicio {
    
    @Autowired
    private BarraRepositorio barraRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Transactional
    public void registrar(String nombre,String idUsuario) throws ErrorServicio {

       
       Publicacion barra = new Publicacion();
       //barra.setAlta(new Date());
       barra.setUsuario(usuarioServicio.buscarPorId(idUsuario));
       
       usuarioServicio.actualizarListBarras(idUsuario,barra.getId());
       barra.setNombre(nombre);
       
        
        barraRepositorio.save(barra);
        

       

    }
    
    //ACTUALIZAR PRECIO TOTAL DE BARRA
    
    @Transactional
    public void actualizarPrecioBarra(Publicacion barra,float costoRuptura) throws ErrorServicio{
        //buscamos el usuario y getiamos las listas de Publicacion
    
    barra.setPrecioTotal(barra.getPrecioTotal()-costoRuptura);
    
    
    
    
    }
    
        
    @Transactional
    public void actualizarStockBarra(String idBarra,int numeroRupturas) throws ErrorServicio{
        //buscamos el usuario y getiamos las listas de Publicacion
    Publicacion barra = buscarPorId(idBarra);
    barra.setTotalUnidades(barra.getTotalUnidades()-numeroRupturas);
     
            
    
    }
   
    public List<Publicacion> listarTodas(){
        return barraRepositorio.findAll();
    }
    public float calcularPrecioTotal(List<Comentario> cristalerias){
        
        float suma =0;
        
        for (Comentario cristaleria : cristalerias) {
            
            suma = suma + cristaleria.getPrecioTotal();
            System.out.println("precio"+suma);
        }
        
    
    return suma;
    
    
    }
     public Publicacion buscarPorId(String id) throws ErrorServicio {

        Optional<Publicacion> respuesta = barraRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Publicacion barra = respuesta.get();
            return barra;
        } else {

            throw new ErrorServicio("No se encontró la barra solicitada");
        }

    }
     
     public void deshabilitar(String id) throws ErrorServicio{
     
          Publicacion barra = buscarPorId(id);
          barraRepositorio.delete(barra);
          barraRepositorio.deleteById(id);
     
     }
     

}
