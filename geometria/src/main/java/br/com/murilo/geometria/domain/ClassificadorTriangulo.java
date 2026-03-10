package br.com.murilo.geometria.domain;

import br.com.murilo.geometria.model.Triangulo;

public class ClassificadorTriangulo {

    public ResultadoTriangulo classificar(Triangulo triangulo) {
        if (!existeTriangulo(triangulo)) {
            return new ResultadoTriangulo(
                    false,
                    TipoTriangulo.INVALIDO,
                    "Os lados informados nao formam um triangulo."
            );
        }

        if (todosOsLadosIguais(triangulo)) {
            return new ResultadoTriangulo(true, TipoTriangulo.EQUILATERO, "Triangulo equilatero.");
        }

        if (doisLadosIguais(triangulo)) {
            return new ResultadoTriangulo(true, TipoTriangulo.ISOSCELES, "Triangulo isosceles.");
        }

        return new ResultadoTriangulo(true, TipoTriangulo.ESCALENO, "Triangulo escaleno.");
    }

    private boolean existeTriangulo(Triangulo triangulo) {
        double ladoA = triangulo.getLadoA();
        double ladoB = triangulo.getLadoB();
        double ladoC = triangulo.getLadoC();

        return ladoA + ladoB > ladoC
                && ladoA + ladoC > ladoB
                && ladoB + ladoC > ladoA;
    }

    private boolean todosOsLadosIguais(Triangulo triangulo) {
        return triangulo.getLadoA() == triangulo.getLadoB()
                && triangulo.getLadoB() == triangulo.getLadoC();
    }

    private boolean doisLadosIguais(Triangulo triangulo) {
        return triangulo.getLadoA() == triangulo.getLadoB()
                || triangulo.getLadoA() == triangulo.getLadoC()
                || triangulo.getLadoB() == triangulo.getLadoC();
    }
}
