package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.*;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class LibroServicioTest {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;


    @Test
    @Sql("classpath:administradores.sql")
    public void registrarLibroTest(){

        try{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaPublicacion =sdf.parse("2021/01/04");


            Administrador administrador= administradorServicio.obtenerAdministrador("5");

            Libro libro = new Libro(1234,"Cien años de soledad",true,"Gabriel Garcia Marquez",fechaPublicacion);
            libro.setAdministrador(administrador);

            Libro libroRegistrado = libroServicio.registrarLibro(libro);

            Assertions.assertNotNull(libroRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*
    @Test
    @Sql("classpath:libros.sql")
    public void actualizatLugarTest(){

        try {

            Libro aux = new Libro();

            aux.setEstado(false);

            libroServicio.actualizarLibro(12345,aux);

            Libro libroEncontrado = libroServicio.obtenerLibro(12345);
            Assertions.assertEquals(false,libroEncontrado.getEstado());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:libros.sql")
    public void eliminarLibroTest(){

        try {

            Libro libroEncontrado = libroServicio.obtenerLibro(123456);

            libroServicio.eliminarLibro(libroEncontrado.getIsbn());
            Libro libroBorrado= libroServicio.obtenerLibro(123456);

            Assertions.assertNull(libroBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:libros.sql")
    public void listarLibrosTest(){

        List<Libro> libros= libroServicio.listarLibro();

        System.out.println(libros);
    }

     */



}
