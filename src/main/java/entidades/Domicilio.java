package entidades;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Domicilio {
    private Long id;
    private String calle;
    private int numero;
    private int cp;
    private Localidad localidad;
}
