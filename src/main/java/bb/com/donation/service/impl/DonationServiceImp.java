package bb.com.donation.service.impl;


import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.enums.DonationStatus;
import bb.com.donation.exceptions.ErrorMessages;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Person;
import bb.com.donation.repository.DonationRepository;
import bb.com.donation.service.DonationService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DonationServiceImp implements DonationService {

    final DonationRepository donationRepository;
    final PersonServiceImp personService;
    final ProductServiceImp productService;

    final ModelMapper modelMapper;

    public DonationServiceImp(DonationRepository donationRepository, PersonServiceImp personService, ProductServiceImp productService, ModelMapper modelMapper) {
        this.donationRepository = donationRepository;
        this.personService = personService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
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
    @Cacheable("donation")
    public Donation getById(Long id) {
        return donationRepository.findById(id).orElseThrow (() -> new ValidacaoException (ErrorMessages.DONATION_NOT_FOUND));
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
    @Cacheable("donation")
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
        if (name.isBlank() || name.isEmpty())
            return donationRepository.findAll(pageable);
        return this.getByName(name, pageable);
    }

    @Cacheable("donation_status")
    public Page<Donation> getAllOrByDonationStatus(String donationStatusString, Pageable pageable) {

        DonationStatus donationStatus = DonationStatus.valueOf(donationStatusString);
        if (donationStatusString.isBlank() || donationStatusString.isEmpty() || Objects.isNull(donationStatusString))
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
            throw new ValidacaoException(ErrorMessages.DONATION_NOT_FOUND);
        }
        statusVerification(donation, status);
        DonationStatus donationStatus = DonationStatus.valueOf(status);
        donation.setDonationStatus (donationStatus);
        return donationRepository.save(donation);
    }

    @Transactional
    public Donation setInterest(Long donationId, Long personID) {

        Donation donation = getById(donationId);
        Person personInterest = personService.getById(personID);
        if(donation == null) {
            throw new ValidacaoException(ErrorMessages.DONATION_NOT_FOUND);
        }
        if((long) donation.getPersonInterested ().size () >3) {
            throw new ValidacaoException("Maximum of 3 persons");
        }
        if(personInterest == null) {
            throw new ValidacaoException(ErrorMessages.PERSON_NOT_FOUND);
        }
        donation.getPersonInterested ().add (personInterest);
        return donationRepository.save(donation);
    }

    public Donation removeInterest(Long donationId, Long personID) {

        Donation donation = getById(donationId);
        Person personInterest = personService.getById(personID);
        if(donation == null) {
            throw new ValidacaoException(ErrorMessages.DONATION_NOT_FOUND);
        }
        if(personInterest == null) {
            throw new ValidacaoException(ErrorMessages.PERSON_NOT_FOUND);
        }
        donation.getPersonInterested ().remove (personInterest);
        return donationRepository.save(donation);
    }

    private void statusVerification(Donation donation, String status) {
        DonationStatus newDonationStatus = DonationStatus.valueOf(status);
        DonationStatus donationStatus = donation.getDonationStatus();

        if(donationStatus.equals (newDonationStatus)) {
            throw new ValidacaoException ("Donation status was already " + donationStatus);
        }
        if(donationStatus.equals (DonationStatus.NEW) && newDonationStatus.equals (DonationStatus.ONPROGRESS)) {
            return;
        }
        if(donationStatus.equals (DonationStatus.ONPROGRESS) && newDonationStatus.equals (DonationStatus.FINISHED)) {
            return;
        }
        throw new ValidacaoException ("Donation status can't be changed from " + donationStatus + " to " + newDonationStatus);
    }

}
