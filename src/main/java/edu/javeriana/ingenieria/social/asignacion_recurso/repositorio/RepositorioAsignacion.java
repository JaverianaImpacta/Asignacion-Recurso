package edu.javeriana.ingenieria.social.asignacion_recurso.repositorio;

import edu.javeriana.ingenieria.social.asignacion_recurso.dominio.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAsignacion extends JpaRepository<Asignacion, Integer> {
    List<Asignacion> findAllByProyecto(Integer proyecto);

    List<Asignacion> findAllByRecurso(String recurso);
}
