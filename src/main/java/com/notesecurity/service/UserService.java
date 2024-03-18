package com.notesecurity.service;

import com.notesecurity.database.entity.User;
import com.notesecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {

        userRepository.save(user);
    }

    public User getById(UUID id) throws Exception {
        User user = Optional.ofNullable(userRepository.findById(id))
                .get()
                .orElseThrow(() -> new Exception("User not found!"));
        return user;
    }


}

