package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Libro implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @Column(name = "isbn",nullable = false)
    @EqualsAndHashCode.Include
    private int isbn;

    @Column(name = "titulo",length = 100,nullable = false)
    private String titulo;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "autor",length = 100,nullable = false)
    private String autor;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    public Libro(int isbn, String titulo, Boolean estado, String autor, Date fechaPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.estado = estado;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }
}
