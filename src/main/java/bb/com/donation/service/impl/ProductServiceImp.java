package bb.com.donation.service.impl;

import bb.com.donation.dto.product.ProductGenericDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import bb.com.donation.repository.ProductRepository;
import bb.com.donation.service.ProductService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product save(ProductGenericDTO productGenericDTO) {
        return productRepository.save(productGenericDTO.toProduct());
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById (id).orElseThrow (() -> new ValidacaoException("Produto não encontrado"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll ();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById (id);
    }



    @Override
    public Page<Product> filtrar(String filtro, Pageable pageable) {
        final Product productFiltro = new Product();
        productFiltro.setName(filtro);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        final Example<Product> productExample = Example.of(productFiltro, exampleMatcher);

        return productRepository.findAll(productExample, pageable);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById (id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll ();
    }
}
