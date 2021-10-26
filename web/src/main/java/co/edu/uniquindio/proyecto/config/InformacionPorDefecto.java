package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            // datos con los que se pueden ingresa
            // si ingresa con el admin le permite hacer todo
            // con los datos del ususario solo ingresa

            Administrador admin1= new Administrador("1010017812","Melissa","304","meli123","mortiz@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Usuario u = new Usuario("1193409775", "usuario","310", "usuario", "usuario@mail.com");
            usuarioServicio.registrarUsuario(u);

            Ciudad ciudad1 = new Ciudad("Calarcá");
            Ciudad ciudad3 = new Ciudad("Medellin");
            Ciudad ciudad4 = new Ciudad("Pereira");
            Ciudad ciudad5 = new Ciudad("Armenia");
            Ciudad ciudad6 = new Ciudad("Bogota");
            Ciudad ciudad7 = new Ciudad("Cucuta");
            Ciudad ciudad8 = new Ciudad("Villavicencio");
            Ciudad ciudad9 = new Ciudad("Cali");
            Ciudad ciudad10 = new Ciudad("Tulua");
            Ciudad ciudad11 = new Ciudad("Ibague");

            ciudadServicio.registrarCiudad(ciudad1);
            ciudadServicio.registrarCiudad(ciudad3);
            ciudadServicio.registrarCiudad(ciudad4);
            ciudadServicio.registrarCiudad(ciudad5);
            ciudadServicio.registrarCiudad(ciudad6);
            ciudadServicio.registrarCiudad(ciudad7);
            ciudadServicio.registrarCiudad(ciudad8);
            ciudadServicio.registrarCiudad(ciudad9);
            ciudadServicio.registrarCiudad(ciudad10);
            ciudadServicio.registrarCiudad(ciudad11);

        }

    }
}
