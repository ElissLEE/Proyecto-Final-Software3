package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.util.*;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;


    @Test
    public void registrarAdministradorTest(){

        try{

            Administrador administradorNuevo = new Administrador("8","Bernardo","340","barni2123","ba@gmail.com");

            Administrador administradorRegistrado = administradorServicio.registrarAdministrador(administradorNuevo);

            System.out.println(administradorNuevo);

            Assertions.assertNotNull(administradorRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:administradores.sql")
    public void actualizarAdministradorTest(){

        try{

            Administrador aux = new Administrador();

            aux.setPassword("contrasenia123");

            administradorServicio.actualizarAdministrador(aux,"meli@gmail.com","meli123");

            Administrador administradorEncontrado = administradorServicio.obtenerAdministrador("5");
            Assertions.assertEquals("contrasenia123",administradorEncontrado.getPassword());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:administradores.sql")
    public void eliminarAdministradorTest(){

        try{

            Administrador administradorEncontrado = administradorServicio.obtenerAdministrador("5");

            administradorServicio.eliminarAdministrador(administradorEncontrado.getEmail());
            Administrador admministradorBorrado = administradorServicio.obtenerAdministrador("5");

            Assertions.assertNull(admministradorBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:administradores.sql")
    public void listarAdministradorTest(){

        List<Administrador> administradores = administradorServicio.listarAdministradores();

        System.out.println(administradores);
    }

}
