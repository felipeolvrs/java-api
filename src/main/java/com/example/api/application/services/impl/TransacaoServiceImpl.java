package com.example.api.application.services.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {

        if (dto == null || dto.getValor() == null || dto.getDataHora() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos obrigatórios ausentes");
        }

        if (dto.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Valor não pode ser negativo.");
        }

        if( dto.getDataHora().isAfter(OffsetDateTime.now())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Data não pode ser no futuro");

        }
        
        Transacao transacao = new Transacao();
        transacao.setDataHora(dto.getDataHora());
        transacao.setValor(dto.getValor());
        repository.adicionarTransacao(transacao);

        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return repository.listarTransacoes();
    }

    public void deletarTodasTransacoes() {
    repository.deletarTodasTransacoes();
}


}
