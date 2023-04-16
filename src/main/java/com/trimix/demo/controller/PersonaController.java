package com.trimix.demo.controller;

import java.util.List;
import java.util.Optional;

import com.trimix.demo.model.Persona;
import com.trimix.demo.model.TipoDocumento;
import com.trimix.demo.service.PersonaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService){this.personaService=personaService;}

    public PersonaController(){}

    @GetMapping
    @ApiOperation(value = "Get all persons", notes = "When you use this method without any parameter, you get all persons. ")
    public ResponseEntity<List<Persona>> getAllPersons(@RequestParam(required = false) TipoDocumento tipoDocumento) {
        return new ResponseEntity<>(personaService.getPersonas(tipoDocumento), HttpStatus.OK);
    }

    @GetMapping(value = "/getTiposDocumentos")
    public ResponseEntity<List<TipoDocumento>> getTiposDocumentos(){
        return new ResponseEntity<>(personaService.tipoDocumentoValues(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get Person by ID", notes = "Get Person by ID")
    public ResponseEntity<Persona> getPersonById(@PathVariable long id) {
        Optional<Persona> persona = personaService.getPersonaById(id);
        if (persona.isPresent()) {
            return new ResponseEntity<>(persona.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Add Person", notes = "Add Person")
    @PostMapping
    public ResponseEntity<Persona> addPerson(@RequestBody Persona persona) {
        Optional<Persona> personaToAdd = personaService.addPersona(persona);
        if (personaToAdd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @ApiOperation(value = "Remove Person", notes = "Remove Person")
    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> removePerson(@PathVariable long id) {
        Optional<Persona> persona = personaService.removePersona(id);
        if (!persona.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persona.get(), HttpStatus.OK);

    }

    @ApiOperation(value = "Update Person", notes = "Update Person")
    @PutMapping
    public ResponseEntity<Persona> updatePerson(@RequestBody Persona persona) {
        Optional<Persona> personaToUpdate = personaService.updatePersona(persona);
        if (personaToUpdate.isPresent()) {
            return new ResponseEntity<>(personaToUpdate.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/names")
    @ApiOperation(value = "Get persons by names", notes = "")
    public ResponseEntity<List<Persona>> getPersonsByNames(@RequestParam String name) {
        return new ResponseEntity<>(personaService.getPersonaByName(name), HttpStatus.OK);
    }

    @GetMapping("/kindDocument")
    @ApiOperation(value = "Get persons by kindDocument", notes = "")
    public ResponseEntity<List<Persona>> getPersonsByKindDocument(@RequestParam TipoDocumento tipoDocumento) {
        return new ResponseEntity<>(personaService.getPersonaByKindDocument(tipoDocumento), HttpStatus.OK);
    }


}
