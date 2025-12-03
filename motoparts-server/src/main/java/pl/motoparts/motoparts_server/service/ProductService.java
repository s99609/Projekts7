package pl.motoparts.motoparts_server.service;

import pl.motoparts.motoparts_server.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product updated);

    void deleteProduct(Long id);
}
