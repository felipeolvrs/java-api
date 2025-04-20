package com.example.api.application.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.repository.TransacaoRepository;
import com.example.api.application.services.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository repository;

    @Autowired
    public TransacaoServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    public Transacao salvar(TransacaoDTO dto) {
        Transacao transacao = new Transacao();

        repository.adicionarTransacao(transacao);

        return transacao;
    }
}
