package edu.javeriana.ingenieria.social.asignacion_recurso.servicio;

import edu.javeriana.ingenieria.social.asignacion_recurso.dominio.Asignacion;
import edu.javeriana.ingenieria.social.asignacion_recurso.repositorio.RepositorioAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServicioAsignacion {
    @Autowired
    private RepositorioAsignacion repositorio;

    public List<Asignacion> obtenerAsignaciones(){
        return repositorio.findAll();
    }

    public List<Asignacion> obtenerAsignaciones(Integer proyecto){
        return repositorio.findAllByProyecto(proyecto);
    }

    public List<Asignacion> obtenerAsignaciones(String recurso){
        return repositorio.findAllByRecurso(recurso);
    }

    public Asignacion obtenerAsignacion(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Asignacion crearAsignacion(Asignacion asignacion){
        return repositorio.save(asignacion);
    }

    public Asignacion actualizarAsignacion(Integer id, Asignacion asignacion){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        asignacion.setId(id);
        return repositorio.save(asignacion);
    }

    public Asignacion borrarAsignacion(Integer id){
        Asignacion aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return null;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean asignacionExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean asignacionExiste(Asignacion asignacion){
        List<Asignacion> aux = obtenerAsignaciones(asignacion.getProyecto());
        for(Asignacion a : aux){
            if(Objects.equals(a.getRecurso(), asignacion.getRecurso())){
                return true;
            }
        }
        return false;
    }
}
