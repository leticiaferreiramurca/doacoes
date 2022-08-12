package bb.com.donation.controller;


import bb.com.donation.dto.person.PersonSaveDTO;
import bb.com.donation.model.Person;
import bb.com.donation.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/person")
@Tag(name = "Pessoas", description = "Gerenciamento das pessoas")
public class PersonController {
    final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Person by Id")
    public ResponseEntity<Person> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (personService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @PostMapping
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

    @DeleteMapping("/{id}")
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

    @GetMapping("/filtro")
    @Operation(summary = "Get Person by Name")
    public ResponseEntity<Page<Person>> getByName(String name, Pageable pageable) {
        try {
            return ResponseEntity.ok(personService.filtrar(name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
