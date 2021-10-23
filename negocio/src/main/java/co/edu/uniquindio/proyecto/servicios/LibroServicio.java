package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Libro;

import java.util.List;

public interface LibroServicio {

    Libro registrarLibro(Libro libro) throws Exception;

    void actualizarLibro(int isbn,Libro libro) throws Exception;

    void eliminarLibro(int isbn) throws Exception;

    Libro obtenerLibro(int isbn) throws Exception;

    List<Libro> listarLibro();
}
