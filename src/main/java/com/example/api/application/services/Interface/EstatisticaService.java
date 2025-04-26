package com.example.api.application.services.Interface;

import java.util.Optional;

import com.example.api.application.dto.EstatisticaDTO;
import com.example.api.application.dto.PeriodoDTO;
import com.example.api.application.models.Transacao;

public interface EstatisticaService {
    EstatisticaDTO calcularEstatisticas();
    EstatisticaDTO calcularEstatisticasPorPeriodo(PeriodoDTO periodo);
    Optional<Transacao> findLastTransacao();
}
