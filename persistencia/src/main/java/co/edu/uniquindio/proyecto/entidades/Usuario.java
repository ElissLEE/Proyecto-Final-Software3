package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    @JsonIgnore
    private Ciudad ciudad;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= CONSTRUCTOR  =================================//
    public Usuario(String id, String nombre,String telefono, String password, String email) {
        super(id, nombre,telefono, password, email);
    }

}
