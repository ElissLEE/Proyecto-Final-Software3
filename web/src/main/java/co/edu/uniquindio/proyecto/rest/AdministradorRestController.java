package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorRestController {

    @Autowired
    private AdministradorServicio administradorServicio;

    @GetMapping
    public List<Administrador> listar(){

        return administradorServicio.listarAdministradores();
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Administrador administrador){

        try {
            administradorServicio.registrarAdministrador(administrador);
            return ResponseEntity.status(201).body(new Mensaje("El administrador se ha registrado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> actualizar(@PathVariable(name="id") String id, @RequestBody Administrador administrador){

        try {
            administradorServicio.actualizar(id,administrador);
            return ResponseEntity.status(200).body(new Mensaje("El administrador se actualizo correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name="email") String email){

        try {
            administradorServicio.eliminarAdministrador(email);
            return ResponseEntity.status(200).body(new Mensaje("El administrador se elimino correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable(name="id") String id){

        try {
            return ResponseEntity.status(200).body(administradorServicio.obtenerAdministrador(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


}
