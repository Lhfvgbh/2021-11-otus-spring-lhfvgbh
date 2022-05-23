package ru.otus.lhfvgbh.prototype.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getUserList() {
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable String name) {
        return userService.findDTOByName(name);
    }

    @PreAuthorize("!hasAuthority('ADMIN')")
    @PostMapping(value = "/sign_up", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO signUp(@RequestBody UserDTO userDTO) {
        if (userService.save(userDTO)) {
            return userDTO;
        } else {
            return new UserDTO();
        }
    }

    @PostMapping(value = "/editProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO editProfile(@RequestBody UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()
                || !userDTO.getPassword().equals(userDTO.getConfirmedPassword())) {
            return new UserDTO();
        } else {
            userService.updateProfile(userDTO);
            return userDTO;
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/{id}/ban", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO ban(@RequestBody UserDTO userDTO) {
        userService.deactivate(userDTO.getId());
        return userDTO;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/{id}/unban", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO unban(@RequestBody UserDTO userDTO) {
        userService.activate(userDTO.getId());
        return userDTO;
    }
}
