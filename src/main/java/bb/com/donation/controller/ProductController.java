package bb.com.donation.controller;

import bb.com.donation.dto.product.ProductSaveDTO;
import bb.com.donation.model.Product;
import bb.com.donation.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
@Tag(name = "Produtos", description = "Produtos para doar.")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @Operation(summary = "Get All Products")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by ID")
    public Product getById(@PathVariable Long id) {
        return productService.getById (id);
    }

    @GetMapping("/filtro")
    @Operation(summary = "Get Product by Name")
    public ResponseEntity<Page<Product>> getByName(String name, Pageable pageable) {
        try {
            return ResponseEntity.ok(productService.filtrar(name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    @Operation(summary = "Save Product")
    public Product save(@RequestBody ProductSaveDTO productSaveDTO) {
        return productService.save(productSaveDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
