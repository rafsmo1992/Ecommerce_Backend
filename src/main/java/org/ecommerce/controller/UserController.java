package org.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.controller.exception.UserNotFoundException;
import org.ecommerce.domain.User;
import org.ecommerce.domain.dto.ErrorDto;
import org.ecommerce.domain.dto.UserDto;
import org.ecommerce.mapper.UserMapper;
import org.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ecommercee")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User>users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDto userDto = userMapper.mapToUserDto(userService.getUserById(id));
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            String errorMessage = "User with ID " + id + " not found";
            System.out.println("User with ID " + id + " not found");
            ErrorDto errorDto = new ErrorDto(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
        }
    }

    @GetMapping(value = "/emails")
    public ResponseEntity<List<String>> getAllEmails() {
        List<User> users = userService.getAllUsers();
        List<String> emails = users.stream().map(User::getUserEmail).collect(Collectors.toList());
        return ResponseEntity.ok(emails);
    }
    @GetMapping(value = "/users/emails/{partialEmail}")
    public ResponseEntity<List<UserDto>> getUsersByEmailContaining(@PathVariable String partialEmail) {
        List<UserDto> users = userService.getUsersByEmailContaining(partialEmail);
        return ResponseEntity.ok(users);
    }


    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateTask(@RequestBody UserDto userDto) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "/users/{userId}")
    public void deleteTask(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

}
