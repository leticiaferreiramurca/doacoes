package bb.com.donation.service.impl;

import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import bb.com.donation.repository.CommunityRepository;
import bb.com.donation.service.CommunityService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Community> filtrar(String filtro, Pageable pageable) {
        final Community communityFiltro = new Community();
        communityFiltro.setName(filtro);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        final Example<Community> communityExample = Example.of(communityFiltro, exampleMatcher);

        return communityRepository.findAll(communityExample, pageable);
    }
}
