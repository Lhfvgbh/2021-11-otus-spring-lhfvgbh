package ru.otus.lhfvgbh.prototype.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lhfvgbh.prototype.domain.Role;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.exceptions.InvalidUserException;
import ru.otus.lhfvgbh.prototype.repository.UserRepository;
import ru.otus.lhfvgbh.prototype.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.core.userdetails.User.builder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean save(UserDTO userDTO) {
        if (userDTO.getPassword().equals(userDTO.getConfirmedPassword())) {
            User user = User.builder()
                    .name(userDTO.getName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .email(userDTO.getEmail())
                    .role(Role.CLIENT)
                    .isActive(true)
                    .build();

            userRepository.save(user);
            return true;
        } else {
            throw new InvalidUserException("Cannot save, please check your input!");
        }
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findDTOByName(String name) {
        return UserDTO.toDTO(userRepository.findByName(name));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO userDTO) {
        User user = userRepository.findByName(userDTO.getName());

        if (user == null) {
            throw new InvalidUserException("Cannot save sign up, please check your input!");
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()
                && userDTO.getPassword().equals(userDTO.getConfirmedPassword())
                && userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            user.setIsActive(userDTO.getIsActive());
            userRepository.save(user);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name '" + username + "' not found!");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return builder()
                .username(username)
                .password(user.getPassword())
                .authorities(roles)
                .build();
    }

    @Transactional
    @Override
    public void deactivate(Long userId) {
        userRepository.deactivate(userId);
    }

    @Transactional
    @Override
    public void activate(Long userId) {
        userRepository.activate(userId);
    }
}
