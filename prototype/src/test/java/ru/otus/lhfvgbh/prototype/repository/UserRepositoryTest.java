package ru.otus.lhfvgbh.prototype.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.otus.lhfvgbh.prototype.domain.Role;
import ru.otus.lhfvgbh.prototype.domain.User;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тестирование UserRepoJpa")
@DataJpaTest
@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("method deactivate")
    @Test
    void deactivateUserTest() {
        User expected = User.builder()
                .name("Test")
                .password("pass")
                .email("test@email.ru")
                .isActive(true)
                .role(Role.CLIENT)
                .build();

        expected.setId(entityManager.persist(expected).getId());

        userRepository.deactivate(expected.getId());

        User actual = userRepository.findById(expected.getId()).orElse(new User());

        assertThat(actual.getIsActive()).isFalse();
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @DisplayName("method activate")
    @Test
    void activateUserTest() {
        User expected = User.builder()
                .name("Test")
                .password("pass")
                .email("test@email.ru")
                .isActive(false)
                .role(Role.CLIENT)
                .build();

        expected.setId(entityManager.persist(expected).getId());

        userRepository.activate(expected.getId());

        User actual = userRepository.findById(expected.getId()).orElse(new User());

        assertThat(actual.getIsActive()).isTrue();
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }
}