package pl.motoparts.motoparts_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private TyreSeason season;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @Column(unique = true)
    private String sku;         //kod katalogowy

    @Column(length = 2000)
    private String description;
}
