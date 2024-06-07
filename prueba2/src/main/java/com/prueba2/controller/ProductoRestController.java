package com.prueba2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba2.entity.Producto;
import com.prueba2.repository.ProductoRepository;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")


public class ProductoRestController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/producto")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto body) {
        body.calcularValores();
        return productoRepository.save(body);
    }

    @GetMapping("/producto")
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable("id") Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/producto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Producto body, @PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto not found with id: " + id));
        
        producto.setDescripcion(body.getDescripcion());
        producto.setPrecio(body.getPrecio());
        producto.setCantidad(body.getCantidad());
        producto.calcularValores();

        productoRepository.save(producto);
    }

    @DeleteMapping("/producto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
    }
}