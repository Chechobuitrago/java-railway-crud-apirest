package com.sbuitrago.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbuitrago.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
