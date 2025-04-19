package com.example.api.application.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Transacao {

    private Long id;
    private BigDecimal valor;
    private OffsetDateTime dataHora;

}
