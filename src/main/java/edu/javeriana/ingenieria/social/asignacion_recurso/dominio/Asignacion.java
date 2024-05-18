package edu.javeriana.ingenieria.social.asignacion_recurso.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="asignacion_recurso")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer proyecto, cantidad;
    private String recurso;
}
