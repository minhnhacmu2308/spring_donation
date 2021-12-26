package com.java.spring_donations.services;

import com.java.spring_donations.domain.Donation;
import com.java.spring_donations.domain.UserDonation;

import java.util.List;

public interface UserDonationService {

    UserDonation save(UserDonation userDonation);
    List<UserDonation> findUserDonationByDonation(Donation donation);
    UserDonation findUserDonationById(int id);
}
