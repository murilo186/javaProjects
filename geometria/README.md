# Projeto 04 - Classificador de Triangulos (POO)

Projeto desenvolvido como pratica de Programacao Orientada a Objetos (Java).

Objetivo: modelar um classificador de triangulos aplicando boas praticas de POO, separacao de responsabilidades, uso de `enum` e validacoes de dominio.

---

## Objetivos do Projeto

- Modelar um problema matematico simples usando POO
- Separar regra de negocio da camada de execucao
- Aplicar encapsulamento
- Utilizar `enum` para tipos de triangulo
- Validar dados no dominio
- Evitar `Main` com regra de classificacao

---

## Conceitos aplicados

- Pilares da POO
- Encapsulamento
- Responsabilidade unica (SRP)
- Separacao de camadas (Main vs Dominio)
- `enum` para modelagem de dominio
- Validacao no construtor

---

## Estrutura do Projeto

br.com.murilo.geometria
|
+-- Main (menu interativo)
|
+-- domain
|  +-- ClassificadorTriangulo
|  +-- ResultadoTriangulo
|  +-- TipoTriangulo (enum)
|
+-- model
   +-- Triangulo

---

## Funcionalidades

- Informar tres lados
- Validar se os lados sao positivos
- Verificar existencia do triangulo
- Classificar como EQUILATERO, ISOSCELES ou ESCALENO
- Exibir resultado no console
- Repetir operacoes via menu

---

## Regras principais

- Todos os lados devem ser maiores que zero
- O triangulo so existe se a soma de dois lados for maior que o terceiro
- Triangulo valido pode ser equilatero, isosceles ou escaleno

---

## Exemplo de Execucao

===== CLASSIFICADOR DE TRIANGULOS =====
1 - Classificar triangulo
0 - Sair

Lado A: 5
Lado B: 5
Lado C: 5

Valido: sim
Tipo: EQUILATERO

---

## Como Executar

1. Abrir a pasta `geometria`
2. Compilar os arquivos Java
3. Executar a classe `Main`
4. Utilizar o menu no console

---

## Melhorias Futuras

- Calcular perimetro
- Calcular area com formula de Heron
- Criar testes unitarios com JUnit
- Adicionar historico de classificacoes

---

## Autor

Murilo

Projeto desenvolvido como parte do roadmap de aprendizado em Java Backend.
