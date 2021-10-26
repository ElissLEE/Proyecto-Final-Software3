package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    //================================= Instancia del repositorio =================================//
    @Autowired
    private AdministradorRepo administradorRepo;

    //================================= Metodo para registrar o crear un administrador =================================//
    @Test
    public void registrarAdministradorTest(){

        Administrador administradorNuevo = new Administrador();
        administradorNuevo.setId("1193409775");
        administradorNuevo.setNombre("Sebastian");
        administradorNuevo.setPassword("21quintero04");
        administradorNuevo.setEmail("@gmail.com");
        administradorNuevo.setTelefono("3167127633");

        Administrador administradorGuardado= administradorRepo.save(administradorNuevo);

        Assertions.assertNotNull(administradorGuardado);
    }

    //================================= Metodo para eliminar un administrador =================================//
    @Test
    public void eliminarAdministradorTest(){

        Administrador administradorNuevo = new Administrador();
        administradorNuevo.setId("1193409775");
        administradorNuevo.setNombre("Sebastian");
        administradorNuevo.setPassword("21quintero04");
        administradorNuevo.setEmail("@gmail.com");
        administradorNuevo.setTelefono("3167127633");

        administradorRepo.save(administradorNuevo);

        administradorRepo.delete(administradorNuevo);

        Administrador administradorBorrado= administradorRepo.findById("1193409775").orElse(null);

        Assertions.assertNull(administradorBorrado);
    }

    //================================= Metodo para actualizar o modificar un administrador =================================//
    @Test
    public void actualizarAdministradorTest(){

        Administrador administradorNuevo = new Administrador();
        administradorNuevo.setId("1193409775");
        administradorNuevo.setNombre("Sebastian");
        administradorNuevo.setPassword("21quintero04");
        administradorNuevo.setEmail("@gmail.com");
        administradorNuevo.setTelefono("3167127633");

        Administrador administradorGuardado= administradorRepo.save(administradorNuevo);

        administradorGuardado.setEmail("sebas@gmail.com");
        administradorRepo.save(administradorGuardado);

        Administrador administradorBuscado= administradorRepo.findById("1193409775").orElse(null);

        Assertions.assertEquals("sebas@gmail.com",administradorBuscado.getEmail());
    }

    //================================= Metodo para obtener los administradores =================================//
    @Test
    @Sql("classpath:administradores.sql")
    public void listarAdministradoresTest(){

        List<Administrador> lista = administradorRepo.findAll();
        System.out.println(lista);
    }
}
