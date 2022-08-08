package bb.com.donation.service.impl;

import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Product;
import bb.com.donation.repository.ProductRepository;
import bb.com.donation.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
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
    public Product update(Product product) {
        return productRepository.save (product);
    }
}
