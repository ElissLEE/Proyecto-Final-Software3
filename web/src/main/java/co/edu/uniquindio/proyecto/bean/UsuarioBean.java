package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    @Getter @Setter
    private CiudadServicio ciudadServicio;

    @Autowired
    private MailService mailService;


    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;


    @PostConstruct
    public void inicializar() {

        this.ciudades = ciudadServicio.listarCiudades();
    }


    public void sendMailRespuesta(String respuesta,String email){

        String subject = "En hora buena, alguien respondio tu comentario";
        String message = respuesta;

        mailService.sendMail("unilocal0804@gmail.com", email,subject,message);

    }

}
