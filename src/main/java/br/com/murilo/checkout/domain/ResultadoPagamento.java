package main.java.br.com.murilo.checkout.domain;

import java.math.BigDecimal;

public class ResultadoPagamento {

    private final BigDecimal subtotal;
    private final BigDecimal ajuste; // negativo = desconto | positivo = juros
    private final BigDecimal totalFinal;

    public ResultadoPagamento(BigDecimal subtotal, BigDecimal ajuste, BigDecimal totalFinal) {
        this.subtotal = subtotal;
        this.ajuste = ajuste;
        this.totalFinal = totalFinal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getAjuste() {
        return ajuste;
    }

    public BigDecimal getTotalFinal() {
        return totalFinal;
    }
}