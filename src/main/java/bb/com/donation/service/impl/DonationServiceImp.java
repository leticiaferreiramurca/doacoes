package bb.com.donation.service.impl;


import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;

import bb.com.donation.repository.DonationRepository;
import bb.com.donation.service.DonationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImp implements DonationService {

    DonationRepository donationRepository;
    public DonationServiceImp(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Donation save(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Donation getById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    @Override
    public Donation getByName(String name) {
        return donationRepository.findByName(name).orElseThrow (() -> new ValidacaoException ("Não foi encontrado nenhuma doação com o nome " + name));
    }


}
