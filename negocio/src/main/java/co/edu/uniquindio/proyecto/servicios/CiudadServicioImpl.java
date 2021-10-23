package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {

        if (ciudad.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {

        if (ciudad.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(int id) throws Exception {

        Ciudad ciudadEncontrada=obtenerCiudad(id);

        if (ciudadEncontrada != null){
            ciudadRepo.delete(ciudadEncontrada);
        }else{
            throw new Exception("La ciudad que desea eliminar no existe");
        }
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws Exception {

        Optional<Ciudad> ciudad= ciudadRepo.findById(id);

        if (ciudad.isEmpty()){
            throw new Exception("La ciudad buscada no existe");
        }

        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
