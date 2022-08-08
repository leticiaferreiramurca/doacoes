package bb.com.donation.service;

import bb.com.donation.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService extends GenericService<Product, Long> {

    Product update(Product product);
}
