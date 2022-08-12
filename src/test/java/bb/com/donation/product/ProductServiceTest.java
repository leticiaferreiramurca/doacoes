package bb.com.donation.product;


import bb.com.donation.dto.person.PersonSaveDTO;
import bb.com.donation.dto.product.ProductSaveDTO;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import bb.com.donation.service.ProductService;
import bb.com.donation.service.impl.PersonServiceImp;
import bb.com.donation.service.impl.ProductServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    PersonServiceImp personServiceImp;

    @Test
    void filterProductTest() {
        PersonSaveDTO personSaveDTO = new PersonSaveDTO();
        personSaveDTO.setName("test");
        Person person = personServiceImp.save (personSaveDTO);
        ProductSaveDTO productSaveDTO = new ProductSaveDTO();
        productSaveDTO.setName("product");
        productSaveDTO.setOwner (person.getId ());
        productServiceImp.save (productSaveDTO);



        Pageable pageable = Pageable.unpaged();
        Page<Product> foundProduct = productService.filtrar ("product",pageable);
        Assertions.assertEquals (1, foundProduct.getTotalElements ());
        Assertions.assertEquals ("product", foundProduct.getContent ().get (0).getName ());


    }
}
