package com.peopleapi.controller;

import com.peopleapi.dto.PersonDto;
import com.peopleapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.save(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> showAll() {
        return ResponseEntity.ok(personService.getAll());
    }


}
