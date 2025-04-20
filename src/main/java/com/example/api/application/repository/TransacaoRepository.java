package com.example.api.application.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.api.application.models.Transacao;


@Repository
public class TransacaoRepository {
    private ArrayList<Transacao> lista;
    private static long contadorId = 1;

    public TransacaoRepository() {
        this.lista = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacao.setId(contadorId++);
        this.lista.add(transacao);
    }

    public ArrayList<Transacao> listarTransacoes() {
        return this.lista;
    }

    public void deletarTodasTransacoes(){
        lista.clear();
    }
}
