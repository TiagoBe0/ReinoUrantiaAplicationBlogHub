

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Publicacion;
import com.proyecto.demo.entidades.Cristal;
import com.proyecto.demo.entidades.Comentario;
import com.proyecto.demo.entidades.Foto;
import com.proyecto.demo.entidades.Usuario;
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
import org.springframework.web.multipart.MultipartFile;
import com.proyecto.demo.repositorios.ComentarioRepositorio;

@Service
public class ComentarioServicio {
    
    
    @Autowired
    private ComentarioRepositorio cristaleriaRepositorio;
     @Autowired
    private PublicacionServicio barraServicio;
     @Autowired
     private CristalServicio cristalServicio;
     
     @Autowired
     private UsuarioServicio usuarioServicio;
     
     
     @Autowired
     private BarraRepositorio barraRepositorio;
    
     @Autowired
    private FotoServicio fotoServicio;

    
    @Transactional
    public void registrar(MultipartFile archivo, String texto, String idPublicacion,String idUsuario) throws ErrorServicio {

        

        

       Comentario comentario = new Comentario();
       
      
       // Foto foto = fotoServicio.guardar(archivo);
       comentario.setDescripcion(texto);
        //Creamos una barra de pertenecencia y añadimos a lista de cristaleria comoa tributo
        Publicacion publicacion =barraServicio.buscarPorId(idPublicacion);
        comentario.setIdUsuario(idUsuario);
         List<Comentario> comentarios = publicacion.getListaCristalerias();
         comentarios.add(comentario);
       
         publicacion.setListaCristalerias(comentarios);
          
        
         
        comentario.setBarraPerteneciente(publicacion);
        
        
        cristaleriaRepositorio.save(comentario);
        

       

    }
    
    @Transactional
    public void modificar(MultipartFile archivo, String texto, String idPublicacion,String idUsuario) throws ErrorServicio {

       Comentario comentario = new Comentario();
      
         
       
      
       // Foto foto = fotoServicio.guardar(archivo);
       comentario.setDescripcion(texto);
        //Creamos una barra de pertenecencia y añadimos a lista de cristaleria comoa tributo
        Publicacion publicacion =barraServicio.buscarPorId(idPublicacion);
        comentario.setIdUsuario(idUsuario);
         List<Comentario> comentarios = publicacion.getListaCristalerias();
         comentarios.add(comentario);
       
         publicacion.setListaCristalerias(comentarios);
          
            cristaleriaRepositorio.save(comentario);
        
    }
    
    
    
    public List<Comentario> todasCristalrias(){
 
        return cristaleriaRepositorio.findAll();
        
    }
    
   
     public void deshabilitar(String id) throws ErrorServicio{
     
          cristaleriaRepositorio.deleteById(id);
     
     }
    
    
    public Comentario buscarPorId(String id) throws ErrorServicio {

        Optional<Comentario> respuesta = cristaleriaRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Comentario cristaleria = respuesta.get();
            return cristaleria;
        } else {

            throw new ErrorServicio("No se encontró la cristaleria solicitada");
        }

    }
    
}
