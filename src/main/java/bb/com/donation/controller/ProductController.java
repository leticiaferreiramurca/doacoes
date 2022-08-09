package bb.com.donation.controller;

import bb.com.donation.dto.product.ProductSaveDTO;
import bb.com.donation.model.Product;
import bb.com.donation.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById (id);
    }

    @GetMapping("/getByName/{name}")
    public Product getByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @PostMapping("/save")
    public Product save(@RequestBody ProductSaveDTO productSaveDTO) {
        return productService.save(productSaveDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
