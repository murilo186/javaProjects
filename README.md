# Exercicios Java

Colecao de projetos Java organizados em pastas independentes.

## Projetos

- `checkout/`: sistema de checkout em console
- `geometria/`: classificador de triangulos em console

## Estrutura

Cada projeto possui sua propria raiz e sua propria pasta `src/main/java`.

## Execucao rapida

### checkout

```powershell
cd checkout
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName })
java -cp out br.com.murilo.checkout.Main
```

### geometria

```powershell
cd geometria
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName })
java -cp out br.com.murilo.geometria.Main
```
