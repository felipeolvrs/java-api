package com.example.api.application.services;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;

public interface TransacaoService {
    Transacao salvar( TransacaoDTO dto) ;
    
}