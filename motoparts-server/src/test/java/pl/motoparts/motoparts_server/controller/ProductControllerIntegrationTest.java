package pl.motoparts.motoparts_server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.motoparts.motoparts_server.model.Product;
import pl.motoparts.motoparts_server.model.ProductCategory;
import pl.motoparts.motoparts_server.model.TyreSeason;

import pl.motoparts.motoparts_server.repository.ProductRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateProductWithPost() throws Exception {
        String json = """
                {
                  "name": "Test Tire",
                  "brand": "Pirelli",
                  "category": "TIRE",
                  "season": "SUMMER",
                  "price": 320.50,
                  "sku": "Test-123",
                  "description": "Integration test product"
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Test Tire"))
                .andExpect(jsonPath("$.brand").value("Pirelli"));

        assertThat(productRepository.count()).isEqualTo(1);
    }

    @Test
    void shouldReturnProductsWithGet() throws Exception {
        Product product = Product.builder()
                .name("Saved Tire")
                .brand("Michelin")
                .category(ProductCategory.TIRE)
                .season(TyreSeason.SUMMER)
                .price(new BigDecimal(400.50))
                .sku("SAVED-001")
                .description("Saved in DB")
                .build();

        productRepository.save(product);

        mockMvc.perform(get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Saved Tire"))
                .andExpect(jsonPath("$[0].brand").value("Michelin"));
    }
}
