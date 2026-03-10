# 🛒 Projeto 03 – Sistema de Checkout (POO)

Projeto desenvolvido como prática de Programação Orientada a Objetos (Java).

Objetivo: modelar um sistema simples de carrinho e pagamento aplicando boas práticas de POO, separação de responsabilidades e uso correto de Collections e BigDecimal.

---

## 🎯 Objetivos do Projeto

- Modelar domínio de compra (Produto, Item, Carrinho)
- Separar regra de negócio da camada de execução
- Aplicar descontos e juros conforme forma de pagamento
- Utilizar `List` corretamente
- Utilizar `BigDecimal` para cálculos financeiros
- Aplicar encapsulamento e imutabilidade
- Evitar "classe Deus"

---

## 🧠 Conceitos aplicados

- Pilares da POO
- Encapsulamento
- Responsabilidade única (SRP)
- Enum para tipo forte
- Collections (`List`)
- `BigDecimal` para dinheiro
- Separação de camadas (Main vs Domínio)

---

## 📦 Estrutura do Projeto


br.com.murilo.checkout
│
├── Main (menu interativo)
│
├── domain
│ ├── Carrinho
│ ├── Pedido
│ ├── CalculadoraPagamento
│ ├── ResultadoPagamento
│ └── FormaPagamento (enum)
│
└── model
├── Produto
└── ItemCarrinho


---

## 🛍️ Funcionalidades

- Adicionar produto ao carrinho
- Remover produto por nome
- Listar itens do carrinho
- Calcular subtotal
- Escolher forma de pagamento
- Aplicar desconto ou juros
- Imprimir resumo do pedido

---

## 💳 Regras de Pagamento

| Forma de Pagamento        | Regra Aplicada |
|---------------------------|---------------|
| PIX / Dinheiro           | 15% desconto  |
| Crédito à vista          | 10% desconto  |
| Crédito 2x               | Sem alteração |
| Crédito 3x ou mais       | 10% juros     |

---

## 🧾 Exemplo de Execução

===== RESUMO DO PEDIDO =====
Notebook | Qtd: 1 | Total: R$ 3500.00
Mouse Gamer | Qtd: 3 | Total: R$ 450.00
Subtotal: R$ 3950.00
Forma de pagamento: PIX_DINHEIRO
Ajuste: R$ -592.50
Total final: R$ 3357.50

---

## 🚀 Como Executar

1. Clonar o repositório
2. Abrir no IntelliJ
3. Executar a classe `Main`
4. Utilizar o menu interativo

---

## 🔎 Melhorias Futuras (Evolução)

- Transformar em API REST (Spring Boot)
- Persistir pedidos em banco de dados
- Implementar cupons de desconto
- Implementar controle de estoque
- Criar testes unitários
- Aplicar princípios SOLID

---

## 👨‍💻 Autor

Murilo

Projeto desenvolvido como parte do roadmap de aprendizado em Java Backend.