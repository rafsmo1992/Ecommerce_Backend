package org.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.controller.exception.UserNotFoundException;
import org.ecommerce.domain.User;
import org.ecommerce.domain.dto.UserDto;
import org.ecommerce.mapper.UserMapper;
import org.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final UserMapper userMapper;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<String> getAllEmails() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToEmailList(users);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public List<UserDto> getUsersByEmailContaining(String partialEmail) {
        List<User> users = userRepository.findByUserEmailContaining(partialEmail);
        return userMapper.mapToUserDtoList(users);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}