package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;

public interface PersonaServicio {

    Persona login(String email,String password) throws Exception;

    Persona obtenerPersonaEmail(String email) throws Exception;

    void cambiarPassword(String email,String passwordN) throws Exception;
}
