package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    private final AdministradorRepo administradorRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Administrador> admEmail = administradorRepo.findByEmail(email);

        return admEmail.isPresent();
    }

    @Override
    public Administrador registrarAdministrador(Administrador a) throws Exception {

        if (a.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres");
        }

        if (a.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres compa");
        }

        if (a.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        if (a.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres compa");
        }

        if(estaDisponible(a.getEmail())){
            throw new Exception("El administrador ya existe");
        }

        return administradorRepo.save(a);
    }

    @Override
    public void actualizarAdministrador(Administrador a,String email, String password) throws Exception {

        Administrador administradorObtenido = obtenerEmailPassword(email,password);


        if(administradorObtenido!= null){
            administradorObtenido.setId(a.getId());
            administradorObtenido.setEmail(a.getEmail());
            administradorObtenido.setNombre(a.getNombre());
            administradorObtenido.setPassword(a.getPassword());
            administradorObtenido.setTelefono(a.getTelefono());

            administradorRepo.save(administradorObtenido);
        }
    }

    @Override
    public void eliminarAdministrador(String email) throws Exception {
        Administrador administradorEncontrado = obtenerAdministradorEmail(email);

        if (administradorEncontrado  != null){
           administradorRepo.delete(administradorEncontrado );
        } else {
            throw new Exception("El administrador no ha sido encontrado");
        }
    }

    @Override
    public Administrador obtenerAdministrador(String id) throws Exception {
        Optional<Administrador> administrador = administradorRepo.findById(id);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador con el id ingresado");
        }

        return administrador.get();
    }

    @Override
    public Administrador obtenerAdministradorEmail(String email) throws Exception {

        Optional<Administrador> administrador = administradorRepo.findByEmail(email);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador con el correo ingresado");
        }

        return administrador.get();
    }


    @Override
    public void actualizar(String id,Administrador a){

        try {
            Administrador administradorEncontrado = obtenerAdministrador(id);

            if (administradorEncontrado!=null){

                administradorEncontrado.setNombre(a.getNombre());
                administradorEncontrado.setPassword(a.getPassword());
                administradorEncontrado.setTelefono(a.getTelefono());
                administradorEncontrado.setEmail(a.getEmail());

                administradorRepo.save(administradorEncontrado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador obtenerEmailPassword(String email, String password) throws Exception {

        Administrador administrador =administradorRepo.findByEmailAndPassword(email, password);

        if(administrador == null){

            throw new Exception("¡Ups! No te hemos podido encontrar");
        }

        return administrador;
    }
}
