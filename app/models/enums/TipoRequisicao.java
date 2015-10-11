package models.enums;

/**
 * Created by Rafael on 23/09/2015.
 */
public enum TipoRequisicao {

    AQUISICAO("Aquisição"), RENOVACAO("Renovação");

    TipoRequisicao(String descricao){
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
