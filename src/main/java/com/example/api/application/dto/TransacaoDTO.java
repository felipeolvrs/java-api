package com.example.api.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {

    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    private LocalDateTime dataHora;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(
        regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
        message = "CPF deve estar no formato XXX.XXX.XXX-XX"
    )
    private String cpf;

    private String banco;
    private String senha;
}
