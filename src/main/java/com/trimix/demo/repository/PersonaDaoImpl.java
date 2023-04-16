package com.trimix.demo.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trimix.demo.model.Persona;
import com.trimix.demo.model.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl implements PersonaDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonaDaoImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void savePersona(Persona newPersona) {
        String sql = "INSERT INTO persona(id, nombre, apellido, numeroDocumento, tipoDocumento, fechaNacimiento)VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, newPersona.getId(), newPersona.getNombre(), newPersona.getApellido(), newPersona.getNumeroDocumento(), newPersona.getTipoDocumento().name(), newPersona.getFechaNacimiento());
    }

    @Override
    public List<Persona> getPersonas() {
        String sql = "SELECT * FROM persona";
        List<Persona> personaList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getPersonasList(personaList, maps);
    }

    @Override
    public Optional<Persona> findById(long id) {
        String sql = "SELECT * FROM persona where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new RowMapper<Persona>() {
            @Override
            public Persona mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Persona(resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getLong("numeroDocumento"),
                    TipoDocumento.valueOf(resultSet.getString("tipoDocumento")),
                    resultSet.getDate("fechaNacimiento")
                    );
            }
        }, id));
    }

    @Override
    public void updatePersona(Persona personaToUpdate) {
        String sql = "UPDATE persona SET nombre=?, apellido=?, numeroDocumento=?, tipoDocumento=?, fechaNacimiento=? WHERE id=?";
        jdbcTemplate.update(sql, personaToUpdate.getNombre(), personaToUpdate.getApellido(), personaToUpdate.getNumeroDocumento(), personaToUpdate.getTipoDocumento().name(), personaToUpdate.getFechaNacimiento(), personaToUpdate.getId());
    }

    @Override
    public void deletePersona(long id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Persona> findAllByName(String name) {
        name = "%"+name+"%";
        String sql = "SELECT * FROM persona WHERE nombre LIKE '"+name+"'";
        List<Persona> personaList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getPersonasList(personaList, maps);
    }

    @Override
    public List<Persona> findAllByKindDocument(TipoDocumento tipoDocumento) {
        String sql = "SELECT * FROM persona WHERE tipoDocumento=?";
        List<Persona> personaList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, tipoDocumento.name());
        return getPersonasList(personaList, maps);
    }

    private List<Persona> getPersonasList(List<Persona> personaList, List<Map<String, Object>> maps) {
        maps.stream().forEach(element -> personaList.add(new Persona(
            Long.parseLong(String.valueOf(element.get("id"))),
            String.valueOf(element.get("nombre")),
            String.valueOf(element.get("apellido")),
            Long.parseLong(String.valueOf(element.get("numeroDocumento"))),
            TipoDocumento.valueOf(String.valueOf(element.get("tipoDocumento"))),
            Date.valueOf(String.valueOf(element.get("fechaNacimiento")))
        )));
        return personaList;
    }
}
