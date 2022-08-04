package bb.com.donation.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/produto")
public class PersonController {



    @Operation(summary = "Lista todos os produtos")
    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }
}
