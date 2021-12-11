package com.java.spring_donations.services;

import com.java.spring_donations.domain.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationService {

    Page<Donation> findAll(Pageable pageable);

    List<Donation> findAll();

}