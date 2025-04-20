package com.example.api.application.repository;

import java.util.ArrayList;

import com.example.api.application.models.Transacao;



public class TransacaoRepository {
    private ArrayList<Transacao> lista;

    public TransacaoRepository() {
        this.lista = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        this.lista.add(transacao);
    }

    public ArrayList<Transacao> listarTransacoes() {
        return this.lista;
    }
}
