package com.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba2.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

}
