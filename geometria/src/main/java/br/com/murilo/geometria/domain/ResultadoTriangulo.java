package br.com.murilo.geometria.domain;

public class ResultadoTriangulo {

    private final boolean valido;
    private final TipoTriangulo tipo;
    private final String mensagem;

    public ResultadoTriangulo(boolean valido, TipoTriangulo tipo, String mensagem) {
        this.valido = valido;
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    public boolean isValido() {
        return valido;
    }

    public TipoTriangulo getTipo() {
        return tipo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
