package bb.com.donation.service;

import bb.com.donation.dto.product.ProductGenericDTO;
import bb.com.donation.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService extends GenericService<Product, Long, ProductGenericDTO> {

    Product findByName(String name);

    void deleteById(Long id);

    List<Product> findAll();
}
