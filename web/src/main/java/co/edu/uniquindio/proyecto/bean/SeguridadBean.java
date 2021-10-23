package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.MailService;
import co.edu.uniquindio.proyecto.servicios.PersonaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private Persona personaAux;

    @Getter @Setter
    private  boolean autenticado;

    @Autowired
    private PersonaServicio personaServicio;

    @Getter @Setter
    @NotBlank
    private String email,emailR,password;

    @Getter @Setter
    private String rol;

    @Autowired
    private MailService mailService;


    public String iniciarSesion(){

        if (email!=null && password!=null) {
            try {
                persona = personaServicio.login(email,password);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! ingreso correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                if (persona instanceof Usuario){
                    rol="usuario";

                }else if (persona instanceof Administrador){
                    rol="admin";
                }
                autenticado=true;

                System.out.println(persona.getNombre() + autenticado+" "+rol);
               return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }

        return null;
    }


    public String cerrarSesion(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }


    public String buscarPorEmail(){

        try {
            personaAux = personaServicio.obtenerPersonaEmail(emailR);

            if(personaAux!=null){

                sendMail();


            }else{

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El email que ingreso no se encuentra registrado");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

            return "/index?faces-redirect=true";

        }catch (Exception e){

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
        return null;
    }


    public void cambiarPassword(){

        try {
            if (!password.isEmpty()){

                personaServicio.cambiarPassword(email,password);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la contraseña se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "¡Lo sentimos! no pudimos actualizar tu contraseña");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

    }

    public void sendMail() {

        String subject = "Recuperacion de contraseña";
        String url = "http://localhost:8080/recuperarContrasena.xhtml";
        String message = "Cordial Saludo para recuperar su contraseña, de click en el siguiente enlace" + "\n" + url;

        mailService.sendMail("meliortizmahone@gmail.com", personaAux.getEmail(), subject, message);
        System.out.println("Correo enviado");

    }
}
