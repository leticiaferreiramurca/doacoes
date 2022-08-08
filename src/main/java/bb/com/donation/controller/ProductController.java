package bb.com.donation.controller;

import bb.com.donation.model.Product;
import bb.com.donation.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/getByName/{name}")
    public Product getByName(@PathVariable String name) {
        return productRepository.findByName(name);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
