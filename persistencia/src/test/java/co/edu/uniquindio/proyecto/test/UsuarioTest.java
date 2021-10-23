package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //================================= Metodo para registrar o crear un usuario =================================//
    @Test
    @Sql("classpath:ciudades.sql")
    public void registrarUsuarioTest(){

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Usuario usuarioNuevo = new Usuario();

        usuarioNuevo.setId("123");
        usuarioNuevo.setNombre("Fernando");
        usuarioNuevo.setPassword("fercho123");
        usuarioNuevo.setEmail("@gmail.com");
        usuarioNuevo.setCiudad(ciudadBuscada);
        ciudadBuscada.getUsuarios().add(usuarioNuevo);

        Usuario usuarioGuardado = usuarioRepo.save(usuarioNuevo);

        System.out.println(usuarioGuardado.toString());

        Assertions.assertNotNull(usuarioGuardado);
    }

    //================================= Metodo para eliminar un usuario =================================//
    @Test
    @Sql("classpath:ciudades.sql")
    public void eliminarUsuarioTest() {

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Usuario usuarioNuevo = new Usuario("123", "Fernando","311", "fercho123", "@gmail.com");
        usuarioNuevo.setCiudad(ciudadBuscada);
        ciudadBuscada.getUsuarios().add(usuarioNuevo);

        usuarioRepo.save(usuarioNuevo);

        usuarioRepo.delete(usuarioNuevo);

        Usuario usuarioBorrado = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNull(usuarioBorrado);
    }

    //================================= Metodo para actualizar o modificar un usuario =================================//
    @Test
    @Sql("classpath:ciudades.sql")
    public void actualizarUsuarioTest(){

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Usuario usuarioNuevo = new Usuario("123", "Fernando","311", "fercho123", "@gmail.com");
        usuarioNuevo.setCiudad(ciudadBuscada);
        ciudadBuscada.getUsuarios().add(usuarioNuevo);

        Usuario usuarioGuardado=  usuarioRepo.save(usuarioNuevo);

        usuarioGuardado.setEmail("f@gmail.com");
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado= usuarioRepo.findById("123").orElse(null);

        Assertions.assertEquals("f@gmail.com",usuarioBuscado.getEmail());
    }

    //================================= Metodo para obtener los usuarios =================================//
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosTest(){

        List<Usuario> lista = usuarioRepo.findAll();
        System.out.println(lista);

    }

    //================================= Metodo para validar un inicio de secion =================================//
    @Test
    @Sql("classpath:usuarios.sql")
    public void iniciarSecionTest(){
        Usuario usuarioNuevo = usuarioRepo.findByEmailAndPassword("f@gmail.com","fer123") ;

        Assertions.assertNotNull(usuarioNuevo);
    }

    //================================= Metodo para listar los usuarios ordenadamente =================================//
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPaginadosTest(){

        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        for (Usuario usuarioNuevo:lista){
            System.out.println(lista);
        }
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosDadoNombreTest(){

        List<Usuario> lista = usuarioRepo.findByNombre("Manuela");
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPaginados2Test(){

        List<Usuario> lista = usuarioRepo.obtenerUsuarios(PageRequest.of(0,2));
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerUsuariosCiudadTest(){

        List<Usuario>usuarios=ciudadRepo.obtenerCiudadUsuario("Armenia");

        for(Usuario u:usuarios){

            System.out.println(u);
        }
    }
}