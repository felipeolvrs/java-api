package com.example.api.application.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.api.application.dto.TransacaoDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.services.implementations.TransacaoServiceFactory;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoServiceFactory transacaoServiceFactory;

    public TransacaoController(TransacaoServiceFactory transacaoServiceFactory) {
        this.transacaoServiceFactory = transacaoServiceFactory;
    }

    @GetMapping
    public ResponseEntity<?> listarTransacoes(@RequestParam String banco) {
        try {
            List<Transacao> lista = transacaoServiceFactory.listarTransacoesPorBanco(banco);
            return ResponseEntity.ok(lista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarTransacao(@RequestBody TransacaoDTO dto) {
        try {
            Transacao transacao = transacaoServiceFactory.salvarTransacao(dto);
            if (transacao == null) {
                return ResponseEntity.badRequest().body("Transação inválida para o banco informado");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> deletarTodasTransacoes(@RequestParam String banco) {
        try {
            transacaoServiceFactory.deletarTodasTransacoesPorBanco(banco);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/periodo")
    public ResponseEntity<?> deletarPorPeriodo(
            @RequestParam String banco,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        try {
            transacaoServiceFactory.deletarPorPeriodoPorBanco(banco, dataInicial, dataFinal);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Operação não permitida");
        }
    }

    @GetMapping("/cpf")
    public ResponseEntity<?> buscarPorCpf(
            @RequestParam String banco,
            @RequestParam String valor) {
        try {
            List<Transacao> lista = transacaoServiceFactory.listarTransacoesPorBanco(banco).stream()
                    .filter(t -> t.getCpf() != null && t.getCpf().equals(valor))
                    .toList();
            return ResponseEntity.ok(lista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        }
    }

    @GetMapping("/nome")
    public ResponseEntity<?> buscarPorNome(
            @RequestParam String banco,
            @RequestParam String valor) {
        try {
            List<Transacao> lista = transacaoServiceFactory.listarTransacoesPorBanco(banco).stream()
                    .filter(t -> t.getNome() != null && t.getNome().equalsIgnoreCase(valor))
                    .toList();
            return ResponseEntity.ok(lista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarNomeCpf(
            @PathVariable Integer id,
            @RequestBody TransacaoDTO dto) {
        try {
            String banco = dto.getBanco();
            var service = transacaoServiceFactory.getServiceByBanco(banco);
            var transacoes = service.listarTransacoes();

            var transacaoOpt = transacoes.stream()
                    .filter(t -> t.getId() != null && t.getId().equals(id))
                    .findFirst();

            if (transacaoOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Transacao transacao = transacaoOpt.get();
            transacao.setNome(dto.getNome());
            transacao.setCpf(dto.getCpf());

            return ResponseEntity.ok(transacao);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Banco inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
