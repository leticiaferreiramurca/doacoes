package bb.com.donation.service.impl;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Post;
import bb.com.donation.repository.PostRepository;
import bb.com.donation.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final DonationServiceImp donationService;
    private final ModelMapper modelMapper;


    public PostServiceImpl(PostRepository postRepository, DonationServiceImp donationService, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.donationService = donationService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Post save(PostSaveDTO postSaveDTO)  {
        Post post = Post.builder ()
                .name (postSaveDTO.getName ())
                .description (postSaveDTO.getDescription ())
                .urlImg (postSaveDTO.getUrlImg ())
                .donation (donationService.getById (postSaveDTO.getIdDonation ()))
                .build ();

        if (Objects.isNull(post.getDonation ())) {
            throw new ValidacaoException("Donation not found");
        }
        return postRepository.save(post);
    }
    @Override
    @Transactional
    public Post edit(Post post) {
        Post postByID = getById(post.getId ());
        if(Objects.isNull (postByID)){
            throw new ValidacaoException ("Não foi encontrado nenhum post com o id " + post.getId ());
        }
        postByID.setName (post.getName ());
        postByID.setDescription (post.getDescription ());
        postByID.setUrlImg (post.getUrlImg ());
        return postRepository.save(postByID);
    }

    @Override
    @Transactional
    public Post save(PostGenericDTO t) {

        Post post = modelMapper.map(t, Post.class);
        Donation donation =  donationService.getById(post.getDonation().getId());
        if(donation==null){
            throw new ValidacaoException ("Donation not found");
        }
        return postRepository.save(post);
    }

    @Override
    @Cacheable("post")
    public Post getById(Long aLong) {
        return postRepository.findById(aLong).orElse(null);
    }

    @Override
    @Cacheable("post")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        postRepository.deleteById(aLong);
    }
}
