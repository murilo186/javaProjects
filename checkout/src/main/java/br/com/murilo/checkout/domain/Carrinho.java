package br.com.murilo.checkout.domain;

import br.com.murilo.checkout.model.ItemCarrinho;
import br.com.murilo.checkout.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    // Operacoes publicas do carrinho
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
            throw new IllegalArgumentException("Nome do produto nao pode ser vazio.");
        }

        ItemCarrinho item = encontrarItemPorNome(nomeProduto);

        if (item == null) {
            throw new IllegalArgumentException("Produto nao encontrado no carrinho: " + nomeProduto);
        }

        itens.remove(item);
    }

    public void limpar() {
        itens.clear();
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
        // Nao devolve a lista mutavel "na mao" para proteger o estado do carrinho.
        return Collections.unmodifiableList(itens);
    }

    // Metodos auxiliares privados
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
