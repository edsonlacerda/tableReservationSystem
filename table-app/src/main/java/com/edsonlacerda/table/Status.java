package com.edsonlacerda.table;

public enum Status {
    FREE("Liberado"),
    WAITING("Aguardando"),
    OCCUPIED("Ocupado");
    private final String descricao;
    Status(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
