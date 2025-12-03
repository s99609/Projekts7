package pl.motoparts.motoparts_server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.motoparts.motoparts_server.model.Role;
import pl.motoparts.motoparts_server.model.User;
import pl.motoparts.motoparts_server.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser_shouldSaveUser_whenUsernameIsFree() {
        User user = User.builder()
                .username("admin")
                .password("admin123")
                .role(Role.ADMIN)
                .build();

        when(userRepository.existsByUsername("admin")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        User result = userService.createUser(user);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUsername()).isEqualTo("admin");
        verify(userRepository).existsByUsername("admin");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_shouldThrowBadRequest_whenUsernameAlreadyExists() {
        User user = User.builder()
                .username("admin")
                .password("admin123")
                .role(Role.ADMIN)
                .build();

        when(userRepository.existsByUsername("admin")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException rse = (ResponseStatusException) ex;
                    assertThat(rse.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                });

        verify(userRepository).existsByUsername("admin");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_shouldReturnUser_whenExists() {
        User user = User.builder()
                .id(1L)
                .username("user1")
                .password("pwd")
                .role(Role.WORKSHOP)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertThat(result.getUsername()).isEqualTo("user1");
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserById_shouldThrow_whenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(IllegalArgumentException.class);

        verify(userRepository).findById(99L);
    }
}
