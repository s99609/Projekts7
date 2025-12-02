package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.Stock;
import pl.motoparts.motoparts_server.model.Product;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);
}