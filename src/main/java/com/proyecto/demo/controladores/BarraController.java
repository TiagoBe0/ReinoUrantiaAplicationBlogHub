

package com.proyecto.demo.controladores;


import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.servicios.BarraServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/barra")
public class BarraController {
    
    @Autowired
    private BarraServicio barraServicio;
    
 
    
      @GetMapping("/formulario")
    public String form(ModelMap modelo) {
        
        
        return "registroBarra.html";
    }
    
    
    
    @GetMapping("/listabarras")
    public String listabarras(ModelMap modelo) {
        modelo.put("barras", barraServicio.listarTodas());
        
        return "registroCristaleria.html";
    }
    @GetMapping("/formularioCristaleria")
    public String formCristaleria(ModelMap modelo) {
        
        
        return "registroCristaleria.html";
    }
    
     @PostMapping("/registrar")
    public String registrar(ModelMap modelo,  @RequestParam String nombre, @RequestParam String idUsuario) throws ErrorServicio {
        
         System.out.println("EL NOMBRE DE LA BARRA LLEGOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

         
         
        try {
            barraServicio.registrar(nombre,idUsuario);
        } catch (ErrorServicio ex) {
           modelo.put(nombre,"nombre");
            
             
            return "registroCristaleria.html";
        }
        modelo.put("titulo", "Bienvenido");
        modelo.put("descripcion", "Barra cargada correctamente");
        return "exitoBarra.html";
    }

}
