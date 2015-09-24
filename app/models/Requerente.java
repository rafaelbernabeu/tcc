package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Rafael on 23/09/2015.
 */
@Entity
public class Requerente extends Model {

    public String nome;
    public String sobrenome;
    public String CPF;
    public String cargo;
    public String lotacao;

    @OneToMany
    public List<Arma> armas;

    @OneToMany
    public List<Municao> municoes;

    public TipoSituacao situacao;

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}
