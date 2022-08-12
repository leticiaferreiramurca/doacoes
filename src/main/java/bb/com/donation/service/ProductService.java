package bb.com.donation.service;

import bb.com.donation.dto.product.ProductGenericDTO;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService extends GenericService<Product, Long, ProductGenericDTO> {

    Page<Product> filtrar(String filtro, Pageable pageable);

    void deleteById(Long id);

    List<Product> findAll();
}
