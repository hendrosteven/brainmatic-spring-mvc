package com.brainmatics.data.repos;

import java.util.ArrayList;
import java.util.List;

import com.brainmatics.data.entity.Product;

public class ProductFakeRepo implements ProductRepo{

    private List<Product> products = new ArrayList<>();

    public ProductFakeRepo() {
        products.add(new Product("001","Product 1", 100, "Product 1 Description"));
        products.add(new Product("002","Product 2", 200, "Product 2 Description"));
        products.add(new Product("003","Product 3", 300, "Product 3 Description"));
        products.add(new Product("004","Product 4", 400, "Product 4 Description"));
        products.add(new Product("005","Product 5", 500, "Product 5 Description"));
    }

    @Override
    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public Product findOne(String code) {
        for(Product product : this.products){          
            if(product.getCode().equalsIgnoreCase(code)){                
                return product;
            }
        }
        return null;
    }

    @Override
    public Product createOne(Product product) {
        this.products.add(product);
        return product;
    }

    @Override
    public void removeOne(String code) {
        for(Product product : this.products){          
            if(product.getCode().equalsIgnoreCase(code)){                
                this.products.remove(product);
                break;
            }
        }
    }

    @Override
    public Product updateOne(Product product) {        
        for(Product temp : this.products){                      
            if(temp.getCode().equalsIgnoreCase(product.getCode())){  
                products.remove(temp);
                products.add(product);             
                return temp;
            }
        }
        return null;
    }
    
}
