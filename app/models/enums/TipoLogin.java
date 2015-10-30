package models.enums;

/**
 * Created by Rafael on 18/09/2015.
 */
public enum TipoLogin {

    DFPC("Diretoria de Fiscalização de Produtos Controlados"), POLICIAMILITAR("Polícia Militar"), ADMIN("Administrador");

    TipoLogin(String descricao){
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