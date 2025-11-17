package entidades;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pais {
    private Long id;
    private String nombre;
}
