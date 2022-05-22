package ru.otus.lhfvgbh.prototype.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDTO userDTO);

    void save(User user);

    List<UserDTO> getAll();

    UserDTO findDTOByName(String name);

    User findByName(String name);

    void updateProfile(UserDTO userDTO);

    void deactivate(Long userId);

    void activate(Long userId);
}
