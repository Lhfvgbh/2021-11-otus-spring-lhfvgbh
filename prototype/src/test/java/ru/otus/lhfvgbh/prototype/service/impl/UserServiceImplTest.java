package ru.otus.lhfvgbh.prototype.service.impl;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.lhfvgbh.prototype.domain.Role;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.repository.UserRepository;
import ru.otus.lhfvgbh.prototype.service.UserService;

@SpringBootTest
class UserServiceImplTest {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }


    @Test
    void checkFindByName() {
        String name = "Admin";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(expectedUser);

        User actualUser = userService.findByName(name);

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

    }

    @Test
    void checkSaveDTO() {
        UserDTO userDto = UserDTO.builder()
                .name("Test")
                .email("email")
                .password("password")
                .confirmedPassword("password")
                .isActive(true)
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        boolean result = userService.save(userDto);

        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }

    @Test
    void checkFindDTOByName() {
        String name = "Admin";
        User mock = User.builder()
                .id(1L)
                .name(name)
                .build();

        UserDTO expected = UserDTO.builder()
                .id(1L)
                .name(name)
                .build();

        Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(mock);

        UserDTO actual = userService.findDTOByName(name);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);

    }

    /*@Test
    void checkUpdateProfile() {
        User first = User.builder()
                .id(1L)
                .name("Admin")
                .email("admin@example.com")
                .password("password")
                .isActive(true)
                .build();

        UserDTO expected = UserDTO.builder()
                .id(1L)
                .name("Admin")
                .email("admin@example.ru")
                .password("password")
                .isActive(true)
                .build();

        userRepository.save(first);

        //Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(first);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        userService.updateProfile(expected);

        User actual = userService.findByName("Admin");

        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());

        Mockito.verify(userRepository).updateProfile(Mockito.any());
    }*/
}