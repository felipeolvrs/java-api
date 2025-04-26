package com.example.api.application.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.application.dto.EstatisticaDTO;
import com.example.api.application.dto.PeriodoDTO;
import com.example.api.application.models.Transacao;
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

    @GetMapping("/ultima")
    public ResponseEntity<Transacao> getUltimaTransacao(){
        Optional<Transacao> ultima =  estatisticaService.findLastTransacao();
        
        if(ultima.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(ultima.get(), HttpStatus.OK);
        
    }
}

