package com.springboot.app.item.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Producto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Date createAt;
    private Integer port;
}
