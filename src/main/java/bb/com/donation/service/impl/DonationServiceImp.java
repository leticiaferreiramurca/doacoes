package bb.com.donation.service.impl;


import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.enums.ConditionType;
import bb.com.donation.enums.DonationStatus;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
import bb.com.donation.repository.DonationRepository;
import bb.com.donation.service.DonationService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
        Donation donationSearch = donationRepository.findByProductId(donationSaveDTO.getProductId());
        if (donationSearch != null) {
            throw new ValidacaoException ("Product already exists");
        }
        Donation donation = donationSaveDTO.toDonation();
        donation.setPersonOwner (personService.getById (donationSaveDTO.getOwnerId ()));
        donation.setProduct (productService.getById (donationSaveDTO.getProductId ()));
        donation.setCreatedAt (LocalDate.now ());
        donation.setDonationStatus (DonationStatus.NEW);
        return donationRepository.save (donation);
    }

    @Override
    public Donation getById(Long id) {
        return donationRepository.findById(id).orElseThrow (() -> new ValidacaoException ("Donation not found"));
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
    public Page<Donation> getByName(String name, Pageable pageable) {
        final Donation donationFiltro = new Donation();
        donationFiltro.setName(name);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        final Example<Donation> donationExample = Example.of(donationFiltro, exampleMatcher);

        return donationRepository.findAll(donationExample, pageable);
    }


    public Page<Donation> getAllOrByName(String name, Pageable pageable) {
        if (name.isBlank() || name.isEmpty() || Objects.isNull(name))
            return donationRepository.findAll(pageable);
        return this.getByName(name, pageable);
    }

    public Page<Donation> getAllOrByDonationStatus(String donationStatusString, Pageable pageable) {

        DonationStatus donationStatus = DonationStatus.valueOf(donationStatusString);
        if (Objects.isNull(donationStatus))
            return donationRepository.findAll(pageable);
        return this.findByDonationStatus(donationStatus, pageable);
    }

    private Page<Donation> findByDonationStatus(DonationStatus donationStatus, Pageable pageable) {
        final Donation donationFiltro = new Donation();
        donationFiltro.setDonationStatus(donationStatus);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        final Example<Donation> donationExample = Example.of(donationFiltro, exampleMatcher);

        return donationRepository.findAll(donationExample, pageable);
    }

    public Donation changeStatus(Long id, String status) {
        Donation donation = getById(id);
        if(donation == null) {
            throw new ValidacaoException("Donation not found");
        }
        statusVerification(donation, status);
        DonationStatus donationStatus = DonationStatus.valueOf(status);
        donation.setDonationStatus (donationStatus);
        return donationRepository.save(donation);
    }

    private void statusVerification(Donation donation, String status) {
        DonationStatus donationStatus = DonationStatus.valueOf(status);

        if(donation.getDonationStatus().equals(DonationStatus.valueOf(status))) {
            throw new ValidacaoException("Donation already in this status");
        }
        if(donation.getDonationStatus().equals(DonationStatus.ONPROGRESS) || donationStatus.equals(DonationStatus.NEW)) {
            throw new ValidacaoException("Donation was already created");
        }else if(donation.getDonationStatus().equals(DonationStatus.FINISHED)) {
            throw new ValidacaoException("Donation was already done");
        }
    }

    //    TODO: filtro pelo status





}
