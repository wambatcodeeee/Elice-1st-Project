package com.example.firstproject.user.repository;

import com.example.firstproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    boolean existsByUserId(String userId);
}
