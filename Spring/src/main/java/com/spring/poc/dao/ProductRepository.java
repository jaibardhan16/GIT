package com.spring.poc.dao;


import org.springframework.data.repository.CrudRepository;

import com.spring.poc.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}