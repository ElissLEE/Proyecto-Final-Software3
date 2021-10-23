package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad,Integer>{

    //================================= REPOSITORIO DE CIUDAD =================================//

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombreCiudad")
    List<Usuario> obtenerCiudadUsuario(String nombreCiudad);

    @Query("select c.nombre, u from Ciudad c left join c.usuarios u")
    List<Object[]> obtenerCiudadUsuarios();


}
