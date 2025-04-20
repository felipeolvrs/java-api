package com.example.api.application.services;

import java.util.List;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;

public interface TransacaoService {
    Transacao salvarTransacao( TransacaoDTO dto);

    List<Transacao> listarTransacoes();


}