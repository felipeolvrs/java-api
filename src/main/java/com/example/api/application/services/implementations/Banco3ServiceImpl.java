package com.example.api.application.services.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.repository.TransacaoRepository;
import com.example.api.application.services.Interface.TransacaoService;

@Service
public class Banco3ServiceImpl implements TransacaoService {

    private final TransacaoRepository repository;

    public Banco3ServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        Transacao transacao = new Transacao(dto);
        repository.adicionarTransacao(transacao);
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return repository.listarTransacoes();
    }

    @Override
    public void deletarTodasTransacoes() {
        repository.deletarTodasTransacoes();
    }

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {

        repository.clearDataBetween(inicio, fim);
    }
}
