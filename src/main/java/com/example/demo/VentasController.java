package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/ventas") // Prefijo común para todas las rutas de este controlador
public class VentasController {
    private List<Venta> ventas = new ArrayList<>();

    // Constructor del controlador
    public VentasController() {
        // Poblar la lista con ventas de ejemplo
        ventas.add(new Venta(
            1L, // ID de la venta
            LocalDate.now(), // Fecha de la venta
            Arrays.asList(
                new Producto(1, "Comida Para Perro", 2950.0), // Primer producto
                new Producto(2, "Juguete Para Perro", 1200.0)  // Segundo producto
            )
        ));

        // Agregando otras ventas a la lista manualmente
        ventas.add(new Venta(
            2L,
            LocalDate.now(), 
            Arrays.asList(new Producto(3, "Arena Para Gato", 3550.0),
                          new Producto(2, "Juguete Para Perro", 1200.0))
        ));
        ventas.add(new Venta(
            3L,
            LocalDate.now(), 
            Arrays.asList(new Producto(4, "Pelet", 3000.0))
        ));
        ventas.add(new Venta(
            4L,
            LocalDate.now(), 
            Arrays.asList(new Producto(4, "Pelet", 3000.0),
                          new Producto(3, "Arena Para Gato", 3550.0))
        ));
    }

    // Método para obtener todas las ventas
    @GetMapping // Ruta será /ventas
    public List<Venta> getVentas(){
        return ventas;
    }

    // Método para obtener una venta por ID
    @GetMapping("/{id}") // Ruta será /ventas/{id}
    public Venta obtenerVentaPorId(@PathVariable Long id) {
        // Buscar la venta por ID y devolverla, o null si no se encuentra
        return ventas.stream()
                     .filter(venta -> venta.getId().equals(id))
                     .findFirst()
                     .orElse(null);
    }

    // Método para calcular ganancias por periodo (diario, mensual, anual)
    @GetMapping("/ganancias/{periodo}") // Ruta será /ventas/ganancias/{periodo}
    public Double calcularGanancias(@PathVariable String periodo) {
        LocalDate hoy = LocalDate.now();
        return ventas.stream()
                     .filter(venta -> {
                         switch (periodo.toLowerCase()) {
                             case "diaria":
                                 return venta.getFecha().isEqual(hoy);
                             case "mensual":
                                 return venta.getFecha().getMonth() == hoy.getMonth() && venta.getFecha().getYear() == hoy.getYear();
                             case "anual":
                                 return venta.getFecha().getYear() == hoy.getYear();
                             default:
                                 return false;
                         }
                     })
                     .mapToDouble(venta -> venta.getProductos().stream().mapToDouble(Producto::getPrecio).sum())
                     .sum();
    }

}