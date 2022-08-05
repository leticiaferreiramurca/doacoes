package bb.com.donation.controller;


import bb.com.donation.service.impl.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/produto")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }



    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }

}
