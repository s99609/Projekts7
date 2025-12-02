package pl.motoparts.motoparts_server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workshops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 10)
    private String nip;

    private String address;
    private String phone;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
