package com.example.api.application.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransacaoDTO {
    
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}