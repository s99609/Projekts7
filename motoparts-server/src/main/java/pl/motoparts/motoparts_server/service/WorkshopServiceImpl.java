package pl.motoparts.motoparts_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.motoparts.motoparts_server.model.Workshop;
import pl.motoparts.motoparts_server.repository.WorkshopRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @Override
    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workshop not found with id: " + id));
    }

    @Override
    public Workshop createWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop updateWorkshop(Long id, Workshop workshop) {
        Workshop existing = getWorkshopById(id);

        existing.setName(workshop.getName());
        existing.setAddress(workshop.getAddress());
        existing.setEmail(workshop.getEmail());
        existing.setPhone(workshop.getPhone());
        existing.setNip(workshop.getNip());
        existing.setUser(workshop.getUser());

        return workshopRepository.save(existing);
    }

    @Override
    public void deleteWorkshop(Long id) {
        if (!workshopRepository.existsById(id)) {
            throw new IllegalArgumentException("Workshop not found with id: " + id);
        }
        workshopRepository.deleteById(id);
    }
}