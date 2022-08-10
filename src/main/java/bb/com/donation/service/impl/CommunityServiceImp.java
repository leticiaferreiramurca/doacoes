package bb.com.donation.service.impl;

import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import bb.com.donation.repository.CommunityRepository;
import bb.com.donation.service.CommunityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImp implements CommunityService {

    private final  CommunityRepository communityRepository;
    private final  PersonServiceImp personServiceImp;

    public CommunityServiceImp(CommunityRepository communityRepository, PersonServiceImp personServiceImp) {
        this.communityRepository = communityRepository;
        this.personServiceImp = personServiceImp;
    }


    @Override
    public Community save(CommunitySaveDTO communitySaveDTO) {
        Community community = communitySaveDTO.toCommunity ();
        Person person = personServiceImp.findById (communitySaveDTO.getIdPerson ());
        community.setPersonOwner (person);
        return communityRepository.save(community);
    }

    @Override
    public Community getById(java.lang.Long aLong) {
        return communityRepository.findById(aLong).orElseThrow (() -> new RuntimeException("Não foi possível encontrar a comunidade"));
    }

    @Override
    public List<Community> getAll() {
        return communityRepository.findAll();
    }

    @Override
    public void delete(java.lang.Long aLong) {
        communityRepository.deleteById(aLong);
    }

    @Override
    public Community getByName(String name) {
        return communityRepository.findByName(name);
    }
}
