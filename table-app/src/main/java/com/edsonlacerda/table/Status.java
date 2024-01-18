package com.edsonlacerda.table;

public enum Status {
    FREE("Liberado"),
    WAITING("Aguardando"),
    CLOSED("Ocupado");
    private final String descricao;
    Status(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
