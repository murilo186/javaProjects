package br.com.murilo.checkout.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraPagamento {

    public ResultadoPagamento calcular(BigDecimal subtotal, FormaPagamento forma) {

        if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Subtotal inválido.");
        }
        if (forma == null) {
            throw new IllegalArgumentException("Forma de pagamento inválida.");
        }

        BigDecimal fator = switch (forma) {
            case PIX_DINHEIRO -> new BigDecimal("0.85");
            case CREDITO_AVISTA -> new BigDecimal("0.90");
            case CREDITO_2X -> new BigDecimal("1.00");
            case CREDITO_3X_OU_MAIS -> new BigDecimal("1.10");
        };

        BigDecimal totalFinal = subtotal.multiply(fator).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ajuste = totalFinal.subtract(subtotal).setScale(2, RoundingMode.HALF_UP);

        return new ResultadoPagamento(
                subtotal.setScale(2, RoundingMode.HALF_UP),
                ajuste,
                totalFinal
        );
    }
}
