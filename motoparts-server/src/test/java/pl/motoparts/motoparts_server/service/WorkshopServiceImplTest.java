package pl.motoparts.motoparts_server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.motoparts.motoparts_server.model.Workshop;
import pl.motoparts.motoparts_server.repository.WorkshopRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkshopServiceImplTest {

    @Mock
    private WorkshopRepository workshopRepository;

    private WorkshopServiceImpl workshopService;

    @BeforeEach
    void setUp() {
        workshopService = new WorkshopServiceImpl(workshopRepository);
    }

    @Test
    void getWorkshopById_shouldReturnWorkshop_whenExists() {
        Workshop workshop = Workshop.builder()
                .id(1L)
                .name("Moto-Pol")
                .address("Chełm, ul. Przyjazni 2")
                .email("warsztat@moto-pol.pl")
                .phone("123456789")
                .nip("1234567890")
                .build();

        when(workshopRepository.findById(1L)).thenReturn(Optional.of(workshop));

        Workshop result = workshopService.getWorkshopById(1L);

        assertThat(result.getName()).isEqualTo("Moto-Pol");
        verify(workshopRepository).findById(1L);
    }

    @Test
    void getWorkshopById_shouldThrow_whenNotFound() {
        when(workshopRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> workshopService.getWorkshopById(99L))
                .isInstanceOf(IllegalArgumentException.class);

        verify(workshopRepository).findById(99L);
    }

    @Test
    void updateWorkshop_shouldCopyFieldsAndSave() {
        Workshop existing = Workshop.builder()
                .id(1L)
                .name("Stary warsztat")
                .address("Stary adres")
                .email("stary@gmail.com")
                .phone("123123123")
                .nip("3213213213")
                .build();

        Workshop updatedData = Workshop.builder()
                .name("Nowy warsztat")
                .address("Nowy adres")
                .email("nowy@gmail.com")
                .phone("999999999")
                .nip("8888888888")
                .build();

        when(workshopRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(workshopRepository.save(any(Workshop.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Workshop result = workshopService.updateWorkshop(1L, updatedData);

        assertThat(result.getName()).isEqualTo("Nowy warsztat");
        assertThat(result.getAddress()).isEqualTo("Nowy adres");
        assertThat(result.getEmail()).isEqualTo("nowy@gmail.com");
        assertThat(result.getPhone()).isEqualTo("999999999");
        assertThat(result.getNip()).isEqualTo("8888888888");

        verify(workshopRepository).findById(1L);
        verify(workshopRepository).save(any(Workshop.class));
    }
}
