package com.example.demo.controller.account;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.entity.account.User;
import com.example.demo.entity.account.UserDetailsImpl;
import com.example.demo.entity.dto.user.NewUserDTO;
import com.example.demo.entity.dto.user.UserLogin;
import com.example.demo.service.account.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/loginFailed")
    public ResponseEntity<String> loginFailed() {
        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewAccount(@RequestBody NewUserDTO userDTO) {
        if (isUsernameExisted(userDTO.getUsername())) {
            return new ResponseEntity<>("Username already existed", HttpStatus.IM_USED);
        }
        if (isEmailExisted(userDTO.getEmail())) {
            return new ResponseEntity<>("Email already existed", HttpStatus.IM_USED);
        }
        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirm())) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.NOT_ACCEPTABLE);
        }
        this.userService.saveUser(userDTO);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLogin userLogin) {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtTokenFromUserDetails((UserDetailsImpl) authentication.getPrincipal());
        return new ResponseEntity<>(jwt, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list-user")
    public ResponseEntity<List<User>> listUser() {
        List<User> users = this.userService.findAllUser();
        if (users.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private boolean isUsernameExisted(String username) {
        return this.userService.checkUserExisted(username);
    }

    private boolean isEmailExisted(String email) {
        return this.userService.checkEmailExisted(email);
    }
}
