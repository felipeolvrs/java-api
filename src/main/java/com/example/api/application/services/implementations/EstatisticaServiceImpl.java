package com.example.api.application.services.implementations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.application.dto.EstatisticaDTO;
import com.example.api.application.models.Transacao;
import com.example.api.application.repository.TransacaoRepository;
import com.example.api.application.services.Interface.EstatisticaService;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    private final TransacaoRepository repository;

    @Autowired
    public EstatisticaServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public EstatisticaDTO calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusMinutes(10); 


        List<Transacao> ultimasTransacoes = repository.findByDataHoraAfter(limite);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = null;
        BigDecimal max = null;

        for (Transacao transacao : ultimasTransacoes) {
            BigDecimal valor = transacao.getValor();
            sum = sum.add(valor);

            if (min == null || valor.compareTo(min) < 0) {
                min = valor;
            }

            if (max == null || valor.compareTo(max) > 0) {
                max = valor;
            }
        }

        int count = ultimasTransacoes.size();
        BigDecimal avg = count > 0 ? sum.divide(BigDecimal.valueOf(count), 3, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        System.out.println("Soma: " + sum);
System.out.println("Valor mínimo: " + min);
System.out.println("Valor máximo: " + max);

        EstatisticaDTO estatistica = new EstatisticaDTO();
        estatistica.setCount(count);
        estatistica.setSum(sum);
        estatistica.setAvg(avg);
        estatistica.setMin(min != null ? min : BigDecimal.ZERO);
        estatistica.setMax(max != null ? max : BigDecimal.ZERO);


        return estatistica;
    }
}
