# Projeto 05 — Sistema Bancário (POO)

## Contexto

Este projeto simula um sistema bancário simples utilizando Java e Programação Orientada a Objetos.

O objetivo é treinar modelagem de domínio, gerenciamento de estado e interação entre objetos, simulando operações básicas de uma conta bancária.

O sistema será executado via **console** e permitirá que o usuário realize operações como criar conta, depositar, sacar e transferir dinheiro.

---

# Objetivo do Projeto

Construir uma aplicação Java de console que permita:

* criar contas bancárias
* realizar depósitos
* realizar saques
* transferir valores entre contas
* consultar saldo
* visualizar histórico de transações
* listar contas existentes

O foco do projeto é **modelagem orientada a objetos**, não interface.

---

# Escopo

### O sistema deve permitir

* criação de contas bancárias
* gerenciamento de múltiplas contas
* movimentação financeira
* registro de histórico de transações
* consulta de saldo
* menu interativo no console

### O sistema NÃO precisa ter

* banco de dados
* API REST
* interface gráfica
* autenticação
* persistência em arquivo

---

# Regras de Negócio

## Regra 1 — Saldo inicial

Ao criar uma conta, o saldo inicial deve ser **zero**.

---

## Regra 2 — Depósito

Um depósito deve:

* aceitar apenas valores positivos
* aumentar o saldo da conta
* registrar uma transação no histórico

---

## Regra 3 — Saque

Um saque deve:

* aceitar apenas valores positivos
* verificar se há saldo suficiente
* diminuir o saldo
* registrar uma transação

Caso o saldo seja insuficiente, lançar exceção com mensagem clara.

---

## Regra 4 — Transferência

Uma transferência deve:

* verificar se a conta de origem possui saldo
* debitar da conta origem
* creditar na conta destino
* registrar duas transações:

  * uma de débito
  * uma de crédito

---

## Regra 5 — Histórico

Toda operação financeira deve ser registrada.

Cada registro deve conter:

* tipo da operação
* valor
* data/hora
* descrição opcional

---

# Requisitos Técnicos

### Linguagem

* Java
* aplicação de console
* sem frameworks

---

### Boas práticas obrigatórias

* atributos privados
* validações no domínio
* uso de `List`
* uso de `enum`
* evitar lógica no `Main`
* separação entre domínio e interface

---

# Estrutura sugerida de pacotes

src/main/java/br/com/murilo/bank/

Main.java

domain/

* Banco.java
* Conta.java
* OperacaoConta.java
* TipoOperacao.java

model/

* Transacao.java

---

# Responsabilidade das Classes

## Conta

Representa uma conta bancária.

Atributos:

* numero
* titular
* saldo
* historicoTransacoes

Responsabilidades:

* armazenar saldo
* realizar operações financeiras
* registrar histórico de transações

Métodos principais:

* depositar(valor)
* sacar(valor)
* transferir(contaDestino, valor)
* getSaldo()
* getHistorico()

---

## Banco

Responsável por gerenciar todas as contas.

Atributos:

* lista de contas

Métodos:

* criarConta(titular)
* buscarConta(numero)
* listarContas()

---

## Transacao

Representa um registro de movimentação financeira.

Atributos:

* valor
* tipo
* dataHora
* descricao

Esse objeto representa um **evento financeiro ocorrido na conta**.

---

## TipoOperacao (enum)

Enum que representa o tipo de operação:

* DEPOSITO
* SAQUE
* TRANSFERENCIA_ENVIO
* TRANSFERENCIA_RECEBIMENTO

---

## OperacaoConta

Classe responsável por executar regras financeiras.

Responsabilidades:

* validar valores
* garantir saldo suficiente
* criar registros de transação

Essa classe centraliza a lógica financeira.

---

# Fluxo do Programa

## Menu Principal

1 Criar conta
2 Depositar
3 Sacar
4 Transferir
5 Ver saldo
6 Ver histórico
7 Listar contas
0 Sair

---

## Fluxo Criar Conta

1 pedir nome do titular
2 gerar número da conta
3 criar objeto Conta
4 adicionar no Banco

---

## Fluxo Depósito

1 pedir número da conta
2 pedir valor
3 realizar depósito

---

## Fluxo Saque

1 pedir número da conta
2 pedir valor
3 verificar saldo
4 realizar saque

---

## Fluxo Transferência

1 pedir conta origem
2 pedir conta destino
3 pedir valor
4 realizar transferência

---

## Fluxo Histórico

1 pedir número da conta
2 listar transações registradas

---

# Exemplo de Execução

===== SISTEMA BANCÁRIO =====

1 Criar conta
2 Depositar
3 Sacar
4 Transferir
5 Ver saldo
6 Ver histórico
7 Listar contas
0 Sair

Escolha uma opção: 1

Nome do titular: Murilo

Conta criada com sucesso
Número da conta: 1001

---

# Validações Obrigatórias

* não permitir valores negativos
* não permitir saque maior que saldo
* não permitir transferência para conta inexistente
* não permitir depósito zero

---

# Critérios de Conclusão

O projeto será considerado concluído quando:

* houver classe Conta
* houver classe Banco
* houver classe Transacao
* houver enum TipoOperacao
* houver menu funcional no Main
* depósitos, saques e transferências funcionarem
* histórico de transações for exibido corretamente

---

# Critérios de Qualidade

O código deve:

* compilar sem erros
* ter nomes claros
* ter responsabilidade bem definida
* evitar duplicação de lógica
* ter organização em pacotes

---

# Melhorias Futuras

* persistência em banco de dados
* autenticação de usuário
* limite diário de saque
* conta corrente vs conta poupança
* API REST com Spring Boot
* testes unitários com JUnit

---

# Observação

Este projeto é didático.

O foco é treinar:

* modelagem de domínio
* interação entre objetos
* gerenciamento de estado
* boas práticas de POO
