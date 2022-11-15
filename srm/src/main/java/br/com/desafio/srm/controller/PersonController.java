package br.com.desafio.srm.controller;

import br.com.desafio.srm.request.PersonRequest;
import br.com.desafio.srm.response.PersonResponse;
import br.com.desafio.srm.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(value = "/people/", tags = "People", description = "End points responsáveis por gerenciar os recursos das Pessoas do SRM")
@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personRequest));
    }
}
