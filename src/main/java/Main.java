import entidades.*;
import java.time.LocalTime;
import repositorio.InMemoryRepository;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> repository = new InMemoryRepository<>();
        // País
        Pais pais1 = Pais.builder()
                .id(Long.valueOf(1))
                .nombre("Argentina")
                .build();
        // Provincias
        Provincia provincia1 = Provincia.builder()
                .id(Long.valueOf(1))
                .nombre("Buenos Aires")
                .pais(pais1)
                .build();
        Provincia provincia2= Provincia.builder()
                .id(Long.valueOf(2))
                .nombre("Córdoba")
                .pais(pais1)
                .build();
        // Localidades
        Localidad localidad1 = Localidad.builder()
                .id(Long.valueOf(1))
                .nombre("CABA")
                .provincia(provincia1)
                .build();
        Localidad localidad2 = Localidad.builder()
                .id(Long.valueOf(2))
                .nombre("La Plata")
                .provincia(provincia1)
                .build();
        Localidad localidad3 = Localidad.builder()
                .id(Long.valueOf(3))
                .nombre("Córdoba Capital")
                .provincia(provincia2)
                .build();
        Localidad localidad4 = Localidad.builder()
                .id(Long.valueOf(4))
                .nombre("Villa Carlos Paz")
                .provincia(provincia2)
                .build();
        // Domicilios
        Domicilio domicilio1 = Domicilio.builder()
                .id(Long.valueOf(1))
                .calle("9 de Julio")
                .numero(700)
                .cp(1000)
                .localidad(localidad1)
                .build();
        Domicilio domicilio2 = Domicilio.builder()
                .id(Long.valueOf(2))
                .calle("San Martín")
                .numero(1300)
                .cp(1200)
                .localidad(localidad2)
                .build();
        Domicilio domicilio3 = Domicilio.builder()
                .id(Long.valueOf(3))
                .calle("Los Olmos")
                .numero(540)
                .cp(4300)
                .localidad(localidad3)
                .build();
        Domicilio domicilio4 = Domicilio.builder()
                .id(Long.valueOf(4))
                .calle("Chile")
                .numero(150)
                .cp(4700)
                .localidad(localidad4)
                .build();
        // Sucursales
        Sucursal sucursal1 = Sucursal.builder()
                .id(Long.valueOf(1))
                .nombre("Sucursal Central")
                .domicilio(domicilio1)
                .es_Casa_Matriz(true)
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(17, 0))
                .build();
        Sucursal sucursal2 = Sucursal.builder()
                .id(Long.valueOf(2))
                .nombre("Sucursal La Plata")
                .domicilio(domicilio2)
                .es_Casa_Matriz(false)
                .horarioApertura(LocalTime.of(8, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .build();
        Sucursal sucursal3 = Sucursal.builder()
                .id(Long.valueOf(3))
                .nombre("Sucursal Capital")
                .domicilio(domicilio3)
                .es_Casa_Matriz(true)
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(17, 0))
                .build();
        Sucursal sucursal4 = Sucursal.builder()
                .id(Long.valueOf(4))
                .nombre("Sucursal Carlos Paz")
                .domicilio(domicilio1)
                .es_Casa_Matriz(false)
                .horarioApertura(LocalTime.of(13, 0))
                .horarioCierre(LocalTime.of(21, 0))
                .build();
        // Empresas
        Empresa empresa1 = Empresa.builder()
                .id(Long.valueOf(1))
                .nombre("Software Genérico S.A.")
                .razonSocial("IVA")
                .cuit(Long.valueOf(12345678910L))
                .logo("SGSA")
                .sucursales(new HashSet<>())
                .build();
        Empresa empresa2 = Empresa.builder()
                .id(Long.valueOf(2))
                .nombre("Aires Manolo")
                .razonSocial("IVA")
                .cuit(Long.valueOf(19876543201L))
                .logo("Aima")
                .sucursales(new HashSet<>())
                .build();
        empresa1.addSucursal(sucursal1);
        empresa1.addSucursal(sucursal2);
        empresa2.addSucursal(sucursal3);
        empresa2.addSucursal(sucursal4);
        // Pruebas
        repository.save(empresa1);
        repository.save(empresa2);
        // Mostrar todas las empresas
        System.out.println("Todas las empresas:");
        repository.findAll().forEach(System.out::println);
        // Buscar empresa por ID
        repository.findById(Long.valueOf(1)).ifPresent(e -> System.out.println("Empresa encontrada por ID 1: " + e));
        // Buscar empresa por nombre
        System.out.println("Empresas con nombre 'Aires Manolo':");
        repository.genericFindByField("nombre", "Aires Manolo").forEach(System.out::println);
        // Actualizar empresa por ID
        Empresa empresaActualizada = Empresa.builder()
                .id(Long.valueOf(1))
                .nombre("Empresa 1 Actualizada")
                .razonSocial("Razon Social 1 Actualizada")
                .cuit(Long.valueOf(12345678901L))
                .sucursales(empresa1.getSucursales())
                .build();

        repository.genericUpdate(Long.valueOf(1), empresaActualizada);
        Optional<Empresa> empresaVerificada = repository.findById(Long.valueOf(1));
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));

        // Eliminar empresa por ID
        repository.genericDelete(Long.valueOf(2));
        Optional<Empresa> empresaEliminada = repository.findById(Long.valueOf(2));
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 2 ha sido eliminada.");
        }

        // Mostrar todas las empresas restantes
        System.out.println("Todas las empresas después de la eliminación:");
        List<Empresa> empresasRestantes = repository.findAll();
        empresasRestantes.forEach(System.out::println);
        System.out.println("--------------Mostrar las sucursales de una empresa determinada");
        // Mostrar las sucursales de una empresa determinada
        Optional<Empresa> empresa = repository.findById(Long.valueOf(1));
        if (empresa.isPresent()) {
            System.out.println("Sucursales de la empresa con ID "+ empresa.get().getId()+ ":");
            Set<Sucursal> sucursales = empresa.get().getSucursales();
            sucursales.forEach(System.out::println);
        } else {
            System.out.println("Empresa con ID " + " no encontrada.");
        }
    }
}
