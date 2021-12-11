package com.java.spring_donations.repositorys;

import com.java.spring_donations.domain.Role;
import com.java.spring_donations.domain.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleById(int id);
}
