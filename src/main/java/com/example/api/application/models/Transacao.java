package com.example.api.application.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    private Integer id;
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve ter o formato XXX.XXX.XXX-XX")
    private String cpf;

    public Transacao(com.example.api.application.dto.TransacaoDTO dto) {
        this.valor = dto.getValor();
        this.dataHora = dto.getDataHora();
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
    }
}
