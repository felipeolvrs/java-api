package com.example.api.application.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.api.application.models.Transacao;

@Repository
public class TransacaoRepository {

    // Lista para armazenar as transações
    private final List<Transacao> lista = new ArrayList<>();
    private static long contadorId = 1;

    // Método para adicionar uma transação à lista
    public void adicionarTransacao(Transacao transacao) {
        transacao.setId(contadorId++);
        lista.add(transacao);
    }

    // Método para listar todas as transações
    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(lista); 
    }

    // Método para limpar todas as transações
    public void deletarTodasTransacoes() {
        lista.clear();
    }

    // Método para buscar transações ocorridas após uma data limite
    public List<Transacao> findByDataHoraAfter(LocalDateTime limite) {
        List<Transacao> recentes = new ArrayList<>();
        for (Transacao transacao : lista) {
            if (transacao.getDataHora().isAfter(limite)) {
                recentes.add(transacao);
            }
        }
        return recentes;
    }

    public List<Transacao> findByDataHoraBetween(LocalDateTime dataInicial,  LocalDateTime dataFinal){
        return lista.stream()
        .filter(t -> t.getDataHora().isAfter(dataInicial) && t.getDataHora().isBefore(dataFinal))
        .collect(Collectors.toList());
    }

    public Optional<Transacao> findLastTransacao(){
        return lista.stream()
        .max((t1, t2) -> t1.getDataHora().compareTo(t2.getDataHora()));
    }



}
