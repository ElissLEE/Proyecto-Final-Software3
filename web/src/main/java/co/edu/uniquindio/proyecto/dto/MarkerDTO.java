package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkerDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private float lat, lng;
    private boolean aprobado;
}
