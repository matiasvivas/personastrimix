package com.trimix.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoDocumento {

    @JsonProperty("DNI") DNI,
    @JsonProperty("PASAPORTE") PASAPORTE,
    @JsonProperty("CEDULA") CEDULA,

}
