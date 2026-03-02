package main.java.br.com.murilo.checkout.model;

import java.math.BigDecimal;

public class ItemCarrinho {

    private final Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade adicional deve ser maior que zero.");
        }
        this.quantidade += quantidade;
    }

    public BigDecimal totalItem() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return produto.getNome() +
                " | Qtd: " + quantidade +
                " | Total: R$ " + totalItem();
    }
}