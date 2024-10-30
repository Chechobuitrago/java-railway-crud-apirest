package com.sbuitrago.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbuitrago.apirest.apirest.Entities.Producto;
import com.sbuitrago.apirest.apirest.Repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired // Inyección de dependencias
    private ProductoRepository productoRepository;

    @GetMapping // Indica que es un método GET
    public List<Producto> getAllProductos() { // Método que retorna todos los productos
        return productoRepository.findAll();
    }

    @GetMapping("/{id}") // Indica que es un método GET
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID" + id));
    }

    @PostMapping // Indica que es un método POST
    public Producto createProducto(@RequestBody Producto producto) { // Método que crea un producto
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}") // Indica que es un método PUT
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto updatedProducto) { // Método que
                                                                                                   // actualiza un
                                                                                                   // producto
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID" + id));
        producto.setNombre(updatedProducto.getNombre());
        producto.setPrecio(updatedProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}") // Indica que es un método DELETE
    public String deletedProducto(@PathVariable Long id) { // Método que elimina un producto
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID" + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " ha sido eliminado";
    }

}
