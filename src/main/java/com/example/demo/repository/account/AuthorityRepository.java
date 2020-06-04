package com.example.demo.repository.account;

import com.example.demo.entity.account.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveAuthority(User user) {
        entityManager.createNativeQuery("INSERT INTO authorities(user_id, authority) VALUES (?, ?)")
                .setParameter(1, user.getId())
                .setParameter(2, "ROLE_USER")
                .executeUpdate();

    }
}
