package entidades;

import lombok.Builder;
import lombok.Data;
import java.time.LocalTime;

@Builder
@Data
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;
    private Domicilio domicilio;
}
