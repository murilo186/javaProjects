param(
    [string]$DbPath = "C:\checkout\biblioteca-cli\data\db\biblioteca.db",
    [string]$SchemaPath = "C:\checkout\biblioteca-cli\docs\database-schema.sql"
)

$ErrorActionPreference = "Stop"

if (!(Test-Path $SchemaPath)) {
    throw "Schema file not found: $SchemaPath"
}

$dbDir = Split-Path -Parent $DbPath
if (!(Test-Path $dbDir)) {
    New-Item -ItemType Directory -Force -Path $dbDir | Out-Null
}

python -c "import sqlite3, pathlib; db=pathlib.Path(r'$DbPath'); schema=pathlib.Path(r'$SchemaPath').read_text(encoding='utf-8'); conn=sqlite3.connect(db); conn.executescript(schema); conn.commit(); conn.close(); print(f'SQLite initialized at: {db}')"
