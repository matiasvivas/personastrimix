package com.trimix.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.trimix.demo.model.Persona;
import com.trimix.demo.model.TipoDocumento;
import com.trimix.demo.repository.PersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaDao personaDao;

    @Autowired
    public PersonaService(PersonaDao personaDao){this.personaDao=personaDao;}

    public List<Persona> getPersonas(TipoDocumento tipoDocumento) {
        if (tipoDocumento != null) {
            return personaDao.findAllByKindDocument(tipoDocumento);
        }
        return personaDao.getPersonas();
    }

    public Optional<Persona> getPersonaById(long id) {
        return personaDao.findById(id);
    }

    public Optional<Persona> removePersona(long id) {
        Optional<Persona> personaToRemove = personaDao.findById(id);
        if (personaToRemove.isPresent()) {
            personaDao.deletePersona(personaToRemove.get().getId());
            return personaToRemove;
        }
        return Optional.empty();
    }


    public Optional<Persona> addPersona(Persona personaToAdd) {
        personaDao.savePersona(personaToAdd);
        return Optional.of(personaToAdd);

    }


    public Optional<Persona> updatePersona(Persona personaToUpdate) {
        Optional<Persona> persona = personaDao.findById(personaToUpdate.getId());
        if (persona.isPresent()) {
            personaDao.updatePersona(personaToUpdate);
            return Optional.of(personaToUpdate);
        }
        return persona;
    }

    public List<TipoDocumento> tipoDocumentoValues() {
        return Arrays.asList(TipoDocumento.values());
    }

    public List<Persona> getPersonaByName(String name) {
        return personaDao.findAllByName(name);
    }

    public List<Persona> getPersonaByKindDocument(TipoDocumento tipoDocumento) {
        return personaDao.findAllByKindDocument(tipoDocumento);
    }

}
