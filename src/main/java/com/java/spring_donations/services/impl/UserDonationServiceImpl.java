package com.java.spring_donations.services.impl;

import com.java.spring_donations.domain.Donation;
import com.java.spring_donations.domain.UserDonation;
import com.java.spring_donations.repositorys.UserDonationRepository;
import com.java.spring_donations.services.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDonationServiceImpl implements UserDonationService {

    @Autowired
    UserDonationRepository userDonationRepository;

    @Override
    public UserDonation save(UserDonation userDonation) {
        return userDonationRepository.save(userDonation);
    }

    @Override
    public List<UserDonation> findUserDonationByDonation(Donation donation) {
        return userDonationRepository.findUserDonationByDonation(donation);
    }
}
