package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar(){

        return usuarioServicio.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Usuario usuario){

        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(201).body(new Mensaje("El usuario se ha registrado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> actualizar(@PathVariable(name="id") String id, @RequestBody Usuario usuario){

        try {
         usuarioServicio.actualizar(id,usuario);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se actualizo correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name="id") String id){

        try {
            usuarioServicio.eliminarUsuarioId(id);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se elimino correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable(name="id") String id){

        try {
            return ResponseEntity.status(200).body(usuarioServicio.obtenerUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }



}
