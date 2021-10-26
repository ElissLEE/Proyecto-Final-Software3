package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {


    //================================= CONSTRUCTOR  =================================//
    public Administrador(String id, String nombre,String telefono, String password, String email) {
        super(id, nombre, telefono, password, email);
    }

    //================================= RELACION CON LA ENTIDAD LIBRO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    @JsonIgnore
    private List<Libro> libros;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuarios;
}
