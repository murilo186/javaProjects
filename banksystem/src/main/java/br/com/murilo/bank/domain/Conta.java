package br.com.murilo.bank.domain;

import br.com.murilo.bank.model.Transacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta {

    private final int numero;
    private String titular;
    private BigDecimal saldo;
    private final List<Transacao> historicoTransacoes;

    public Conta(int numero, String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("Titular nao pode ser vazio.");
        }

        this.numero = numero;
        this.titular = titular.trim();
        this.saldo = BigDecimal.ZERO;
        this.historicoTransacoes = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public void atualizarTitular(String novoTitular) {
        if (novoTitular == null || novoTitular.isBlank()) {
            throw new IllegalArgumentException("Titular nao pode ser vazio.");
        }

        this.titular = novoTitular.trim();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historicoTransacoes);
    }

    void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    void registrarTransacao(Transacao transacao) {
        historicoTransacoes.add(transacao);
    }

    @Override
    public String toString() {
        return "Conta " + numero + " | Titular: " + titular + " | Saldo: R$ " + saldo;
    }
}
