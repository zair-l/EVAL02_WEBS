package com.tecsup.eval02.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PacienteRequest {

    @NotBlank
    private String dni;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    private String fechaNacimiento;

    private String sexo;
    private String direccion;

    @Size(max = 50)
    private String telefono;

    @Email
    private String correo;

}

