package com.example.demo.service.account;

import com.example.demo.entity.account.User;
import com.example.demo.entity.dto.user.NewUserDTO;
import com.example.demo.repository.account.AuthorityRepository;
import com.example.demo.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void saveUser(NewUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        this.userRepository.save(user);
        this.authorityRepository.saveAuthority(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public boolean checkUserExisted(String username) {
        return findUserByUsername(username) != null;
    }

    @Override
    public boolean checkEmailExisted(String email) {
        return this.userRepository.findUserByEmail(email) != null;
    }
}
