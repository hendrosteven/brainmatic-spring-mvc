package com.brainmatics.data.repos;

import java.util.List;

import com.brainmatics.data.entity.Product;

public interface ProductRepo {
    public List<Product> findAll();
    public Product findOne(String code);
    public Product createOne(Product product);
    public void removeOne(String code);
    public Product updateOne(Product product);
}
