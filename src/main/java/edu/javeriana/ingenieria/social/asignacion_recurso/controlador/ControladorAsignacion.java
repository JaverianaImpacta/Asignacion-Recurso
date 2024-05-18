package edu.javeriana.ingenieria.social.asignacion_recurso.controlador;

import edu.javeriana.ingenieria.social.asignacion_recurso.dominio.Asignacion;
import edu.javeriana.ingenieria.social.asignacion_recurso.servicio.ServicioAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorAsignacion {
    @Autowired
    private ServicioAsignacion servicio;

    @GetMapping("listar")
    public List<Asignacion> obtenerAsignaciones() {
        return servicio.obtenerAsignaciones();
    }

    @GetMapping("obtenerProyecto")
    public ResponseEntity<List<Asignacion>> obtenerAsignaciones(@RequestParam Integer proyecto) {
        if(proyecto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerAsignaciones(proyecto).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerAsignaciones(proyecto), HttpStatus.OK);
    }

    @GetMapping("obtenerRecurso")
    public ResponseEntity<List<Asignacion>> obtenerAsignaciones(@RequestParam String recurso){
        if(recurso==null || recurso.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerAsignaciones(recurso).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerAsignaciones(recurso), HttpStatus.OK);
    }

    @GetMapping("obtener")
    public ResponseEntity<Asignacion> obtenerAsignacion(@RequestParam Integer id){
        if(id==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerAsignacion(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerAsignacion(id), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Asignacion> crearAsignacion(@RequestBody Asignacion asignacion){
        if(asignacion==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.asignacionExiste(asignacion) || servicio.asignacionExiste(asignacion.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearAsignacion(asignacion), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Asignacion> actualizarAsignacion(@RequestParam Integer id, @RequestBody Asignacion asignacion){
        if(id==null || asignacion==null || !Objects.equals(asignacion.getId(), id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarAsignacion(id, asignacion) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(asignacion, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarAsignacion(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarAsignacion(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
