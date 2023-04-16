package com.trimix.demo.repository;

import java.util.List;
import java.util.Optional;

import com.trimix.demo.model.Persona;
import com.trimix.demo.model.TipoDocumento;

public interface PersonaDao {

    void savePersona(Persona newPersona);

    List<Persona> getPersonas();

    Optional<Persona> findById(long id);

    void updatePersona(Persona personaToUpdate);

    void deletePersona(long id);

    List<Persona> findAllByName(String name);

    List<Persona> findAllByKindDocument(TipoDocumento tipoDocumento);
}
