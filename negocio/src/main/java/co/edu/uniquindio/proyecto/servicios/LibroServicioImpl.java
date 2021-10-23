package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.repositorios.LibroRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServicioImpl implements LibroServicio{

    private final LibroRepo libroRepo;

    public LibroServicioImpl(LibroRepo libroRepo) {
        this.libroRepo= libroRepo;
    }

    @Override
    public Libro registrarLibro(Libro libro) throws Exception {

        if (libro.getTitulo().length()>100){
            throw new Exception("El titulo solo puede tener 100 caracteres ");
        }

        if (libro.getAutor().length()>100){
            throw new Exception("El autor solo puede tener 100 caracteres ");
        }

        libro.setEstado(true);
        return libroRepo.save(libro);

    }

    @Override
    public void actualizarLibro(int isbn,Libro libro) throws Exception {

        Libro libroObtenido= obtenerLibro(isbn);

        if(libroObtenido != null){

            libroObtenido.setIsbn(libro.getIsbn());
            libroObtenido.setAutor(libro.getAutor());
            libroObtenido.setEstado(libro.getEstado());
            libroObtenido.setTitulo(libro.getTitulo());

            libroRepo.save(libroObtenido);
        }

    }

    @Override
    public void eliminarLibro(int isbn) throws Exception {

        Libro libroEncontrado=obtenerLibro(isbn);

        if (libroEncontrado != null){
            libroRepo.delete(libroEncontrado);
        }else{
            throw new Exception("El libro buscado no existe");
        }

    }

    @Override
    public Libro obtenerLibro(int isbn) throws Exception {

        Libro libro= libroRepo.findByIsbn(isbn);

        if (libro==null){
            throw new Exception("El libro buscado no existe");
        }

        return libro;
    }

    @Override
    public List<Libro> listarLibro() {
        return libroRepo.findAll();
    }
}
