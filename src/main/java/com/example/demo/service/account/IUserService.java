package com.example.demo.service.account;

import com.example.demo.entity.dto.user.NewUserDTO;
import com.example.demo.entity.account.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void saveUser(NewUserDTO userDTO);

    User findUserByUsername(String username);

    List<User> findAllUser();

    Optional<User> findUserById(Long id);

    boolean checkUserExisted(String username);

    boolean checkEmailExisted(String email);
}
