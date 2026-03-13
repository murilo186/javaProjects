param(
    [string]$MainClass = "com.biblioteca.app.Main"
)

$ErrorActionPreference = "Stop"

$projectRoot = "C:\checkout\biblioteca-cli"
$outDir = Join-Path $projectRoot "out"
$resourcesDir = Join-Path $projectRoot "src\main\resources"
$libJar = Join-Path $projectRoot "lib\sqlite-jdbc-3.50.3.0.jar"

if (!(Test-Path $outDir)) {
    throw "Output directory not found: $outDir. Run compile.ps1 first."
}

if (!(Test-Path $libJar)) {
    throw "JDBC jar not found: $libJar"
}

$classpath = "$outDir;$resourcesDir;$libJar"
java --enable-native-access=ALL-UNNAMED -cp $classpath $MainClass
