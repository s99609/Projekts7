package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.Product;
import pl.motoparts.motoparts_server.model.ProductCategory;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(ProductCategory category);

    List<Product> findByBrandIgnoreCase(String brand);

    List<Product> findByNameContainingIgnoreCase(String namePart);
}