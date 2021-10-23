package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    //================================= Instancia del repositorio =================================//
    @Autowired
    private CiudadRepo ciudadRepo;

    //================================= Metodo para registrar o crear una ciudad =================================//
    @Test
    public void registrarCiudadTest(){

        Ciudad ciudadNueva = new Ciudad("Armenia");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        System.out.println(ciudadGuardada.toString());

        Assertions.assertNotNull(ciudadGuardada);
    }

    //================================= Metodo para eliminar una ciudad =================================//
    @Test
    public void eliminarCiudadTest(){

        Ciudad ciudadNueva = new Ciudad("Armenia");

        ciudadRepo.save(ciudadNueva);
        ciudadRepo.delete(ciudadNueva);

        Ciudad ciudadBorrada= ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(ciudadBorrada);
    }

    //================================= Metodo para actualizar o modificar una ciudad =================================//
    @Test
    public void actualizarCiudadTest(){

        Ciudad ciudadNueva = new Ciudad("Armenia");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        ciudadGuardada.setNombre("Calarcá");
        ciudadRepo.save(ciudadGuardada);

        Ciudad ciudadBuscada= ciudadRepo.findById(1).orElse(null);

        System.out.println(ciudadBuscada.toString());

        Assertions.assertEquals("Calarcá",ciudadBuscada.getNombre());
    }

    //================================= Metodo para obtener las ciudades =================================//
    @Test
    @Sql("classpath:ciudades.sql")
    public void listarCiudadesTest(){

        List<Ciudad> lista = ciudadRepo.findAll();
        System.out.println(lista);
    }
}
