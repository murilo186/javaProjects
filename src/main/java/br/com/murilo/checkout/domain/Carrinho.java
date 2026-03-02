package main.java.br.com.murilo.checkout.domain;

import main.java.br.com.murilo.checkout.model.ItemCarrinho;
import main.java.br.com.murilo.checkout.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        ItemCarrinho existente = encontrarItemPorProduto(produto);

        if (existente != null) {
            existente.adicionarQuantidade(quantidade);
            return;
        }

        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public void removerPorNome(String nomeProduto) {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }

        ItemCarrinho item = encontrarItemPorNome(nomeProduto);

        if (item == null) {
            throw new IllegalArgumentException("Produto não encontrado no carrinho: " + nomeProduto);
        }

        itens.remove(item);
    }

    public BigDecimal subtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (ItemCarrinho item : itens) {
            subtotal = subtotal.add(item.totalItem());
        }

        return subtotal;
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public int quantidadeDeItens() {
        return itens.size();
    }

    public List<ItemCarrinho> getItens() {
        // não devolve a lista mutável "na mão" pra não bagunçar o carrinho
        return Collections.unmodifiableList(itens);
    }

    private ItemCarrinho encontrarItemPorProduto(Produto produto) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().equals(produto)) {
                return item;
            }
        }
        return null;
    }

    private ItemCarrinho encontrarItemPorNome(String nomeProduto) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().getNome().equalsIgnoreCase(nomeProduto)) {
                return item;
            }
        }
        return null;
    }
}