package com.example.api.application.services.Interface;

import com.example.api.application.dto.EstatisticaDTO;
import com.example.api.application.dto.PeriodoDTO;

public interface EstatisticaService {
    EstatisticaDTO calcularEstatisticas();
    EstatisticaDTO calcularEstatisticasPorPeriodo(PeriodoDTO periodo);
    
}
