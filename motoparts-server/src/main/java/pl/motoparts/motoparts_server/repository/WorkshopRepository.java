package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
