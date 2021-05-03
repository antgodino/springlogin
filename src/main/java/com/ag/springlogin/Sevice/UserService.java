package com.ag.springlogin.Sevice;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
