

package com.proyecto.demo.repositorios;

import com.proyecto.demo.entidades.Cristaleria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CristaleriaRepositorio extends JpaRepository<Cristaleria,String> {

}
