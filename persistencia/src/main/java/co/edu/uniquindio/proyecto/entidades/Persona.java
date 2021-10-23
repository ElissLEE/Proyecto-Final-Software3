package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

//================================= RELACION DE HERENCIA =================================//

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Persona implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @Column(name = "id", length = 10)
    @Size(max = 10,message = "El valor ingresado excede los 10 caracteres")
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "nombre",length = 100,nullable = false)
    @NotBlank
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String nombre;

    @Column(name = "telefono",length = 100,nullable = true)
    private String telefono;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona(String id, String nombre,String telefono, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.telefono= telefono;
    }


}
