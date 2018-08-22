package com.mycompany.easycondows.user.service;

import com.mycompany.easycondows.user.model.User;
import com.mycompany.easycondows.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
