package main.java.br.com.murilo.checkout;

import main.java.br.com.murilo.checkout.domain.Carrinho;
import main.java.br.com.murilo.checkout.domain.FormaPagamento;
import main.java.br.com.murilo.checkout.domain.Pedido;
import main.java.br.com.murilo.checkout.model.Produto;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Carrinho carrinho = new Carrinho();
        Scanner sc = new Scanner(System.in);

        while (true) {
            exibirMenu();

            int opcao = lerInt(sc, "Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> adicionarProduto(sc, carrinho);
                    case 2 -> removerProduto(sc, carrinho);
                    case 3 -> listarCarrinho(carrinho);
                    case 4 -> finalizarCompra(sc, carrinho);
                    case 0 -> {
                        System.out.println("Encerrando...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                // fallback para não quebrar o programa por entrada inesperada
                System.out.println("Erro inesperado: " + e.getMessage());
            }

            System.out.println(); // linha em branco
        }
    }

    private static void exibirMenu() {
        System.out.println("===== CHECKOUT =====");
        System.out.println("1) Adicionar produto");
        System.out.println("2) Remover produto (por nome)");
        System.out.println("3) Ver carrinho");
        System.out.println("4) Finalizar compra");
        System.out.println("0) Sair");
        System.out.println("====================");
    }

    private static void adicionarProduto(Scanner sc, Carrinho carrinho) {
        System.out.println("=== Adicionar produto ===");

        String nome = lerString(sc, "Nome do produto: ");
        BigDecimal preco = lerBigDecimal(sc, "Preço (ex: 150.00): ");
        int qtd = lerInt(sc, "Quantidade: ");

        Produto produto = new Produto(nome, preco);
        carrinho.adicionar(produto, qtd);

        System.out.println("Produto adicionado ao carrinho.");
    }

    private static void removerProduto(Scanner sc, Carrinho carrinho) {
        System.out.println("=== Remover produto ===");

        if (carrinho.estaVazio()) {
            System.out.println("Carrinho está vazio.");
            return;
        }

        String nome = lerString(sc, "Nome do produto para remover: ");
        carrinho.removerPorNome(nome);

        System.out.println("Produto removido.");
    }

    private static void listarCarrinho(Carrinho carrinho) {
        System.out.println("=== Carrinho ===");

        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        carrinho.getItens().forEach(System.out::println);
        System.out.println("Subtotal: R$ " + carrinho.subtotal());
    }

    private static void finalizarCompra(Scanner sc, Carrinho carrinho) {
        System.out.println("=== Finalizar compra ===");

        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio. Adicione itens antes de finalizar.");
            return;
        }

        FormaPagamento forma = escolherFormaPagamento(sc);

        Pedido pedido = new Pedido(carrinho, forma);
        pedido.imprimirResumo();

        // Didático: depois de finalizar, zera o carrinho criando outro.
        // (Alternativa seria ter carrinho.limpar(), mas ainda não criamos.)
        System.out.println("Compra finalizada. Reiniciando carrinho...");
        carrinho.limpar();
    }

    private static FormaPagamento escolherFormaPagamento(Scanner sc) {
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1) PIX/Dinheiro (15% desconto)");
        System.out.println("2) Crédito à vista (10% desconto)");
        System.out.println("3) Crédito 2x (sem desconto/juros)");
        System.out.println("4) Crédito 3x ou mais (10% juros)");

        int opcao = lerInt(sc, "Opção: ");

        return switch (opcao) {
            case 1 -> FormaPagamento.PIX_DINHEIRO;
            case 2 -> FormaPagamento.CREDITO_AVISTA;
            case 3 -> FormaPagamento.CREDITO_2X;
            case 4 -> FormaPagamento.CREDITO_3X_OU_MAIS;
            default -> throw new IllegalArgumentException("Forma de pagamento inválida.");
        };
    }

    /**
     * Como o Carrinho não tem limpar(), aqui vamos fazer o “reset didático”:
     * - removendo item por item (com base no nome).
     * Se preferir, crie carrinho.limpar() e substitua por carrinho.limpar().
     */
    private static void limparCarrinho(Carrinho carrinho) {
        // remove até ficar vazio
        while (!carrinho.estaVazio()) {
            String nome = carrinho.getItens().get(0).getProduto().getNome();
            carrinho.removerPorNome(nome);
        }
    }

    // ===== Helpers de leitura =====

    private static String lerString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();
        while (s == null || s.isBlank()) {
            System.out.print("Valor inválido. " + prompt);
            s = sc.nextLine();
        }
        return s.trim();
    }

    private static int lerInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
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
                System.out.println("Digite um valor válido (ex: 150.00).");
            }
        }
    }
}