package com.example.api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.services.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class trasacaoController {
    
    @Autowired
    private TransacaoService TransacaoService;

    @PostMapping
    public Transacao criarTransacao(@RequestBody TransacaoDTO transacaoBancaria) {
        return TransacaoService.salvar(transacaoBancaria);
    }
}