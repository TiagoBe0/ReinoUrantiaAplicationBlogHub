package com.proyecto.demo.controladores;


import com.proyecto.demo.entidades.Comentario;
import com.proyecto.demo.entidades.Publicacion;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.entidades.Zona;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.PublicacionServicio;
import com.proyecto.demo.servicios.CristalServicio;
import com.proyecto.demo.servicios.ComentarioServicio;
import com.proyecto.demo.servicios.RupturaServicio;
import com.proyecto.demo.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ComentarioServicio cristaleriaServicio;

    @Autowired
    private RupturaServicio rupturaServicio;
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    @Autowired
    private PublicacionServicio barraServicio;
    
    @Autowired
    private CristalServicio cristalServicio;
    
    
    //COPIA DE HTML FORMULARIO USUARIO
     
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-perfil-nuevo")
    public String editarPerfil(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
        System.out.println("Estamos llegando a controlador del usuariossesision");
        //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "perfilModificar.html";
    }
    //CREAR PUBLICACION GET
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-publicacion")
    public String editarPerfilviejo(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
      
        

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registroPublicacion.html";
    }
      //REGISTRO BARRA POST
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-publicacion")
    public String actualizarBarra(ModelMap modelo, HttpSession session,   String id,String  titulo,  String contenido ,MultipartFile archivo) throws ErrorServicio {
        //Aqui me comunico con MODIFICARBARRA
         usuarioServicio.modificarBarra(id, titulo,contenido,archivo);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    
    
    
    
     //HTML CREAR CRISTAL
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-cristal")
    public String crearCristal(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
         //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registroCristal.html";
    }
    
    //ESTE ES PARA ENTRAR AL FORMULARIO DE COMENTARIO GET
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-comentario/{id}/{idPublicacion}")
    public String cargarCristaleria(HttpSession session, @PathVariable String id,@PathVariable String idPublicacion, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        model.put("publicacion", barraServicio.buscarPorId(idPublicacion));
      
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
              model.addAttribute("cristales",cristalServicio.listarTodas());
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registroComentario.html";
    }
    
     //POST CREAR COMENTARIO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-comentario")
    public String actualizarCristaleria(ModelMap modelo, HttpSession session,   String id,String idPublicacion, String contenido) throws ErrorServicio {
        
        //Aqui me comunico con modificar cristaleria
         cristaleriaServicio.modificar(null, contenido, idPublicacion, id);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    
    
    
    
     //ESTE ES PARA ENTRAR AL PANEL BARRA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/panel-barra")
    public String panelBarra(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
        System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("rupturas",usuario.getTodasLasRupturas());
             model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "panelBarra.html";
    }
    
     //ESTE ES PARA ENTRAR AL FORMULARIO DE RUPTURA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-ruptura")
    public String cargarRuptura(HttpSession session , String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        model.put("cristalerias",usuarioServicio.buscarPorId(id).getTodasLasCristalerias());
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registroRuptura.html";
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/editar-barra-form")
    public String editarBarraFormulario(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        barraServicio.registrar(nombre, id);
System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registroBarra.html";
    }
    
     @GetMapping("/registroUsuario")
    public String registro() {
        
        
        return "registroUsuario.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-perfil")
    public String actualizar(ModelMap modelo, HttpSession session, MultipartFile archivo, @RequestParam String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            usuarioServicio.modificar(archivo, id, nombre, apellido, mail, clave2, clave2);
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);
            modelo.put("error", ex.getMessage());
            modelo.put("perfil", usuario);

            return "perfil.html";
        }

    }
    //AGREGAR AMIGOS
   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/agregar-amigo/{id}/{idAmigo}")
    public String agregarAmigo(ModelMap modelo, HttpSession session, @PathVariable String id,@PathVariable String idAmigo) {
       System.out.println(id+";"+idAmigo+"ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            usuarioServicio.agregarAmigo(id,idAmigo);
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    //LOFIC
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
     @GetMapping("/loginUsuario")
    public String palabrota() {
        
        return "loginUsuario1.html";
    }
     @GetMapping("/listarbarra/{idUsuario}")
    public String barras(ModelMap modelo , @PathVariable String idUsuario) throws ErrorServicio {
        List<Publicacion> barras =usuarioServicio.buscarPorId(idUsuario).getBarras();
       modelo.put("barras", barras);
        return "registroBarra.html";
    }
    //INICIO DE USUARIO LOGIN GET
     @GetMapping("/inicioUsuario")
    public String inicioUsuario(ModelMap modelo) {
        
         List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        List<Publicacion> todasPublicaciones = barraServicio.listarTodas();
        List<Comentario> todosComentarios=cristaleriaServicio.todasCristalrias();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
          modelo.addAttribute("publicaciones", todasPublicaciones);
          modelo.addAttribute("comentarios", todosComentarios);
        
        modelo.put("usuarios",usuarioServicio.todosLosUsuarios());
        
        return  "index_blog.html";
    }
    
     @PostMapping("/registrar")
    public String registrar( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        System.out.println("LLEGAMOS A LOS CONTROLADORES TIOOOOOOO");
        try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
        modelo.put("titulo", "Bienvenido a Tinder de Mascotas");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    } 


    
    
    
    //CREAR CRISTALERIA PPST
    
    
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-cristal")
    public String actualizarCristal(ModelMap modelo, HttpSession session,@RequestParam   String id,MultipartFile archivo, String nombre) throws ErrorServicio {
        
        //Aqui me comunico con modificar cristaleria
         cristalServicio.registrar(archivo, nombre);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            
            //usuarioServicio.modificarBarra(id,nombre);
             modelo.addAttribute("cristales",cristalServicio.listarTodas());
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    
    
    
    
    
    
    
    

//METODO PARA MODIFICAR BARRA
@GetMapping("/modificar-barra-panel/{id}")
	public String modificarBarraPanel(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
		Publicacion barra =barraServicio.buscarPorId(id);
            modelo.put("barra", barra);
            modelo.addAttribute("rupturas",barra.getUsuario().getTodasLasRupturas());
          
            modelo.put("cristalerias",barraServicio.buscarPorId(id).getListaCristalerias() );
            return "modificarBarraPanel.html";
	}
        
        
        
        
        //METODO PARA MODIFICAR BARRA queda  guardado para despuesS GESTIR DE RYTYRAS
        @GetMapping("/ruptura/{id}")
	public String crearRutura(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
		
            modelo.put("cristaleria",cristaleriaServicio.buscarPorId(id));
           
            return "registroRuptura.html";
	}

        
        //MODIFICAR RUPTURA DESDE PABEL DE BARRAS
 @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-ruptura")
    public String actualizarRuptura(ModelMap modelo, HttpSession session,@RequestParam String id, @RequestParam String idCristaleria, @RequestParam String nombre, @RequestParam String explicacion, @RequestParam int cantidad) throws ErrorServicio {

        Usuario usuario = null;
        rupturaServicio.modificar(nombre, explicacion, cantidad, idCristaleria,id);
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            

            return "perfil.html";
        }

    }

}//llave de clase
