package bb.com.donation.service.impl;

import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Post;
import bb.com.donation.model.Product;
import bb.com.donation.repository.PostRepository;
import bb.com.donation.service.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    final ProductServiceImp productService;

    public PostServiceImpl(PostRepository postRepository,  ProductServiceImp productService) {
        this.postRepository = postRepository;
        this.productService = productService;

    }

    @Override
    public Post save(@NotNull PostSaveDTO postGenericDTO) {
        Product product = productService.getById(postGenericDTO.getProductId ());
        if(product == null) {
            throw new ValidacaoException("Product not found");
        }
        Post post = postGenericDTO.toPost();
        return postRepository.save(post);
    }

    @Override
    public Post getById(Long aLong) {
        return postRepository.findById(aLong).orElseThrow (()-> new ValidacaoException ("Post not found"));
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        Post post = postRepository.findById(aLong).orElseThrow (()-> new ValidacaoException ("Post not found"));
        postRepository.delete(post);
    }



    public Page<Post> getByName(String name, Pageable pageable) {
        final Post postFiltro = new Post();
        postFiltro.setName(name);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Post> example = Example.of(postFiltro, exampleMatcher);
        return postRepository.findAll(example, pageable);
    }
}
