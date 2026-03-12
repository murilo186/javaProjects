$ErrorActionPreference = "Stop"

$projectRoot = "C:\checkout\biblioteca-cli"
$srcDir = Join-Path $projectRoot "src\main\java"
$outDir = Join-Path $projectRoot "out"
$libJar = Join-Path $projectRoot "lib\sqlite-jdbc-3.50.3.0.jar"

if (!(Test-Path $srcDir)) {
    throw "Source directory not found: $srcDir"
}

if (!(Test-Path $libJar)) {
    throw "JDBC jar not found: $libJar"
}

if (!(Test-Path $outDir)) {
    New-Item -ItemType Directory -Force -Path $outDir | Out-Null
}

$javaFiles = Get-ChildItem -Path $srcDir -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
if (!$javaFiles) {
    throw "No Java files found under $srcDir"
}

javac -encoding UTF-8 -cp $libJar -d $outDir $javaFiles
Write-Host "Compilation finished: $outDir"
