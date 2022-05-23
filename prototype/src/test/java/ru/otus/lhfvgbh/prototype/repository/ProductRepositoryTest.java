package ru.otus.lhfvgbh.prototype.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.otus.lhfvgbh.prototype.domain.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование ProductRepoJpa")
@DataJpaTest
@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("method disable")
    @Test
    void disableProductTest() {
        Product expected = Product.builder()
                .name("Product")
                .description("desc")
                .isAvailable(true)
                .price(new BigDecimal("50.0"))
                .build();

        expected.setId(entityManager.persist(expected).getId());

        productRepository.disable(expected.getId());

        Product actual = productRepository.findById(expected.getId()).orElse(new Product());

        assertThat(actual.getIsAvailable()).isFalse();
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @DisplayName("method enable")
    @Test
    void enableProductTest() {
        Product expected = Product.builder()
                .name("Product")
                .description("desc")
                .isAvailable(false)
                .price(new BigDecimal("50.0"))
                .build();

        expected.setId(entityManager.persist(expected).getId());

        productRepository.enable(expected.getId());

        Product actual = productRepository.findById(expected.getId()).orElse(new Product());

        assertThat(actual.getIsAvailable()).isTrue();
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

}

