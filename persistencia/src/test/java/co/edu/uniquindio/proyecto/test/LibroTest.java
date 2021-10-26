package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.repositorios.LibroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Relacion con la base de datos
public class LibroTest {


    //================================= Instancias del repositorio =================================//
    @Autowired
    private LibroRepo libroRepo;

    @Autowired
    private AdministradorRepo  administradorRepo;

    //================================= Metodo para registrar o crear un libro =================================//
    @Test
    @Sql("classpath:administradores.sql")
    public void registrarLibroTest(){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaPublicacion =sdf.parse("2021/01/04");


            Administrador administrador = administradorRepo.findById("5").orElse(null);

            Libro libro = new Libro(1234,"Cien años de soledad",true,"Gabriel Garcia Marquez",fechaPublicacion);

            libro.setAdministrador(administrador);

            Libro libroGuardado = libroRepo.save(libro);
            Assertions.assertNotNull(libroGuardado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //================================= Metodo para eliminar un libro =================================//
    @Test
    @Sql("classpath:libro.sql")
    public void eliminarLugarTest() {

            Libro libro= libroRepo.findByIsbn(12345);

            libroRepo.delete(libro);

            Libro libroBorrado = libroRepo.findById(12345).orElse(null);

            Assertions.assertNull(libroBorrado);

    }

    //================================= Metodo para actualizar o modificar un librp =================================//
    @Test
    @Sql("classpath:libro.sql")
    public void actualizarModeradorTest(){



            Libro libroGuardado = libroRepo.findByIsbn(12345);

            libroGuardado.setEstado(false);
            libroRepo.save(libroGuardado);

            Libro libroBuscado= libroRepo.findById(12345).orElse(null);

            Assertions.assertEquals(false,libroBuscado.getEstado());

    }

    //================================= Metodo para obtener los lugares =================================//
    @Test
    @Sql("classpath:libro.sql")
    public void listarLugaresTest(){

        List<Libro> lista = libroRepo.findAll();
        System.out.println(lista);
    }

}
