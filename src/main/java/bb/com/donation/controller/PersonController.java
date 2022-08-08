package bb.com.donation.controller;


import bb.com.donation.model.Person;
import bb.com.donation.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/person")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/getAll")
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok (personService.getAll());
    }


    @GetMapping("/Id/{id}")
    @Operation(summary = "Get Person by Id")
    public ResponseEntity<Person> getById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok (personService.getById (id));
    }

    @PostMapping("/save")
    @Operation(summary = "Save Person")
    public ResponseEntity<Person> save(@RequestBody @Valid @NotNull Person person) {
        Person personSaved = personService.save (person);
        return ResponseEntity.ok (personSaved);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Person")
    public void delete(@PathVariable Long id) {
        personService.delete (id);
    }

    @GetMapping("/getByName/{name}")
    @Operation(summary = "Get Person by Name")
    public ResponseEntity<List<Person>> getByName(@PathVariable String name) {
        return ResponseEntity.ok (personService.getByName (name));
    }

}
