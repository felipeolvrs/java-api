package com.example.api.application.services.impl;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.services.TransacaoService;

public class TransacaoServiceImpl implements TransacaoService {

    @Override
    public Transacao salvar(TransacaoDTO dto) {

        Transacao transacao = new Transacao(null, dto.getValor(),dto.getDataHora());
        return transacao;
    }
    
}
