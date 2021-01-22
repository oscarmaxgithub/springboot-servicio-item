package com.springboot.app.item.clientes;

import com.springboot.app.item.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-productos",path = "/products")
public interface ProductoClientRest {

    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/detalle/{id}")
    public Producto detalle(@PathVariable Long id);
}
