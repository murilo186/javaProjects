package main.java.br.com.murilo.checkout;

import main.java.br.com.murilo.checkout.domain.CalculadoraPagamento;
import main.java.br.com.murilo.checkout.domain.Carrinho;
import main.java.br.com.murilo.checkout.domain.FormaPagamento;
import main.java.br.com.murilo.checkout.domain.Pedido;
import main.java.br.com.murilo.checkout.domain.ResultadoPagamento;
import main.java.br.com.murilo.checkout.model.Produto;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Criando produtos
        Produto notebook = new Produto("Notebook", new BigDecimal("3500.00"));
        Produto mouse = new Produto("Mouse Gamer", new BigDecimal("150.00"));

        // Criando carrinho
        Carrinho carrinho = new Carrinho();

        // Adicionando itens
        carrinho.adicionar(notebook, 1);
        carrinho.adicionar(mouse, 2);

        // Adicionando o mesmo produto novamente (deve somar quantidade)
        carrinho.adicionar(mouse, 1);


      Pedido pedido = new Pedido(carrinho, FormaPagamento.PIX_DINHEIRO);
pedido.imprimirResumo();
    }
}