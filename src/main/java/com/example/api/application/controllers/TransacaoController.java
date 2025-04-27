package com.example.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.services.Interface.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes(); 
    }
    
    @PostMapping
    public Transacao criarTransacao(@RequestBody TransacaoDTO dto) {
        return transacaoService.salvarTransacao(dto);
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deletarTodasTransacoes() {
        transacaoService.deletarTodasTransacoes();
        return ResponseEntity.noContent().build();
    }

    
    
}
