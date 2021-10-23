package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.PersonaRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonaServicioImpl implements PersonaServicio{

    @Autowired
    private PersonaRepo personaRepo;

    @Override
    public Persona login(String email, String password) throws Exception {

        Optional<Persona> persona = personaRepo.findByEmailAndPassword(email,password);

        if (persona ==null){
            throw new Exception("Los datos de autenticación son incorrectos");
        }

        return persona.get();
    }


    @Override
    public Persona obtenerPersonaEmail(String email) throws Exception {

        Optional<Persona> personaEncontrada = personaRepo.findByEmail(email);

        if(personaEncontrada.isEmpty()){

            throw new Exception("No existe un usuario con el correo dado");
        }
        return personaEncontrada.get();
    }


    @Override
    public void cambiarPassword(String email,String passwordN) throws Exception {

        Persona personaEncontrada = obtenerPersonaEmail(email);

        if (personaEncontrada!=null){
            personaEncontrada.setPassword(passwordN);
            personaRepo.save(personaEncontrada);
        }else{
            throw new Exception("No existe una persona con el correo dado");
        }

    }

}
