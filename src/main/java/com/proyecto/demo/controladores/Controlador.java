package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Comentario;
import com.proyecto.demo.entidades.Publicacion;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.entidades.Zona;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.ComentarioServicio;
import com.proyecto.demo.servicios.PublicacionServicio;
import com.proyecto.demo.servicios.UsuarioServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Controlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;
    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private ComentarioServicio comentarioServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        List<Publicacion> todasPublicaciones = publicacionServicio.listarTodas();
        List<Comentario> todosComentarios=comentarioServicio.todasCristalrias();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
          modelo.addAttribute("publicaciones", todasPublicaciones);
          modelo.addAttribute("comentarios", todosComentarios);
        return "index_blog.html";
    }
    
    @GetMapping("/formularioBarra")
    public String form(ModelMap modelo) {
        
        
        return "formularioBarra.html";
    }
  

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
    	
    	List<Usuario> usuarios = usuarioServicio.todosLosUsuarios();
    	
    	modelo.addAttribute("usuarios", usuarios);
    	
        return "inicio.html";
    }

    @GetMapping("/loginUsuarioModelo")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "loginUsuario1.html";
    }
     @GetMapping("/loginUsuarioControlador")
    public String loginUser() {
        
        return "loginUsuario1.html";
    }

    @GetMapping("/registro")
    public String registro() {
        
        
        return "registroUsuario.html";
    }

    @PostMapping("/registrar")
    public String registrar( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
          try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
        modelo.put("titulo", "Bienvenido a Tinder de Mascotas");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    }

}
