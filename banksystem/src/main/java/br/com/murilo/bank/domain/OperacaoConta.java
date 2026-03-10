package br.com.murilo.bank.domain;

import br.com.murilo.bank.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperacaoConta {

    public void depositar(Conta conta, BigDecimal valor) {
        validarValorPositivo(valor);
        conta.creditar(valor);
        conta.registrarTransacao(new Transacao(
                valor,
                TipoOperacao.DEPOSITO,
                LocalDateTime.now(),
                "Deposito realizado"
        ));
    }

    public void sacar(Conta conta, BigDecimal valor) {
        validarValorPositivo(valor);
        validarSaldoSuficiente(conta, valor);
        conta.debitar(valor);
        conta.registrarTransacao(new Transacao(
                valor,
                TipoOperacao.SAQUE,
                LocalDateTime.now(),
                "Saque realizado"
        ));
    }

    public void transferir(Conta origem, Conta destino, BigDecimal valor) {
        if (origem.getNumero() == destino.getNumero()) {
            throw new IllegalArgumentException("A conta de destino deve ser diferente da conta de origem.");
        }

        validarValorPositivo(valor);
        validarSaldoSuficiente(origem, valor);

        origem.debitar(valor);
        destino.creditar(valor);

        origem.registrarTransacao(new Transacao(
                valor,
                TipoOperacao.TRANSFERENCIA_ENVIO,
                LocalDateTime.now(),
                "Transferencia enviada para a conta " + destino.getNumero()
        ));

        destino.registrarTransacao(new Transacao(
                valor,
                TipoOperacao.TRANSFERENCIA_RECEBIMENTO,
                LocalDateTime.now(),
                "Transferencia recebida da conta " + origem.getNumero()
        ));
    }

    private void validarValorPositivo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    private void validarSaldoSuficiente(Conta conta, BigDecimal valor) {
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a operacao.");
        }
    }
}
