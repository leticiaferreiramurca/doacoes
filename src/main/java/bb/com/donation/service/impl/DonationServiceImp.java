package bb.com.donation.service.impl;


import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;

import bb.com.donation.repository.DonationRepository;
import bb.com.donation.service.DonationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationServiceImp implements DonationService {

    final DonationRepository donationRepository;
    final PersonServiceImp personService;
    final ProductServiceImp productService;

    public DonationServiceImp(DonationRepository donationRepository, PersonServiceImp personService, ProductServiceImp productService) {
        this.donationRepository = donationRepository;
        this.personService = personService;
        this.productService = productService;
    }


    @Override
    public Donation save(DonationSaveDTO donationSaveDTO) {
        Donation donation = donationSaveDTO.toDonation();
        donation.setPersonOwner (personService.getById (donationSaveDTO.getOwnerId ()));
        donation.setProduct (productService.getById (donationSaveDTO.getProductId ()));
        donation.setCreatedAt (LocalDate.now ());
        return donationRepository.save (donation);
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
    public void delete(Long aLong) {
        donationRepository.deleteById(aLong);
    }

    @Override
    public Donation getByName(String name) {
        return donationRepository.findByName(name).orElseThrow (() -> new ValidacaoException ("Não foi encontrado nenhuma doação com o nome " + name));
    }


}
