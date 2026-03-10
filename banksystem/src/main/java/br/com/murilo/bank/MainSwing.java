package br.com.murilo.bank;

import br.com.murilo.bank.domain.Banco;
import br.com.murilo.bank.domain.Conta;
import br.com.murilo.bank.domain.OperacaoConta;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;

public class MainSwing {

    private final Banco banco = new Banco();
    private final OperacaoConta operacaoConta = new OperacaoConta();
    private JTextArea areaResultado;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainSwing().iniciar());
    }

    private void iniciar() {
        JFrame frame = new JFrame("Sistema Bancario");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(new JLabel("Sistema bancario em Swing"), BorderLayout.WEST);

        JPanel painelBotoes = new JPanel(new GridLayout(3, 3, 8, 8));
        painelBotoes.add(criarBotao("Criar conta", this::criarConta));
        painelBotoes.add(criarBotao("Depositar", this::depositar));
        painelBotoes.add(criarBotao("Sacar", this::sacar));
        painelBotoes.add(criarBotao("Transferir", this::transferir));
        painelBotoes.add(criarBotao("Ver saldo", this::verSaldo));
        painelBotoes.add(criarBotao("Ver historico", this::verHistorico));
        painelBotoes.add(criarBotao("Listar contas", this::listarContas));
        painelBotoes.add(criarBotao("Atualizar titular", this::atualizarTitular));
        painelBotoes.add(criarBotao("Remover conta", this::removerConta));

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setText("Selecione uma operacao.");

        frame.add(painelTopo, BorderLayout.NORTH);
        frame.add(painelBotoes, BorderLayout.WEST);
        frame.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JButton criarBotao(String titulo, Runnable acao) {
        JButton botao = new JButton(titulo);
        botao.addActionListener(e -> executarAcao(acao));
        return botao;
    }

    private void executarAcao(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Falha inesperada: " + e.getMessage());
        }
    }

    private void criarConta() {
        String titular = pedirTexto("Nome do titular:");
        Conta conta = banco.criarConta(titular);
        exibirMensagem("Conta criada com sucesso.\nNumero da conta: " + conta.getNumero());
    }

    private void depositar() {
        Conta conta = buscarContaPorDialogo("Numero da conta para deposito:");
        BigDecimal valor = pedirValor("Valor do deposito:");
        operacaoConta.depositar(conta, valor);
        exibirMensagem("Deposito realizado.\nNovo saldo: R$ " + conta.getSaldo());
    }

    private void sacar() {
        Conta conta = buscarContaPorDialogo("Numero da conta para saque:");
        BigDecimal valor = pedirValor("Valor do saque:");
        operacaoConta.sacar(conta, valor);
        exibirMensagem("Saque realizado.\nNovo saldo: R$ " + conta.getSaldo());
    }

    private void transferir() {
        Conta origem = buscarContaPorDialogo("Conta de origem:");
        Conta destino = buscarContaPorDialogo("Conta de destino:");
        BigDecimal valor = pedirValor("Valor da transferencia:");
        operacaoConta.transferir(origem, destino, valor);
        exibirMensagem(
                "Transferencia realizada com sucesso.\n" +
                        "Saldo origem: R$ " + origem.getSaldo() + "\n" +
                        "Saldo destino: R$ " + destino.getSaldo()
        );
    }

    private void verSaldo() {
        Conta conta = buscarContaPorDialogo("Numero da conta:");
        exibirMensagem("Saldo atual da conta " + conta.getNumero() + ": R$ " + conta.getSaldo());
    }

    private void verHistorico() {
        Conta conta = buscarContaPorDialogo("Numero da conta:");
        if (conta.getHistorico().isEmpty()) {
            exibirMensagem("Nenhuma transacao registrada para a conta " + conta.getNumero() + ".");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Historico da conta ").append(conta.getNumero()).append("\n\n");
        conta.getHistorico().forEach(transacao -> sb.append(transacao).append("\n"));
        exibirMensagem(sb.toString());
    }

    private void listarContas() {
        if (banco.listarContas().isEmpty()) {
            exibirMensagem("Nenhuma conta cadastrada.");
            return;
        }

        StringBuilder sb = new StringBuilder("Contas cadastradas\n\n");
        banco.listarContas().forEach(conta -> sb.append(conta).append("\n"));
        exibirMensagem(sb.toString());
    }

    private void atualizarTitular() {
        int numeroConta = pedirNumero("Numero da conta:");
        String novoTitular = pedirTexto("Novo nome do titular:");
        banco.atualizarTitular(numeroConta, novoTitular);
        Conta conta = banco.buscarConta(numeroConta);
        exibirMensagem("Titular atualizado com sucesso.\n" + conta);
    }

    private void removerConta() {
        int numeroConta = pedirNumero("Numero da conta para remover:");
        banco.removerConta(numeroConta);
        exibirMensagem("Conta removida com sucesso.");
    }

    private Conta buscarContaPorDialogo(String mensagem) {
        int numeroConta = pedirNumero(mensagem);
        return banco.buscarConta(numeroConta);
    }

    private int pedirNumero(String mensagem) {
        String entrada = pedirTexto(mensagem);

        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Digite um numero inteiro valido.");
        }
    }

    private BigDecimal pedirValor(String mensagem) {
        String entrada = pedirTexto(mensagem).replace(",", ".");

        try {
            return new BigDecimal(entrada);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Digite um valor valido.");
        }
    }

    private String pedirTexto(String mensagem) {
        String valor = JOptionPane.showInputDialog(null, mensagem, "Sistema Bancario", JOptionPane.QUESTION_MESSAGE);

        if (valor == null) {
            throw new IllegalArgumentException("Operacao cancelada.");
        }

        if (valor.isBlank()) {
            throw new IllegalArgumentException("O campo nao pode ficar vazio.");
        }

        return valor.trim();
    }

    private void exibirMensagem(String mensagem) {
        areaResultado.setText(mensagem);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
