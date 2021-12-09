package com.java.spring_donations.repositorys;

import com.java.spring_donations.domain.Donation;
import com.java.spring_donations.domain.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
}
