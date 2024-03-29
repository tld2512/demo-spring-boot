package com.example.demo.service.account;

import com.example.demo.entity.account.Authority;
import com.example.demo.entity.account.User;
import com.example.demo.repository.account.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("myUserService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository IUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = IUserRepository.findUserByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            String[] authorities = user.getAuthorities().stream().map(Authority::getAuthority).toArray(String[]::new);
            userBuilder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return userBuilder.build();
    }
}
