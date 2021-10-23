package co.edu.uniquindio.proyecto.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class inicioBean implements Serializable {


    @PostConstruct
    public void inicializar(){

        }

}
