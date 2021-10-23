package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.WebApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
public class UsuarioRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void registrarUsuarioTest() throws Exception{

        Usuario usuario= new Usuario("1","Braian","3011","braian123","bra@gmail.com");

        mockMvc.perform(post("/api/usuarios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());


    }

    @Test
    @Transactional
    public void actualizarUsuarioTest() throws Exception{

        Usuario u = new Usuario("1193409775","Fernando","3094","fer123","fer@gmail.com");

        mockMvc.perform(put("/api/usuarios/{id}","1193409775")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(u)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void eliminarUsuarioTest() throws Exception {

        mockMvc.perform(delete("/api/usuarios/{id}", "1193409775")
                        .contentType("application/json"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void obtenerUsuarioTest() throws Exception{

        mockMvc.perform(get("/api/usuarios/{id}","1193409775")
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void listarUsuarios() throws Exception{

        mockMvc.perform(get("/api/usuarios/")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
