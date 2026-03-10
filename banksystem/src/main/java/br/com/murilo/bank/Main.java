package br.com.murilo.bank;

import br.com.murilo.bank.domain.Banco;
import br.com.murilo.bank.domain.Conta;
import br.com.murilo.bank.domain.OperacaoConta;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        OperacaoConta operacaoConta = new OperacaoConta();

        while (true) {
            exibirMenu();
            int opcao = lerInt(sc, "Escolha uma opcao: ");

            try {
                switch (opcao) {
                    case 1 -> criarConta(sc, banco);
                    case 2 -> depositar(sc, banco, operacaoConta);
                    case 3 -> sacar(sc, banco, operacaoConta);
                    case 4 -> transferir(sc, banco, operacaoConta);
                    case 5 -> verSaldo(sc, banco);
                    case 6 -> verHistorico(sc, banco);
                    case 7 -> listarContas(banco);
                    case 0 -> {
                        System.out.println("Encerrando...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("===== SISTEMA BANCARIO =====");
        System.out.println("1 Criar conta");
        System.out.println("2 Depositar");
        System.out.println("3 Sacar");
        System.out.println("4 Transferir");
        System.out.println("5 Ver saldo");
        System.out.println("6 Ver historico");
        System.out.println("7 Listar contas");
        System.out.println("0 Sair");
        System.out.println("============================");
    }

    private static void criarConta(Scanner sc, Banco banco) {
        String titular = lerString(sc, "Nome do titular: ");
        Conta conta = banco.criarConta(titular);

        System.out.println("Conta criada com sucesso.");
        System.out.println("Numero da conta: " + conta.getNumero());
    }

    private static void depositar(Scanner sc, Banco banco, OperacaoConta operacaoConta) {
        Conta conta = buscarContaPorEntrada(sc, banco, "Numero da conta para deposito: ");
        BigDecimal valor = lerBigDecimal(sc, "Valor do deposito: ");

        operacaoConta.depositar(conta, valor);
        System.out.println("Deposito realizado. Novo saldo: R$ " + conta.getSaldo());
    }

    private static void sacar(Scanner sc, Banco banco, OperacaoConta operacaoConta) {
        Conta conta = buscarContaPorEntrada(sc, banco, "Numero da conta para saque: ");
        BigDecimal valor = lerBigDecimal(sc, "Valor do saque: ");

        operacaoConta.sacar(conta, valor);
        System.out.println("Saque realizado. Novo saldo: R$ " + conta.getSaldo());
    }

    private static void transferir(Scanner sc, Banco banco, OperacaoConta operacaoConta) {
        Conta origem = buscarContaPorEntrada(sc, banco, "Conta de origem: ");
        Conta destino = buscarContaPorEntrada(sc, banco, "Conta de destino: ");
        BigDecimal valor = lerBigDecimal(sc, "Valor da transferencia: ");

        operacaoConta.transferir(origem, destino, valor);
        System.out.println("Transferencia realizada com sucesso.");
        System.out.println("Saldo origem: R$ " + origem.getSaldo());
        System.out.println("Saldo destino: R$ " + destino.getSaldo());
    }

    private static void verSaldo(Scanner sc, Banco banco) {
        Conta conta = buscarContaPorEntrada(sc, banco, "Numero da conta: ");
        System.out.println("Saldo atual: R$ " + conta.getSaldo());
    }

    private static void verHistorico(Scanner sc, Banco banco) {
        Conta conta = buscarContaPorEntrada(sc, banco, "Numero da conta: ");

        if (conta.getHistorico().isEmpty()) {
            System.out.println("Nenhuma transacao registrada.");
            return;
        }

        System.out.println("=== Historico da conta " + conta.getNumero() + " ===");
        conta.getHistorico().forEach(System.out::println);
    }

    private static void listarContas(Banco banco) {
        if (banco.listarContas().isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        System.out.println("=== Contas cadastradas ===");
        banco.listarContas().forEach(System.out::println);
    }

    private static Conta buscarContaPorEntrada(Scanner sc, Banco banco, String prompt) {
        int numeroConta = lerInt(sc, prompt);
        return banco.buscarConta(numeroConta);
    }

    private static String lerString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String valor = sc.nextLine();

        while (valor == null || valor.isBlank()) {
            System.out.print("Valor invalido. " + prompt);
            valor = sc.nextLine();
        }

        return valor.trim();
    }

    private static int lerInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static BigDecimal lerBigDecimal(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim().replace(",", ".");

            try {
                BigDecimal valor = new BigDecimal(entrada);
                if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor valido.");
            }
        }
    }
}
