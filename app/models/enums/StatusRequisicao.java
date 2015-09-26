package models.enums;

/**
 * Created by Rafael on 26/09/2015.
 */
public enum StatusRequisicao {

    FAVORAVEL("Favorável"), DESFAVORAVEL("Desfavorável"), AGUARDANDO("Aguardando");

    StatusRequisicao(String descricao){
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
