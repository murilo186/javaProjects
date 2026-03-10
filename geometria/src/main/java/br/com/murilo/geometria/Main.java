package br.com.murilo.geometria;

import br.com.murilo.geometria.domain.ClassificadorTriangulo;
import br.com.murilo.geometria.domain.ResultadoTriangulo;
import br.com.murilo.geometria.model.Triangulo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClassificadorTriangulo classificador = new ClassificadorTriangulo();

        while (true) {
            exibirMenu();
            int opcao = lerInt(sc, "Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> classificarTriangulo(sc, classificador);
                case 0 -> {
                    System.out.println("Encerrando...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opcao invalida.");
            }

            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("===== CLASSIFICADOR DE TRIANGULOS =====");
        System.out.println("1 - Classificar triangulo");
        System.out.println("0 - Sair");
        System.out.println("=======================================");
    }

    private static void classificarTriangulo(Scanner sc, ClassificadorTriangulo classificador) {
        System.out.println("=== Informe os lados ===");

        try {
            double ladoA = lerDouble(sc, "Lado A: ");
            double ladoB = lerDouble(sc, "Lado B: ");
            double ladoC = lerDouble(sc, "Lado C: ");

            Triangulo triangulo = new Triangulo(ladoA, ladoB, ladoC);
            ResultadoTriangulo resultado = classificador.classificar(triangulo);

            System.out.println("Lados informados: " + triangulo.exibirLados());
            System.out.println("Valido: " + (resultado.isValido() ? "sim" : "nao"));
            System.out.println("Tipo: " + resultado.getTipo());
            System.out.println("Mensagem: " + resultado.getMensagem());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static int lerInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static double lerDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim().replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }
}
