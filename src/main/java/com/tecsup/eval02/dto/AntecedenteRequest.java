package com.tecsup.eval02.dto;

import jakarta.validation.constraints.NotBlank;

public class AntecedenteRequest {
    @NotBlank
    private String tipo;
    @NotBlank
    private String descripcion;
}
