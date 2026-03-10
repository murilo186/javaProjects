
# 🔺 Projeto 04 — Classificador de Triângulos (POO)

Projeto desenvolvido como prática de **Programação Orientada a Objetos em Java**, baseado em um exercício clássico de lógica de programação: validar e classificar triângulos a partir dos três lados informados.

O foco principal do projeto é **modelagem de domínio**, separação de responsabilidades e organização de código.

---

# 🎯 Objetivos do Projeto

- Modelar um problema matemático simples usando POO
- Separar **entrada/saída** da **regra de negócio**
- Aplicar **encapsulamento**
- Utilizar **enum para tipos de domínio**
- Criar código organizado em camadas
- Evitar lógica procedural no `Main`

---

# 🧠 Conceitos Aplicados

- Programação Orientada a Objetos
- Encapsulamento
- Responsabilidade única (SRP)
- Separação de camadas
- Enum para modelagem de domínio
- Validação de dados no construtor
- Organização em pacotes

---

# 📦 Estrutura do Projeto

```

br.com.murilo.triangulo
│
├── Main (menu interativo)
│
├── domain
│   ├── ClassificadorTriangulo
│   ├── ResultadoTriangulo
│   └── TipoTriangulo (enum)
│
└── model
└── Triangulo

```

---

# 🔺 Regras Matemáticas

Um triângulo só existe se:

```

ladoA + ladoB > ladoC
ladoA + ladoC > ladoB
ladoB + ladoC > ladoA

```

Caso contrário, os lados **não formam um triângulo válido**.

---

# 📐 Classificação dos Triângulos

| Tipo | Regra |
|-----|------|
| EQUILATERO | todos os lados iguais |
| ISOSCELES | dois lados iguais |
| ESCALENO | todos os lados diferentes |

---

# 🛠️ Funcionalidades

O sistema permite:

- informar três lados
- validar os lados
- verificar se o triângulo existe
- classificar o tipo do triângulo
- exibir o resultado ao usuário
- repetir a operação via menu

---

# 💻 Exemplo de Execução

```

===== CLASSIFICADOR DE TRIÂNGULOS =====

Informe o lado A: 5
Informe o lado B: 5
Informe o lado C: 5

Triângulo válido
Tipo: EQUILATERO

```

Outro exemplo:

```

Informe o lado A: 1
Informe o lado B: 2
Informe o lado C: 10

Triângulo inválido
Os lados informados não formam um triângulo.

```

---

# 🚀 Como Executar

1. Clonar o repositório
2. Abrir no IntelliJ ou VS Code
3. Executar a classe `Main`
4. Utilizar o menu interativo

---

# 🔎 Melhorias Futuras

Possíveis evoluções para este projeto:

- calcular **perímetro do triângulo**
- calcular **área (fórmula de Heron)**
- criar **testes unitários com JUnit**
- transformar o classificador em **API REST**
- adicionar **histórico de classificações**

---

# 👨‍💻 Autor

Murilo

Projeto desenvolvido como parte do roadmap de aprendizado em **Java Backend e POO**.
```

---

