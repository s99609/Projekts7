package pl.motoparts.motoparts_server.service;

import pl.motoparts.motoparts_server.model.Workshop;

import java.util.List;

public interface WorkshopService {

    List<Workshop> getAllWorkshops();

    Workshop getWorkshopById(Long id);

    Workshop createWorkshop(Workshop workshop);

    Workshop updateWorkshop(Long id, Workshop workshop);

    void deleteWorkshop(Long id);
}