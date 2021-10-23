package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface AdministradorServicio {

    Administrador registrarAdministrador(Administrador a) throws Exception;

    void actualizarAdministrador(Administrador a,String email,String password) throws Exception;

    void eliminarAdministrador(String email) throws Exception;

    Administrador obtenerAdministrador(String id) throws Exception;

    Administrador obtenerAdministradorEmail(String email) throws Exception;

    List<Administrador> listarAdministradores();

    Administrador obtenerEmailPassword(String email,String password) throws Exception;
}
