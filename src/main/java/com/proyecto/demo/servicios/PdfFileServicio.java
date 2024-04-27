

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.PdfFile;
import com.proyecto.demo.repositorios.PdfFileRepositorio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfFileServicio {
    
    @Autowired
    private PdfFileRepositorio pdfRepositorio;
    
    
    @Transactional
    public PdfFile guardar(String nombre , int codigo , MultipartFile archivo) throws IOException{
     if (archivo != null && !archivo.isEmpty()) {
            try {
                PdfFile foto = new PdfFile();
                foto.setMime(archivo.getContentType());
                foto.setNombre(nombre);
                foto.setArchivo(archivo.getBytes());

                return pdfRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    
    }
    
    public PdfFile buscarPorId(String id){
        Optional<PdfFile> archivo =pdfRepositorio.findById(id);
        if(archivo!=null){
        
        
        PdfFile pdf =  archivo.get();
        return pdf ;
        }else{
        
        
        return null;
        }
      
        }
    
    
    
    public List<PdfFile> getAll(){
    
    return pdfRepositorio.findAll();
    }

}
