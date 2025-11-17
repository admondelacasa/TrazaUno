package entidades;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Provincia {
    private Long id;
    private String nombre;
    private Pais pais;
}
