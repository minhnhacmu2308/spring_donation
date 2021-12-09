package com.java.spring_donations.repositorys;

import com.java.spring_donations.domain.User;
import com.java.spring_donations.domain.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDonationRepository extends JpaRepository<UserDonation,Integer> {
}
