package com.java.spring_donations.repositorys;

import com.java.spring_donations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
