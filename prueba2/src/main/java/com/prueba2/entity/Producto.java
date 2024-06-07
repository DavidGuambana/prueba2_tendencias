package com.prueba2.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigo;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private int cantidad;

    @Transient
    private Double valorCompra;

    @Transient
    private Double descuento;

    @Transient
    private Double iva;

    @Transient
    private Double total;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.cantidad = cantidad;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public Double getDescuento() {
        return descuento;
    }

    public Double getIva() {
        return iva;
    }

    public Double getTotal() {
        return total;
    }

    public void calcularValores() {
        this.valorCompra = this.precio * this.cantidad;
        this.descuento = this.valorCompra > 50 ? this.valorCompra * 0.10 : 0;
        this.iva = (this.valorCompra - this.descuento) * 0.12;
        this.total = this.valorCompra - this.descuento + this.iva;
    }
}
