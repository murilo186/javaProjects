package br.com.murilo.geometria.model;

public class Triangulo {

    private final double ladoA;
    private final double ladoB;
    private final double ladoC;

    public Triangulo(double ladoA, double ladoB, double ladoC) {
        validarLado(ladoA, "A");
        validarLado(ladoB, "B");
        validarLado(ladoC, "C");
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    public double getLadoA() {
        return ladoA;
    }

    public double getLadoB() {
        return ladoB;
    }

    public double getLadoC() {
        return ladoC;
    }

    public String exibirLados() {
        return String.format("A=%.2f, B=%.2f, C=%.2f", ladoA, ladoB, ladoC);
    }

    private void validarLado(double lado, String nomeLado) {
        if (lado <= 0) {
            throw new IllegalArgumentException("O lado " + nomeLado + " deve ser maior que zero.");
        }
    }
}
