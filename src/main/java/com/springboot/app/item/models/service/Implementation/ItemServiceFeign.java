package com.springboot.app.item.models.service.Implementation;

import com.springboot.app.item.clientes.ProductoClientRest;
import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Producto;
import com.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
//@Primary
//le decimos que por defecto implemente este servicio cundo hay 2 clases que implementan una interfaz
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductoClientRest objClienteFeign;


    @Override
    public List<Item> findAll() {

        return objClienteFeign.listar().stream().map(producto->new Item(producto,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(objClienteFeign.detalle(id),cantidad);
    }
}
