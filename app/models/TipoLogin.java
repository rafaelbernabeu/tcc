package models;

import javax.persistence.Entity;

/**
 * Created by Rafael on 18/09/2015.
 */
@Entity
public enum TipoLogin {

    EXERCITO("Exército"), POLICIAMILITAR("Polícia Militar");

    TipoLogin(String descricao){
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
