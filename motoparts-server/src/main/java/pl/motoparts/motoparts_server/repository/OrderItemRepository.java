package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.OrderItem;
import pl.motoparts.motoparts_server.model.Order;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);
}
