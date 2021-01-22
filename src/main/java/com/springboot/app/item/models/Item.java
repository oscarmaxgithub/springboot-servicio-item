package com.springboot.app.item.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Producto producto;
    private Integer cantidad;

    public Double getTotal() {
        return producto.getPrecio().doubleValue() * cantidad.doubleValue();
    }
}
