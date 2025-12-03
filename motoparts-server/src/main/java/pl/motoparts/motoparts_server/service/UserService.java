package pl.motoparts.motoparts_server.service;

import pl.motoparts.motoparts_server.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    void deleteUser(Long id);
}
