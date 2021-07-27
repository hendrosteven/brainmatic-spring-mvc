package com.brainmatics.data.repos;

import com.brainmatics.data.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
    
    //other function 
    
}
