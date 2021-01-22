package com.springboot.app.item.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Producto;
import com.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
//    @Qualifier("serviceRestTemplate")
    private ItemService objItemService;

    @GetMapping("/listar")
    private List<Item> listar(){
        return objItemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/detalle/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id,@PathVariable Integer cantidad){
        return objItemService.findById(id,cantidad);
    }

    public Item metodoAlternativo(@PathVariable Long id,@PathVariable Integer cantidad){
        Item ite=new Item();
        Producto prod=new Producto();
        ite.setCantidad(cantidad);
        prod.setId(id);
        prod.setNombre("no hay producto");
        prod.setPrecio(BigDecimal.valueOf(600.0));
        ite.setProducto(prod);
        return ite;
    }
}
