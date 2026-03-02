package main.java.br.com.murilo.checkout.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

    private final String nome;
    private final BigDecimal preco;

    public Produto(String nome, BigDecimal preco){
        if (nome == null || nome.isBlank()){

            throw new IllegalArgumentException("Nome do produto n pode ser vazio");


        }
        if(preco == null || preco.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Preco deve ser maior que zero");
        }
        this.nome = nome;
        this.preco = preco;


    }
    public String getNome(){
        return nome;
    }
    public BigDecimal getPreco(){
        return preco;
    }
    @Override
    public String toString(){
        return nome + " - R$ " + preco;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Produto produto))return false;
        return nome.equals(produto.nome) && preco.equals(produto.preco);

    }
    @Override
    public int hashCode(){
        return Objects.hash(nome,preco);
    }


}