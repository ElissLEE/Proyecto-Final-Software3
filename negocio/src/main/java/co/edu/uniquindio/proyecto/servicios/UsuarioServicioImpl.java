package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    public boolean estaDisponible(String email){
        Usuario usuarioEmail = usuarioRepo.findByEmail(email);

        if (usuarioEmail!=null){
            return  true;
        }

        return false;
    }


    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

        if (u.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres ");
        }


        if (u.getTelefono().length()>30){
            throw new Exception("El telefono solo puede tener 30 caracteres ");
        }

        if (u.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres ");
        }

        if (u.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        if (u.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres compa");
        }

        if(estaDisponible(u.getEmail())){
            throw new Exception("El usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public void actualizarUsuario(String email,Usuario u) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmail(email);

        if(usuarioEncontrado!=null){

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setPassword(u.getPassword());
            usuarioEncontrado.setCiudad(u.getCiudad());
            usuarioEncontrado.setEmail(u.getEmail());
            usuarioEncontrado.setTelefono(u.getTelefono());

            usuarioRepo.save(usuarioEncontrado);
        }

    }

    @Override
    public void actualizar(String id,Usuario u){

        try {
            Usuario usuarioEncontrado = obtenerUsuario(id);

            if (usuarioEncontrado!=null){

                usuarioEncontrado.setNombre(u.getNombre());
                usuarioEncontrado.setPassword(u.getPassword());
                usuarioEncontrado.setCiudad(u.getCiudad());
                usuarioEncontrado.setTelefono(u.getTelefono());
                usuarioEncontrado.setEmail(u.getEmail());

                usuarioRepo.save(usuarioEncontrado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void eliminarUsuario(String email,String password) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password) ;

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
        }
    }

    @Override
    public void eliminarUsuarioId(String id) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuario(id);

        if (usuarioEncontrado!=null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
        }
    }


    @Override
    public Usuario obtenerUsuario(String id) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
    }

    @Override
    public Usuario obtenerUsuarioEmail(String email) throws Exception {

        Usuario usuario = usuarioRepo.findByEmail(email);

        if(usuario!=null){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario;
    }


    @Override
    public Usuario obtenerUsuarioEmailPassword(String email,String password) throws Exception {

        Usuario usuario = usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario==null){
            throw new Exception("No existe un usuario con el correo y contraseña ingresado");
        }

        return usuario;
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }


}
