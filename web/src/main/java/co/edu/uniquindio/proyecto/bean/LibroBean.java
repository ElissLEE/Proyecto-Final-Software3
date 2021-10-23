package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import co.edu.uniquindio.proyecto.servicios.MailService;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class LibroBean implements Serializable {

    private final LibroServicio libroServicio;
    private final MailService mailService;

    @Getter
    @Setter
    private Libro libro;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter
    @Setter
    private ArrayList<String> dias;

    public LibroBean(LibroServicio libroServicio, MailService mailService) {
        this.libroServicio = libroServicio;
        this.mailService = mailService;
    }

    @PostConstruct
    public void inicializar() {
        this.libro= new Libro();
    }

    public void registrarLibro() {
        try {
            if (personaLogin != null) {

                libro.setAdministrador((Administrador) personaLogin);
                libroServicio.registrarLibro(libro);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el libro se registro correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }


    public void actualizarLibro() {

        if (personaLogin != null) {

            try {

                libroServicio.actualizarLibro(libro.getIsbn(),libro);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el libro se actualizo correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos actualizar el libro");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        }

    }


    public void eliminarLibro() {

        if (personaLogin != null) {
            try {

                libroServicio.eliminarLibro(libro.getIsbn());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el libro se elimino correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No pudimos eliminar el libro");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }


}
