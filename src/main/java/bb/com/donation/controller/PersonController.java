package bb.com.donation.controller;


import bb.com.donation.dto.person.PersonSaveDTO;
import bb.com.donation.model.Person;
import bb.com.donation.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok (personService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Save Person")
    public ResponseEntity<Person> save(@RequestBody @Valid @NotNull PersonSaveDTO person) {
        try {
            Person personSaved = personService.save (person);
            return ResponseEntity.ok (personSaved);
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Person")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            personService.delete (id);
            return ResponseEntity.ok ().build ();
        }catch ( Exception e ) {
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @GetMapping("/getByName/{name}")
    @Operation(summary = "Get Person by Name")
    public ResponseEntity<List<Person>> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok (personService.getByName (name));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

}
