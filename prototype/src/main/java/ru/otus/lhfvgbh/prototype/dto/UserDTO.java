package ru.otus.lhfvgbh.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lhfvgbh.prototype.domain.Role;
import ru.otus.lhfvgbh.prototype.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    private Long id;

    private String name;

    private String password;

    private String confirmedPassword;

    private String email;

    private Role role;

    private Boolean isActive;

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .confirmedPassword(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .build();
    }
}
