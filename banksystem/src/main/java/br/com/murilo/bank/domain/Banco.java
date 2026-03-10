package br.com.murilo.bank.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {

    private final List<Conta> contas;
    private int proximoNumeroConta;

    public Banco() {
        this.contas = new ArrayList<>();
        this.proximoNumeroConta = 1001;
    }

    public Conta criarConta(String titular) {
        Conta conta = new Conta(proximoNumeroConta, titular);
        contas.add(conta);
        proximoNumeroConta++;
        return conta;
    }

    public Conta buscarConta(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }

        throw new IllegalArgumentException("Conta nao encontrada: " + numero);
    }

    public void atualizarTitular(int numero, String novoTitular) {
        Conta conta = buscarConta(numero);
        conta.atualizarTitular(novoTitular);
    }

    public void removerConta(int numero) {
        Conta conta = buscarConta(numero);

        if (conta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("A conta so pode ser removida com saldo zerado.");
        }

        contas.remove(conta);
    }

    public List<Conta> listarContas() {
        return Collections.unmodifiableList(contas);
    }
}
