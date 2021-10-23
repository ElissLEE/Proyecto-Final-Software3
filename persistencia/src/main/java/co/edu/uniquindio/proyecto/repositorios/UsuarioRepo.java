package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {


    //================================= REPOSITORIO DE USUARIO =================================//

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pageable);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort sort);

    @Query("select u from Usuario u where u.email = :email and u.nombre= :nombre")
    Usuario obtenerUsuario( @Param("email")String email ,@Param("nombre")String nombre);


    Usuario findByEmailAndPassword(String email,String password);


    Usuario findByEmail(String email);

    List<Usuario> findByNombre(String nombre);

    @Query("select u.ciudad.nombre from Usuario u")
    List<Usuario> obtenerUsuarios2();  //Ejemplo

    @Query("select u from Usuario u order by u.nombre asc")
    List<Usuario> obtenerListaUsuariosOrdenadosAlfabeticamente();


    @Query("select u from Usuario u where u.email like '%@gmail.%'")
    List<Usuario> obtenerUsuariosDeGmail();

    @Query("select u from Usuario u where u.email like :dominio")
    List<Usuario> obtenerUsuariosDeDominio(String dominio);

    @Query("select u from Usuario u where u.email like concat('%',:dominio,'%')")
    List<Usuario> obtenerUsuariosDeDominio2(String dominio);

}
