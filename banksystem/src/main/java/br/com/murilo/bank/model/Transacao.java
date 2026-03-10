package br.com.murilo.bank.model;

import br.com.murilo.bank.domain.TipoOperacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {

    private final BigDecimal valor;
    private final TipoOperacao tipo;
    private final LocalDateTime dataHora;
    private final String descricao;

    public Transacao(BigDecimal valor, TipoOperacao tipo, LocalDateTime dataHora, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return dataHora + " | " + tipo + " | R$ " + valor + " | " + descricao;
    }
}
