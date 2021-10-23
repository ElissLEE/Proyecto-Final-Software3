package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador,String> {

    //================================= REPOSITORIO DE ADMINISTRADOR =================================//


    Optional<Administrador> findByEmail(String email);
    Administrador findByEmailAndPassword(String email,String password);
}
