

package com.proyecto.demo.repositorios;

import com.proyecto.demo.entidades.Publicacion;
import com.proyecto.demo.entidades.Comentario;
import com.proyecto.demo.entidades.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfFileRepositorio extends JpaRepository<PdfFile,String> {

}
