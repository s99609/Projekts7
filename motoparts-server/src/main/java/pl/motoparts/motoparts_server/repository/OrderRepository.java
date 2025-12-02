package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.Order;
import pl.motoparts.motoparts_server.model.Workshop;
import pl.motoparts.motoparts_server.model.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByWorkshop(Workshop workshop);

    List<Order> findByStatus(OrderStatus status);
}