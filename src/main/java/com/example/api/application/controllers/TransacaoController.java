package com.example.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.services.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public List<Transacao> getTransacoes() {
        return transacaoService.listarTransacoes(); 
    }

    @PostMapping
    public Transacao postTransacao(@RequestBody TransacaoDTO transacaoBancaria) {
        return transacaoService.salvarTransacao(transacaoBancaria);
    }
}
