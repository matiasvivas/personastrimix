package com.trimix.demo.configuration;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DbConfiguration {

    private final DataSource dataSource;

    @Autowired
    public DbConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initial(){
        String sql = "CREATE TABLE IF NOT EXISTS persona(id int, nombre varchar(255), apellido varchar(255), numeroDocumento long, tipoDocumento varchar(255), fechaNacimiento date, PRIMARY KEY (id))";
        getJdbcTemplate().update(sql);
    }
}
