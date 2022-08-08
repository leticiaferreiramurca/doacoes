package bb.com.donation.controller;


import bb.com.donation.dto.PersonDTO;
import bb.com.donation.model.Person;
import bb.com.donation.service.impl.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/produto")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok (personService.getAllPersons ());
    }


    @GetMapping("/Id/{id}")
    public ResponseEntity<Person> getById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok (personService.getById (id));
    }

    @PostMapping("/save")
    @Operation(summary = "Salvar um produto")
    public ResponseEntity<Person> save(@RequestParam @Valid @NotNull PersonDTO personDTO) {
        Person personSaved = personService.save (personDTO.toPerson ());
        return ResponseEntity.ok (personSaved);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete (id);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Person> getByName(@PathVariable String name) {
        return ResponseEntity.ok (personService.getByName (name));
    }

}
