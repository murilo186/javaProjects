# Projeto 04 - Classificador de Triangulos (POO)

## Contexto

Este projeto e uma evolucao de um exercicio de logica:
- ler tres valores que representam os lados de um triangulo
- verificar se os lados formam um triangulo valido
- determinar se o triangulo e equilatero, isosceles ou escaleno

Neste projeto, isso deve ser implementado usando Programacao Orientada a Objetos em Java, com foco didatico, codigo limpo e separacao de responsabilidades.

---

## Objetivo do projeto

Construir uma aplicacao Java de console que:

1. receba tres lados
2. valide se eles formam um triangulo
3. classifique o triangulo
4. exiba o resultado ao usuario
5. permita repetir o processo via menu

O foco principal nao e matematica avancada, e sim:
- modelagem de dominio
- encapsulamento
- validacao
- enum
- separacao entre entrada/saida e regra de negocio

---

## Escopo

### O sistema deve permitir

- informar tres lados
- validar os lados
- classificar o triangulo
- exibir mensagens amigaveis
- repetir a operacao no menu ate o usuario sair

### O sistema NAO precisa ter

- banco de dados
- API REST
- interface grafica
- persistencia em arquivo
- testes unitarios por enquanto

---

## Regras de negocio

### Regra 1 - lados validos
Todos os lados devem ser maiores que zero.

Se qualquer lado for menor ou igual a zero:
- lancar excecao com mensagem clara

### Regra 2 - condicao de existencia de triangulo
Um triangulo so existe se:

- ladoA + ladoB > ladoC
- ladoA + ladoC > ladoB
- ladoB + ladoC > ladoA

Se essa regra nao for satisfeita:
- o triangulo e invalido
- deve ser exibida mensagem apropriada

### Regra 3 - classificacao
Se o triangulo for valido, classificar como:

- EQUILATERO -> todos os lados iguais
- ISOSCELES -> dois lados iguais
- ESCALENO -> todos os lados diferentes

---

## Estrutura sugerida de pacotes

src/main/java/br/com/murilo/geometria/

- Main.java

domain/
- ClassificadorTriangulo.java
- ResultadoTriangulo.java
- TipoTriangulo.java

model/
- Triangulo.java

---

## Criterios de conclusao

O projeto sera considerado concluido quando:

- houver classe `Triangulo`
- houver enum `TipoTriangulo`
- houver classe `ResultadoTriangulo`
- houver classe `ClassificadorTriangulo`
- houver `Main` com menu funcionando
- o programa classificar corretamente os cenarios principais
- a regra estiver separada do `Main`
