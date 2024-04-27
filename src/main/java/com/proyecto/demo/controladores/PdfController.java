

package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.PdfFile;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.servicios.PdfFileServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class PdfController {
    
    
    @Autowired
    private PdfFileServicio pdfServicio;
    
    
     @GetMapping("/ver/{id}")
    public ResponseEntity<byte[]> fotoCristal(@PathVariable String id) {

        try {
            PdfFile pdf = pdfServicio.buscarPorId(id);
            if (pdf.getArchivo() == null) {
                throw new ErrorServicio("El usuario no tiene una foto asignada.");
            }
            byte[] archivo = pdf.getArchivo();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(archivo, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
     @GetMapping("/lista")
    public String form(ModelMap modelo) {
        List<PdfFile> pdfs = pdfServicio.getAll();
        modelo.put("archivos", pdfs);
        
        return "listaPdf.html";
    }
    
   

}
