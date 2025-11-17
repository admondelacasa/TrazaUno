package entidades;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Localidad {
    private Long id;
    private String nombre;
    private Provincia provincia;
}
