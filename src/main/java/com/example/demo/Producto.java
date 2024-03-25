package com.example.demo;

public class Producto {
    private int id;
    private String nombre;
    private Double precio;

    // Constructor, getters 
    public Producto(int id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getPrecio() {
        return precio;
    }
}