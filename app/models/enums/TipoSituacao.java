package models.enums;

/**
 * Created by Rafael on 23/09/2015.
 */
public enum TipoSituacao {

    ATIVO("Ativo"), INATIVO("Inativo"), REFORMADO("Reformado");

    TipoSituacao(String descricao){
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
