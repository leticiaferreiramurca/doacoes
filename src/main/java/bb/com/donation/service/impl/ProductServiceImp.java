package bb.com.donation.service.impl;

import bb.com.donation.dto.product.ProductGenericDTO;
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
    public Product findByName(String name) {
        return productRepository.findByName (name);
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
