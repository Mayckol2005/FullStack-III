package com.shopsmart.inventory_service;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class InventoryController {

    // Simulamos una base de datos en memoria con una lista
    private List<Producto> inventario = new ArrayList<>();

    public InventoryController() {
        // Agregamos un producto inicial con precio en CLP ($1.250.000)
        inventario.add(new Producto(1L, "Laptop Gamer ASUS ROG", 1250000, 15));
        inventario.add(new Producto(2L, "Monitor Samsung 27 pulgadas", 180000, 30));
    }

    // Endpoint GET /productos: Retorna la lista de productos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return inventario;
    }

    // Endpoint POST /productos: Permite agregar un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto nuevoProducto) {
        inventario.add(nuevoProducto);
        return nuevoProducto;
    }
}