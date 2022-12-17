package com.example.dnsproject.repository;

import com.example.dnsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUserName(final String userName);//parameter name must same with property in Client class
}
