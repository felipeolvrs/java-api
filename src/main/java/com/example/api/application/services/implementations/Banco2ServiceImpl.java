package com.example.api.application.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.repository.TransacaoRepository;
import com.example.api.application.services.Interface.TransacaoService;

@Service
public class Banco2ServiceImpl implements TransacaoService {

    private final TransacaoRepository repository;

    public Banco2ServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        if (dto.getDataHora() == null || dto.getDataHora().getYear() != 2025) {
            return null;
        }
        Transacao transacao = new Transacao(dto);
        repository.adicionarTransacao(transacao);
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return repository.listarTransacoes().stream()
                .filter(t -> t.getValor().compareTo(new java.math.BigDecimal("1000.00")) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarTodasTransacoes() {
        throw new UnsupportedOperationException("Use deletarPorPeriodoPorBanco com senha apropriada");
    }

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
        if (!"BD2@456".equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }
        List<Transacao> paraRemover = repository.listarTransacoes().stream()
                .filter(t -> !t.getDataHora().isBefore(inicio) && !t.getDataHora().isAfter(fim))
                .collect(Collectors.toList());
        paraRemover.forEach(t -> repository.listarTransacoes().remove(t));
    }
}
