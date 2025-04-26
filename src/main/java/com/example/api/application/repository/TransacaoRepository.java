package com.example.api.application.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.example.api.application.models.Transacao;

@Repository
public class TransacaoRepository {

    private final List<Transacao> lista = new ArrayList<>();
    private static long contadorId = 1;

    public void adicionarTransacao(Transacao transacao) {
        transacao.setId(contadorId++);
        lista.add(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(lista); 
    }

    public void deletarTodasTransacoes() {
        lista.clear();
    }

    public List<Transacao> findByDataHoraAfter(LocalDateTime  limite) {
        List<Transacao> recentes = new ArrayList<>();
        for (Transacao transacao : lista) {
            if (transacao.getDataHora().isAfter(limite)) {
                recentes.add(transacao);
            }
        }
        return recentes;
    }

    public List<Transacao> findByDataHoraBefore(LocalDateTime  limite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDataHoraBefore'");
    }
}
