package com.shopsmart.inventory_service;

public class Producto {
    private Long id;
    private String nombre;
    private int precio; // Cambiado a int (entero) para Pesos Chilenos
    private int stock;

    // Constructores
    public Producto() {}

    public Producto(Long id, String nombre, int precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}