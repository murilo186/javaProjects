# Projeto 05 - Sistema Bancario (POO)

Projeto desenvolvido como pratica de Programacao Orientada a Objetos (Java).

Objetivo: modelar um sistema bancario simples aplicando boas praticas de POO, separacao de responsabilidades, uso de `List`, `enum` e controle de estado com historico de transacoes.

---

## Objetivos do Projeto

- Modelar dominio bancario com multiplas contas
- Separar regra de negocio da camada de execucao
- Registrar historico de transacoes
- Aplicar validacoes de saldo e valores
- Utilizar `BigDecimal` para valores monetarios
- Evitar `Main` com regra financeira

---

## Conceitos aplicados

- Pilares da POO
- Encapsulamento
- Responsabilidade unica (SRP)
- Collections (`List`)
- `BigDecimal` para dinheiro
- `enum` para tipos de operacao
- Separacao de camadas (Main vs Dominio)

---

## Estrutura do Projeto

br.com.murilo.bank
|
+-- Main (menu interativo)
|
+-- domain
|  +-- Banco
|  +-- Conta
|  +-- OperacaoConta
|  +-- TipoOperacao (enum)
|
+-- model
   +-- Transacao

---

## Funcionalidades

- Criar contas bancarias
- Depositar valores
- Sacar valores
- Transferir entre contas
- Atualizar titular de conta
- Remover conta com saldo zerado
- Consultar saldo
- Ver historico de transacoes
- Listar contas existentes

---

## Regras principais

- Conta criada com saldo inicial zero
- Deposito aceita apenas valor positivo
- Saque exige saldo suficiente
- Transferencia debita a origem e credita o destino
- Remocao de conta exige saldo zerado
- Toda movimentacao gera transacao no historico

---

## Exemplo de Execucao

===== SISTEMA BANCARIO =====
1 Criar conta
2 Depositar
3 Sacar
4 Transferir
5 Ver saldo
6 Ver historico
7 Listar contas
8 Atualizar titular
9 Remover conta
0 Sair

---

## Como Executar

1. Abrir a pasta `banksystem`
2. Compilar os arquivos Java com `javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName })`
3. Executar a classe principal com `java -cp out br.com.murilo.bank.Main`
4. Utilizar o menu no console

---

## Melhorias Futuras

- Persistencia em banco de dados
- Autenticacao
- Limites por operacao
- Tipos diferentes de conta
- Testes unitarios com JUnit

---

## Autor

Murilo

Projeto desenvolvido como parte do roadmap de aprendizado em Java Backend.
