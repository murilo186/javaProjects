package main.java.br.com.murilo.checkout.domain;
import java.math.BigDecimal;

public class Pedido {



    private final Carrinho carrinho;
    private final FormaPagamento formaPagamento;
    private final ResultadoPagamento resultado;

    public Pedido(Carrinho carrinho, FormaPagamento formaPagamento) {

        if (carrinho == null || carrinho.estaVazio()) {
            throw new IllegalArgumentException("Carrinho vazio.");
        }

        this.carrinho = carrinho;
        this.formaPagamento = formaPagamento;

        CalculadoraPagamento calculadora = new CalculadoraPagamento();
        this.resultado = calculadora.calcular(
                carrinho.subtotal(),
                formaPagamento
        );
    }

    public void imprimirResumo() {

        System.out.println("\n===== RESUMO DO PEDIDO =====");

        carrinho.getItens().forEach(System.out::println);

        System.out.println("-----------------------------");
        System.out.println("Subtotal: R$ " + resultado.getSubtotal());
        System.out.println("Forma de pagamento: " + formaPagamento);
        System.out.println("Ajuste: R$ " + resultado.getAjuste());
        System.out.println("Total final: R$ " + resultado.getTotalFinal());
        System.out.println("=============================");
    }

    
}
