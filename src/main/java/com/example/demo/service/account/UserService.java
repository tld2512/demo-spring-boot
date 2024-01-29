package com.example.demo.service.account;

import com.example.demo.entity.account.User;
import com.example.demo.entity.dto.user.NewUserDTO;
import com.example.demo.repository.account.AuthorityRepository;
import com.example.demo.repository.account.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableMethodSecurity(securedEnabled = true)
public class UserService implements IUserService {

    @Autowired
    private IUserRepository IUserRepository;
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
        this.IUserRepository.save(user);
        this.authorityRepository.saveAuthority(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.IUserRepository.findUserByUsername(username);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAllUser() {
        return this.IUserRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.IUserRepository.findById(id);
    }

    @Override
    public boolean checkUserExisted(String username) {
        return findUserByUsername(username) != null;
    }

    @Override
    public boolean checkEmailExisted(String email) {
        return this.IUserRepository.findUserByEmail(email) != null;
    }
}
