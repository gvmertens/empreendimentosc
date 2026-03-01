package br.com.mertens.emprendimentosc.enums;

import lombok.Getter;

public enum DominioAtivoInativo {

    //@formatter:off
    ATIVO( "Ativo", 1, "A"),
    INATIVO( "Inativo", 0, "I");
    //@formatter:on

    private final String desc;

    private final Integer valorNumerico;

    @Getter
    private final String abreviacao;

    DominioAtivoInativo(String desc, Integer valorNumerico, String abreviacao) {
        this.desc = desc;
        this.valorNumerico = valorNumerico;
        this.abreviacao = abreviacao;
    }


    public Integer getCod() {
        return valorNumerico;
    }


    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name();
    }

    public Integer getValorNumerico() {
        return valorNumerico;
    }

    @Override
    public String toString() {
        return getDesc();
    }

    public Integer convertToDatabaseColumn(DominioAtivoInativo dominio) {
        return dominio.valorNumerico;
    }


}
