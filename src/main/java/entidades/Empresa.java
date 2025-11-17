package entidades;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;

@Builder
@Data
@ToString(exclude = "sucursales")
public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Long cuit;
    private String logo;
    private HashSet<Sucursal> sucursales;
    public void addSucursal(Sucursal sucursal){
        this.sucursales.add(sucursal);
    }
    public void removeSucursal(Sucursal sucursal){
        this.sucursales.remove(sucursal);
    }
}
