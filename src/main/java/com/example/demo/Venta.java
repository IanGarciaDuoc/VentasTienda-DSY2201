package com.example.demo;

import java.time.LocalDate;
import java.util.List;

public class Venta {
    private Long id; // Identificador único de la venta
    private LocalDate fecha; // Fecha en la que se realizó la venta
    private List<Producto> productos; // Lista de productos vendidos

    // Constructor de la clase
    public Venta(Long id, LocalDate fecha, List<Producto> productos) {
        this.id = id; // Asigna el identificador único
        this.fecha = fecha; // Asigna la fecha de la venta
        this.productos = productos; // Asigna la lista de productos vendidos
    }

    // Método para obtener el ID de la venta
    public Long getId() {
        return id;
    }

    // Método para obtener la fecha de la venta
    public LocalDate getFecha() {
        return fecha;
    }

    // Método para obtener la lista de productos vendidos
    public List<Producto> getProductos() {
        return productos;
    }

    // Método para calcular el monto total de la venta
    // Suma los precios de todos los productos incluidos en esta venta
    public Double getMonto() {
        return productos.stream()
                        .mapToDouble(Producto::getPrecio) // Convierte cada producto a su precio
                        .sum(); // Suma todos los precios
    }
}
