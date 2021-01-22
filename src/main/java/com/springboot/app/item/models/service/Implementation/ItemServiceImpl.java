package com.springboot.app.item.models.service.Implementation;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Producto;
import com.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
//        List<Producto> productos= Arrays.asList(clienteRest.getForObject("http://localhost:8001/products/listar",Producto[].class));
        List<Producto> productos= Arrays.asList(clienteRest.getForObject("http://servicio-productos/products/listar",Producto[].class));

        //ahora se usa Java 8 para pasar cada prodcuto a objeto tipo Item y almacenarlo en una Lista de tipo item
        return productos.stream().map(producto->new Item(producto,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String,String> pathVariables=new HashMap<>();
        pathVariables.put("id",String.valueOf(id));
//        Producto producto= clienteRest.getForObject("http://localhost:8001/products/detalle/{id}",Producto.class,pathVariables);
        Producto producto= clienteRest.getForObject("http://servicio-productos/products/detalle/{id}",Producto.class,pathVariables);
        return new Item(producto,cantidad);
    }
}
