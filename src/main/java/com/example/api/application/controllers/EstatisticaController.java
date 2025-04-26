package com.example.api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.application.dto.EstatisticaDTO;
import com.example.api.application.dto.PeriodoDTO;
import com.example.api.application.services.Interface.EstatisticaService;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public EstatisticaDTO getEstatisticas() {
        return estatisticaService.calcularEstatisticas();
    }

    @GetMapping("/periodo")
    public EstatisticaDTO getTotalizadoresPorPeriodo(@RequestBody PeriodoDTO periodo) {
        return estatisticaService.calcularEstatisticasPorPeriodo(periodo);
    }
}

